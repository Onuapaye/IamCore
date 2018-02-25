package com.fortress.iacb.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fortress.iacb.datamodel.IdentityBismark;

/**
 * <h3>Class Description</h3>
 * <p>
 * This class serves as the entry point for all methods for handling File CRUD
 * operations in the IdentityBismark application
 * </p>
 * 
 * It has a constructor to help set the path of the File to be worked on. It
 * creates the file if it does not exist.
 * 
 * <h3>Class Usage</h3>
 * 
 * <p>
 * This class can be used by creating an object of it and instantiating it by
 * using the new keyword and passing the path of a File to it. </br>
 * 
 * For example the code snippet below creates an instance of the class and calls
 * on the {@code createIdentityBismarkFileRecord() } method to create a record
 * in the file.
 * </p>
 * 
 * <pre>
 * {@code
 * 
 * private static final String FILEPATH = "c:\\users\\mr kasapa\\testjava\\myfile.txt";
 * 
 * IdentityBismarkFileDAO identityBismarkFileDAO = new IdentityBismarkFileDAO(filePath);
 * 
 * IdentityBismark iBismark = new IdentityBismark;
 * iBismark.setFirstName("Kasapa");
 * iBismark.setLastName("Onuapaye");
 * 
 * identityBismarkFileDAO.createIdentityBismarkFileRecord(iBismark);
 * }
 * </pre>
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class IdentityBismarkFileDAO {

	private final String filePath;

	private final PrintWriter printWriter;
	private final Scanner scanner;
	static StringBuffer stringBuffer = new StringBuffer();

	/**
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public IdentityBismarkFileDAO(String filePath) throws IOException {

		this.filePath = filePath;

		final File file = new File(this.filePath);

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

		final FileOutputStream fileOutputStream = new FileOutputStream(file, true);

		printWriter = new PrintWriter(fileOutputStream);
		scanner = new Scanner(file);

	}

	/**
	 * 
	 * @param iBismark
	 */
	public void createIdentityBismarkFileRecord(IdentityBismark iBismark) {

		printWriter.println("------------------");

		printWriter.println(iBismark.getUniqueId());
		printWriter.println(iBismark.getFirstName());
		printWriter.println(iBismark.getLastName());
		printWriter.println(iBismark.getEmail());
		printWriter.println(iBismark.getDisplayName());

		printWriter.println("------------------");

		printWriter.flush();

	}

	/**
	 * 
	 * @param searchCriteria
	 * @return
	 */
	public List<IdentityBismark> searchIdentityFileRecords(IdentityBismark searchCriteria) {
		final List<IdentityBismark> listOfIdentityBismark = new ArrayList<>();

		while (scanner.hasNext()) {

			final IdentityBismark iBismark = new IdentityBismark();

			scanner.nextLine();

			iBismark.setUniqueId(scanner.nextLine());
			iBismark.setFirstName(scanner.nextLine());
			iBismark.setLastName(scanner.nextLine());
			iBismark.setEmail(scanner.nextLine());

			scanner.nextLine();

			if (criteriaCheck(searchCriteria, iBismark)) {
				listOfIdentityBismark.add(iBismark);

			}

		}

		return listOfIdentityBismark;

	}

	/*
	 * This method verifies if indeed the record of the identity was saved in the
	 * file
	 */
	private boolean criteriaCheck(IdentityBismark searchCriteria, IdentityBismark iBismark) {

		return iBismark.getFirstName().contains(searchCriteria.getFirstName())
				|| iBismark.getLastName().contains(searchCriteria.getLastName())
				|| iBismark.getEmail().contains(searchCriteria.getEmail())
				|| iBismark.getUniqueId().contains(searchCriteria.getUniqueId());
	}

	/**
	 * 
	 * @param iBismark
	 */
	public void updateIdentityBismarkFile(IdentityBismark iBismark, String fileNameAndPath) {

		BufferedWriter bufwriter = null;
		
		boolean fileRead = readFileContents(fileNameAndPath);
		
		if (fileRead) {			
			try {
				
				//search and replace contents to be updated
				setReplacementContents(iBismark);
				
				//update
				bufwriter = new BufferedWriter(new FileWriter(fileNameAndPath));

				// writes the edited string buffer to the new file
				bufwriter.write(stringBuffer.toString());
				

			} catch (Exception e) {
				
				// if an exception occurs
				System.out.println("Error occured while attempting to write to file: " + e.getMessage());
			}finally {
				
				// close the file
				try {
					bufwriter.close();
				} catch (IOException e) {
					//log error into the log file
				}
			}
		}
		
		
		
	}

	/**
	 * 
	 */
	private void setReplacementContents(IdentityBismark iBismark) {
		// prompt for a line in file to edit/update
		System.out.println("Please enter the content(s) of a row/line you would like to edit: ");

		// read the row/line to edit or update
		String rowToEdit = scanner.nextLine();

		// prompt for a row/line in file to update/edit
		System.out.println("Please enter the replacement text: ");

		// read the row/line to replace
		String replacementText = scanner.nextLine();

		// now we get the starting point of the text we want to edit/update
		int startIndex = stringBuffer.indexOf(rowToEdit);

		// now we add the staring index of the text with text length to get the end index
		int endIndex = startIndex + rowToEdit.length();

		// this is where the actual replacement of the text happens
		stringBuffer.replace(startIndex, endIndex, replacementText);

		// used to debug and check the string was replaced
		System.out.println("You have sucessfully updated or edited the record text:\n" + stringBuffer);
	}

	/**
	 * 
	 * @param iBismark
	 */
	public void deleteIdentityBismarkFile(IdentityBismark iBismark) {
		// TODO
	}

	/*
	 * This method will read the set file name and returns true if
	 */
	private static boolean readFileContents(String filePath) {

		/*
		 * if(filePath == null) { // prompt for file name and path
		 * System.out.println("Please enter your file name and path i.e C:\\test.txt: "
		 * ); }
		 */

		// filename = sc.nextLine();// read in the file name
		Scanner fileToRead = null;

		try {

			// point the scanner method to the file,
			// check if there is a next line and it is not null and then read it in
			fileToRead = new Scanner(new File(filePath));

			for (String recordRow; fileToRead.hasNextLine() && (recordRow = fileToRead.nextLine()) != null;) {

				// print or show each line or record as it reads the file contents
				System.out.println(recordRow);

				// append all text read-in from the file to a string buffer which
				// will be used to edit the contents of the file
				stringBuffer.append(recordRow).append("\r\n");
			}

			return true;

		} catch (FileNotFoundException ex) {

			// if the file cannot be found an exception will be thrown
			System.out.println("The file " + filePath + " could not be found! " + ex.getMessage());

			return false;

		} finally {

			// if an error occurs now we close the file to exit gracefully
			if (fileToRead != null) {
				fileToRead.close();
			}
		}
	}
	
}
