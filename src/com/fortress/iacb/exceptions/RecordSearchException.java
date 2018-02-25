package com.fortress.iacb.exceptions;

import com.fortress.iacb.datamodel.IdentityBismark;

public class RecordSearchException extends RecordDataHandlingException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RecordSearchException(Exception exceptionCause, IdentityBismark iBismark) {
		super(exceptionCause, iBismark);
	}
	
	
	@Override
	public String getMessage() {
		return "An error occured while searching for record for the identity " + ibException;
	}
}
