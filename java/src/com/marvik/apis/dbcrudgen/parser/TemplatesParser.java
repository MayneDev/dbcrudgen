package com.marvik.apis.dbcrudgen.parser;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.io.FilesHandler;

/**
 * Templates Parser - Used for parsing templates into meaningful source files
 * 
 * @author victor
 *
 */
public class TemplatesParser {

	private FilesHandler filesHandler;

	public TemplatesParser() {
		filesHandler = new FilesHandler();
	}

	/**
	 * @return the filesHandler
	 */
	public FilesHandler getFilesHandler() {
		return filesHandler;
	}

	/**
	 * Creates a source file
	 * 
	 * @param absoluteFileName
	 * @param sourceCode
	 */
	public boolean createSourceFile(String absoluteFileName, String sourceCode) {
		return getFilesHandler().createByteWeighedFile(absoluteFileName, sourceCode);
	}

	/**
	 * JavaTemplatesParser#getJavaColumnQueryCrudTemplate
	 * 
	 * Return the right CRUD for a table column based on the data type of the
	 * column
	 * 
	 * @param columnDatatype
	 * @return CrudTemplates
	 */
	protected String getJavaObjectDataType(String columnDatatype) {

		columnDatatype = NativeUtils.toLetters(columnDatatype);

		// Boolean
		if (columnDatatype.equalsIgnoreCase("Boolean")) {
			return "boolean";
		}
		// Byte
		if (columnDatatype.equalsIgnoreCase("Byte")) {
			return "byte";
		}
		// Integer
		if (columnDatatype.equalsIgnoreCase("Integer")) {
			return "int";
		}
		// Integer
		if (columnDatatype.equalsIgnoreCase("Int")) {
			return "int";

		}
		// Date
		if (columnDatatype.equalsIgnoreCase("Date")) {
			// TO DO ADD SOURCE CODE
		}
		// Double
		if (columnDatatype.equalsIgnoreCase("Double")) {
			return "double";
		}
		// Float
		if (columnDatatype.equalsIgnoreCase("Float")) {
			return "float";
		}
		// Long
		if (columnDatatype.equalsIgnoreCase("Long")) {
			return "long";
		}
		// MYSQL Long
		if (columnDatatype.equalsIgnoreCase("mediumtext")) {
			return "long";
		}
		// String
		if (columnDatatype.equalsIgnoreCase("String")) {
			return "String";
		}
		// Varchar
		if (columnDatatype.equalsIgnoreCase("varchar")) {
			return "String";
		}
		// Text
		if (columnDatatype.equalsIgnoreCase("text")) {
			return "String";
		}

		System.out.println("COULD NOT GET THE DATATYPE FOR [" + columnDatatype + "]");

		return "Object";
	}

}
