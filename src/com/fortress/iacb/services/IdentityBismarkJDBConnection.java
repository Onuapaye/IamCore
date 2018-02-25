package com.fortress.iacb.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class IdentityBismarkJDBConnection {

	private static final String DATABASE_HOST = "jdbc:derby://localhost:1527/IACBdb;create=true";
	private static final String DATABASE_USERNAME = "fortress";
	private static final String DATABASE_PASSWORD = "4tress";
	
	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getIdentityBismarkDBConnection() throws ClassNotFoundException, SQLException {
		
		final String hostName = DATABASE_HOST; 
        final String userName = DATABASE_USERNAME; 
        final String dbPassword = DATABASE_PASSWORD;
    
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
		final Connection conn = DriverManager.getConnection(hostName, userName, dbPassword);
		
		return conn;
	}
}
