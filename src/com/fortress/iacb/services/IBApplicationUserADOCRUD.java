package com.fortress.iacb.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fortress.iacb.datamodel.IBApplicationUser;
import com.fortress.iacb.exceptions.RecordCreationException;
import com.fortress.iacb.exceptions.RecordDeletionException;
import com.fortress.iacb.exceptions.RecordSearchException;
import com.fortress.iacb.exceptions.RecordUpdateException;

/**
 * 
 * @author Mr Bismark Atta FRIMPONG
 *
 */
public class IBApplicationUserADOCRUD {

	private static final IdentityBismarkLogger IBLOGGER = new IdentityBismarkLogger(IBApplicationUserADOCRUD.class);

	private IdentityBismarkCloseSQLResources closeSQLResource = new IdentityBismarkCloseSQLResources();

	/**
	 * <h4>The createIdentityBismarkRecord() Method</h4>
	 * <p>
	 * </p>
	 * 
	 * @param iBUser
	 * @return
	 * @throws SQLException
	 */
	public int createIdentityBismarkUserRecord(IBApplicationUser iBUser) throws RecordCreationException {

		IBLOGGER.info("Creating an application User record for " + iBUser);

		// creation of a flag to check the creation success or failure
		int recordCount = -1;

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "INSERT INTO tblUser(userName, userPassword, userGroup, userEnableLogin) VALUES (?, ?, ?, ?)";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, iBUser.getUserName());
			prepStatement.setString(2, iBUser.getUserPassword());
			prepStatement.setInt(3, iBUser.getUserGroup());
			prepStatement.setInt(4, iBUser.getUserEnableLogin());

			recordCount = prepStatement.executeUpdate();

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to create a record for the identity " + iBUser);
		} finally {

			closeSQLResource.closeSQLResources(conn, prepStatement);
		}
		return recordCount;
	}

	/**
	 * 
	 * @param iBUser
	 * @return
	 * @throws RecordUpdateException
	 */
	public int updateIdentityBismarkUserRecord(IBApplicationUser iBUser) throws RecordUpdateException {

		// logging of error with level of info
		IBLOGGER.info("Updating an application User record for " + iBUser);

		// creation of a flag to check the creation success or failure
		int updateCount = -1;

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "UPDATE tblUser SET tblUser.userPassword=?, tblUser.userGroup=?, tblUser.userEnableLogin=?"
					+ " WHERE tblUser.userName=?";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, iBUser.getUserPassword());
			prepStatement.setInt(2, iBUser.getUserGroup());
			prepStatement.setInt(3, iBUser.getUserEnableLogin());
			prepStatement.setString(4, iBUser.getUserName());

			updateCount = prepStatement.executeUpdate();

		} catch (Exception e) {

			// log the error that may or might occur during the running of the update
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to update a record for the identity " + iBUser);

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}
		return updateCount;
	}

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws RecordDeletionException
	 */
	public int deleteIdentityBismarkUserRecord(String userName) throws RecordDeletionException {

		// logging of error with level of info
		IBLOGGER.info("Deleting an application User record for " + userName.toUpperCase());

		// creation of a flag to check the creation success or failure
		int recordCount = -1;

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "DELETE FROM tblUser WHERE userName=?";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, userName);

			recordCount = prepStatement.executeUpdate();

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to delete a record of the User " + userName.toUpperCase());

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}
		return recordCount;
	}

	/**
	 * 
	 * @param searchCriteria
	 * @return
	 * @throws RecordSearchException
	 */
	public List<IBApplicationUser> searchIdentityBismarkUserRecords(IBApplicationUser searchCriteria)
			throws RecordSearchException {
		

		// logging of error with level of info
		IBLOGGER.info("Searching for a record of the user " + searchCriteria.getUserName());

		final List<IBApplicationUser> listOfIBApplicationUser = new ArrayList<>();

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		IBApplicationUser iBUser = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "SELECT userName, userGroup, userEnableLogin FROM tblUser "
					+ "WHERE (? IS NULL OR userName LIKE ?) " + "OR (? IS NULL OR userGroup = ?) "
					+ "OR (? IS NULL OR userEnableLogin = ?)";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, searchCriteria.getUserName());
			prepStatement.setString(2, searchCriteria.getUserName() + "%");
			prepStatement.setInt(3, searchCriteria.getUserGroup());
			prepStatement.setInt(4, searchCriteria.getUserGroup());
			prepStatement.setInt(5, searchCriteria.getUserEnableLogin());
			prepStatement.setInt(6, searchCriteria.getUserEnableLogin());

			final ResultSet resultSet = prepStatement.executeQuery();
			iBUser = new IBApplicationUser();
			
			while (resultSet.next()) {
				iBUser.setUserName(resultSet.getString("userName"));
				iBUser.setUserPassword("not shown");
				iBUser.setUserGroup(resultSet.getInt("userGroup"));
				iBUser.setUserEnableLogin(resultSet.getInt("userEnableLogin"));

				listOfIBApplicationUser.add(iBUser);
			}
		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while searching an application User record for " + searchCriteria.getUserName());
		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}
		return listOfIBApplicationUser;
	}

	/**
	 * This method will retrieve all the User records in the database (User table) and returns them 
	 * as a List of IBApplicationUser object type.
	 * @return
	 * Returns a List of type IBApplicationUser object.
	 * <pre>
	 * {@code
	 * List<IBApplicationUser> ibaUser = new ArrayList<>()
	 * .....;
	 * .....;
	 * return ibaUser;
	 * }
	 * </pre>
	 * @throws RecordSearchException
	 * It implement the RecordSearchExpception class
	 */
	public List<IBApplicationUser> searchIdentityBismarkUserRecords()
			throws RecordSearchException {

		// logging of error with level of info
		IBLOGGER.info("Initializing the retrieval of all User record from the Usertable");

		final List<IBApplicationUser> listOfIBApplicationUser = new ArrayList<>();

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		IBApplicationUser iBUser = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "SELECT userName, userGroup, userPassword, userEnableLogin FROM tblUser";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			final ResultSet resultSet = prepStatement.executeQuery();

			while (resultSet.next()) {
				iBUser = new IBApplicationUser();

				iBUser.setUserName(resultSet.getString("userName"));
				iBUser.setUserPassword("not shown");
				iBUser.setUserGroup(resultSet.getInt("userGroup"));
				iBUser.setUserEnableLogin(resultSet.getInt("userEnableLogin"));

				listOfIBApplicationUser.add(iBUser);
			}
		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while searching an application User record for " + iBUser);
		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return listOfIBApplicationUser;
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 * @throws RecordSearchException
	 */
	public int searchIdentityBismarkUserNamePassword(IBApplicationUser ibaUser) throws RecordSearchException {

		// logging of error with level of info
		IBLOGGER.info(
				"Searching the record for the User: " + ibaUser.getUserName().toUpperCase() + " for authentication");

		int rowsCount = 0;

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "SELECT userName, userPassword, userGroup, userEnableLogin FROM tblUser "
					+ "WHERE (userName=?) AND (userPassword=?)";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);
			prepStatement.setString(1, ibaUser.getUserName());
			prepStatement.setString(2, ibaUser.getUserPassword());

			final ResultSet resultSet = prepStatement.executeQuery();
			while (resultSet.next()) {
				rowsCount = rowsCount + 1;
			}

		} catch (Exception e) {
			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase() + " occured while searching User record for "
					+ ibaUser.getUserName().toUpperCase() + " for authentication.");
		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return rowsCount;
	}

	/**
	 * This method returns the number of rows found matching the Username which has
	 * been provided as the parameter which is used in the SQL query and returns an
	 * integer indicating the number of records found.
	 * 
	 * @param fieldName
	 * @param whereField
	 * @param searchValue
	 * @return
	 */
	public int getSpecificRecordCount(String fieldName, String whereField, String searchValue) {

		// logging of error with level of info
		IBLOGGER.info("Searching for a record of the application user " + searchValue);

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		int rowCount = 0;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements

			String sqlStatement = "SELECT " + fieldName + " FROM tblUser WHERE " + whereField + "=?";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			/*
			 * create a PreparedStatement object variable type in order to help execute the
			 * SQL statements written PreparedStatement is preferred over Statement because
			 * it helps prevent SQL injection and other attacks
			 */
			prepStatement = conn.prepareStatement(sqlStatement);
			prepStatement.setString(1, searchValue);

			final ResultSet resultSet = prepStatement.executeQuery();

			// loop through the results set and assign the values to the class properties or
			// fields
			while (resultSet.next()) {
				rowCount = rowCount + 1;
			}

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to search a record for the user " + searchValue);

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return rowCount;
	}

	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public List<IBApplicationUser> getUserRecordsAsList(String userName) {

		// logging of error with level of info
		IBLOGGER.info("Searching for a record of the application user " + userName.toUpperCase() + " and return the results as a list");

		List<IBApplicationUser> ibaUserList = new ArrayList<>();
		
		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements

			String sqlStatement = "SELECT userName, userGroup, userEnableLogin FROM tblUser WHERE userName=?";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			/*
			 * create a PreparedStatement object variable type in order to help execute the
			 * SQL statements written PreparedStatement is preferred over Statement because
			 * it helps prevent SQL injection and other attacks
			 */
			prepStatement = conn.prepareStatement(sqlStatement);
			prepStatement.setString(1, userName);

			final ResultSet resultSet = prepStatement.executeQuery();

			IBApplicationUser iUser = new IBApplicationUser();
			// loop through the results set and assign the values to the class properties or
			// fields
			while (resultSet.next()) {

				iUser.setUserName(resultSet.getString("userName"));
				iUser.setUserGroup(resultSet.getInt("userGroup"));
				iUser.setUserEnableLogin(resultSet.getInt("userEnableLogin"));
				
				ibaUserList.add(iUser);
			}

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to search a record for the user " + userName.toUpperCase() + " and return it as list.");

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return ibaUserList;
	}
	
	
	/**
	 * This method returns all the number of records found in the User table and
	 * returns the total number of records found as integer based on the parameter
	 * asterics (*)
	 * 
	 * <h3>Code Snippet</h3>
	 * 
	 * <pre>
	 * {@code
	 * 		ibaUserCRUD = new IBApplicationUserADOCRUD();
	 *		int rCount = ibaUserCRUD.getSpecificRecordCount("*");
	 * }
	 * </pre>
	 * 
	 * @param selectAsterics
	 * @return
	 */
	public int getSpecificRecordCount(String selectAsterics) {

		// logging of error with level of info
		IBLOGGER.info("Searching for a specific record in the User table");

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		int rowCount = 0;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			String sqlStatement = "SELECT " + selectAsterics + " FROM tblUser";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			/*
			 * create a PreparedStatement object variable type in order to help execute the
			 * SQL statements written PreparedStatement is preferred over Statement because
			 * it helps prevent SQL injection and other attacks
			 */
			prepStatement = conn.prepareStatement(sqlStatement);
			// prepStatement.setString(1, selectAsterics);

			final ResultSet resultSet = prepStatement.executeQuery();

			// loop through the results set and assign the values to the class properties or
			// fields
			while (resultSet.next()) {
				rowCount = rowCount + 1;
			}

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while searching all records of the User table.");

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return rowCount;
	}

	/**
	 * This method selects from the database the presented Username as the parameter
	 * and checks if the User account has been enabled to login into the
	 * application. It returns true if enable else it returns false.
	 * 
	 * @param userName
	 * @return
	 */
	public int getEnableLoginState(String userName) {

		// logging of error with level of info
		IBLOGGER.info("Searching for a record of the application user " + userName + " to check enable login status ");

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;
		IBApplicationUser ibaUser = null;

		int rowCount = 0;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "SELECT userName, userEnableLogin FROM tblUser WHERE userName=? AND userEnableLogin=?";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			// instantiate the new class object
			// and set a value to the required field variable
			ibaUser = new IBApplicationUser();
			ibaUser.setUserName(userName);
			ibaUser.setUserEnableLogin(1);

			prepStatement.setString(1, ibaUser.getUserName());
			prepStatement.setInt(2, ibaUser.getUserEnableLogin());
			
			final ResultSet resultSet = prepStatement.executeQuery();

			// loop through the results set and assign the values to the class properties or
			// fields
			while (resultSet.next()) {
				rowCount = rowCount + 1;
			}

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to search a record for the user " + ibaUser);

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return rowCount;
	}

	/**
	 * This method will create a default application user record at initial run time
	 * when it checks that no user record exist in the application for the first
	 * time
	 */
	public void createDefaultApplicationUser() {
		IBLOGGER.info("Creating a default user record if none exist " + "Default User");

		// creation of a flag to check the creation success or failure
		// int recordCount = -1;

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		final String USERNAME = "admin";
		final String USERPASSWORD = "@123456u";
		final Integer USERGROUP = IBApplicationUser.getUG_DEFAULT();
		final Integer USERENABLELOGIN = IBApplicationUser.ENABLE_LOGIN_VAL_YES;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "INSERT INTO tblUser(userName, userPassword, userGroup, userEnableLogin) VALUES (?, ?, ?, ?)";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, USERNAME);
			prepStatement.setString(2, USERPASSWORD);
			prepStatement.setInt(3, USERGROUP);
			prepStatement.setInt(4, USERENABLELOGIN);

			prepStatement.executeUpdate();

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to create a default user record for " + USERNAME);
		} finally {

			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		// return recordCount;
	}
}
