package com.fortress.iacb.datamodel;

/**
 * 
 * @author Mr Bismark Atta FRIMPONG
 *
 */
public class IBApplicationUser {

	private String userName;
	private String userPassword;
	private Integer userEnableLogin;
	private Integer userGroup;

	public static final String ADM_USER = "Administrator";
	public static final String DEF_USER = "System Default";
	public static final String POW_USER = "Power User";
	public static final String REA_USER = "Reader";
	
	public static final String ENABLE_LOGIN_YES = "Yes";
	public static final String ENABLE_LOGIN_NO = "No";
	
	public static final Integer ENABLE_LOGIN_VAL_YES = 1;
	public static final Integer ENABLE_LOGIN_VAL_NO = 0;
	

	private static final Integer UG_DEFAULT = 0; // for default user also as administrator
	public static Integer getUG_DEFAULT() {
		return UG_DEFAULT;
	}
	
	private final static Integer UG_ADMIN = 1; // administrator level {can create, delete, search, update}
	public static Integer getUG_ADMIN() {
		return UG_ADMIN;
	}

	private final static Integer UG_POWER = 2; // power user level {can create, search, update}
	public static Integer getUG_POWER() {
		return UG_POWER;
	}

	private final static Integer UG_VIEWER = 3; // lower level user {can only view records - search}
	public static Integer getUG_VIEWER() {
		return UG_VIEWER;
	}

	private static String loggedInUsername = "";
	private static Integer loggedInUserGroup = -1;
	
	public static String getLoggedInUsername() {
		return loggedInUsername;
	}

	public static void setLoggedInUsername(String loggedInUsername) {
		IBApplicationUser.loggedInUsername = loggedInUsername;
	}

	public static Integer getLoggedInUserGroup() {
		return loggedInUserGroup;
	}

	public static void setLoggedInUserGroup(Integer loggedInUserGroup) {
		IBApplicationUser.loggedInUserGroup = loggedInUserGroup;
	}

	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * 
	 * @param string
	 */
	public void setUserPassword(String string) {
		this.userPassword = string;
	}

	/**
	 * 
	 * @return
	 */
	public int getUserEnableLogin() {
		return userEnableLogin;
	}

	/**
	 * 
	 * @param userEnableLogin
	 */
	public void setUserEnableLogin(int userEnableLogin) {
		this.userEnableLogin = userEnableLogin;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getUserGroup() {
		return userGroup;
	}

	/**
	 * 
	 * @param userGroup
	 */
	public void setUserGroup(Integer userGroup) {
		switch (userGroup) {
		case 1:
			this.userGroup = IBApplicationUser.UG_ADMIN;
			break;
		case 2:
			this.userGroup = IBApplicationUser.UG_POWER;
			break;
		case 3:
			this.userGroup = IBApplicationUser.UG_VIEWER;
			break;
		default:
			this.userGroup = userGroup;
		}

	}

	/**
	 * 
	 */
	public IBApplicationUser() {
		
	}
	
	/**
	 * 
	 * @param userName
	 * @param userPassword
	 * @param userGroup
	 * @param userEnableLogin
	 */
	public IBApplicationUser(String userName, String userPassword, Integer userGroup, int userEnableLogin) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userGroup = userGroup;
		this.userEnableLogin = userEnableLogin;
	}
	
	
	/**
	  * This method overrides the default toString() method of the IdentityBismark class.
	 * Overriding is used to provide the  implementation toString() method that is 
	 * already provided by the super class
	 */
	@Override
	public String toString() {
		return "The User Details Are - User Name :" + this.userName + 
				" Password : (not shown) " + 
				" User Group : " + (this.userGroup.toString() == getUG_ADMIN().toString()?
				" Administrator " : (this.userGroup.toString() == getUG_POWER().toString())?
				" Power User " : "Viewer") +
				" Enable Login : " + this.userEnableLogin.toString();
	}
	
	
	/**
	 * <h4>Algorithm for Computing hashCode()</h4>
	 *  This method overrides the default class hashCode() method and computes a field’s 
	 *  hash code by calling hashCode() on it. E.g. firstName.hashCode() 
	 *  
	 *  A common algorithm is to start with some arbitrary number (usually a small prime) 
	 *  and to repeatedly multiply it with another before adding a field’s hash value
	 */
	@Override
	public int hashCode() {
		int iacbPrime = 31;
		int iacbResults = 1;
		
		iacbResults = iacbPrime * iacbResults + ((userName == null) ? 0 : userName.hashCode()); 
		iacbResults = iacbPrime * iacbResults + ((userPassword == null) ? 0 : userPassword.hashCode());
		iacbResults = iacbPrime * iacbResults + ((userGroup == null) ? 0 : userGroup.hashCode());
		iacbResults = iacbPrime * iacbResults + ((userEnableLogin == null) ? 0 : userEnableLogin.hashCode());
		
		return iacbResults;
	}
	
	/**
	 *  This method checks if some other Object passed to it as an argument is equal to 
	 *  the Object on which it is invoked.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		
		if(object == null) {
			return false;
		}
		
		if(getClass() != object.getClass()) {
			return false;
		}
		
		final IBApplicationUser ibUser = (IBApplicationUser) object;
		
		//user name
		if(userName == null) {
			if(ibUser.userName != null) {
				return false;
			}
		} else if(!userName.equals(ibUser.userName)) {
			return false;
		}
		
		//password
		if(userPassword == null) {
			if(ibUser.userPassword != null) {
				return false;
			}
		} else if(!userPassword.equals(ibUser.userPassword)) {
			return false;
		}
		
		//enable login
		if(userEnableLogin == null) {
			if(ibUser.userEnableLogin != null) {
				return false;
			}
		} else if(!userEnableLogin.equals(ibUser.userEnableLogin)) {
			return false;
		}
		
		//user group
		if(userGroup == null) {
			if(ibUser.userGroup != null) {
				return false;
			}
		} else if(!userGroup.equals(ibUser.userGroup)) {
			return false;
		}
		
		return true;
	}
	
}
