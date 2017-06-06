package com.marvik.apis.dbcrudgen.projects.php.configuration;

import com.marvik.apis.dbcrudgen.projects.ProjectConfiguration;

public class PHPProjectConfiguration extends ProjectConfiguration {

	private String lowLevelCrudScriptsStorageDirectory;
	private String highLevelCrudScriptsStorageDirectory;
	private String sqlScriptsStorageDirectory;

	private String phpDatabaseAPIScriptsStorageDirectory;

	/**
	 * Project Configuration Properties
	 * 
	 * @param projectName
	 */
	public PHPProjectConfiguration(String projectName, String sqlScriptsStorageDirectory,
			String phpDatabaseAPIScriptsStorageDirectory) {
		super(projectName);
	}

	/**
	 * Project Configuration Properties
	 * 
	 * @param projectName
	 */
	@Deprecated
	public PHPProjectConfiguration(String projectName, String sqlScriptsStorageDirectory) {
		super(projectName);
	}

	/**
	 * Project Configuration Properties
	 * 
	 * @param projectName
	 */
	public PHPProjectConfiguration(String projectName) {
		super(projectName);
	}

	/**
	 * Project PHP Table Low Level CRUD Scripts Storage Directory This is the
	 * folder where the PHP Database CRUD Scripts will be saved
	 * 
	 * @param lowLevelCrudScriptsStorageDirectory
	 */
	public void setProjectPHPTableCrudLowLevelScriptsStorageDirectory(String lowLevelCrudScriptsStorageDirectory) {
		this.lowLevelCrudScriptsStorageDirectory = lowLevelCrudScriptsStorageDirectory;
	}

	/**
	 * Project PHP Database API Scripts Storage Directory This is the folder
	 * where the PHP Database API Scripts will be saved
	 * 
	 * @param phpDatabaseAPIScriptsStorageDirectory
	 */
	public void setProjectPHPDatabaseAPIScriptsStorageDirectory(String phpDatabaseAPIScriptsStorageDirectory) {
		this.phpDatabaseAPIScriptsStorageDirectory = phpDatabaseAPIScriptsStorageDirectory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.projects.ProjectConfiguration#
	 * setProjectStorageDirectory(java.lang.String)
	 */
	@Override
	public void setProjectStorageDirectory(String projectStorageDirectory) {
		super.setProjectStorageDirectory(projectStorageDirectory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.projects.ProjectConfiguration#
	 * getProjectStorageDirectory()
	 */
	@Override
	public String getProjectStorageDirectory() {
		return super.getProjectStorageDirectory();
	}

	/**
	 * 
	 * @return Project PHP Database CRUD Scripts Storage Directory
	 */
	public String getLowLevelCrudScriptsStorageDirectory() {
		return lowLevelCrudScriptsStorageDirectory;
	}

	/**
	 * 
	 * @return Project PHP Database API Scripts Storage Directory
	 */
	public String getPhpDatabaseAPIScriptsStorageDirectory() {
		return phpDatabaseAPIScriptsStorageDirectory;
	}

	/**
	 * @return the sqlScriptsStorageDirectory
	 */
	public String getProjectSQLScriptsStorageDirectory() {
		return sqlScriptsStorageDirectory;
	}

	/**
	 * @param sqlScriptsStorageDirectory
	 *            the sqlScriptsStorageDirectory to set
	 */
	public void setProjectSQLScriptsStorageDirectory(String sqlScriptsStorageDirectory) {
		this.sqlScriptsStorageDirectory = sqlScriptsStorageDirectory;
	}

	/**
	 * Project PHP Table High Level CRUD Scripts Storage Directory This is the
	 * folder where the PHP Database CRUD Scripts will be saved
	 * 
	 * @param highLevelCrudScriptsStorageDirectory
	 */
	public void setProjectPHPTableCrudHighLevelScriptsStorageDirectory(String highLevelCrudScriptsStorageDirectory) {
		this.highLevelCrudScriptsStorageDirectory = highLevelCrudScriptsStorageDirectory;

	}

	/**
	 * 
	 * @return HighLevelCrudScriptsStorageDirectory
	 */
	public String getHighLevelCrudScriptsStorageDirectory() {
		return highLevelCrudScriptsStorageDirectory;
	}
}
