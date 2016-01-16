package com.marvik.apis.dbcrudgen.creator.android;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassContentProviderTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassDatabaseTablesTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassSQLTableTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassSQLiteOpenHelperTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassTableCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidInterfaceCrudOperations;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeFloatTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeGenericTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeIntTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeLongTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeStringTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDefaultTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementAddUriMatcherTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementContentProviderSQLDeleteTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementContentProviderSQLInsertTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementContentProviderSQLQueryTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementContentProviderSQLUpdateTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementSQLTableColumnStatementTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatmentContentValuesPutTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidVariableSQLTableColumnTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidVariableSQLTableCreateSQLTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidVariableUriMatcherCodeTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates.FileNameTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidCRUDCreator extends CrudCreator {

	/**
	 * AndroidProjectConfiguration
	 */
	private AndroidProjectConfiguration androidProjectConfiguration;

	/**
	 * AndroidTemplatesParser
	 */
	AndroidTemplatesParser androidTemplatesParser;

	/**
	 * AndroidClassTableCrudTemplate
	 */
	private AndroidClassTableCrudTemplate androidClassTableCrudTemplate;

	/**
	 * Android CRUD Generator - Generates the full CRUD for android databases
	 */
	public AndroidCRUDCreator() {
		androidClassTableCrudTemplate = new AndroidClassTableCrudTemplate();
		androidTemplatesParser = new AndroidTemplatesParser();
	}

	@Deprecated
	@Override
	public CrudTemplates getCrudTemplate() {
		return getAndroidClassTableCrudTemplate();
	}

	/**
	 * AndroidCRUDCreator#getAndroidClassTableCrudTemplate
	 * 
	 * @return AndroidClassTableCrudTemplate
	 */
	public AndroidClassTableCrudTemplate getAndroidClassTableCrudTemplate() {
		return androidClassTableCrudTemplate;
	}

	/**
	 * AndroidCRUDCreator#getAndroidTemplatesParser
	 * 
	 * @return AndroidTemplatesParser
	 */
	public AndroidTemplatesParser getAndroidTemplatesParser() {
		return androidTemplatesParser;
	}

	/**
	 * Returns the android project configuration
	 * {@link AndroidCRUDCreator#getAndroidProjectConfiguration()}
	 * 
	 * @return androidProjectConfiguration
	 */
	public AndroidProjectConfiguration getAndroidProjectConfiguration() {
		return androidProjectConfiguration;
	}

	/**
	 * Sets the android project configuration
	 * {@link AndroidCRUDCreator#setAndroidProjectConfiguration(AndroidProjectConfiguration)}
	 * 
	 * @param androidProjectConfiguration
	 */
	public void setAndroidProjectConfiguration(AndroidProjectConfiguration androidProjectConfiguration) {
		this.androidProjectConfiguration = androidProjectConfiguration;
	}

	/**
	 * Creates the android database crud source code
	 * {@link AndroidCRUDCreator#createProject(Database)}
	 * 
	 * @param database
	 */
	public void createProject(Database database) {

		if (androidProjectConfiguration == null) {
			throw new NullPointerException("Android Project Configuration cannot be null");
		}

		// Project storage directory
		String projectStorageDir = getAndroidProjectConfiguration().getProjectStorageDir();
		createDirectory(projectStorageDir);

		// Content provider class package
		String contentProviderPackage = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getContentProviderPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + contentProviderPackage);

		// Content provide class name
		String contentProviderClass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getContentProviderClass();

		// SQLite open helper class package
		String sqliteOpenHelperSubclassPackage = getAndroidProjectConfiguration()
				.getAndroidContentProviderConfiguration().getAndroidDatabaseConfiguration()
				.getSqliteOpenHelperClassPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + sqliteOpenHelperSubclassPackage);

		// SQLite open helper class
		String sqliteOpenHelperSubclass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getSqliteOpenHelperClass();

		// Database table package
		String databaseTablesPackage = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getTablesSchemasPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + databaseTablesPackage);

		// Database name
		String databaseName = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getDatabaseName();

		// Database version
		int databaseVersion = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getDatabaseVersion();

		// Create table schemas source file and saves it on disk
		createTablesSchemasSourceFile(database, projectStorageDir, databaseTablesPackage);
	}

	/**
	 * AndroidCRUDCreator#createTablesSchemasSourceFile
	 * 
	 * Creates all the source code for all the tables schems and saves the source code to the disk.
	 * @param database
	 * @param projectStorageDir
	 * @param databaseTablesPackage
	 */
	private void createTablesSchemasSourceFile(Database database, String projectStorageDir,
			String databaseTablesPackage) {
		String tablesSchemas = getAndroidTemplatesParser().createTablesSchemas(getAndroidProjectConfiguration(),
				database.getTables());
		String tablesSchemasAbsoluteSourceFile = projectStorageDir + NativeUtils.getFileSeparator() + databaseTablesPackage + NativeUtils.getFileSeparator()
				+ FileNameTemplates.Android.TABLE_SCHEMAS_FILE_NAME;
		boolean createTablesSchemasSourceFile = createSourceFile(tablesSchemasAbsoluteSourceFile, tablesSchemas);

		if (createTablesSchemasSourceFile) {
			System.out.println("Created Tables Source File");
		}
	}

	/**
	 * Creates a source file
	 * 
	 * @param absoluteFileName
	 * @param sourceCode
	 */
	private boolean createSourceFile(String absoluteFileName, String sourceCode) {
		return getFilesHandler().createByteWeighedFile(absoluteFileName, sourceCode);
	}

	/**
	 * Creates a directory
	 * 
	 * @param directory
	 */
	private void createDirectory(String directory) {
		getFilesHandler().createDirectories(new File(directory));
	}

}
