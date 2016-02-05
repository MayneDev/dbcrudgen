/**
 * 
 */
package com.marvik.apis.dbcrudgen.core.databases.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.marvik.apis.dbcrudgen.database.connection.DatabaseConnectionProperties;

/**
*Created on Feb 5, 2016-10:21:33 AM by victor
*/

/**
 * MYSQLQueryExecutor provides handles for executing MYSQL Queries
 * 
 * @author victor
 *
 */
public class MYSQLQueryExecutor {

	/**
	 * MYSQLQueryExecutor provides handles for executing MYSQL Queries
	 */

	private MYSQLDatabaseConnection databaseConnection;

	public MYSQLQueryExecutor(MYSQLDatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	/**
	 * Get a MYSQL Database Connection
	 * 
	 * @return the databaseConnection
	 */
	public MYSQLDatabaseConnection getDatabaseConnection() {
		return databaseConnection;
	}

	/**
	 * Creates a statement
	 * 
	 * @return Statement
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public Statement createStatement() throws SQLException, ClassNotFoundException {
		
		return getConnection().createStatement();
	}

	/**
	 * getConnection
	 * 
	 * Get a database connection
	 * 
	 * @return Connection
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		return getDatabaseConnection().getConnection();		
	}

	/**
	 * Executes an sql query
	 * 
	 * @param sql
	 * @return a result set
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public ResultSet execSQL(String sql) throws SQLException, ClassNotFoundException {
		return createStatement().executeQuery(sql);
	}
	/**
	 * Executes an sql query
	 * 
	 * @param sql
	 * @return a boolean
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public boolean execute(String sql) throws SQLException, ClassNotFoundException {
		return createStatement().execute(sql);
	}
}
