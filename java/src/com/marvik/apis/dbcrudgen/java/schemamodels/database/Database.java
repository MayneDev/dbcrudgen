package com.marvik.apis.dbcrudgen.java.schemamodels.database;

import com.marvik.apis.dbcrudgen.java.schemamodels.tables.Table;

public class Database {

	String databaseName;
	Table[] tables;

	public Database(String databaseName, Table[] tables) {
		super();
		this.databaseName = databaseName;
		this.tables = tables;
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @return the tables
	 */
	public Table[] getTables() {
		return tables;
	}

}
