package com.marvik.apis.dbcrudgen.creator.android;

import java.io.File;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.parser.android.contentprovider.AndroidContentProvidersTemplatesParser;
import com.marvik.apis.dbcrudgen.parser.android.crudoperations.AndroidCrudOperationsTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.sqliteopenhelper.AndroidSQLiteOpenHelperTemplateParser;
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
	 * AndroidContentProvidersTemplatesParser
	 */
	private AndroidContentProvidersTemplatesParser androidContentProvidersTemplatesParser;

	/**
	 * AndroidSQLiteOpenHelperTemplateParser
	 */
	private AndroidSQLiteOpenHelperTemplateParser androidSQLiteOpenHelperTemplateParser;

	/**
	 * AndroidCrudOperationsTemplateParser
	 */
	private AndroidCrudOperationsTemplateParser androidCrudOperationsTemplateParser;

	/**
	 * Android CRUD Generator - Generates the full CRUD for android databases
	 */
	public AndroidCRUDCreator() {
		androidClassTableCrudTemplate = new AndroidClassTableCrudTemplate();
		androidTableSchemasTemplatesParser = new AndroidTableSchemasTemplatesParser();
		androidContentProvidersTemplatesParser = new AndroidContentProvidersTemplatesParser();
		androidSQLiteOpenHelperTemplateParser = new AndroidSQLiteOpenHelperTemplateParser();
		androidCrudOperationsTemplateParser = new AndroidCrudOperationsTemplateParser();
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
	 * AndroidCRUDCreator#getAndroidSQLiteOpenHelperTemplateParser
	 * 
	 * @return AndroidSQLiteOpenHelperTemplateParser
	 */
	public AndroidSQLiteOpenHelperTemplateParser getAndroidSQLiteOpenHelperTemplateParser() {
		return androidSQLiteOpenHelperTemplateParser;
	}

	/**
	 * AndroidCRUDCreator#getAndroidCrudOperationsTemplateParser
	 * 
	 * @return AndroidCrudOperationsTemplateParser
	 */
	public AndroidCrudOperationsTemplateParser getAndroidCrudOperationsTemplateParser() {
		return androidCrudOperationsTemplateParser;
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

		// SQLite open helper class package
		String sqliteOpenHelperSubclassPackage = getAndroidProjectConfiguration()
				.getAndroidContentProviderConfiguration().getAndroidDatabaseConfiguration()
				.getSqliteOpenHelperClassPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + sqliteOpenHelperSubclassPackage);

		// Database table package
		String databaseTablesPackage = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getTablesSchemasPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + databaseTablesPackage);

		// table CRUD package
		String tablesCRUDPackage = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getTablesCRUDPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + tablesCRUDPackage);

		// Create table schemas source file and saves it on disk
		createTablesSchemasSourceFile(database, projectStorageDir, databaseTablesPackage);

		// Create the database content provider file and saves it on disk
		createContentProviderSourceFile(projectStorageDir, contentProviderPackage, database);

		// create SQLiteOpenHelper Subclass
		createSQLiteOpenHelperSourceFile(projectStorageDir, sqliteOpenHelperSubclassPackage);

		// create CRUD Operations interface
		createAbstractCRUDOperationsSourceFile(projectStorageDir, tablesCRUDPackage);
	}

	// create CRUD Operations interface
	private void createAbstractCRUDOperationsSourceFile(String projectStorageDir,String tablesCrudPackage ) {

		//Create the source code
		String tablesAbstractCRUDOperationsSourceCode = getAndroidCrudOperationsTemplateParser().createSourceCode(tablesCrudPackage);
		
		String abstractCrudOperationsSourceFile = FileNameTemplates.Android.CRUD_OPERATIONS_INTERFACE_CLASS_NAME;
		
		//the table CRUD operations source file absolute path
		String tablesAbstractCRUDOperationsSourceFile =  projectStorageDir + NativeUtils.getFileSeparator()
		+ tablesCrudPackage + NativeUtils.getFileSeparator() + abstractCrudOperationsSourceFile;
		
		// write source code to disk
		boolean createAbstractCRUDOperationsSourceFile = createSourceFile(tablesAbstractCRUDOperationsSourceFile,
				tablesAbstractCRUDOperationsSourceCode);

		if (createAbstractCRUDOperationsSourceFile) {
			System.out.println("Created Abstract CRUD Operations Source File");
		}
	}

	// create SQLiteOpenHelper Subclass
	private void createSQLiteOpenHelperSourceFile(String projectStorageDir,String sqliteOpenHelperSubclassPackage) {

		// SQLite open helper class
		String sqliteOpenHelperSubclass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getSqliteOpenHelperClass();

		// Create source code
		String sqliteOpenHelperSubclassSourceCode = getAndroidSQLiteOpenHelperTemplateParser()
				.createSQLiteOpenHelperSubclassSourceCode(androidProjectConfiguration);

		// Create source code file absolute path
		String sQLiteOpenHelperSourceFile = projectStorageDir + NativeUtils.getFileSeparator()
				+ sqliteOpenHelperSubclassPackage + NativeUtils.getFileSeparator() + sqliteOpenHelperSubclass;

		// write source code to disk
		boolean createSQLiteOpenHelperSubclassSourceFile = createSourceFile(sQLiteOpenHelperSourceFile,
				sqliteOpenHelperSubclassSourceCode);

		if (createSQLiteOpenHelperSubclassSourceFile) {
			System.out.println("Created SQLiteOpen Helper Source File");
		}
	}

	// Create the database content provider file and saves it on disk
	private void createContentProviderSourceFile(String projectStorageDir,String contentProviderPackage,
			Database database) {
		
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
		String tablesSchemasSourceCode = getAndroidTableSchemasTemplatesParser()
				.createTablesSchemas(getAndroidProjectConfiguration(), database.getTables());
		String tablesSchemasAbsoluteSourceFile = projectStorageDir + NativeUtils.getFileSeparator()
				+ databaseTablesPackage + NativeUtils.getFileSeparator()
				+ FileNameTemplates.Android.TABLE_SCHEMAS_FILE_NAME;
		boolean createTablesSchemasSourceFile = createSourceFile(tablesSchemasAbsoluteSourceFile, tablesSchemasSourceCode);

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
