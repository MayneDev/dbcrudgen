package com.marvik.apis.dbcrudgen.creator.android;

import java.io.File;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.parser.android.contentprovider.AndroidContentProvidersTemplatesParser;
import com.marvik.apis.dbcrudgen.parser.android.crudoperations.AndroidCrudOperationsTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.javaobjects.AndroidJavaObjectDefaultEncapsulationTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.sqliteopenhelper.AndroidSQLiteOpenHelperTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.tablecrud.AndroidTableCRUDTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.tablemodel.AndroidTableModelTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.tableschemas.AndroidTableSchemasTemplatesParser;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassTableCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.android.javaobject.encapsulation.AndroidJavaObjectDefaultEncapsulationTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidCRUDCreator extends CrudCreator {

	/**
	 * AndroidProjectConfiguration
	 */
	private AndroidProjectConfiguration androidProjectConfiguration;

	/**
	 * Android CRUD Generator - Generates the full CRUD for android databases
	 */
	public AndroidCRUDCreator() {

	}

	@Deprecated
	@Override
	public CrudTemplates getCrudTemplate() {
		return new AndroidClassTableCrudTemplate();
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
	 * {@link AndroidCRUDCreator#getAndroidContentProviderConfiguration()}
	 * 
	 * @return AndroidContentProviderConfiguration
	 */
	public AndroidContentProviderConfiguration getAndroidContentProviderConfiguration() {
		return getAndroidProjectConfiguration().getAndroidContentProviderConfiguration();
	}

	/**
	 * {@link AndroidCRUDCreator#getAndroidDatabaseConfiguration()}
	 * 
	 * @return AndroidDatabaseConfiguration
	 */
	public AndroidDatabaseConfiguration getAndroidDatabaseConfiguration() {
		return getAndroidContentProviderConfiguration().getAndroidDatabaseConfiguration();
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
		String contentProviderPackage = getAndroidContentProviderConfiguration().getContentProviderPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + contentProviderPackage);

		// SQLite open helper class package
		String sqliteOpenHelperSubclassPackage = getAndroidDatabaseConfiguration().getSqliteOpenHelperClassPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + sqliteOpenHelperSubclassPackage);

		// Database table package
		String databaseTablesPackage = getAndroidDatabaseConfiguration().getTablesSchemasPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + databaseTablesPackage);

		// table CRUD package
		String tablesCRUDPackage = getAndroidDatabaseConfiguration().getTablesCRUDPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + tablesCRUDPackage);

		// Create table schemas source file and saves it on disk
		createTablesSchemasSourceFile(database, projectStorageDir, databaseTablesPackage);

		// Create the database content provider file and saves it on disk
		createContentProviderSourceFile(projectStorageDir, contentProviderPackage, database);

		// create SQLiteOpenHelper Subclass
		createSQLiteOpenHelperSourceFile(projectStorageDir, sqliteOpenHelperSubclassPackage);

		// create CRUD Operations interface
		createAbstractCRUDOperationsSourceFile(projectStorageDir, tablesCRUDPackage);

		// create table custom CRUD class
		createTableCRUDClassSourceFile(database, projectStorageDir, tablesCRUDPackage);

		// create tables model info classes
		String columnsModelInfoPackage = getAndroidDatabaseConfiguration().getTablesInfosModelClassesPackage();
		createDirectory(projectStorageDir + NativeUtils.getFileSeparator() + columnsModelInfoPackage);
		createTableModelInfoClassesSourceFiles(database, projectStorageDir, columnsModelInfoPackage);
	}

	// create tables model classes
	private void createTableModelInfoClassesSourceFiles(Database database, String projectStorageDir,
			String tablesModelInfoPackage) {

		AndroidTableModelTemplateParser androidTableModelTemplateParser = new AndroidTableModelTemplateParser();
		AndroidJavaObjectDefaultEncapsulationTemplateParser androidJavaObjectDefaultEncapsulationTemplateParser = new AndroidJavaObjectDefaultEncapsulationTemplateParser();

		for (Table table : database.getTables()) {

			String tableClassName = NativeUtils.toJavaBeansClass(table.getTableName());

			// package where this table model info class will be saved
			String tableModelSourceFilePackageFilePath = tablesModelInfoPackage + NativeUtils.getFileSeparator()
					+ tableClassName.toLowerCase();

			// the table model source file
			String tableModelSourceFile = projectStorageDir + NativeUtils.getFileSeparator()
					+ tableModelSourceFilePackageFilePath + NativeUtils.getFileSeparator() + tableClassName
					+ TemplateTags.Android.INFO + AndroidProjectFileNames.JAVA_FILE_EXTENSION;

			String tableModelSourceCode = androidTableModelTemplateParser.createSourceCode(
					androidJavaObjectDefaultEncapsulationTemplateParser,
					NativeUtils.parseJavaPackage(tableModelSourceFilePackageFilePath), table);

			boolean createTableCRUDSourceFile = createSourceFile(tableModelSourceFile, tableModelSourceCode);

			if (createTableCRUDSourceFile) {

				System.out.println("Created " + tableClassName + " Model Info Source File");
			}
		}
	}

	private void createTableCRUDClassSourceFile(Database database, String projectStorageDir, String tablesCRUDPackage) {

		// create table custom CRUD class

		AndroidTableCRUDTemplateParser androidTableCRUDTemplateParser = new AndroidTableCRUDTemplateParser();

		for (Table table : database.getTables()) {

			String packageFilePath = tablesCRUDPackage;

			String tableCRUDSourceCode = androidTableCRUDTemplateParser.createSourceCode(packageFilePath, table);

			String tableClassName = NativeUtils.toJavaBeansClass(table.getTableName());

			String tableCRUDSourceFile = projectStorageDir + NativeUtils.getFileSeparator() + tablesCRUDPackage
					+ NativeUtils.getFileSeparator() + tableClassName + AndroidProjectFileNames.JAVA_FILE_EXTENSION;

			boolean createTableCRUDSourceFile = createSourceFile(tableCRUDSourceFile, tableCRUDSourceCode);

			if (createTableCRUDSourceFile) {
				System.out.println("Created " + tableClassName + " CRUD Source File");
			}
		}

	}

	// create CRUD Operations interface
	private void createAbstractCRUDOperationsSourceFile(String projectStorageDir, String tablesCrudPackage) {

		AndroidCrudOperationsTemplateParser androidCrudOperationsTemplateParser = new AndroidCrudOperationsTemplateParser();

		// Create the source code
		String tablesAbstractCRUDOperationsSourceCode = androidCrudOperationsTemplateParser
				.createSourceCode(tablesCrudPackage);

		String abstractCrudOperationsSourceFile = AndroidProjectFileNames.CRUD_OPERATIONS_INTERFACE_CLASS_NAME;

		// the table CRUD operations source file absolute path
		String tablesAbstractCRUDOperationsSourceFile = projectStorageDir + NativeUtils.getFileSeparator()
				+ tablesCrudPackage + NativeUtils.getFileSeparator() + abstractCrudOperationsSourceFile
				+ AndroidProjectFileNames.JAVA_FILE_EXTENSION;

		// write source code to disk
		boolean createAbstractCRUDOperationsSourceFile = createSourceFile(tablesAbstractCRUDOperationsSourceFile,
				tablesAbstractCRUDOperationsSourceCode);

		if (createAbstractCRUDOperationsSourceFile) {
			System.out.println("Created Abstract CRUD Operations Source File");
		}
	}

	// create SQLiteOpenHelper Subclass
	private void createSQLiteOpenHelperSourceFile(String projectStorageDir, String sqliteOpenHelperSubclassPackage) {

		AndroidSQLiteOpenHelperTemplateParser androidSQLiteOpenHelperTemplateParser = new AndroidSQLiteOpenHelperTemplateParser();

		// SQLite open helper class
		String sqliteOpenHelperSubclass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getSqliteOpenHelperClass();

		// Create source code
		String sqliteOpenHelperSubclassSourceCode = androidSQLiteOpenHelperTemplateParser
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
	private void createContentProviderSourceFile(String projectStorageDir, String contentProviderPackage,
			Database database) {

		AndroidContentProvidersTemplatesParser androidContentProvidersTemplatesParser = new AndroidContentProvidersTemplatesParser();

		// Content provide class name
		String contentProviderClass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getContentProviderClass();

		String contentProviderSourceCode = androidContentProvidersTemplatesParser
				.createContentProviderSourceFile(getAndroidProjectConfiguration(), database.getTables());

		String contentProviderSourceCodeFile = projectStorageDir + NativeUtils.getFileSeparator()
				+ contentProviderPackage + NativeUtils.getFileSeparator() + contentProviderClass
				+ AndroidProjectFileNames.JAVA_FILE_EXTENSION;

		boolean createContentProvidersSourceFile = createSourceFile(contentProviderSourceCodeFile,
				contentProviderSourceCode);

		if (createContentProvidersSourceFile) {
			System.out.println("Created Content providers Source File");
		}
	}

	/**
	 * AndroidCRUDCreator#createTablesSchemasSourceFile
	 * 
	 * Creates all the source code for all the tables schemas and saves the
	 * source code to the disk.
	 * 
	 * @param database
	 * @param projectStorageDir
	 * @param databaseTablesPackage
	 */
	private void createTablesSchemasSourceFile(Database database, String projectStorageDir,
			String databaseTablesPackage) {

		AndroidTableSchemasTemplatesParser androidTableSchemasTemplatesParser = new AndroidTableSchemasTemplatesParser();

		String tablesSchemasSourceCode = androidTableSchemasTemplatesParser
				.createTablesSchemas(getAndroidProjectConfiguration(), database.getTables());
		String tablesSchemasAbsoluteSourceFile = projectStorageDir + NativeUtils.getFileSeparator()
				+ databaseTablesPackage + NativeUtils.getFileSeparator()
				+ AndroidProjectFileNames.TABLE_SCHEMAS_FILE_NAME;
		boolean createTablesSchemasSourceFile = createSourceFile(tablesSchemasAbsoluteSourceFile,
				tablesSchemasSourceCode);

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
