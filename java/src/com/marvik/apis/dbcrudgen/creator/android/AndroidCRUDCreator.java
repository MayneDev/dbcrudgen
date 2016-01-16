package com.marvik.apis.dbcrudgen.creator.android;

import java.io.File;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.parser.android.contentprovider.AndroidContentProvidersTemplatesParser;
import com.marvik.apis.dbcrudgen.parser.android.tableschemas.AndroidTableSchemasTemplatesParser;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassTableCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates.FileNameTemplates;

public class AndroidCRUDCreator extends CrudCreator {

	/**
	 * AndroidProjectConfiguration
	 */
	private AndroidProjectConfiguration androidProjectConfiguration;

	/**
	 * AndroidTableSchemasTemplatesParser
	 */
	AndroidTableSchemasTemplatesParser androidTableSchemasTemplatesParser;

	/**
	 * AndroidClassTableCrudTemplate
	 */
	private AndroidClassTableCrudTemplate androidClassTableCrudTemplate;

	/**
	 * 
	 */
	private AndroidContentProvidersTemplatesParser androidContentProvidersTemplatesParser;

	/**
	 * Android CRUD Generator - Generates the full CRUD for android databases
	 */
	public AndroidCRUDCreator() {
		androidClassTableCrudTemplate = new AndroidClassTableCrudTemplate();
		androidTableSchemasTemplatesParser = new AndroidTableSchemasTemplatesParser();
		androidContentProvidersTemplatesParser = new AndroidContentProvidersTemplatesParser();
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
	public AndroidTableSchemasTemplatesParser getAndroidTableSchemasTemplatesParser() {
		return androidTableSchemasTemplatesParser;
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
	 * AndroidCRUDCreator#getAndroidContentProvidersTemplatesParser
	 * 
	 * @return AndroidContentProvidersTemplatesParser
	 */
	public AndroidContentProvidersTemplatesParser getAndroidContentProvidersTemplatesParser() {
		return androidContentProvidersTemplatesParser;
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

		// Create the database content provider file and saves it on disk
		createContentProviderSourceFile(androidProjectConfiguration, database);
	}

	private void createContentProviderSourceFile(AndroidProjectConfiguration androidProjectConfiguration,
			Database database) {
		// Project storage directory
		String projectStorageDir = getAndroidProjectConfiguration().getProjectStorageDir();

		// Content provider class package
		String contentProviderPackage = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getContentProviderPackage();

		// Content provide class name
		String contentProviderClass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getContentProviderClass();

		String contentProviderSourceCode = getAndroidContentProvidersTemplatesParser()
				.createContentProviderSourceFile(getAndroidProjectConfiguration(), database.getTables());

		String contentProviderSourceCodeFile = projectStorageDir + NativeUtils.getFileSeparator()
				+ contentProviderPackage + NativeUtils.getFileSeparator() + contentProviderClass
				+ FileNameTemplates.Android.JAVA_FILE_EXTENSION;

		boolean createContentProvidersSourceFile = createSourceFile(contentProviderSourceCodeFile,
				contentProviderSourceCode);

		if (createContentProvidersSourceFile) {
			System.out.println("Created Content providers Source File");
		}
	}

	/**
	 * AndroidCRUDCreator#createTablesSchemasSourceFile
	 * 
	 * Creates all the source code for all the tables schems and saves the
	 * source code to the disk.
	 * 
	 * @param database
	 * @param projectStorageDir
	 * @param databaseTablesPackage
	 */
	private void createTablesSchemasSourceFile(Database database, String projectStorageDir,
			String databaseTablesPackage) {
		String tablesSchemas = getAndroidTableSchemasTemplatesParser()
				.createTablesSchemas(getAndroidProjectConfiguration(), database.getTables());
		String tablesSchemasAbsoluteSourceFile = projectStorageDir + NativeUtils.getFileSeparator()
				+ databaseTablesPackage + NativeUtils.getFileSeparator()
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
