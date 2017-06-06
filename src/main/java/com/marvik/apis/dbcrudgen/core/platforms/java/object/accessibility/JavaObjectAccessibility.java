package com.marvik.apis.dbcrudgen.core.platforms.java.object.accessibility;

public enum JavaObjectAccessibility {

	/**
	 * JavaObjectAccessibility#PRIVATE
	 * 
	 * Used for declaring objects that are only accessible on the scope of the
	 * class
	 */
	PRIVATE,

	/**
	 * JavaObjectAccessibility#DEFAULT
	 * 
	 * Used for declaring objects that are only accessible on the scope of the
	 * class
	 */
	DEFAULT,

	/**
	 * JavaObjectAccessibility#DEFAULT
	 * 
	 * Used for declaring objects that are only accessible on the scope of the
	 * class and its subclasses
	 */
	PROTECTED,
	/**
	 * JavaObjectAccessibility#PUBLIC
	 * 
	 * Used for declaring objects that are accessible on the whole scope of the
	 * java universe
	 */
	PUBLIC

}