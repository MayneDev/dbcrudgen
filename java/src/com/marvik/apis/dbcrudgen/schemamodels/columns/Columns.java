package com.marvik.apis.dbcrudgen.schemamodels.columns;

import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

public class Columns {

	private String columnName;
	private DataType dataType;
	private String[] columnNames;

	public Columns(String columnName, DataType dataType) {
		this.columnName = columnName;
		this.dataType = dataType;
	}

	/*
	 * This constructor should be improved in future
	 */
	public Columns(String[] columnNames) {
		this.columnNames = columnNames;

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

	/**
	 * @return the columnNames
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * @param columnNames the columnNames to set
	 */
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	

}
