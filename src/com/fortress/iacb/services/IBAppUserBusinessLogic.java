package com.fortress.iacb.services;

import java.util.List;

import javax.swing.JRadioButton;

import com.fortress.iacb.datamodel.IBApplicationUser;
import com.fortress.iacb.exceptions.RecordSearchException;

/**
 * 
 * @author Mr Kasapa
 *
 */
public class IBAppUserBusinessLogic {

	private static final IdentityBismarkLogger IBLOGGER = new IdentityBismarkLogger(IBApplicationUserADOCRUD.class);

	private IBApplicationUserADOCRUD ibaUserCRUD = null;

	/**
	 * This method calls a main method from a class with the parameter of the
	 * IBApplicationUser class and returns true or false based on its search results
	 * 
	 * @param ibaUser
	 * @return
	 */
	public boolean IsUniqueIDExisting(IBApplicationUser ibaUser) {

		ibaUserCRUD = new IBApplicationUserADOCRUD();
		int rCount = ibaUserCRUD.getSpecificRecordCount("userName", "userName", ibaUser.getUserName());

		if (rCount > 0) {
			return true;// record found
		} else {
			return false;// record not found
		}
	}

	private final int uFIELD_ERROR_15 = 15;

	/**
	 * This method basically validates and check the length of characters entered
	 * into a text field and returns an integer as an error code
	 * 
	 * @param iBismark
	 * @return
	 */
	public boolean IsUserFieldLengthValid(IBApplicationUser ibUser) {
		final int lenUName =ibUser.getUserName().length();
		final int lenPWord =ibUser.getUserPassword().length();
		
		if ( lenUName > uFIELD_ERROR_15) {
			return true;
		} else if ( lenPWord > uFIELD_ERROR_15) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param ibaUser
	 * @return
	 */
	public boolean IsAnyRequiredFieldEmpty(IBApplicationUser ibaUser) {
		if (ibaUser.getUserName().isEmpty()) {
			return true;
		} else if (ibaUser.getUserPassword().isEmpty()) {
			return true;
		} else if (ibaUser.getUserGroup() < 0) {
			return true;
		} else if (ibaUser.getUserPassword().isEmpty()) {
			return true;
		} else if (ibaUser.getUserEnableLogin() < 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks if user records are existing in the application and
	 * returns true if a record is found else it returns false
	 * 
	 * @return
	 */
	public boolean IsApplicationHavingUserRecords() {

		ibaUserCRUD = new IBApplicationUserADOCRUD();
		int rCount = ibaUserCRUD.getSpecificRecordCount("*");

		if (rCount > 0) {
			return true;// records found
		} else {
			return false;// record not found
		}
	}

	/**
	 * This method accepts the IBApplicationUser class as type parameter and then
	 * calls a method which searches the database for a record matching the Username
	 * and Password and returns a ResultSet. If the record set is null then the
	 * Username or Password provided is wrong but if does has records then a match
	 * is found.
	 *
	 * @param ibaUser
	 * @return
	 */
	public boolean IsUserNamePasswordValid(IBApplicationUser ibaUser) {

		ibaUserCRUD = new IBApplicationUserADOCRUD();
		boolean state = false;
		try {

			final int rs = ibaUserCRUD.searchIdentityBismarkUserNamePassword(ibaUser);

			if (rs <= 0) {
				IBLOGGER.info("No record(s) found matching the Username: " + ibaUser.getUserName() + " and Password: "
						+ ibaUser.getUserPassword());
				// null - no record(s) found
				state = false;
			} else {
				IBLOGGER.info("Access authentication sucessful for the Username: " + ibaUser.getUserName()
						+ " with Password: " + ibaUser.getUserPassword());
				state = true;
			}

		} catch (RecordSearchException e) {
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to check for Username/Password validity for " + ibaUser.getUserName());
		}
		return state;
	}

	/**
	 * 
	 * @param password
	 * @param rePassword
	 * @return
	 */
	public boolean IsPasswordMatching(String password, String rePassword) {
		if (!password.equals(rePassword)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * <h3>Method Description</h3> This method checks to see if the user is allowed
	 * to login. That is EnableLogin is set to true or false. It returns a boolean
	 * type value of true if it is enabled else false. It takes the IBApplication
	 * class object as its parameter
	 * 
	 * @param ibUser
	 * @return
	 */
	public boolean IsUserLoginAllowed(String userName) {
		ibaUserCRUD = new IBApplicationUserADOCRUD();
		final int getRowCount = ibaUserCRUD.getEnableLoginState(userName);
		if (getRowCount <= 0) {
			IBLOGGER.info("No Username found matching " + userName.toUpperCase() + "during login to the application.");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * <h3>Method Description</h3>
	 * This method checks and returns Integer value if the present logged-in user has the access level
	 * or right to perform a specific operation in the application.
	 * @param ibUser
	 * It accepts the IBApplicationUser class object as its parameter
	 * @return
	 * It returns an integer value with the following interpretations;
	 * <ul>
	 * <li>0 if user group is System Default (which can perform CRUD operations on both Identities AND Users)</li>
	 * <li>1 if user group is Administrator (which can perform CRUD operations on both Identities AND Users)</li>
	 * <li>2 if user group is Power (which can perform only CRUD operation on Identities and NOT Users</li>
	 * <li>3 if user group is Viewer (which can perform Read only operations on Identities NOT Users</li>
	 * </ul>
	 */
	public int IsUserAllowToPerformThisOperation(String userName) {
		
		IBApplicationUserADOCRUD ibUserCRUD = new IBApplicationUserADOCRUD();
		IBApplicationUser ibUser = new IBApplicationUser();
		
		List<IBApplicationUser> iUserList = ibUserCRUD.getUserRecordsAsList(ibUser.getUserName());

		int size = iUserList.size();
		for (int i = 0; i < size; i++) {
			IBApplicationUser.setLoggedInUsername(iUserList.get(i).getUserName());
			IBApplicationUser.setLoggedInUserGroup(iUserList.get(i).getUserGroup());
		}
		
		if (ibUser.getUserGroup() == IBApplicationUser.getUG_DEFAULT()) {
			return 0;
		} else if (ibUser.getUserGroup() == IBApplicationUser.getUG_ADMIN()) {
			return 1;
		}else if (ibUser.getUserGroup() == IBApplicationUser.getUG_POWER()) {
			return 2;
		} else {
			return 3;
		}
	}
	
	/**
	 * <h3>Method Description</h3>
	 * This method retrieves the User details from the Database and sets them to the application MDI
	 * which are shown in the Status bar. This will help to know the logged-in user profile name and user group as well.
	 * @param userName
	 * It takes a String of the Username as its parameter.
	 */
	public static void getLoggedInUserInformation(String userName) {
		IBApplicationUserADOCRUD ibUserCRUD = new IBApplicationUserADOCRUD();
	
		List<IBApplicationUser> iUserList = ibUserCRUD.getUserRecordsAsList(userName);

		int size = iUserList.size();
		for (int i = 0; i < size; i++) {
			IBApplicationUser.setLoggedInUsername(iUserList.get(i).getUserName());
			IBApplicationUser.setLoggedInUserGroup(iUserList.get(i).getUserGroup());
		}
	}
	

	/**
	 * 
	 * <h3>Method Description</h3>
	 * The method will check to for which CRUD operation the User has selected to use in the application.
	 * It accepts four (4) items as its parameters of the object type JRadioButton
	 * @param createRadioButton
	 * The radio button for the Create operation
	 * @param readRadioButton
	 * The radio button for the Read operation
	 * @param updateRadioButton
	 * The radio button for the Update operation
	 * @param deleteRadioButton
	 * The radio button for the Delete operation
	 * @return
	 * Returns true if there is a selected radio button else return false
	 */
	public boolean getCRUDOperationToPerform(JRadioButton createRadioButton, JRadioButton readRadioButton,
			JRadioButton updateRadioButton, JRadioButton deleteRadioButton) {

		if (!createRadioButton.isSelected() && !readRadioButton.isSelected() && !updateRadioButton.isSelected() && !deleteRadioButton.isSelected()) {
			UtilityClass.showMessageBox("Select a CRUD Operation to perform", "Unspecified Operation", 1);
			return false;
		}else {
			return true;
		}
	}
}
