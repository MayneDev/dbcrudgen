package com.marvik.apis.dbcrudgen.templates.tags;

public class TemplateTags {

	/**
	 * TAG_EMPTY_STRING
	 */
	public static final String TAG_EMPTY_STRING = "";

	/**
	 * TAG_PRINTING_CHAR_COMMA
	 */
	public static final CharSequence TAG_PRINTING_CHAR_COMMA = ",";

	public static class PHP {

		/**
		 * COLUMN_NAME TAG
		 */
		public static final String COLUMN_NAME_TEMPLATE_TAG = "COLUMN_NAME";

		/**
		 * TABLE_NAME TAG
		 */
		public static final String TABLE_NAME = "$TABLE_NAME";
		/**
		 * CLASS_NAME TAG
		 */
		public static final String CLASS_NAME = "CLASS_NAME";

		/**
		 * DIRECTORY TAG
		 */
		public static final String DIRECTORY = "$DIRECTORY";

		/**
		 * JAVA_BEANS_CLASSNAME TAG
		 */
		public static final String JAVA_BEANS_CLASSNAME = "$JAVA_BEANS_CLASSNAME";
		/**
		 * DB_CRUD_GENERATOR_TEMPLATE TAG
		 */
		public static final String DB_CRUD_GENERATOR_TEMPLATE = "$CRUD_GENERATOR_TEMPLATE";

		/**
		 * PRIMARY_KEY TAG
		 */
		public static final String PRIMARY_KEY = "PRIMARY_KEY";

		/**
		 * DATA_ACTIONS_FILE_PATH TAG
		 */
		public static final String DATA_ACTIONS_FILE_PATH = "$DATA_ACTIONS_FILE_PATH";

		/**
		 * DATABASE_UTILS_FILE_PATH TAG
		 */
		public static final String DATABASE_UTILS_FILE_PATH = "$DATABASE_UTILS_FILE_PATH";

		/**
		 * DATABASE_CONNECTION_INC_FILE TAG
		 */
		public static final String DATABASE_CONNECTION_INC_FILE_PATH = "$DATABASE_CONNECTION_INC_FILE";

		/**
		 * QUERIED_COLUMN TAG
		 */
		public static final String QUERIED_COLUMN = "QUERIED_COLUMN";
		/**
		 * QUERIED_COLUMN_PLURAL TAG
		 */
		public static final String QUERY_RESULTS = "QUERY_RESULTS";
		/**
		 * PRIMARY_KEYS_COLUMN_PARAMS TAG
		 */
		public static final String FUNCTION_PARAMS_KEYS = "FUNCTION_PARAMS_KEYS";
		/**
		 * PRIMARY_KEYS_PARAM_VALUES TAG
		 */
		public static final String FUNCTION_PARAMS_VALUES = "FUNCTION_PARAMS_VALUES";
		/**
		 * TABLE_COLUMNS_ACCESSOR_FUNCTIONS TAG
		 */
		public static final String TABLE_COLUMNS_ACCESSOR_FUNCTIONS = "$TABLE_COLUMNS_ACCESSOR_FUNCTIONS";
		/**
		 * TABLE_COLUMNS_QUERY_FUNCTIONS TAG
		 */
		public static final String TABLE_COLUMNS_CRUD_FUNCTIONS = "$TABLE_COLUMNS_CRUD_FUNCTIONS";

	}

	public static class SQL {
		/**
		 * DIRECTORY TAG
		 */
		public static final String DIRECTORY = "$DIRECTORY";
		/**
		 * TABLE_NAME TAG
		 */
		public static final String TABLE_NAME = "$TABLENAME";
	}

	public static class DatabaseConnection {

		/**
		 * SERVER_HOST TAG
		 */
		public static final String SERVER_HOST = "$SERVER_HOST";
		/**
		 * DATABASE_USER TAG
		 */
		public static final String DATABASE_USER = "$DATABASE_USER";
		/**
		 * DATABASE_USER TAG
		 */
		public static final String USER_PASSWORD = "$USER_PASSWORD";
		/**
		 * DATABASE_USER TAG
		 */
		public static final String DATABASE_NAME = "$DATABASE_NAME";
		/**
		 * DATABASE_USER TAG
		 */
		public static final String DATABASE_CONNECTION_INC_FILE = "$DATABASE_CONNECTION_INC_FILE";

	}

	public static class Android {
		/**
		 * TAG_TABLE_COLUMN_DEFINITION
		 */
		public static final CharSequence TAG_TABLE_COLUMN_DEFINITION = "$TABLE_COLUMN_DEFINITION";

		/**
		 * TAG_TABLE_COLUMN_REFERENCE
		 */
		public static final CharSequence TAG_TABLE_COLUMN_REFERENCE = "$TABLE_COLUMN_REFERENCE";
		/**
		 * TAG_TABLE_COLUMNS
		 */
		public static final CharSequence TABLE_COLUMNS = "$TABLE_COLUMNS";

		/**
		 * TAG TABLE NAME
		 */
		public static final CharSequence TABLE_NAME = "$TABLE_NAME";

		/**
		 * TAG CONTENT_PROVIDER_PACKAGE
		 */
		public static final String CONTENT_PROVIDER_PACKAGE = "$CONTENT_PROVIDER_PACKAGE";
		/**
		 * TAG CONTENT_PROVIDER_CLASS
		 */
		public static final String CONTENT_PROVIDER_CLASS = "$CONTENT_PROVIDER_CLASS";

		/**
		 * TAG TABLE_CREATE_SQL
		 */
		public static final CharSequence TABLE_CREATE_SQL = "$TABLE_CREATE_SQL";

		/**
		 * TAG TABLE_COLUMN_DEFINITION
		 */
		public static final String TABLE_COLUMN_DEFINITION = "$TABLE_COLUMN_DEFINITION";
		/**
		 * TAG TABLE_COLUMN_DATATYPE
		 */
		public static final String TABLE_COLUMN_DATATYPE = "$TABLE_COLUMN_DATATYPE";

		/**
		 * TAG TABLE_COLUMNS_COMMA_SEPARATOR
		 */
		public static final CharSequence TABLE_COLUMNS_COMMA_SEPARATOR = "$TABLE_COLUMNS_COMMA_SEPARATOR";
		/**
		 * TAG TABLE_JAVA_BEANS_CLASS_NAME
		 */
		public static final CharSequence TABLE_JAVA_BEANS_CLASS_NAME = "$TABLE_JAVA_BEANS_CLASS_NAME";

	}

}
