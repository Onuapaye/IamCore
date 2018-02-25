package com.fortress.iacb.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class IdentityBismarkLogger {
	
	private static final String LOG_FILEPATH = "C:\\Users\\Mr Kasapa\\Dropbox\\EPITA Lectures\\Java\\ClassProjects\\IAmCoreBismark\\iacb4log.txt";
	private static  PrintWriter printWriter;
	
	private static final String ERROR = "ERROR";
	private static final String INFO = "INFO";
	
	/*
	 * This is a static initialisation block. It can be considered as a static version of a constructor. 
	 * While Constructors are run when the class is instantiated, static initialisation blocks 
	 * get run when the class gets loaded by class loader only once.
	 */
	static {
		try {
			printWriter = new PrintWriter(new FileOutputStream(new File(LOG_FILEPATH), true));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final Class<?> iBisClass;
	
	public IdentityBismarkLogger(Class<?> iBisClass) {
		this.iBisClass = iBisClass;
	}
	
	public void info(String errMessage) {
		printMessage(errMessage, INFO);
	}

	public void error(String errMessage) {
		printMessage(errMessage, ERROR);
	}
	
	private void printMessage(String errMessage, String logLevel) {
		
		final String completeMessage = getTimeStamp() + " - " + logLevel + " - " + iBisClass.getCanonicalName() + " " + errMessage;
		
		printWriter.println(completeMessage);
		printWriter.flush();
	}

	private String getTimeStamp() {

		final Date date = new Date();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
		return simpleDateFormat.format(date);
	}
	
	public void error(String errMessage, Exception e) {
		printMessage(errMessage, ERROR);
		e.printStackTrace(printWriter);
		printWriter.flush();
	}
}
