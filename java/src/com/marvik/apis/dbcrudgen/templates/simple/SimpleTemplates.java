package com.marvik.apis.dbcrudgen.templates.simple;

public class SimpleTemplates {
	public static class Android {
		/**
		 * J2SE_MYSQL_DATABASE_TABLE_SQL_VARIABLE
		 */
		public static final String ANDROID_DATABASE_TABLE_SQL_VARIABLE = "$JAVA_BEANS_CLASS_NAME.SQL";
		/**
		 * J2SE_MYSQL_DATABASE_TABLE_SQL_VARIABLE
		 */
		public static final String URI_MATCHER_CODE_SUFFIX = "_URI_MATCHER_CODE";

		/**
		 * STATEMENT_CLASS_IMPORT
		 */
		public static final String STATEMENT_CLASS_IMPORT = Java.STATEMENT_CLASS_IMPORT;

		/**
		 * ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_INT
		 */
		public static final String ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_BOOLEAN = "boolean $COLUMN_OBJECT = cursor.getBoolean(cursor.getColumnIndex($TABLES_SCHEMAS_CLASS.$TABLE_NAME.$COLUMN_NAME);";

		/**
		 * ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_INT
		 */
		public static final String ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_INT = "int $COLUMN_OBJECT = cursor.getInt(cursor.getColumnIndex($TABLES_SCHEMAS_CLASS.$TABLE_NAME.$COLUMN_NAME));";

		/**
		 * ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_LONG
		 */
		public static final String ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_LONG = "long $COLUMN_OBJECT = cursor.getLong(cursor.getColumnIndex($TABLES_SCHEMAS_CLASS.$TABLE_NAME.$COLUMN_NAME));";

		/**
		 * ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING
		 */
		public static final String ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING = "String $COLUMN_OBJECT = cursor.getString(cursor.getColumnIndex($TABLES_SCHEMAS_CLASS.$TABLE_NAME.$COLUMN_NAME));";

		/**
		 * ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_FLOAT
		 */
		public static final String ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_FLOAT = "float $COLUMN_OBJECT = cursor.getFloat(cursor.getColumnIndex($TABLES_SCHEMAS_CLASS.$TABLE_NAME.$COLUMN_NAME));";

		/**
		 * ANDROID_DATABASE_SQL_SEARCH_SELECTION_TEMPLATE
		 */
		public static final String ANDROID_DATABASE_SQL_SEARCH_SELECTION_TEMPLATE = " $TABLES_SCHEMAS_CLASS.$TABLE_NAME.$COLUMN_NAME +\" LIKE '%\"+searchKey+\"%'\" ";

		/**
		 * PRIMARY_KEY_OBJECT
		 */
		public static final String PRIMARY_KEY_OBJECT = "_$PRIMARY_KEY_OBJECT";

		/**
		 * COLUMN_SELECTION_TEMPLATE
		 */
		public static final String COLUMN_SELECTION_TEMPLATE = "$COLUMN_REFERENCE +\"='\" +String.valueOf($PRIMARY_KEY_OBJECT) +\"'\" ";
	}

	public static final class Java {
		/**
		 * STATEMENT_CLASS_IMPORT
		 */
		public static final String STATEMENT_CLASS_IMPORT = "import $CLASS_PACKAGE.$CLASS_NAME;";

		/**
		 * PUBLIC_STATIC_FINAL_INT
		 */
		public static final CharSequence PRIVATE_STATIC_FINAL_INT_MODIFIER = "private static final int ";

		/**
		 * STATEMENT_DELIMETER
		 */
		public static final CharSequence STATEMENT_DELIMETER = ";";

		/**
		 * STRING_DEFAULT_PARSER
		 */
		public static final String STRING_DEFAULT_PARSER = "String.valueOf($OBJECT)";

		/**
		 * JAVA_OBJECT_INIT_STATEMENT_TEMPLATE
		 */
		public static final String JAVA_OBJECT_INIT_STATEMENT_TEMPLATE = "$OBJECT = new $DATATYPE();";

		/**
		 * JAVA_CLASS_VARIABLE_INIT_STATMENT_TEMPLATE
		 */
		public static final String JAVA_CLASS_VARIABLE_INIT_STATMENT_TEMPLATE = "this.$OBJECT = $OBJECT;";
		
		/**
		 * JAVA_CLASS_VARIABLE_INIT_STATMENT_TEMPLATE
		 */
		public static final String NEW_JAVA_CLASS_VARIABLE_INIT_STATMENT_TEMPLATE = "this.${OBJECT} = ${OBJECT};";

		/**
		 * CLASS_PATH_ITEM_ENTRY
		 */
		public static final String CLASS_PATH_ITEM_ENTRY = "<classpathentry exported=\"true\" kind=\"${ITEM_KIND}\" path=\"${ITEM_PATH}\"/>";
		/**
		 * J2SE_MYSQL_DATABASE_TABLE_SQL_VARIABLE
		 */
		public static final String J2SE_MYSQL_DATABASE_TABLE_SQL_VARIABLE = "${JAVA_BEANS_CLASS_NAME}.SQL";
	}

	public static class FileNameTemplates {

		public static class PHP {
			/**
			 * PHP_CLASS_FILENAME_TEMPLATE_FILE_PATH
			 */
			public static final String PHP_CLASS_FILENAME_TEMPLATE_FILE_PATH = "$DIRECTORY$JAVA_BEANS_CLASSNAME.class.php";
		}

		public static class SQL {
			/**
			 * SQL_TABLES_FILE_NAME_TEMPLATE
			 */
			public static final String SQL_TABLES_FILE_NAME_TEMPLATE = "$DIRECTORY$TABLENAME.table.sql";
		}

	}

	public static class PHP {
		/**
		 * TABLE_COLUMN_GETTER_METHOD_TEMPLATE
		 */
		public static final String TABLE_COLUMN_GETTER_METHOD_TEMPLATE = "$this->_get_$COLUMN_NAME";

		/**
		 * PHP_STRING_PARAMETER_OBJECT
		 */
		public static final String PHP_STRING_PARAMETER_OBJECT = "'$OBJECT'";

		/**
		 * PHP_OBJECT_DECLARATION_SYNTAX
		 */
		public static final String PHP_OBJECT_DECLARATION_SYNTAX = "$$OBJECT";
	}
}
