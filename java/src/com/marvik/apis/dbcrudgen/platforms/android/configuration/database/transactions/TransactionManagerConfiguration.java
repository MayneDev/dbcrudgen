package com.marvik.apis.dbcrudgen.platforms.android.configuration.database.transactions;

public class TransactionManagerConfiguration {
	
	private String transactionManagerPackage;
	private String transactionManagerClass;

	public TransactionManagerConfiguration(String transactionManagerPackage, String transactionManagerClass) {
		this.transactionManagerClass = transactionManagerClass;
		this.transactionManagerPackage = transactionManagerPackage;
	}

	/**
	 * @return the transactionManagerPackage
	 */
	public String getTransactionManagerPackage() {
		return transactionManagerPackage;
	}

	/**
	 * @param transactionManagerPackage the transactionManagerPackage to set
	 */
	public void setTransactionManagerPackage(String transactionManagerPackage) {
		this.transactionManagerPackage = transactionManagerPackage;
	}

	/**
	 * @return the transactionManagerClass
	 */
	public String getTransactionManagerClass() {
		return transactionManagerClass;
	}

	/**
	 * @param transactionManagerClass the transactionManagerClass to set
	 */
	public void setTransactionManagerClass(String transactionManagerClass) {
		this.transactionManagerClass = transactionManagerClass;
	}
	
	
}
