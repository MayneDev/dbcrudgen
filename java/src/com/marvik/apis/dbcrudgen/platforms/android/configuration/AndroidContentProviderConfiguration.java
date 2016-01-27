package com.marvik.apis.dbcrudgen.platforms.android.configuration;

import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.provider.ProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.transactions.TransactionManagerConfiguration;

public class AndroidContentProviderConfiguration {


	private AndroidDatabaseConfiguration androidDatabaseConfiguration;

	private ProviderConfiguration providerConfiguration;
	
	private TransactionManagerConfiguration transactionManagerConfiguration;
	/**
	 * AndroidContentProviderConfiguration - Configuration for the android class
	 * that extends ContentProvider
	 * 
	 * @param contentProviderClass
	 * @param contentProviderPackage
	 * @param androidDatabaseConfiguration
	 */

	public AndroidContentProviderConfiguration(ProviderConfiguration provideConfiguration,
			TransactionManagerConfiguration transanctionManagerConfiguration, AndroidDatabaseConfiguration androidDatabaseConfiguration) {
		
		this.providerConfiguration = provideConfiguration;
		this.transactionManagerConfiguration = transanctionManagerConfiguration;
		this.androidDatabaseConfiguration = androidDatabaseConfiguration;
	}


	/**
	 * @return the providerConfiguration
	 */
	public ProviderConfiguration getProviderConfiguration() {
		return providerConfiguration;
	}


	/**
	 * @return the transactionManagerConfiguration
	 */
	public TransactionManagerConfiguration getTransactionManagerConfiguration() {
		return transactionManagerConfiguration;
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
