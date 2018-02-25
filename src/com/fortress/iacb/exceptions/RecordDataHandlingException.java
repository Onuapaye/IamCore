package com.fortress.iacb.exceptions;

import com.fortress.iacb.datamodel.IdentityBismark;

/**
 * <h3>Serialisation</h3>
 * @author Bismark Atta FRIMPONG
 *
 */
public class RecordDataHandlingException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final IdentityBismark ibException;

	public RecordDataHandlingException(Exception exceptionCause, IdentityBismark iBismark) {
		initCause(exceptionCause);
		this.ibException = iBismark;
	}
}
