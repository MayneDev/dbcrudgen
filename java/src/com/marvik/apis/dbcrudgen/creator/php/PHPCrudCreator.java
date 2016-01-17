package com.marvik.apis.dbcrudgen.creator.php;

import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.io.FilesHandler;
import com.marvik.apis.dbcrudgen.parser.php.PHPTemplatesParser;
import com.marvik.apis.dbcrudgen.projects.php.configuration.PHPProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.php.filenames.PHPProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.php.crud.classcrud.PHPTableClassCrudTemplate;

public class PHPCrudCreator extends CrudCreator {

	/**
	 * PHP Table Class Crud Template
	 */
	private PHPTableClassCrudTemplate phpTableClassCrudTemplate;

	/**
	 * PHP Templates Parser
	 */
	private PHPTemplatesParser phpTemplatesParser;

	/**
	 * Project Database Connection Properties
	 */
	private ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties;

	/**
	 * PHP Project Configuration
	 */
	private PHPProjectConfiguration phpProjectConfiguration;

	/**
	 * PHP Crud Template
	 */
	public PHPCrudCreator() {
		super();
		phpTableClassCrudTemplate = new PHPTableClassCrudTemplate();
		phpTemplatesParser = new PHPTemplatesParser();
	}

	/**
	 * Returns the Crud Template
	 */
	@Deprecated
	@Override
	public CrudTemplates getCrudTemplate() {
		return phpTableClassCrudTemplate;
	}

	/**
	 * PHPTemplatesParser getPhpTemplatesParser
	 * 
	 * @return PHPTemplatesParser
	 */
	public PHPTemplatesParser getPHPTemplatesParser() {
		return phpTemplatesParser;
	}

	/**
	 * 
	 * @param table
	 * @return the generated crud for the passed table
	 */
	public String getTableCrud(Table table) {
		return getPHPTemplatesParser().getTableCrud(phpProjectConfiguration, projectDatabaseConnectionProperties,
				table);
	}

	/**
	 * Sets the php project configuration for the project
	 * 
	 * @param phpProjectConfiguration
	 */
	public void setProjectConfiguration(PHPProjectConfiguration phpProjectConfiguration) {
		this.phpProjectConfiguration = phpProjectConfiguration;
	}

	/**
	 * PHP Project Configuration
	 * 
	 * @return phpProjectConfiguration
	 */
	public PHPProjectConfiguration getPhpProjectConfiguration() {
		return phpProjectConfiguration;
	}

	/**
	 * Set the Project Database Connection Properties
	 * 
	 * @param projectDatabaseConnectionProperties
	 */
	public void setProjectDatabaseConnectionProperties(
			ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties) {
		this.projectDatabaseConnectionProperties = projectDatabaseConnectionProperties;
	}

	/**
	 * Project Database Connection Properties
	 * 
	 * @return projectDatabaseConnectionProperties
	 */
	public ProjectDatabaseConnectionProperties getProjectDatabaseConnectionProperties() {
		return projectDatabaseConnectionProperties;
	}

	/**
	 * 1. Creates A Project and all the relevant directories 2. Creates Core
	 * Database Scripts 3. Creates table specific CRUD Script
	 */
	public void createProject(Database database) {

		if (getProjectDatabaseConnectionProperties() == null) {
			throw new NullPointerException("Project Database Connection Properties Cannot be null");
		}
		if (getPhpProjectConfiguration() == null) {
			throw new NullPointerException("Php Project Configuration Cannot be null");
		}

		// Creates all the scripts storage directories.
		createSciptsDirectories();

		// create all the project (required and generated) scripts
		createAllProjectScripts(database);

	}

	/**
	 * Create all the project scripts
	 */
	private void createAllProjectScripts(Database database) {

		// Create all the core required scripts

		// Create the data action script
		createDataActionsSciptFile();

		// Create the database connection script
		createDatabaseConnectionScriptFile();

		// create the database utils script file
		createDatabaseUtilsScriptFile();

		/*
		 * Create all the generated scripts -> Create all table crud -> create
		 * all table sql
		 * 
		 */

		// Create Table CRUD
		for (Table table : database.getTables()) {
			writeTableScriptsToDisk(table);
		}
	}

	/**
	 * Create Database Utils Script File
	 */
	private void createDatabaseUtilsScriptFile() {

		String databaseConnectionScriptFilePath = getDatabaseConnectionScriptAbsoluteFilename();

		String databaseUtilsScript = getPHPTemplatesParser().parseDatabaseUtilsScriptTemplate(
				getProjectDatabaseConnectionProperties(), databaseConnectionScriptFilePath);

		String databaseUtilsScriptsStorageDirectory = getPhpProjectConfiguration()
				.getPhpDatabaseAPIScriptsStorageDirectory();
		String databaseUtilsScriptsFile = databaseUtilsScriptsStorageDirectory
				+ PHPProjectFileNames.DATABASE_UTILS_SCRIPT_FILENAME;

		if (!getFilesHandler().createByteWeighedFile(databaseUtilsScriptsFile, databaseUtilsScript)) {
			System.out.println("Could not create File[" + databaseUtilsScriptsFile + "]");
		} else {
			System.out.println("File Successfully created [" + databaseUtilsScriptsFile + "]");
		}

	}

	/**
	 * Create Database Connection Script File
	 */
	private void createDatabaseConnectionScriptFile() {

		String template = getPHPTemplatesParser().getPhpDatabaseConnectionTemplate().getTemplate();

		String databaseConnectionScriptFile = getDatabaseConnectionScriptAbsoluteFilename();

		if (!getFilesHandler().createByteWeighedFile(databaseConnectionScriptFile, template)) {
			System.out.println("Could not create File[" + databaseConnectionScriptFile + "]");
		} else {
			System.out.println("File Successfully created [" + databaseConnectionScriptFile + "]");
		}
	}

	/**
	 * Get
	 * 
	 * @return Database Connection Script Absolute Filename
	 */
	private String getDatabaseConnectionScriptAbsoluteFilename() {
		String databaseConnectionScriptStorageDirectory = getPhpProjectConfiguration()
				.getPhpDatabaseAPIScriptsStorageDirectory();
		return databaseConnectionScriptStorageDirectory + PHPProjectFileNames.DATABASE_CONNECTION_SCRIPT_FILENAME;
	}

	/**
	 * Create Data Actions SciptFile
	 */
	private void createDataActionsSciptFile() {

		String template = getPHPTemplatesParser().createDataActionsTemplate();
		String dataActionsFilename = getPhpProjectConfiguration().getPhpDatabaseAPIScriptsStorageDirectory()
				+ PHPProjectFileNames.DATA_ACTIONS_SCRIPT_FILENAME;

		if (!getFilesHandler().createByteWeighedFile(dataActionsFilename, template)) {
			System.out.println("Could not create File[" + dataActionsFilename + "]");
		} else {
			System.out.println("File Successfully created [" + dataActionsFilename + "]");

		}

	}

	/**
	 * Writes all the table scripts to disk
	 * 
	 * @param table
	 */
	private void writeTableScriptsToDisk(Table table) {

		String tablesCrudScriptsStorageDirectory = getPhpProjectConfiguration().getCrudScriptsStorageDirectory();
		String tablesSQLScriptsStorageDirectory = getPhpProjectConfiguration().getProjectSQLScriptsStorageDirectory();

		String tableName = table.getTableName();
		String tableSQL = table.getTableSql();
		String tablesCrud = getTableCrud(table);

		String className = getPHPTemplatesParser().parseJavaBeansClassName(tableName);
		String phpClassFileName = getPHPTemplatesParser().parsePHPClassFileName(tablesCrudScriptsStorageDirectory,
				className);

		String sqlTableFileName = getPHPTemplatesParser().parseSQLFileName(tablesSQLScriptsStorageDirectory, tableName);

		if (!getFilesHandler().createByteWeighedFile(phpClassFileName, tablesCrud)) {
			System.out.println("Could not create File[" + phpClassFileName + "]");
		} else {
			System.out.println("File Successfully created [" + phpClassFileName + "]");
		}

		if (!getFilesHandler().createByteWeighedFile(sqlTableFileName, tableSQL)) {
			System.out.println("Could not create File[" + sqlTableFileName + "]");
		} else {

			System.out.println("File Successfully created [" + sqlTableFileName + "]");

		}

	}

	/**
	 * Create directories for saving all the required/generated scripts
	 */
	private void createSciptsDirectories() {
		// Create Database Connection Scripts Storage Directory
		createDatabaseConnectionScriptsDirectory(
				getPhpProjectConfiguration().getPhpDatabaseAPIScriptsStorageDirectory());

		// Create Project Storage Directory
		createProjectStorageDirectory(getPhpProjectConfiguration().getProjectStorageDirectory());

		// Create Project CRUD Scripts Storage Directory
		createProjectCRUDScriptsStorageDirectory(getPhpProjectConfiguration().getCrudScriptsStorageDirectory());

		// Create Database Data Actions Scripts Storage Directory
		createProjectDatabasesActionsDirectory(getPhpProjectConfiguration().getPhpDatabaseAPIScriptsStorageDirectory());

		// Create SQL Scripts Storage Directory
		createProjectDatabasesActionsDirectory(getPhpProjectConfiguration().getProjectSQLScriptsStorageDirectory());

		// Create Database Utilities Script Storage Directory
		createProjectPHPDatabaseAPIScriptsStorageDirectory(
				getPhpProjectConfiguration().getPhpDatabaseAPIScriptsStorageDirectory());
	}

	/**
	 * Create Database Connection Scripts Directory
	 * 
	 * @param phpDatabaseAPIScriptsStorageDirectory
	 * @return PHP Database Connection Scripts Directory
	 */
	private String createDatabaseConnectionScriptsDirectory(String phpDatabaseAPIScriptsStorageDirectory) {
		getFilesHandler().createDirectories(phpDatabaseAPIScriptsStorageDirectory);
		return phpDatabaseAPIScriptsStorageDirectory;

	}

	/**
	 * Create Project Storage Directory
	 * 
	 * @param projectStorageDirectory
	 * @return Project Storage Directory
	 */
	private String createProjectStorageDirectory(String projectStorageDirectory) {
		getFilesHandler().createDirectories(projectStorageDirectory);
		return projectStorageDirectory;
	}

	/**
	 * Create Project CRUD Scripts Storage Directory
	 * 
	 * @param crudScriptsStorageDirectory
	 * @return Project CRUD Scripts Storage Directory
	 */
	private String createProjectCRUDScriptsStorageDirectory(String crudScriptsStorageDirectory) {
		getFilesHandler().createDirectories(crudScriptsStorageDirectory);
		return crudScriptsStorageDirectory;
	}

	/**
	 * Create Project Databases Actions Directory
	 * 
	 * @param phpDatabaseAPIScriptsStorageDirectory
	 * @return Project Databases Actions Directory
	 */
	private String createProjectDatabasesActionsDirectory(String phpDatabaseAPIScriptsStorageDirectory) {
		getFilesHandler().createDirectories(phpDatabaseAPIScriptsStorageDirectory);
		return phpDatabaseAPIScriptsStorageDirectory;
	}

	/**
	 * Create Project PHP Database API Scripts Storage Directory
	 * 
	 * @param phpDatabaseAPIScriptsStorageDirectory
	 * @return Project PHP Database API Scripts Storage Directory
	 */
	private String createProjectPHPDatabaseAPIScriptsStorageDirectory(String phpDatabaseAPIScriptsStorageDirectory) {
		getFilesHandler().createDirectories(phpDatabaseAPIScriptsStorageDirectory);
		return phpDatabaseAPIScriptsStorageDirectory;
	}
}
