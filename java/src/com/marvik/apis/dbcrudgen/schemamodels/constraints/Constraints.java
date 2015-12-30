package com.marvik.apis.dbcrudgen.schemamodels.constraints;

public class Constraints {

	private String constraint;

	public Constraints(String constraint) {
		this.constraint = constraint;
	}

	public Constraints() {
		new Constraints(null);
	}

	/**
	 * @return the constraint
	 */
	public String getConstraint() {
		return constraint;
	}

}
