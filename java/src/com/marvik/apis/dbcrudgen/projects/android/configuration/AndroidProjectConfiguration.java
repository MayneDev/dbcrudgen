package com.marvik.apis.dbcrudgen.projects.android.configuration;

import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;

public class AndroidProjectConfiguration {

	private String projectStorageDir;
	private AndroidContentProviderConfiguration androidContentProviderConfiguration;

	public AndroidProjectConfiguration(String projectStorageDir,
			AndroidContentProviderConfiguration androidContentProviderConfiguration) {
		this.androidContentProviderConfiguration = androidContentProviderConfiguration;
		this.projectStorageDir = projectStorageDir;
	}

	/**
	 * @return the projectStorageDir
	 */
	public String getProjectStorageDir() {
		return projectStorageDir;
	}

	/**
	 * @param projectStorageDir the projectStorageDir to set
	 */
	public void setProjectStorageDir(String projectStorageDir) {
		this.projectStorageDir = projectStorageDir;
	}

	/**
	 * @return the androidContentProviderConfiguration
	 */
	public AndroidContentProviderConfiguration getAndroidContentProviderConfiguration() {
		return androidContentProviderConfiguration;
	}

	/**
	 * @param androidContentProviderConfiguration the androidContentProviderConfiguration to set
	 */
	public void setAndroidContentProviderConfiguration(
			AndroidContentProviderConfiguration androidContentProviderConfiguration) {
		this.androidContentProviderConfiguration = androidContentProviderConfiguration;
	}
	
	
}
