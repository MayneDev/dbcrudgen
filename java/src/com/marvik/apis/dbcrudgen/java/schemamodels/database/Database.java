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

	/*
	 * Create Database SQL schemas from a database object 
	 */
	public String createSQL() {
		// TODO Auto-generated method stub
		
		String databaseCreateSQL = "CREATE DATABASE IF NOT EXISTS `"+getDatabaseName()+"`;";
		
		String tablesCreateSQL = "";
		
		for(Table table : getTables()){
			String tableCreateSQL = table.getTableSql();
			tablesCreateSQL += tableCreateSQL;
		}
		return databaseCreateSQL + tablesCreateSQL;
	}

}
