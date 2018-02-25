package com.fortress.iacb.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fortress.iacb.datamodel.IdentityBismark;

/**
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class IdentityBismarkBusinessLogic {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private IdentityBismarkDAOCRUD iBismarkCRUD = null;

	/**
	 * 
	 * @param iBismark
	 * @return
	 * This method returns True if a field is empty or not set with a value
	 * else will return False
	 */
	public boolean IsFieldEmpty(IdentityBismark iBismark) {

		if (iBismark.getFirstName().isEmpty()) {
			return true;
		} else if (iBismark.getLastName().isEmpty()) {
			return true;
		} else if (iBismark.getUniqueId().isEmpty()) {
			return true;
		} else if (iBismark.getEmail().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	private final int LENGTH_45 = 45;
	private final int LENGTH_10 = 10;

	/**
	 * This method basically validates and check the length of characters entered
	 * into a text field and returns an integer as an error code
	 * 
	 * @param iBismark
	 * It accept the IdentityBismark type as its parameter by getting the unique id value
	 * <pre>
	 * {@code 
	 * 	String firstName = iBismark.getFirst();
	 *  if(firstName.length > LENGTH_45){
	 *  .....;
	 *  .....;
	 * }
	 * </pre>
	 * @return
	 * Returns True if all field lengths are not more than their expected values else will
	 * return False
	 */
	public boolean IsFieldLengthValid(IdentityBismark iBismark) {

		if (iBismark.getFirstName().length() > LENGTH_45) {
			return false;
		} else if (iBismark.getLastName().length() > LENGTH_45) {
			return false;
		} else if (iBismark.getUniqueId().length() > LENGTH_10) {
			return false;
		} else if (iBismark.getEmail().length() > LENGTH_45) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * The method retrieves a list of all unique id's from the database and compares
	 * them with a specific unique id which is to be saved into the database
	 * @param iBismark
	 * It accept the IdentityBismark type as its parameter by getting the unique id value
	 * <pre>
	 * {@code 
	 * 	String uid = iBismark.getUniueID();
	 * }
	 * </pre>
	 * @return
	 * This method returns true when a unique id is already in the database
	 */
	public boolean IsUniqueIDAlreadyInDatabase(IdentityBismark iBismark) {

		iBismarkCRUD = new IdentityBismarkDAOCRUD();

		int rCount = iBismarkCRUD.getRowCountByUID(iBismark.getUniqueId());

		if (rCount > 0) {
			return true;// record found
		} else {
			return false;// record not found
		}
	}

	/**
	 * This method validates any email address that is entered into the application
	 * fields for entry into the database. It returns true or false based on its
	 * matches
	 * 
	 * @param iBismark
	 * @return
	 */
	public boolean IsEmailAddressValid(IdentityBismark iBismark) {

		// compile the regex pattern
		pattern = Pattern.compile(EMAIL_PATTERN);

		matcher = pattern.matcher(iBismark.getEmail());

		return matcher.matches();
	}

}
