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
	}

	public static final class Java {
		/**
		 * PUBLIC_STATIC_FINAL_INT
		 */
		public static final CharSequence PRIVATE_STATIC_FINAL_INT_MODIFIER = "private static final int ";

		/**
		 * STATEMENT_DELIMETER
		 */
		public static final CharSequence STATEMENT_DELIMETER = ";";

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

		public static final class Android {
			/**
			 * JAVA_FILE_EXTENSION
			 */
			public static final String JAVA_FILE_EXTENSION = ".java";

			/**
			 * TABLE_SCHEMAS_CLASS_NAME
			 */
			public static final String TABLE_SCHEMAS_CLASS_NAME = "Tables";

			/**
			 * TABLE_SCHEMAS_FILE_NAME
			 */
			public static final String TABLE_SCHEMAS_FILE_NAME = TABLE_SCHEMAS_CLASS_NAME + JAVA_FILE_EXTENSION;
		}
	}
}
