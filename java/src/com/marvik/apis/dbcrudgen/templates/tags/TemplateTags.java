package com.marvik.apis.dbcrudgen.templates.tags;

import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;

public class TemplateTags {

	/**
	 * TAG_EMPTY_STRING
	 */
	public static final String TAG_EMPTY_STRING = "";

	/**
	 * TAG_PRINTING_CHAR_COMMA
	 */
	public static final CharSequence TAG_PRINTING_CHAR_COMMA = ",";

	/**
	 * TAG_PRINTING_CHAR_DOT
	 */
	public static final CharSequence TAG_PRINTING_CHAR_DOT = NativeTemplateTags.DOT;

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
		 * TABLE_NAME_TAG
		 */
		public static final String TABLE_NAME_TAG = "TABLE_NAME";

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

		/**
		 * TAG JAVA_BEANS_CLASS_NAME
		 */
		public static final CharSequence JAVA_BEANS_CLASS_NAME = "$JAVA_BEANS_CLASS_NAME";

		/**
		 * TAG DATABASE_TABLES_SCHEMAS
		 */
		public static final CharSequence DATABASE_TABLES_SCHEMAS = "$DATABASE_TABLES_SCHEMAS";
		/**
		 * TAG TABLES_DEFINITION_PACKAGE
		 */
		public static final CharSequence TABLES_DEFINITION_PACKAGE = "$TABLES_DEFINITION_PACKAGE";

		/**
		 * TAG TABLES_PACKAGE_DEFINITION
		 */
		public static final CharSequence TABLES_PACKAGE_DEFINITION = "$TABLES_PACKAGE_DEFINITION";
		/**
		 * TAG DATABASE_TABLES_SQL_VARIABLE
		 */
		public static final CharSequence DATABASE_TABLES_SQL_VARIABLE = "$TABLE_SQL";

		/**
		 * TAG PACKAGE_NAME
		 */
		public static final CharSequence PACKAGE_NAME = "$PACKAGE_NAME";
		/**
		 * TAG SQLITE_OPENHELPER_CLASS
		 */
		public static final CharSequence SQLITE_OPENHELPER_CLASS = "$SQLITE_OPENHELPER_CLASS";

		/**
		 * DATABASE_TABLES_CLASS
		 */
		public static final CharSequence DATABASE_TABLES_CLASS = "$DATABASE_TABLES_CLASS";

		/**
		 * MATCH_CODE
		 */
		public static final CharSequence MATCH_CODE = "$MATCH_CODE";

		/**
		 * URI_MATCHER_CODES
		 */
		public static final CharSequence URI_MATCHER_CODES = "$URI_MATCHER_CODES";

		/**
		 * INIT_URI_MATCHES
		 */
		public static final CharSequence INIT_URI_MATCHES = "$INIT_URI_MATCHES";

		/**
		 * TABLE_URI_MATCHER_CODE
		 */
		public static final CharSequence TABLE_URI_MATCHER_CODE = "$TABLE_URI_MATCHER_CODE";

		/**
		 * TABLE_DEFINITION_LINK
		 */
		public static final CharSequence TABLE_DEFINITION_LINK = "$TABLE_DEFINITION_LINK";

		/**
		 * $SQLITE_OPEN_HELPER_SUBCLASS
		 */
		public static final String SQLITE_OPEN_HELPER_SUBCLASS = "$SQLITE_OPEN_HELPER_SUBCLASS";
		/**
		 * SQLITE_OPEN_HELPER_SUBCLASS_DATA_TYPE_VARIABLE
		 */
		public static final String SQLITE_OPEN_HELPER_SUBCLASS_DATA_TYPE_VARIABLE = "$SQLITE_OPEN_HELPER_SUBCLASS_DATA_TYPE_VARIABLE";

		/**
		 * DATABASE_NAME
		 */
		public static final CharSequence DATABASE_NAME = "$DATABASE_NAME";
		/**
		 * DATABASE_VERSION
		 */
		public static final CharSequence DATABASE_VERSION = "$DATABASE_VERSION";

		/**
		 * TABLES_ROWS_QUERY_STATEMENT
		 */
		public static final CharSequence TABLES_ROWS_QUERY_STATEMENTS = "$TABLES_ROWS_QUERY_STATEMENTS";

		/**
		 * TABLES_ROWS_INSERT_STATEMENT
		 */
		public static final CharSequence TABLES_ROWS_INSERT_STATEMENTS = "$TABLES_ROWS_INSERT_STATEMENTS";

		/**
		 * TABLES_ROWS_DELETE_STATEMENTS
		 */
		public static final CharSequence TABLES_ROWS_DELETE_STATEMENTS = "$TABLES_ROWS_DELETE_STATEMENTS";

		/**
		 * TABLES_ROWS_UPDATE_STATMENTS
		 */
		public static final CharSequence TABLES_ROWS_UPDATE_STATEMENTS = "$TABLES_ROWS_UPDATE_STATEMENTS";
		/**
		 * CRUD_OPERATIONS_INTERFACE_PACKAGE
		 */
		public static final CharSequence CRUD_OPERATIONS_INTERFACE_PACKAGE = "$CRUD_OPERATIONS_INTERFACE_PACKAGE";

		/**
		 * DATABASE_TABLE_CLASS
		 */
		public static final CharSequence DATABASE_TABLE_CLASS = "$DATABASE_TABLE_CLASS";

		/**
		 * $COLUMN_SPECIFIC_QUERY_METHODS
		 */
		public static final CharSequence COLUMN_SPECIFIC_QUERY_METHODS = "$COLUMN_SPECIFIC_QUERY_METHODS";
		/**
		 * QUERIED_TABLE_COLUMN
		 */
		public static final CharSequence QUERIED_TABLE_COLUMN_NAME = "$QUERIED_TABLE_COLUMN_NAME";
		/**
		 * $QUERIED_COLUMN
		 */
		public static final CharSequence QUERIED_COLUMN = "$QUERIED_COLUMN";

		/**
		 * QUERIED_TABLE_COLUMN_REFERENCE
		 */
		public static final CharSequence QUERIED_TABLE_COLUMN_REFERENCE = "$QUERIED_TABLE_COLUMN_REFERENCE";

		/**
		 * FUNCTION_PARAMS_VARIABLES
		 */
		public static final CharSequence FUNCTION_PARAMS_VARIABLES = "$FUNCTION_PARAMS_VARIABLES";

		/**
		 * FUNCTION_PARAMS_KEYS
		 */
		public static final CharSequence FUNCTION_PARAMS_KEYS = "$FUNCTION_PARAMS_KEYS";

		/**
		 * FUNCTION_PARAMS_VALUES
		 */
		public static final CharSequence FUNCTION_PARAMS_VALUES = "$FUNCTION_PARAMS_VALUES";

		/**
		 * FUNCTION_PARAMS
		 */
		public static final CharSequence FUNCTION_PARAMS = "$FUNCTION_PARAMS";

		/**
		 * DATATYPE
		 */
		public static final CharSequence DATATYPE = Java.DATATYPE;

		/**
		 * OBJECT
		 */
		public static final CharSequence OBJECT = Java.OBJECT;

		/**
		 * JAVA_BEANS_OBJECT
		 */
		public static final CharSequence JAVA_BEANS_OBJECT = "$JAVA_BEANS_OBJECT";

		/**
		 * CLASS_NAME
		 */
		public static final String CLASS_NAME = "$CLASS_NAME";

		/**
		 * TABLE_COLUMN_CLASS_VARIABLES
		 */
		public static final CharSequence TABLE_COLUMN_CLASS_VARIABLES = "$TABLE_COLUMN_CLASS_VARIABLES";

		/**
		 * INIT_CONSTRUCTOR_PARAMS
		 */
		public static final String INIT_CONSTRUCTOR_PARAMS = "$INIT_CONSTRUCTOR_PARAMS";

		/**
		 * CLASS_VARIABLES_ENCAPSULATOR_METHODS
		 */
		public static final String CLASS_VARIABLES_ENCAPSULATOR_METHODS = "$CLASS_VARIABLES_ENCAPSULATOR_METHODS";

		/**
		 * CONSTRUCTOR_PARAMS
		 */
		public static final CharSequence CONSTRUCTOR_PARAMS = "$CONSTRUCTOR_PARAMS";

		/**
		 * INFO
		 */
		public static final String INFO = "Info";

		/**
		 * TABLE_MODEL_OBJECT
		 */

		public static final String TABLE_MODEL_OBJECT = "$TABLE_MODEL_OBJECT";

		/**
		 * TABLE_MODEL_INFO_CLASS
		 */
		public static final CharSequence TABLE_MODEL_INFO_CLASS = "$TABLE_MODEL_INFO_CLASS";

		/**
		 * CONTENT_VALUES_PUT_STATEMENTS
		 */
		public static final CharSequence CONTENT_VALUES_PUT_STATEMENTS = "$CONTENT_VALUES_PUT_STATEMENTS";

		/**
		 * COLUMN_NAME
		 */
		public static final CharSequence COLUMN_NAME = "$COLUMN_NAME";

		/**
		 * $TABLE_MODEL_CLASS
		 */
		public static final CharSequence TABLE_MODEL_CLASS = "$TABLE_MODEL_CLASS";

		/**
		 * CLASS_PACKAGE
		 */
		public static final CharSequence CLASS_PACKAGE = "$CLASS_PACKAGE";

		/**
		 * TABLE_CRUD_CLASS
		 */
		public static final CharSequence TABLE_CRUD_CLASS = "$TABLE_CRUD_CLASS";

		/**
		 * CLASS_OBJECT
		 */
		public static final CharSequence CLASS_OBJECT = "$CLASS_OBJECT";

		/**
		 * $TRANSACTION_CLASS
		 */
		public static final CharSequence TRANSACTION_CLASS = "$TRANSACTION_CLASS";

		/**
		 * TABLES_CRUD_CLASSES_IMPORT
		 */
		public static final CharSequence TABLES_CRUD_CLASSES_IMPORT = "$TABLES_CRUD_CLASSES_IMPORT";

		/**
		 * TABLES_CRUD_CLASSES_GETTERS
		 */
		public static final CharSequence TABLES_CRUD_CLASSES_GETTERS = "$TABLES_CRUD_CLASSES_GETTERS";

	}

	public static class Java {
		/**
		 * OBJECT
		 */
		public static final CharSequence OBJECT = "$OBJECT";

		/**
		 * DATATYPE
		 */
		public static final CharSequence DATATYPE = "$DATATYPE";
	}
}
