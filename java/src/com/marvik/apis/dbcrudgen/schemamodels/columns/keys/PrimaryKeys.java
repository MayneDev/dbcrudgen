package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

public class PrimaryKeys extends ColumnKeys {

	/*
	 * Initializes a string array of the table primary keys
	 */
	public PrimaryKeys(String[] primaryKeys) {
		super(primaryKeys);
	}

	/*
	 * Return the set primary keys
	 */
	public String[] getPrimaryKeys() {
		return getColumnKeys();
	}
}
