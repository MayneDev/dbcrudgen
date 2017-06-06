package com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters;

public enum JavaDelimiter {

	/**
	 * JavaDelimiter#COMMA Used for separating parameters in a method
	 */
	COMMA,

	/**
	 * JavaDelimiter#SEMICOLON Used for terminating a statement
	 */
	SEMICOLON,

	/**
	 * JavaDelimiter#DOT Used for accessing a class members using the class
	 * object
	 */
	DOT,

	/**
	 * JavaDelimiter#NONE does not add a delimiter
	 */
	NONE

}
