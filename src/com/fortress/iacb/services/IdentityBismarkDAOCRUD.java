package com.fortress.iacb.services;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fortress.iacb.datamodel.IdentityBismark;
import com.fortress.iacb.exceptions.RecordSearchException;

/**
 *
 * <h3>Class Description</h3>
 * 
 * <h4>Class Usage</h4>
 * 
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class IdentityBismarkDAOCRUD implements IdentityBismarkDAOInterface {

	private static final IdentityBismarkLogger IBLOGGER = new IdentityBismarkLogger(IdentityBismarkDAOCRUD.class);

	private IdentityBismarkCloseSQLResources closeSQLResource = new IdentityBismarkCloseSQLResources();

	@Override
	public int createIdentityBismarkRecord(IdentityBismark iBismark) throws Exception {
		IBLOGGER.info("Creating a record for an identity " + iBismark.getDisplayName().toUpperCase());

		// creation of a flag to check the creation success or failure
		int recordCount = -1;

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "INSERT INTO tblIdentityBismark(uniqueId, firstName, lastName, email) VALUES (?, ?, ?, ?)";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, iBismark.getUniqueId().toUpperCase());
			prepStatement.setString(2, iBismark.getFirstName());
			prepStatement.setString(3, iBismark.getLastName());
			prepStatement.setString(4, iBismark.getEmail());

			recordCount = prepStatement.executeUpdate();

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to create a record for the identity " + iBismark);
		} finally {

			closeSQLResource.closeSQLResources(conn, prepStatement);
		}
		return recordCount;
	}

	@Override
	public int updateIdentityBismarkRecord(IdentityBismark iBismark) throws Exception {
		// logging of error with level of info
		IBLOGGER.info("Updating a record for an identity " + iBismark.getDisplayName().toUpperCase());

		// creation of a flag to check the creation success or failure
		int updateCount = -1;

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "UPDATE tblIdentityBismark SET " + " firstName=?, lastName=?, email=?"
					+ " WHERE uniqueId=?";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, iBismark.getFirstName());
			prepStatement.setString(2, iBismark.getLastName());
			prepStatement.setString(3, iBismark.getEmail());
			prepStatement.setString(4, iBismark.getUniqueId().toUpperCase());

			updateCount = prepStatement.executeUpdate();

		} catch (Exception e) {

			// log the error that may or might occur during the running of the update
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to update a record for the identity "
					+ iBismark.getDisplayName().toUpperCase());

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}
		return updateCount;
	}

	@Override
	public int deleteIdentityBismarkRecord(IdentityBismark iBismark) throws Exception {
		
		// logging of info with level of info
		IBLOGGER.info("Deleting a record for the identity with unique id " + iBismark.getUniqueId().toUpperCase());

		

		int recordCount = -1;
		Connection conn = null;
		PreparedStatement prepStatement = null;
		try {

			final String sqlStatement = "DELETE FROM tblIdentityBismark WHERE tblIdentityBismark.UNIQUEID ='" + iBismark.getUniqueId() + "'";
			
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();
			prepStatement = conn.prepareStatement(sqlStatement);					

			recordCount = prepStatement.executeUpdate();

		} catch (Exception e) {
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to delete a record of the identity with Unique ID "
					+ iBismark.getUniqueId().toUpperCase());

		} finally {
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}
		return recordCount;
	}

	@Override
	public List<IdentityBismark> searchIdentityBismarkRecords(IdentityBismark searchCriteria) throws Exception {
		// logging of error with level of info
		IBLOGGER.info("Searching for a record of the identity " + searchCriteria.getDisplayName());

		final List<IdentityBismark> listOfIdentityBismark = new ArrayList<>();

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		IdentityBismark iBismark = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "SELECT firstName, lastName, email, uniqueId " + "FROM tblIdentityBismark "
					+ "WHERE (? IS NULL OR firstName LIKE ?) " + "AND (? IS NULL OR lastName LIKE ?) "
					+ "AND (? IS NULL OR email LIKE ?) " + "AND (? IS NULL OR uniqueId = ?)";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, searchCriteria.getFirstName());
			prepStatement.setString(2, searchCriteria.getFirstName() + "%");
			prepStatement.setString(3, searchCriteria.getLastName());
			prepStatement.setString(4, searchCriteria.getLastName() + "%");
			prepStatement.setString(5, searchCriteria.getEmail());
			prepStatement.setString(6, searchCriteria.getEmail() + "%");
			prepStatement.setString(7, searchCriteria.getUniqueId());
			prepStatement.setString(8, searchCriteria.getUniqueId());

			final ResultSet resultSet = prepStatement.executeQuery();

			while (resultSet.next()) {
				iBismark = new IdentityBismark();

				iBismark.setDisplayName(resultSet.getString("firstName"), resultSet.getString("lastName"));
				iBismark.setFirstName(resultSet.getString("firstName"));
				iBismark.setLastName(resultSet.getString("lastName"));
				iBismark.setEmail(resultSet.getString("email"));
				iBismark.setUniqueId(resultSet.getString("uniqueId"));

				listOfIdentityBismark.add(iBismark);
			}
		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to search a record for the identity "
					+ searchCriteria.getDisplayName().toUpperCase());
		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return listOfIdentityBismark;
	}

	/**
	 * 
	 * @param iBismarkSearchWord
	 * @param readSearchBy
	 * @return
	 * @throws RecordSearchException
	 */
	public List<IdentityBismark> searchIdentityBismarkRecords(IdentityBismark iBismarkSearchWord, Integer readSearchBy)
			throws RecordSearchException {

		String whereFieldName = "";		
		String whereFieldValue = "";

		switch (readSearchBy) {
		case 1:
			whereFieldName = "firstName";
			whereFieldValue = iBismarkSearchWord.getFirstName();
			break;
		case 2:
			whereFieldName = "lastName";
			whereFieldValue = iBismarkSearchWord.getLastName();
			break;
		case 3:
			whereFieldName = "email";
			whereFieldValue = iBismarkSearchWord.getEmail();
			break;
		case 4:
			whereFieldName = "uniqueId";
			whereFieldValue = iBismarkSearchWord.getUniqueId();
		default:
			whereFieldName = "uniqueId";
			whereFieldValue = iBismarkSearchWord.getUniqueId();
			break;
		}

		final String sqlQuery = "SELECT * FROM tblIdentityBismark WHERE " + whereFieldName + "=?";

		// logging of error with level of info
		IBLOGGER.info("Begin searching for a record of the identity " + iBismarkSearchWord.getDisplayName() + " where "
				+ whereFieldName.toUpperCase() + " = " + whereFieldValue.toUpperCase());

		final List<IdentityBismark> listOfIdentityBismark = new ArrayList<>();

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;
		IdentityBismark iBismark = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			prepStatement = conn.prepareStatement(sqlQuery);
			prepStatement.setString(1, whereFieldValue);

			final ResultSet resultSet = prepStatement.executeQuery();

			// loop through the results set and assign the values to the class properties or
			// fields
			while (resultSet.next()) {
				iBismark = new IdentityBismark();

				iBismark.setDisplayName(resultSet.getString("firstName"), resultSet.getString("lastName"));
				iBismark.setFirstName(resultSet.getString("firstName"));
				iBismark.setLastName(resultSet.getString("lastName"));
				iBismark.setEmail(resultSet.getString("email"));
				iBismark.setUniqueId(resultSet.getString("uniqueId"));

				listOfIdentityBismark.add(iBismark);
			}
		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to search a record for the identity "
					+ iBismarkSearchWord.getDisplayName().toUpperCase());

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return listOfIdentityBismark;
	}

	/**
	 * This method is an overloaded version which searches for Identities records in
	 * the database
	 * 
	 * @return It returns a list of all records of type IdentityBismark
	 * @throws RecordSearchException
	 *             It throws the RecordSearchException which inherits from the
	 *             Exception class
	 */
	public List<IdentityBismark> searchIdentityBismarkRecords() throws RecordSearchException {

		// logging of error with level of info
		IBLOGGER.info("Begine searching for all records in the IdentityBismark table ");

		final List<IdentityBismark> listOfIdentityBismark = new ArrayList<>();

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;
		IdentityBismark iBismark = null;

		// embed statements with try catch finally block to monitor application errors
		try {

			final String sqlQuery = "SELECT * FROM tblIdentityBismark";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			prepStatement = conn.prepareStatement(sqlQuery);
			final ResultSet resultSet = prepStatement.executeQuery();

			// loop through the results set and assign the values to the class properties or
			// fields
			while (resultSet.next()) {
				iBismark = new IdentityBismark();

				iBismark.setDisplayName(resultSet.getString("firstName"), resultSet.getString("lastName"));
				iBismark.setFirstName(resultSet.getString("firstName"));
				iBismark.setLastName(resultSet.getString("lastName"));
				iBismark.setEmail(resultSet.getString("email"));
				iBismark.setUniqueId(resultSet.getString("uniqueId"));

				listOfIdentityBismark.add(iBismark);
			}
		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to search a record for the identity " + iBismark.getDisplayName());

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return listOfIdentityBismark;
	}

	/**
	 * 
	 * @param uniqueID
	 * @return
	 */
	public int getRowCountByUID(String uniqueID) {

		// logging of error with level of info
		IBLOGGER.info("Searching for a record of the identity " + uniqueID);

		// create an object of the Connection and PreparedStatement classes and set it
		// values to null
		Connection conn = null;
		PreparedStatement prepStatement = null;

		int rowCount = 0;

		// embed statements with try catch finally block to monitor application errors
		try {

			// create a variable of String type to set the SQL statements
			final String sqlStatement = "SELECT uniqueId FROM tblIdentityBismark WHERE uniqueId = ?";

			// set a value to the connection object conn created above
			conn = IdentityBismarkJDBConnection.getIdentityBismarkDBConnection();

			// create a PreparedStatement object variable type in order to help execute the
			// SQL statements written
			// PreparedStatement is preferred over Statement because it helps prevent SQL
			// injection and other attacks
			prepStatement = conn.prepareStatement(sqlStatement);

			prepStatement.setString(1, uniqueID);

			final ResultSet resultSet = prepStatement.executeQuery();

			// loop through the results set and assign the values to the class properties or
			// fields
			while (resultSet.next()) {
				rowCount += 1;
			}

		} catch (Exception e) {

			// log the error that may or might occur during the running of the create
			// process
			IBLOGGER.error("An error of " + e.getMessage().toUpperCase()
					+ " occured while trying to search a record for the identity " + uniqueID);

		} finally {

			// close the various object that needs to be closes in order to save resources
			closeSQLResource.closeSQLResources(conn, prepStatement);
		}

		return rowCount;
	}

}
