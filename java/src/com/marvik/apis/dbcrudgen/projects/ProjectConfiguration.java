package com.marvik.apis.dbcrudgen.projects;

public abstract class ProjectConfiguration {

	
	/**
	 * Project Storage Directory
	 */
	protected String projectStorageDirectory;

	/**
	 * Name of the project
	 */
	private String projectName;
	
	/**
	 * Project Configuration
	 */
	public ProjectConfiguration (String projectName){
		this.projectName = projectName;
	}
	/**
	 * Returns the project storage directory
	 * @return projectStorageDirectory
	 */
	public String getProjectStorageDirectory() {
		return projectStorageDirectory;
	}

	/**
	 * Sets the project storage directory
	 * @param projectStorageDirectory
	 */
	public void setProjectStorageDirectory(String projectStorageDirectory) {
		this.projectStorageDirectory = projectStorageDirectory;
	}

	/**
	 * 
	 * @return Project Name
	 */
	public String getProjectName() {
		return projectName;
	}
	
}
