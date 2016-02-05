/**
 * 
 */
package com.marvik.apis.dbcrudgen.core.platforms.mysql.queries;

/**
*Created on Feb 5, 2016-10:50:55 AM by victor
*/

/**
 * @author victor
 *
 */
public class MYSQLQueries {

	/**
	 * MYSQL Query to show all databases
	 */
	public static final String MYSQL_QUERY_SHOW_DATABASES = "SHOW DATABASES;";

	/**
	 * MYSQL KEY that holds names of the databases
	 */
	public static final String MYSQL_QUERY_SHOW_DATABASES_RESULTS_KEY = "Database";

	/**
	 * MYSQL Query to select a database
	 */
	public static final String MYSQL_QUERY_USE_DATABASE = "USE $DATABASE;";

	/**
	 * MYSQL Query to show all tables in a database
	 */
	public static final String MYSQL_QUERY_SHOW_TABLES = "SHOW TABLES;";

	/**
	 * MYSQL Key that holds all the names of tables in a database
	 */
	public static final String MYSQL_QUERY_SHOW_TABLES_RESULTS_KEY = "Tables_in_$DATABASE";

	/**
	 * Hold the TAGS used in creating custom queries
	 * 
	 * @author victor
	 *
	 */
	public class QueryTags {

		/**
		 * $DATABASE Tag
		 */
		public static final String DATABASE = "$DATABASE";

	}

}
