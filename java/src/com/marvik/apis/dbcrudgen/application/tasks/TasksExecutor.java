/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDatabaseConnection;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDefaultConnectionProperties;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLQueryExecutor;
import com.marvik.apis.dbcrudgen.core.platforms.mysql.queries.MYSQLQueries;
import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
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
	 */
	public List<Database> getDatabases() throws SQLException, ClassNotFoundException {

		List<Database> databases = new ArrayList<Database>();
		ResultSet mysqlDatabases = getMYSQLQueryExecutor().execSQL(MYSQLQueries.MYSQL_QUERY_SHOW_DATABASES);
		for (mysqlDatabases.first(); !mysqlDatabases.isAfterLast(); mysqlDatabases.next()) {
			String databaseName = mysqlDatabases.getString(MYSQLQueries.MYSQL_QUERY_SHOW_DATABASES_RESULTS_KEY);
			databases.add(new Database(databaseName, null));
		}
		return databases;
	}

	/**
	 * @param selectedDatabase
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<String> getDatabaseTables(String database) throws ClassNotFoundException, SQLException {
		String useDatabaseMYSQLQuery = MYSQLQueries.MYSQL_QUERY_USE_DATABASE;
		useDatabaseMYSQLQuery = useDatabaseMYSQLQuery.replace(MYSQLQueries.QueryTags.DATABASE, database);

		getMYSQLQueryExecutor().execute(useDatabaseMYSQLQuery);
		
		String showTables = MYSQLQueries.MYSQL_QUERY_SHOW_TABLES;
				
		ResultSet databaseTables = getMYSQLQueryExecutor().execSQL(showTables);
		List<String> tables = new ArrayList<>();
		
		for (databaseTables.first(); !databaseTables.isAfterLast(); databaseTables.next()) {
			String showTablesResultsKey = MYSQLQueries.MYSQL_QUERY_SHOW_TABLES_RESULTS_KEY;
			showTablesResultsKey = showTablesResultsKey.replace(MYSQLQueries.QueryTags.DATABASE, database);
			String tableName = databaseTables.getString(showTablesResultsKey);
			tables.add(tableName);
		}
	
		return tables;
	}
}
