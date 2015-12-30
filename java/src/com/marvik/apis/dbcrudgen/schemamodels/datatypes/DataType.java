package com.marvik.apis.dbcrudgen.schemamodels.datatypes;

import com.marvik.apis.dbcrudgen.schemamodels.constraints.Constraints;

public class DataType {

	String dataType;
	Constraints constraints;

	/**
	 * @param dataType
	 * @param constraints
	 */
	public DataType(String dataType, Constraints constraints) {
		this.dataType = dataType;
		this.constraints = constraints;
	}

	public String getDataType() {
		return dataType;
	}

	/**
	 * @return the constraints
	 */
	public Constraints getConstraints() {
		return constraints;
	}

	/**
	 * @param constraints
	 *            the constraints to set
	 */
	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
