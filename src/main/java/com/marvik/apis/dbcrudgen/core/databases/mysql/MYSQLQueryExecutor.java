/**
 * 
 */
package com.marvik.apis.dbcrudgen.core.databases.mysql;

import java.net.ConnectException;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.marvik.apis.dbcrudgen.database.connection.DatabaseConnectionProperties;
import com.mysql.jdbc.CommunicationsException;

/**
*Created on Feb 5, 2016-10:21:33 AM by victor
*/

/**
 * MYSQLTransactionsExecutor provides handles for executing MYSQL Queries
 * 
 * @author victor
 *
 */
public class MYSQLQueryExecutor {

	/**
	 * MYSQLTransactionsExecutor provides handles for executing MYSQL Queries
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
	 * @throws CommunicationsException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws SocketException
	 * @throws ConnectException
	 */
	public Statement createStatement()
			throws ConnectException, ClassNotFoundException, CommunicationsException, SocketException, SQLException {

		return getConnection().createStatement();
	}

	/**
	 * getConnection
	 * 
	 * Get a database connection
	 * 
	 * @return Connection
	 * @throws CommunicationsException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws SocketException
	 * @throws ConnectException
	 */
	private Connection getConnection()
			throws ConnectException, ClassNotFoundException, CommunicationsException, SocketException, SQLException {
		return getDatabaseConnection().getConnection();
	}

	/**
	 * Executes an sql query
	 * 
	 * @param sql
	 * @return a result set
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws SocketException
	 * @throws ConnectException
	 */
	public ResultSet execSQL(String sql) {
		try {
			return createStatement().executeQuery(sql);
		} catch (ClassNotFoundException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Executes an sql query
	 * 
	 * @param sql
	 * @return a boolean
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws SocketException
	 * @throws ConnectException
	 * @throws CommunicationsException
	 */
	public boolean execute(String sql) {

		try {
			return createStatement().execute(sql);
		} catch (ClassNotFoundException | SocketException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
