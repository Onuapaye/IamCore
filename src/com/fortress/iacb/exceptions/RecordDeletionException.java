package com.fortress.iacb.exceptions;

import com.fortress.iacb.datamodel.IdentityBismark;

public class RecordDeletionException extends RecordDataHandlingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RecordDeletionException(Exception exceptionCause, IdentityBismark iBismark) {
		super(exceptionCause, iBismark);
	}
	
	
	@Override
	public String getMessage() {
		return "An error occured while attempting to delete a record for the identity " + ibException;
	}
	
}
