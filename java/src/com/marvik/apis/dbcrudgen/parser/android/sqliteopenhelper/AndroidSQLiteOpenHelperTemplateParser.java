package com.marvik.apis.dbcrudgen.parser.android.sqliteopenhelper;

import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidSQLiteOpenHelperTemplateParser extends AndroidTemplatesParser {

	public String createSQLiteOpenHelperSubclassSourceCode(AndroidProjectConfiguration androidProjectConfiguration) {

		// Project storage directory
		String projectStorageDir = androidProjectConfiguration.getProjectStorageDir();

		// SQLite open helper class package
		String sqliteOpenHelperSubclassPackage = androidProjectConfiguration.getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getSqliteOpenHelperClassPackage();

		// SQLite open helper class
		String sqliteOpenHelperSubclass = androidProjectConfiguration.getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getSqliteOpenHelperClass();

		String sqliteOpenHelperSubclassTemplate = getAndroidClassSQLiteOpenHelperTemplate().getTemplate();

		// add package name
		sqliteOpenHelperSubclassTemplate = parseOpenHelperSubclassPackageName(sqliteOpenHelperSubclassTemplate,
				sqliteOpenHelperSubclassPackage);

		// add database tables class import
		sqliteOpenHelperSubclassTemplate = addDatabaseTablesClassImport(sqliteOpenHelperSubclassTemplate,
				androidProjectConfiguration);

		// add sqlite open helper class name
		sqliteOpenHelperSubclassTemplate = parseSQLiteOpenHelperSubClassName(sqliteOpenHelperSubclassTemplate,
				sqliteOpenHelperSubclass);

		return sqliteOpenHelperSubclassTemplate;
	}

	// add sqlite open helper class name
	private String parseSQLiteOpenHelperSubClassName(String sqliteOpenHelperSubclassTemplate,
			String sqliteOpenHelperSubclass) {
		return sqliteOpenHelperSubclassTemplate.replace(TemplateTags.Android.SQLITE_OPEN_HELPER_SUBCLASS,
				sqliteOpenHelperSubclass);
	}

	// add database tables class import
	private String addDatabaseTablesClassImport(String sqliteOpenHelperSubclassTemplate,
			AndroidProjectConfiguration androidProjectConfiguration) {

		AndroidContentProviderConfiguration androidContentProviderConfiguration = androidProjectConfiguration
				.getAndroidContentProviderConfiguration();

		AndroidDatabaseConfiguration androidDatabaseConfiguration = androidContentProviderConfiguration
				.getAndroidDatabaseConfiguration();

		String tableSchemasPackage = androidDatabaseConfiguration.getTablesSchemasPackage();
		tableSchemasPackage = parseJavaPackage(tableSchemasPackage);

		String tableSchemasClass = tableSchemasPackage + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME;
		return sqliteOpenHelperSubclassTemplate.replace(TemplateTags.Android.DATABASE_TABLES_CLASS, tableSchemasClass);
	}

	private String parseOpenHelperSubclassPackageName(String sqliteOpenHelperSubclassTemplate,
			String sqliteOpenHelperSubclassPackage) {
		String packageName = parseJavaPackage(sqliteOpenHelperSubclassPackage);
		return sqliteOpenHelperSubclassTemplate.replace(TemplateTags.Android.PACKAGE_NAME, packageName);
	}

}
