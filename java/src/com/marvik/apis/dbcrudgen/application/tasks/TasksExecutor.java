/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDatabaseConnection;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDefaultConnectionProperties;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLQueryExecutor;
import com.marvik.apis.dbcrudgen.core.platforms.mysql.queries.MYSQLQueries;
import com.marvik.apis.dbcrudgen.core.toolchains.xampp.XAMPP;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;

/**
*Created on Feb 5, 2016-10:40:21 AM by victor
*/

/**
 * @author victor
 *
 */
public class TasksExecutor {

	/**
	 * Tasks Executor
	 * 
	 * Executes all the actions of the main window
	 */
	public TasksExecutor() {
		startXAMPPServer();
		getMYSQLQueryExecutor();
	}

	private MYSQLQueryExecutor queryExecutor;

	/**
	 * Get MYSQL query executor - Get an handle for using to execute MYSQL
	 * queries
	 * 
	 * @return
	 */
	public MYSQLQueryExecutor getMYSQLQueryExecutor() {

		if (queryExecutor == null) {
			queryExecutor = new MYSQLQueryExecutor(getMYSQLDatabaseConnection());
		}
		return queryExecutor;
	}

	private MYSQLDatabaseConnection databaseConnection;

	/**
	 * Get MYSQL Database Connection
	 * 
	 * @return MYSQLDatabaseConnection
	 */
	public MYSQLDatabaseConnection getMYSQLDatabaseConnection() {

		if (databaseConnection == null) {
			databaseConnection = new MYSQLDatabaseConnection(MYSQLDefaultConnectionProperties.DATABASE_HOST,
					MYSQLDefaultConnectionProperties.DATABASE_USER, MYSQLDefaultConnectionProperties.USER_PASSWORD);
		}

		return databaseConnection;
	}

	/**
	 * Get a list of all the MYSQL databases
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws SocketException
	 * @throws ConnectException
	 */
	public List<Database> getDatabases() {

		List<Database> databases = new ArrayList<Database>();
		ResultSet mysqlDatabases = getMYSQLQueryExecutor().execSQL(MYSQLQueries.MYSQL_QUERY_SHOW_DATABASES);
		try {
			for (mysqlDatabases.first(); !mysqlDatabases.isAfterLast(); mysqlDatabases.next()) {
				String databaseName = mysqlDatabases.getString(MYSQLQueries.MYSQL_QUERY_SHOW_DATABASES_RESULTS_KEY);
				databases.add(new Database(databaseName, null));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return databases;
	}

	/**
	 * @param selectedDatabase
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws SocketException
	 * @throws ConnectException
	 */
	public List<String> getDatabaseTables(String database) {
		List<String> tables = new ArrayList<>();
		try {
			String useDatabaseMYSQLQuery = MYSQLQueries.MYSQL_QUERY_USE_DATABASE;
			useDatabaseMYSQLQuery = useDatabaseMYSQLQuery.replace(MYSQLQueries.QueryTags.DATABASE, database);

			getMYSQLQueryExecutor().execute(useDatabaseMYSQLQuery);

			String showTables = MYSQLQueries.MYSQL_QUERY_SHOW_TABLES;

			ResultSet databaseTables = getMYSQLQueryExecutor().execSQL(showTables);
			

			for (databaseTables.first(); !databaseTables.isAfterLast(); databaseTables.next()) {
				String showTablesResultsKey = MYSQLQueries.MYSQL_QUERY_SHOW_TABLES_RESULTS_KEY;
				showTablesResultsKey = showTablesResultsKey.replace(MYSQLQueries.QueryTags.DATABASE, database);
				String tableName = databaseTables.getString(showTablesResultsKey);
				tables.add(tableName);
			}

			return tables;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
	}

	/**
	 * 
	 */
	public void startXAMPPServer() {
		try {
			Process startApache = Runtime.getRuntime().exec(XAMPP.START_APACHE_PATH);
			Process startMysql = Runtime.getRuntime().exec(XAMPP.START_MYSQL_PATH);
			
			if(startApache.isAlive()){
				System.out.println("Apache : Is Alive");
			}else{printStream(startApache.getInputStream());}
			if(startMysql.isAlive()){
				System.out.println("MYSQL : Is Alive");
			}else{printStream(startMysql.getInputStream());}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @param outputStream
	 */
	private void printStream(InputStream inputStream) {
		BufferedReader reader =new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		StringBuilder builder = new StringBuilder();
		try {
			while((line = reader.readLine()) != null){
				builder.append(line);
			}
			System.out.println(builder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
