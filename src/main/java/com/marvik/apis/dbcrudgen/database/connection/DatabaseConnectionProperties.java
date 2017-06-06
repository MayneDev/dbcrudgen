package com.marvik.apis.dbcrudgen.database.connection;

public class DatabaseConnectionProperties {
	
	private String databaseHost;
	private String databaseUser;
	private String databaseUserPassword;
	private String databaseName;
	/**
	 * @param databaseHost
	 * @param databaseUser
	 * @param databaseUserPassword
	 * @param databaseName
	 */
	public DatabaseConnectionProperties(String databaseHost, String databaseUser, String databaseUserPassword,
			String databaseName) {
		this.databaseHost = databaseHost;
		this.databaseUser = databaseUser;
		this.databaseUserPassword = databaseUserPassword;
		this.databaseName = databaseName;
	}
	/**
	 * @return the databaseHost
	 */
	public String getDatabaseHost() {
		return databaseHost;
	}
	/**
	 * @param databaseHost the databaseHost to set
	 */
	public void setDatabaseHost(String databaseHost) {
		this.databaseHost = databaseHost;
	}
	/**
	 * @return the databaseUser
	 */
	public String getDatabaseUser() {
		return databaseUser;
	}
	/**
	 * @param databaseUser the databaseUser to set
	 */
	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}
	/**
	 * @return the databaseUserPassword
	 */
	public String getDatabaseUserPassword() {
		return databaseUserPassword;
	}
	/**
	 * @param databaseUserPassword the databaseUserPassword to set
	 */
	public void setDatabaseUserPassword(String databaseUserPassword) {
		this.databaseUserPassword = databaseUserPassword;
	}
	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	
}
