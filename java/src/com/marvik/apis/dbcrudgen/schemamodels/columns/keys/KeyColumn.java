package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

public class KeyColumn extends TableColumn {

	/**
	 * Sets table key column
	 */
	public KeyColumn(String keyColumnName, DataType dataType) {
		super(keyColumnName, dataType);

	}

	/**
	 * Return the name of the key column
	 */
	public String getKeyColumn() {
		return getColumnName();
	}

	/**
	 * KeyColumn#getDataType
	 * 
	 * @return DataType
	 */
	public DataType getDataType() {
		return super.getDataType();
	}
}
