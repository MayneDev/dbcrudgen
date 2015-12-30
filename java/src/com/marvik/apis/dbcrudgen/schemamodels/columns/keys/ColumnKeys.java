package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

public class ColumnKeys {
	private String[] columnKeys;

	/*
	 * Initializes a string array of the table column keys
	 */
	public ColumnKeys(String[] columnKeys) {
		this.columnKeys = columnKeys;
	}

	/*
	 * Return the set column keys
	 */
	public String[] getColumnKeys() {
		return columnKeys;
	}
}
