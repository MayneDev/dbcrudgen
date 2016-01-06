package com.marvik.apis.dbcrudgen.templates.simple;

public class SimpleTemplates {

	public static class FileNameTemplates {
		public static class PHP {
			public static final String PHP_CLASS_FILENAME_TEMPLATE_FILE_PATH = "$DIRECTORY$JAVA_BEANS_CLASSNAME.class.php";
		}
		public static class SQL {
			public static final String SQL_TABLES_FILE_NAME_TEMPLATE = "$DIRECTORY$TABLENAME.table.sql";
		}
	}
}
