package com.marvik.apis.dbcrudgen.java.schemamodels.columns;

import com.marvik.apis.dbcrudgen.java.schemamodels.datatypes.DataType;

public class Columns {

	String columnName;
	DataType dataType;

	public Columns(String columnName, DataType dataType) {
		this.columnName = columnName;
		this.dataType = dataType;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @return the dataType
	 */
	public DataType getDataType() {
		return dataType;
	}
	
	
}
