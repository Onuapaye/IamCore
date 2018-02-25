package com.fortress.iacb.services;

import java.util.List;

import com.fortress.iacb.datamodel.IBApplicationUser;
import com.fortress.iacb.datamodel.IdentityBismark;

public interface IndentityUserInterface {

	public static final Integer number = 0;

	public int createIdentityBismarkRecord(IBApplicationUser ibaUser) throws Exception;

	public int updateIdentityBismarkRecord(IBApplicationUser ibaUser) throws Exception;

	public int deleteIdentityBismarkRecord(IBApplicationUser ibaUser) throws Exception;

	public List<IdentityBismark> searchIdentityBismarkRecords(IBApplicationUser searchCriteria) throws Exception;
}
