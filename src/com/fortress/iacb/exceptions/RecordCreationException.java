package com.fortress.iacb.exceptions;

import com.fortress.iacb.datamodel.IdentityBismark;

public class RecordCreationException extends RecordDataHandlingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param exceptionCause
	 * @param iBismark
	 */
	public RecordCreationException(Exception exceptionCause, IdentityBismark iBismark) {
		super(exceptionCause, iBismark);
	}
	
	
	@Override
	public String getMessage() {
		return "An error occured while attempting to create a record for the identity " + ibException;
	}
}
