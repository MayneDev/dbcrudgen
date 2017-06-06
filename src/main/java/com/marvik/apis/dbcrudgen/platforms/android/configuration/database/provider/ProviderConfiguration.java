package com.marvik.apis.dbcrudgen.platforms.android.configuration.database.provider;

public class ProviderConfiguration {

	private String contentProviderClass;
	private String contentProviderPackage;
	
	public ProviderConfiguration(String contentProviderClass,String contentProviderPackage){
		this.contentProviderClass = contentProviderClass;
		this.contentProviderPackage = contentProviderPackage;
	}

	/**
	 * @return the contentProviderClass
	 */
	public String getContentProviderClass() {
		return contentProviderClass;
	}

	/**
	 * @param contentProviderClass the contentProviderClass to set
	 */
	public void setContentProviderClass(String contentProviderClass) {
		this.contentProviderClass = contentProviderClass;
	}

	/**
	 * @return the contentProviderPackage
	 */
	public String getContentProviderPackage() {
		return contentProviderPackage;
	}

	/**
	 * @param contentProviderPackage the contentProviderPackage to set
	 */
	public void setContentProviderPackage(String contentProviderPackage) {
		this.contentProviderPackage = contentProviderPackage;
	}
	
	
}
