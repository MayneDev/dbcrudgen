package com.marvik.apis.dbcrudgen.java.schemamodels.tables;

import com.marvik.apis.dbcrudgen.java.schemamodels.columns.Columns;

public class Table {

	String tableName;
	Columns[] columns;
	String tableSql;

	public Table(String tableName, Columns[] columns, String tableSql) {
		super();
		this.tableName = tableName;
		this.columns = columns;
		this.tableSql = tableSql;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the columns
	 */
	public Columns[] getColumns() {
		return columns;
	}

	/**
	 * @return the tableSql
	 */
	public String getTableSql() {
		return tableSql;
	}

	/**
	 * @param tableSql the tableSql to set
	 */
	public void setTableSql(String tableSql) {
		this.tableSql = tableSql;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(Columns[] columns) {
		this.columns = columns;
	}

}
