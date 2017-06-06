package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

public class UniqueKeys extends KeyColumn {

	/**
	 * Defines a unique key column
	 */
	public UniqueKeys(String uniqueKey, DataType dataType) {
		super(uniqueKey, dataType,false);
	}

	/**
	 * Return the unique key column
	 */
	public String getUniqueKey() {
		return getColumnName();
	}
}
