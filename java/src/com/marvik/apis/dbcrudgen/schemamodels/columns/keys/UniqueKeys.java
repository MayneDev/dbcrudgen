package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

public class UniqueKeys extends ColumnKeys {
	
	/*
	 * Initializes a string array of the table unique keys
	 */
	public UniqueKeys(String[] uniqueKeys) {
		super(uniqueKeys);
	}

	/*
	 * Return the set unique keys
	 */
	public String[] getUniqueKeys() {
		return getColumnKeys();
	}
}
