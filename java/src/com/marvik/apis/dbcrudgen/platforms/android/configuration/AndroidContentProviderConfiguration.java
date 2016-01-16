package com.marvik.apis.dbcrudgen.platforms.android.configuration;

public class AndroidContentProviderConfiguration {

	private String contentProviderClass;
	private String contentProviderPackage;
	private AndroidDatabaseConfiguration androidDatabaseConfiguration;

	/**
	 * AndroidContentProviderConfiguration - Configuration for the android class
	 * that extends ContentProvider
	 * 
	 * @param contentProviderClass
	 * @param contentProviderPackage
	 * @param androidDatabaseConfiguration
	 */

	public AndroidContentProviderConfiguration(String contentProviderClass, String contentProviderPackage,
			AndroidDatabaseConfiguration androidDatabaseConfiguration) {
		this.contentProviderClass = contentProviderClass;
		this.contentProviderPackage = contentProviderPackage;
		this.androidDatabaseConfiguration = androidDatabaseConfiguration;
	}

	/**
	 * AndroidContentProviderConfiguration#getContentProviderClass
	 * 
	 * @return contentProviderClass
	 */
	public String getContentProviderClass() {
		return contentProviderClass;
	}

	/**
	 * AndroidContentProviderConfiguration#getContentProviderPackage
	 * 
	 * @return contentProviderPackage
	 */
	public String getContentProviderPackage() {
		return contentProviderPackage;
	}

	/**
	 * AndroidContentProviderConfiguration#setContentProviderClass Sets the
	 * content provider class
	 * 
	 * @param contentProviderClass
	 */
	public void setContentProviderClass(String contentProviderClass) {
		this.contentProviderClass = contentProviderClass;
	}

	/**
	 * AndroidContentProviderConfiguration#setContentProviderPackage Sets the
	 * content provider package
	 * 
	 * @param contentProviderPackage
	 */
	public void setContentProviderPackage(String contentProviderPackage) {
		this.contentProviderPackage = contentProviderPackage;
	}

	/**
	 * AndroidContentProviderConfiguration#getAndroidDatabaseConfiguration
	 * 
	 * @return androidDatabaseConfiguration
	 */
	public AndroidDatabaseConfiguration getAndroidDatabaseConfiguration() {
		return androidDatabaseConfiguration;
	}

	/**
	 * AndroidContentProviderConfiguration#setAndroidDatabaseConfiguration
	 * 
	 * @param androidDatabaseConfiguration
	 */
	public void setAndroidDatabaseConfiguration(AndroidDatabaseConfiguration androidDatabaseConfiguration) {
		this.androidDatabaseConfiguration = androidDatabaseConfiguration;
	}
}
