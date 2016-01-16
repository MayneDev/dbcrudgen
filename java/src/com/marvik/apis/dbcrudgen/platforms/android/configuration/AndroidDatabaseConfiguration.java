package com.marvik.apis.dbcrudgen.platforms.android.configuration;

public class AndroidDatabaseConfiguration {

	private String databaseName;
	private int databaseVersion;

	private String sqliteOpenHelperClass;
	private String sqliteOpenHelperClassPackage;

	private String tablesSchemasPackage;

	/**
	 * AndroidDatabaseConfiguration - Android database configuration
	 * 
	 * @param databaseName
	 * @param databaseVersion
	 * @param sqliteOpenHelperClass
	 * @param sqliteOpenHelperClassPackage
	 * @param tablesSchemasPackage
	 */
	public AndroidDatabaseConfiguration(String databaseName, int databaseVersion, String sqliteOpenHelperClass,
			String sqliteOpenHelperClassPackage, String tablesSchemasPackage) {
		this.databaseName = databaseName;
		this.databaseVersion = databaseVersion;
		this.sqliteOpenHelperClass = sqliteOpenHelperClass;
		this.sqliteOpenHelperClassPackage = sqliteOpenHelperClassPackage;
		this.tablesSchemasPackage = tablesSchemasPackage;
	}

	/**
	 * AndroidDatabaseConfiguration#getDatabaseName
	 * 
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * AndroidDatabaseConfiguration#setDatabaseName
	 * 
	 * @param databaseName
	 *            the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * AndroidDatabaseConfiguration#getDatabaseVersion
	 * 
	 * @return the databaseVersion
	 */
	public int getDatabaseVersion() {
		return databaseVersion;
	}

	/**
	 * AndroidDatabaseConfiguration#setDatabaseVersion
	 * 
	 * @param databaseVersion
	 *            the databaseVersion to set
	 */
	public void setDatabaseVersion(int databaseVersion) {
		this.databaseVersion = databaseVersion;
	}

	/**
	 * AndroidDatabaseConfiguration#getSqliteOpenHelperClass
	 * 
	 * @return the sqliteOpenHelperClass
	 */
	public String getSqliteOpenHelperClass() {
		return sqliteOpenHelperClass;
	}

	/**
	 * AndroidDatabaseConfiguration#setSqliteOpenHelperClass
	 * 
	 * @param sqliteOpenHelperClass
	 *            the sqliteOpenHelperClass to set
	 */
	public void setSqliteOpenHelperClass(String sqliteOpenHelperClass) {
		this.sqliteOpenHelperClass = sqliteOpenHelperClass;
	}

	/**
	 * AndroidDatabaseConfiguration#getSqliteOpenHelperClassPackage
	 * 
	 * @return the sqliteOpenHelperClassPackage
	 */
	public String getSqliteOpenHelperClassPackage() {
		return sqliteOpenHelperClassPackage;
	}

	/**
	 * AndroidDatabaseConfiguration#setSqliteOpenHelperClassPackage
	 * 
	 * @param sqliteOpenHelperClassPackage
	 *            the sqliteOpenHelperClassPackage to set
	 */
	public void setSqliteOpenHelperClassPackage(String sqliteOpenHelperClassPackage) {
		this.sqliteOpenHelperClassPackage = sqliteOpenHelperClassPackage;
	}

	/**
	 * AndroidDatabaseConfiguration#getTablesSchemasPackage
	 * 
	 * @return the tablesSchemasPackage
	 */
	public String getTablesSchemasPackage() {
		return tablesSchemasPackage;
	}

	/**
	 * AndroidDatabaseConfiguration#setTablesSchemasPackage
	 * 
	 * @param tablesSchemasPackage
	 *            the tablesSchemasPackage to set
	 */
	public void setTablesSchemasPackage(String tablesSchemasPackage) {
		this.tablesSchemasPackage = tablesSchemasPackage;
	}

}
