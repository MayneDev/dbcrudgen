package com.marvik.apis.dbcrudgen.projects;

import java.io.File;

import com.marvik.apis.dbcrudgen.io.FileStreamWriter;

public abstract class ProjectConfiguration {

	/**
	 * FileStreamWriter used for writing file to disk
	 */
	private FileStreamWriter fileStreamWriter;
	
	/**
	 * Project Storage Directory
	 */
	private String projectStorageDirectory;

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
	 * @param file
	 * @param scriptSourceCode
	 * writes project scripts to file
	 */
	public void writeProjectScript(File file, String scriptSourceCode) {

	}
}
