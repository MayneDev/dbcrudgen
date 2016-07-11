package com.marvik.apis.dbcrudgen.creator.android;

import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.parser.android.contentprovider.AndroidContentProvidersTemplatesParser;
import com.marvik.apis.dbcrudgen.parser.android.crudoperations.AndroidCrudOperationsTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.database.transactions.AndroidDatabaseTransactionsParser;
import com.marvik.apis.dbcrudgen.parser.android.javaobjects.AndroidJavaObjectDefaultEncapsulationTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.sqliteopenhelper.AndroidSQLiteOpenHelperTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.tablecrud.AndroidTableCRUDTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.tablemodel.AndroidTableModelTemplateParser;
import com.marvik.apis.dbcrudgen.parser.android.tableschemas.AndroidTableSchemasTemplatesParser;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.transactions.TransactionManagerConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassTableCrudTemplate;
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

		String packageName = getAndroidProjectConfiguration().getPackageName();

		// Project storage directory
		String projectStorageDir = getAndroidProjectConfiguration().getProjectStorageDir();
		createDirectory(projectStorageDir);

		// Project java files storage directory
		String javaSrcDir = getAndroidProjectConfiguration().getJavaSrcDir();
		String projectFilesStorageDir = projectStorageDir + NativeUtils.getFileSeparator() + javaSrcDir;
		createDirectory(projectFilesStorageDir);

		// Project files default Storage Directory
		String projectFilesDefaultStorageDirectory = getAndroidProjectConfiguration()
				._getProjectDefaultJavaFilesRootStorageLocation();
		createDirectory(projectFilesDefaultStorageDirectory);

		// Content provider class package
		String contentProviderPackage = getAndroidContentProviderConfiguration().getProviderConfiguration()
				.getContentProviderPackage();
		createDirectory(projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator() + contentProviderPackage);

		// SQLite open helper class package
		String sqliteOpenHelperSubclassPackage = getAndroidDatabaseConfiguration().getSqliteOpenHelperClassPackage();
		createDirectory(
				projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator() + sqliteOpenHelperSubclassPackage);

		// Database table package
		String tablesSchemasStorageLocation = getAndroidDatabaseConfiguration().getTablesSchemasPackage();
		createDirectory(
				projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator() + tablesSchemasStorageLocation);

		// table CRUD storage location and package name
		String tablesCRUDStorageLocation = getAndroidDatabaseConfiguration().getTablesCRUDPackage();
		createDirectory(
				projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator() + tablesCRUDStorageLocation);

		// Create table schemas source file and saves it on disk
		createTablesSchemasSourceFile(database, projectFilesDefaultStorageDirectory, tablesSchemasStorageLocation);

		// Create the database content provider file and saves it on disk
		createContentProviderSourceFile(projectFilesDefaultStorageDirectory, contentProviderPackage, database);

		// create SQLiteOpenHelper Subclass
		createSQLiteOpenHelperSourceFile(projectFilesDefaultStorageDirectory, sqliteOpenHelperSubclassPackage);

		// create CRUD Operations interface

		createAbstractCRUDOperationsSourceFile(projectFilesDefaultStorageDirectory, tablesCRUDStorageLocation);
		// create table custom CRUD class
		String tablesSchemasPackage = packageName + NativeTemplateTags.DOT
				+ NativeUtils.parseJavaPackage(tablesSchemasStorageLocation);
		createTableCRUDClassSourceFile(database, projectFilesDefaultStorageDirectory, packageName, tablesSchemasPackage,
				tablesCRUDStorageLocation);

		// create tables model info classes
		String columnsModelInfoPackage = getAndroidDatabaseConfiguration().getTablesInfosModelClassesPackage();
		createDirectory(projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator() + columnsModelInfoPackage);
		createTableModelInfoClassesSourceFiles(database, projectFilesDefaultStorageDirectory, packageName,
				columnsModelInfoPackage);
		
		// create transactions manager class
		TransactionManagerConfiguration transactionManagerConfiguration = getAndroidContentProviderConfiguration()
				.getTransactionManagerConfiguration();
		//createTransactionManagerClass(packageName, transactionManagerConfiguration, projectFilesDefaultStorageDirectory,
				//tablesCRUDStorageLocation, database.getTables());
	}

	// create transactions manager class
	private void createTransactionManagerClass(String packageName,
			TransactionManagerConfiguration transactionManagerConfiguration, String projectStorageDirectory,
			String tablesCRUDStorageLocation, Table[] tables) {

		String transactionManagerPackage = transactionManagerConfiguration.getTransactionManagerPackage();
		String transactionManagerClass = transactionManagerConfiguration.getTransactionManagerClass();

		String transactionManagerPackageLocation = projectStorageDirectory + NativeUtils.getFileSeparator()
				+ transactionManagerPackage;
		createDirectory(transactionManagerPackageLocation);

		AndroidDatabaseTransactionsParser transactionsParser = new AndroidDatabaseTransactionsParser();
		String transactionManagerSourceCode = transactionsParser.createSourceCode(packageName,
				transactionManagerPackage, transactionManagerClass, tablesCRUDStorageLocation, tables);

		String customTransactionManageClass = transactionManagerConfiguration.getTransactionManagerClass();

		String transactionManagerSourceFile = projectStorageDirectory + NativeUtils.getFileSeparator()
				+ transactionManagerPackage + NativeUtils.getFileSeparator() + customTransactionManageClass
				+ AndroidProjectFileNames.JAVA_FILE_EXTENSION;

		createSourceFile(transactionManagerSourceFile, transactionManagerSourceCode);
	}

	// create tables model classes
	private void createTableModelInfoClassesSourceFiles(Database database, String projectFilesDefaultStorageDirectory,
			String packageName, String tablesModelInfoPackage) {

		AndroidTableModelTemplateParser androidTableModelTemplateParser = new AndroidTableModelTemplateParser();
		
		AndroidJavaObjectDefaultEncapsulationTemplateParser androidJavaObjectDefaultEncapsulationTemplateParser = new AndroidJavaObjectDefaultEncapsulationTemplateParser();

		for (Table table : database.getTables()) {

			String tableClassName = NativeUtils.toJavaBeansClass(table.getTableName());

			// package where this table model info class will be saved
			String tableModelSourceFilePackageFilePath = tablesModelInfoPackage + NativeUtils.getFileSeparator()
					+ tableClassName.toLowerCase();

			// the table model source file
			String tableModelSourceFile = projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator()
					+ tableModelSourceFilePackageFilePath + NativeUtils.getFileSeparator() + tableClassName
					+ TemplateTags.Android.INFO + AndroidProjectFileNames.JAVA_FILE_EXTENSION;

			String tableModelSourceCode = androidTableModelTemplateParser.createSourceCode(
					androidJavaObjectDefaultEncapsulationTemplateParser,
					packageName + NativeTemplateTags.DOT
							+ NativeUtils.parseJavaPackage(tableModelSourceFilePackageFilePath), // Package
																									// name
					table);

			createSourceFile(tableModelSourceFile, tableModelSourceCode);

		}
	}

	private void createTableCRUDClassSourceFile(Database database, String projectFilesDefaultStorageDirectory,
			String packageName, String tablesSchemasPackage, String tableCRUDPackage) {

		// create table custom CRUD class

		AndroidTableCRUDTemplateParser androidTableCRUDTemplateParser = new AndroidTableCRUDTemplateParser();

		for (Table table : database.getTables()) {

			String tableModelsPackage = packageName + NativeTemplateTags.DOT
					+ getAndroidDatabaseConfiguration().getTablesInfosModelClassesPackage() + NativeTemplateTags.DOT
					+ table.getTableName();
			String tableCrudSourceFilePackage = packageName + NativeTemplateTags.DOT + tableCRUDPackage;
			String tableCRUDSourceCode = androidTableCRUDTemplateParser.createSourceCode(tableCrudSourceFilePackage,
					tablesSchemasPackage, tableModelsPackage, table);

			String tableClassName = NativeUtils.toJavaBeansClass(table.getTableName());

			String tableCRUDSourceFile = projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator()
					+ tableCRUDPackage + NativeUtils.getFileSeparator() + tableClassName
					+ AndroidProjectFileNames.JAVA_FILE_EXTENSION;

			createSourceFile(tableCRUDSourceFile, tableCRUDSourceCode);

		}

	}

	// create CRUD Operations interface
	private void createAbstractCRUDOperationsSourceFile(String projectFilesDefaultStorageDirectory,
			String tablesCrudPackage) {

		AndroidCrudOperationsTemplateParser androidCrudOperationsTemplateParser = new AndroidCrudOperationsTemplateParser();

		// Create the source code
		String tablesAbstractCRUDOperationsSourceCode = androidCrudOperationsTemplateParser
				.createSourceCode(getAndroidProjectConfiguration());

		String abstractCrudOperationsSourceFile = AndroidProjectFileNames.CRUD_OPERATIONS_INTERFACE_CLASS_NAME;

		// the table CRUD operations source file absolute path
		String tablesAbstractCRUDOperationsSourceFile = projectFilesDefaultStorageDirectory
				+ NativeUtils.getFileSeparator() + tablesCrudPackage + NativeUtils.getFileSeparator()
				+ abstractCrudOperationsSourceFile + AndroidProjectFileNames.JAVA_FILE_EXTENSION;

		// write source code to disk
		createSourceFile(tablesAbstractCRUDOperationsSourceFile, tablesAbstractCRUDOperationsSourceCode);
	}

	// create SQLiteOpenHelper Subclass
	private void createSQLiteOpenHelperSourceFile(String projectFilesDefaultStorageDirectory,
			String sqliteOpenHelperSubclassPackage) {

		AndroidSQLiteOpenHelperTemplateParser androidSQLiteOpenHelperTemplateParser = new AndroidSQLiteOpenHelperTemplateParser();

		// SQLite open helper class
		String sqliteOpenHelperSubclass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getSqliteOpenHelperClass();

		// Create source code
		String sqliteOpenHelperSubclassSourceCode = androidSQLiteOpenHelperTemplateParser
				.createSQLiteOpenHelperSubclassSourceCode(androidProjectConfiguration);

		// Create source code file absolute path
		String sQLiteOpenHelperSourceFile = projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator()
				+ sqliteOpenHelperSubclassPackage + NativeUtils.getFileSeparator() + sqliteOpenHelperSubclass
				+ AndroidProjectFileNames.JAVA_FILE_EXTENSION;

		// write source code to disk
		createSourceFile(sQLiteOpenHelperSourceFile, sqliteOpenHelperSubclassSourceCode);
	}

	// Create the database content provider file and saves it on disk
	private void createContentProviderSourceFile(String projectFilesDefaultStorageDirectory,
			String contentProviderPackage, Database database) {

		AndroidContentProvidersTemplatesParser androidContentProvidersTemplatesParser = new AndroidContentProvidersTemplatesParser();

		// Content provide class name
		String contentProviderClass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getProviderConfiguration().getContentProviderClass();

		String contentProviderSourceCode = androidContentProvidersTemplatesParser
				.createContentProviderSourceFile(getAndroidProjectConfiguration(), database.getTables());

		String contentProviderSourceCodeFile = projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator()
				+ contentProviderPackage + NativeUtils.getFileSeparator() + contentProviderClass
				+ AndroidProjectFileNames.JAVA_FILE_EXTENSION;

		createSourceFile(contentProviderSourceCodeFile, contentProviderSourceCode);
	}

	/**
	 * AndroidCRUDCreator#createTablesSchemasSourceFile
	 * 
	 * Creates all the source code for all the tables schemas and saves the
	 * source code to the disk.
	 * 
	 * @param database
	 * @param projectFilesDefaultStorageDirectory
	 * @param databaseTablesPackage
	 */
	private void createTablesSchemasSourceFile(Database database, String projectFilesDefaultStorageDirectory,
			String databaseTablesPackage) {

		AndroidTableSchemasTemplatesParser androidTableSchemasTemplatesParser = new AndroidTableSchemasTemplatesParser();

		String tablesSchemasSourceCode = androidTableSchemasTemplatesParser
				.createTablesSchemas(getAndroidProjectConfiguration(), database.getTables());
		String tablesSchemasAbsoluteSourceFile = projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator()
				+ databaseTablesPackage + NativeUtils.getFileSeparator()
				+ AndroidProjectFileNames.TABLE_SCHEMAS_FILE_NAME;
		
		createSourceFile(tablesSchemasAbsoluteSourceFile, tablesSchemasSourceCode);
	}
}
