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
	 * MYSQL Query to select a database
	 */
	public static final String MYSQL_QUERY_USE_DATABASE = "USE $DATABASE;";

	/**
	 * MYSQL Query to show all tables in a database
	 */
	public static final String MYSQL_QUERY_SHOW_TABLES = "SHOW TABLES;";

	public static final String MYSQL_QUERY_SHOW_TABLE_COLUMNS = "SHOW COLUMNS IN $TABLE IN $DATABASE;";

	/**
	 * 
	 */
	public static final String MYSQL_QUERY_SHOW_CREATE_TABLE = "SHOW CREATE TABLE $TABLE;";

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

		/**
		 * TABLE TAG
		 */
		public static final String TABLE = "$TABLE";

	}

	/**
	 * The keys found in the query results
	 * 
	 * @author victor
	 *
	 */
	public class ResultsKeys {

		/**
		 * The keys found in the Show Databases MYSQL Query
		 * 
		 * @author victor
		 *
		 */
		public class ShowDatabases {
			public static final String KEY_DATABASE = "Database";
		}

		/**
		 * The keys found in the Show Database Tables MYSQL Query
		 * 
		 * @author victor
		 *
		 */
		public class ShowDatabaseTables {
			public static final String TABLES_IN_DATABASE = "Tables_in_$DATABASE";
		}

		/**
		 * The keys found in the ShowTableColumns
		 * 
		 * @author victor
		 *
		 */
		public class ShowTableColumns {
			/**
			 * The name of the column
			 */
			public static final String KEY_FIELD = "Field";
			/**
			 * The data type of the column
			 */
			public static final String KEY_TYPE = "Type";
			/**
			 * The null attribute of the column
			 */
			public static final String KEY_NULL = "Null";
			/**
			 * The key for the table PRI - PRIMARY KEY
			 */
			public static final String KEY_TABLE_KEY = "Key";
			/**
			 * The default value for the column
			 */
			public static final String KEY_DEFAULT = "Default";
			/**
			 * Extra flags for the column
			 */
			public static final String KEY_EXTRA = "Extra";

		}

		/**
		 * Keys found after executing the show create table statement
		 * 
		 * @author victor
		 *
		 */
		public class ShowCreateTable {
			/**
			 * table name
			 */
			public static final String KEY_TABLE = "Table";
			/**
			 * Create table statement
			 */
			public static final String KEY_CREATE_TABLE = "Create Table";
		}
	}

}
