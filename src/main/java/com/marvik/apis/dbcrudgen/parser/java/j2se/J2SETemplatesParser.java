/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.java.j2se;

import com.marvik.apis.dbcrudgen.parser.TemplatesParser;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;

/**
*Created on Feb 10, 2016-12:20:16 AM by victor
*/

/**
 * @author victor
 *
 */
public class J2SETemplatesParser extends TemplatesParser {

	/**
	 * J2SETemplatesParser
	 * 
	 * Parses J2SE Templates
	 */
	public J2SETemplatesParser() {

	}

	/**
	 * @param columnDatatype
	 * @return result set getter template
	 */
	protected String getResultSetGetterTemplate(String columnDatatype) {
		
		String javaDataType = getJavaObjectDataType(columnDatatype);

		// Boolean
		if (javaDataType.equalsIgnoreCase("boolean")) {

			return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_BOOLEAN;
		}
		// Byte
		if (javaDataType.equalsIgnoreCase("byte")) {

			return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_BYTE;
		}
		// Integer
		if (javaDataType.equalsIgnoreCase("int")) {

			return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_INT;
		}

		// Date
		if (javaDataType.equalsIgnoreCase("Date")) {

			return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_DATE;
		}
		// Double
		if (javaDataType.equalsIgnoreCase("double")) {

			return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_DOUBLE;
		}
		// Float
		if (javaDataType.equalsIgnoreCase("float")) {

			return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_FLOAT;
		}
		// Long
		if (javaDataType.equalsIgnoreCase("long")) {

			return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_LONG;
		}

		// String
		if (javaDataType.equalsIgnoreCase("String")) {

			return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_STRING;
		}

		// ADDED TO AVOID RETURNING NULL
		return SimpleTemplates.Java.RESULT_SET_VALUES_GETTER_TEMPLATE_STRING;
	}
}
