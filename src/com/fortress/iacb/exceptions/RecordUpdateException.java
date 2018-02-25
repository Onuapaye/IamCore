package com.fortress.iacb.exceptions;

import com.fortress.iacb.datamodel.IdentityBismark;

public class RecordUpdateException extends RecordDataHandlingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RecordUpdateException(Exception exceptionCause, IdentityBismark iBismark) {
		super(exceptionCause, iBismark);
	}
	
	
	@Override
	public String getMessage() {
		return "An error occured while attempting to update a record for the identity " + ibException;
	}
}
