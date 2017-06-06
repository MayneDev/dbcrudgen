package com.marvik.apis.dbcrudgen.database.connection.project;

import com.marvik.apis.dbcrudgen.database.connection.DatabaseConnectionProperties;

public class ProjectDatabaseConnectionProperties extends DatabaseConnectionProperties {

	public ProjectDatabaseConnectionProperties(String databaseHost, String databaseUser, String databaseUserPassword,
			String databaseName) {
		super(databaseHost, databaseUser, databaseUserPassword, databaseName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.database.connection.
	 * DatabaseConnectionProperties#getDatabaseHost()
	 */
	@Override
	public String getDatabaseHost() {

		return super.getDatabaseHost();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.database.connection.
	 * DatabaseConnectionProperties#setDatabaseHost(java.lang.String)
	 */
	@Override
	public void setDatabaseHost(String databaseHost) {

		super.setDatabaseHost(databaseHost);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.database.connection.
	 * DatabaseConnectionProperties#getDatabaseUser()
	 */
	@Override
	public String getDatabaseUser() {

		return super.getDatabaseUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.database.connection.
	 * DatabaseConnectionProperties#setDatabaseUser(java.lang.String)
	 */
	@Override
	public void setDatabaseUser(String databaseUser) {

		super.setDatabaseUser(databaseUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.database.connection.
	 * DatabaseConnectionProperties#getDatabaseUserPassword()
	 */
	@Override
	public String getDatabaseUserPassword() {

		return super.getDatabaseUserPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.database.connection.
	 * DatabaseConnectionProperties#setDatabaseUserPassword(java.lang.String)
	 */
	@Override
	public void setDatabaseUserPassword(String databaseUserPassword) {

		super.setDatabaseUserPassword(databaseUserPassword);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.database.connection.
	 * DatabaseConnectionProperties#getDatabaseName()
	 */
	@Override
	public String getDatabaseName() {

		return super.getDatabaseName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.database.connection.
	 * DatabaseConnectionProperties#setDatabaseName(java.lang.String)
	 */
	@Override
	public void setDatabaseName(String databaseName) {

		super.setDatabaseName(databaseName);
	}

}
