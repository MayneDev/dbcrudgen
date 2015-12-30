package com.marvik.apis.dbcrudgen.java.schemamodels.tables;

import com.marvik.apis.dbcrudgen.java.schemamodels.columns.Columns;

public class Table {

	String tableName;
	Columns[] columns;

	public Table(String tableName, Columns[] columns) {
		super();
		this.tableName = tableName;
		this.columns = columns;
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

}
