package com.marvik.apis.dbcrudgen.projects.php;

import com.marvik.apis.dbcrudgen.projects.ProjectConfiguration;

public class PHPProjectConfiguration extends ProjectConfiguration {

	private String crudScriptsStorageDirectory;
	private String phpDatabaseAPIScriptsStorageDirectory;

	/**
	 * Project Configuration Properties
	 * 
	 * @param projectName
	 */
	public PHPProjectConfiguration(String projectName) {
		super(projectName);
	}

	/**
	 * Project PHP Database CRUD Scripts Storage Directory This is the folder
	 * where the PHP Database CRUD Scripts will be saved
	 * 
	 * @param crudScriptsStorageDirectory
	 */
	public void setProjectCRUDScriptsStorageDirectory(String crudScriptsStorageDirectory) {

	}

	/**
	 * Project PHP Database API Scripts Storage Directory This is the folder
	 * where the PHP Database API Scripts will be saved
	 * 
	 * @param phpDatabaseAPIScriptsStorageDirectory
	 */
	public void setProjectPHPDatabaseAPIScriptsStorageDirectory(String phpDatabaseAPIScriptsStorageDirectory) {

	}

	/*
	 * (non-Javadoc)
	 * @see com.marvik.apis.dbcrudgen.projects.ProjectConfiguration#setProjectStorageDirectory(java.lang.String)
	 */
	@Override
	public void setProjectStorageDirectory(String projectStorageDirectory) {
		super.setProjectStorageDirectory(projectStorageDirectory);
	}

	/*
	 * (non-Javadoc)
	 * @see com.marvik.apis.dbcrudgen.projects.ProjectConfiguration#getProjectStorageDirectory()
	 */
	@Override
	public String getProjectStorageDirectory() {
		return super.getProjectStorageDirectory();
	}

	/**
	 * 
	 * @return Project PHP Database CRUD Scripts Storage Directory
	 */
	public String getCrudScriptsStorageDirectory() {
		return crudScriptsStorageDirectory;
	}

	/**
	 * 
	 * @return Project PHP Database API Scripts Storage Directory
	 */
	public String getPhpDatabaseAPIScriptsStorageDirectory() {
		return phpDatabaseAPIScriptsStorageDirectory;
	}

}
