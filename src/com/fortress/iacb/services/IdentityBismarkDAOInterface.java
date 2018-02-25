package com.fortress.iacb.services;

import java.util.List;

import com.fortress.iacb.datamodel.IdentityBismark;

/**
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public interface IdentityBismarkDAOInterface {

	public static final Integer number = 0;

	public int createIdentityBismarkRecord(IdentityBismark iBismark) throws Exception;

	public int updateIdentityBismarkRecord(IdentityBismark iBismark) throws Exception;

	public int deleteIdentityBismarkRecord(IdentityBismark iBismark) throws Exception;

	public List<IdentityBismark> searchIdentityBismarkRecords(IdentityBismark searchCriteria) throws Exception;

}
