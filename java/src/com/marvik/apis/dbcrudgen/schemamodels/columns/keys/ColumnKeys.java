package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

import com.marvik.apis.dbcrudgen.schemamodels.columns.Columns;

public class ColumnKeys extends Columns{
	private String[] columnKeys;

	/*
	 * Initializes a string array of the table column keys
	 */
	public ColumnKeys(String[] columnKeys) {
		super(columnKeys);
		this.columnKeys = columnKeys;
	}

	/*
	 * Return the set column keys
	 */
	public String[] getColumnKeys() {
		return columnKeys;
	}
}
