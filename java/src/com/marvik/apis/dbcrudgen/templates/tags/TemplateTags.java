package com.marvik.apis.dbcrudgen.templates.tags;

public class TemplateTags {

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
}
