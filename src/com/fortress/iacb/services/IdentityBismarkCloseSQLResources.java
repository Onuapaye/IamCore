package com.fortress.iacb.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <h3>Class Description</h3>
 * <p>This class is created purposefully to close some SQL resources which are used
 * in creating a connection to the Database and also handling SQL statements.</br>
 * 
 * The core aim in creating it aside the reasons given above was that the same codes were called
 * more than two (2) times and per convention, there was a need to create it as a method</p>
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class IdentityBismarkCloseSQLResources {

	/**
	 * This method simply closes some SQL resources after they have been used.
	 * These resources are the Connection and the PreparedStatement objects which are
	 * as well passed at the parameters to the method.
	 * @param connection
	 * @param preparedStatement
	 */
	public void closeSQLResources(Connection connection, PreparedStatement preparedStatement) {
		
		if (connection != null) {
			try {
				connection.close();
			} catch (final SQLException e) {
				//e.printStackTrace();
			}
		}
		
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (final SQLException e) {
				//e.printStackTrace();
			}
		}
	}
}
