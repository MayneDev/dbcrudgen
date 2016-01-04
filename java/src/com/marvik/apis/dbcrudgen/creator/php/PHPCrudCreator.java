package com.marvik.apis.dbcrudgen.creator.php;

import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.io.FileStreamWriter;
import com.marvik.apis.dbcrudgen.parser.PHPTemplatesParser;
import com.marvik.apis.dbcrudgen.projects.php.PHPProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;
import com.marvik.apis.dbcudgen.java.templates.php.PHPTableClassCrudTemplate;

public class PHPCrudCreator extends CrudCreator {

	/**
	 * File Stream Writer
	 */
	private FileStreamWriter fileStreamWriter;

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
		phpTableClassCrudTemplate = new PHPTableClassCrudTemplate();
		phpTemplatesParser = new PHPTemplatesParser();

		fileStreamWriter = new FileStreamWriter();

	}

	/**
	 * Returns the Crud Template
	 */
	@Override
	public CrudTemplates getCrudTemplate() {
		return phpTableClassCrudTemplate;
	}

	/**
	 * 
	 * @param table
	 * @return the generated crud for the passed table
	 */
	public String getTableCrud(Table table) {
		return phpTemplatesParser.getTableCrud(table);
	}

	/**
	 * 
	 * @return File Stream Writer
	 */
	public FileStreamWriter getFileStreamWriter() {
		return fileStreamWriter;
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
	 * Creates A Project Create Core Database Scripts Create table specific CRUD
	 * Script
	 */
	public void createProject(Database database) {

		if (getProjectDatabaseConnectionProperties() == null) {
			throw new NullPointerException("Project Database Connection Properties Cannot be null");
		}
		if (getPhpProjectConfiguration() == null) {
			throw new NullPointerException("Php Project Configuration Cannot be null");
		}
		
		// Create Database Connection Scripts
		createDatabaseConnectionScriptsDirectory(
				getPhpProjectConfiguration().getPhpDatabaseAPIScriptsStorageDirectory());

		// Create Project Storage Directory
		createProjectStorageDirectory(getPhpProjectConfiguration().getProjectStorageDirectory());

		// Create Project CRUD Scripts Storage Directory
		createProjectCRUDScriptsStorageDirectory(getPhpProjectConfiguration().getCrudScriptsStorageDirectory());

		// Create Database Data Actions Scripts
		createProjectDatabasesActionsDirectory(getPhpProjectConfiguration().getPhpDatabaseAPIScriptsStorageDirectory());

		// Create Database Utilities Script
		createProjectPHPDatabaseAPIScriptsStorageDirectory(
				getPhpProjectConfiguration().getPhpDatabaseAPIScriptsStorageDirectory());

		// Create Table CRUD
		for (Table table : database.getTables()) {
			String tablesCrud = getTableCrud(table);
		}
	}

	/**
	 * Create Database Connection Scripts Directory
	 * 
	 * @param phpDatabaseAPIScriptsStorageDirectory
	 * @return PHP Database Connection Scripts Directory
	 */
	private String createDatabaseConnectionScriptsDirectory(String phpDatabaseAPIScriptsStorageDirectory) {
		getFileStreamWriter().createDirectories(phpDatabaseAPIScriptsStorageDirectory);
		return phpDatabaseAPIScriptsStorageDirectory;

	}

	/**
	 * Create Project Storage Directory
	 * 
	 * @param projectStorageDirectory
	 * @return Project Storage Directory
	 */
	private String createProjectStorageDirectory(String projectStorageDirectory) {
		getFileStreamWriter().createDirectories(projectStorageDirectory);
		return projectStorageDirectory;
	}

	/**
	 * Create Project CRUD Scripts Storage Directory
	 * 
	 * @param crudScriptsStorageDirectory
	 * @return Project CRUD Scripts Storage Directory
	 */
	private String createProjectCRUDScriptsStorageDirectory(String crudScriptsStorageDirectory) {
		getFileStreamWriter().createDirectories(crudScriptsStorageDirectory);
		return crudScriptsStorageDirectory;
	}

	/**
	 * Create Project Databases Actions Directory
	 * 
	 * @param phpDatabaseAPIScriptsStorageDirectory
	 * @return Project Databases Actions Directory
	 */
	private String createProjectDatabasesActionsDirectory(String phpDatabaseAPIScriptsStorageDirectory) {
		getFileStreamWriter().createDirectories(phpDatabaseAPIScriptsStorageDirectory);
		return phpDatabaseAPIScriptsStorageDirectory;
	}

	/**
	 * Create Project PHP Database API Scripts Storage Directory
	 * 
	 * @param phpDatabaseAPIScriptsStorageDirectory
	 * @return Project PHP Database API Scripts Storage Directory
	 */
	private String createProjectPHPDatabaseAPIScriptsStorageDirectory(String phpDatabaseAPIScriptsStorageDirectory) {
		getFileStreamWriter().createDirectories(phpDatabaseAPIScriptsStorageDirectory);
		return phpDatabaseAPIScriptsStorageDirectory;
	}
}
