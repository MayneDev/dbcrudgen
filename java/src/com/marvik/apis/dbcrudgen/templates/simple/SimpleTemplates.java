package com.marvik.apis.dbcrudgen.templates.simple;

public class SimpleTemplates {
	public static class Android {
		/**
		 * ANDROID_DATABASE_TABLE_SQL_VARIABLE
		 */
		public static final String ANDROID_DATABASE_TABLE_SQL_VARIABLE = "$JAVA_BEANS_CLASS_NAME.SQL";
		/**
		 * ANDROID_DATABASE_TABLE_SQL_VARIABLE
		 */
		public static final String URI_MATCHER_CODE_SUFFIX = "_URI_MATCHER_CODE";
		
		/**
		 * STATEMENT_CLASS_IMPORT
		 */
		public static final String STATEMENT_CLASS_IMPORT = Java.STATEMENT_CLASS_IMPORT;
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
}
