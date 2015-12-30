package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

public class ForeignKeys extends ColumnKeys {

	/*
	 * Initializes a string array of the table foreign keys
	 */
	public ForeignKeys(String[] foreignKeys) {
		super(foreignKeys);
	}

	/*
	 * Return the set foreign keys
	 */
	public String[] getForeignKeys() {
		return getColumnKeys();
	}
}
