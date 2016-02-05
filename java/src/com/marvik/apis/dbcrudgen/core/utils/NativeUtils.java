package com.marvik.apis.dbcrudgen.core.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.JavaGrammar;
import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters.JavaDelimiter;
import com.marvik.apis.dbcrudgen.core.platforms.java.object.accessibility.JavaObjectAccessibility;
import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.natives.syntax.Syntax.PrintingChars;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public final class NativeUtils {

	public static String getCurrentTime(String format, long timeInMillis) {
		return getFormattedTime(format, timeInMillis);
	}

	public static String getFormattedTime(String format, long timeInMillis) {
		return new SimpleDateFormat(format, Locale.getDefault()).format(new Date(timeInMillis));
	}

	private static String getSystemProperty(String property) {
		return System.getProperty(property);
	}

	public static CharSequence getFileSeparator() {
		return getSystemProperty("file.separator") != null ? getSystemProperty("file.separator") : File.separator;
	}

	/**
	 * Convert a class name to a java bean class name This method is deprecated.
	 * Please use the new {@link NativeUtils#toNewJavaBeansClass(String)}
	 */
	@Deprecated
	public static String toJavaBeansClass(String className) {
		return className.substring(0, 1).toUpperCase() + className.substring(1, className.length());
	}

	/**
	 * Convert a class name to a java bean variable name This method is
	 * deprecated. Please use the new
	 * {@link NativeUtils#toNewJavaBeansClass(String)(String)}
	 */
	@Deprecated
	public static String toJavaBeansVariable(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
	}

	public static CharSequence getClassVariable(String filename) {
		filename = filename.replace(".java", "");
		return filename.substring(0, 1).toLowerCase() + filename.substring(1, filename.length());
	}

	public static String getString(int integer) {
		return String.format("%d", integer);
	}

	/**
	 * NativeUtils#parsePackageName Formats a file path into a java package url
	 * 
	 * @param packageFilePath
	 * @return
	 */
	public static String parseJavaPackage(String packageFilePath) {
		return packageFilePath.replace(NativeUtils.getFileSeparator(), TemplateTags.TAG_PRINTING_CHAR_DOT);
	}

	/**
	 * NativeUtils#parsePackagePath Formats package name to a valid file path
	 * 
	 * @param packageName
	 * @return
	 */
	public static String parsePackagePath(String packageName) {
		return packageName.replace(NativeTemplateTags.DOT, NativeTemplateTags.FORWARD_SLASH);
	}

	public static String createJavaVariable(JavaObjectAccessibility javaAccessibility, String dataType,
			String objectName, JavaDelimiter javaDelimeter) {

		String javaVariable = "";

		switch (javaAccessibility) {
		case PRIVATE:
			javaVariable += " private ";
			break;
		case DEFAULT:
			javaVariable += " ";
			break;
		case PROTECTED:
			javaVariable += " protected ";
			break;
		case PUBLIC:
			javaVariable += " public ";
			break;
		}

		if (dataType.equalsIgnoreCase("INT")) {
			dataType = "int";
		}
		if (dataType.equalsIgnoreCase("INTEGER")) {
			dataType = "int";
		}
		if (dataType.equalsIgnoreCase("LONG")) {
			dataType = "long";
		}
		if (dataType.equalsIgnoreCase("STRING")) {
			dataType = "String";
		}
		if (dataType.equalsIgnoreCase("TEXT")) {
			dataType = "String";
		}
		if (dataType.equalsIgnoreCase("VARCHAR")) {
			dataType = "String";
		}
		if (dataType.equalsIgnoreCase("FLOAT")) {
			dataType = "float";
		}
		if (dataType.equalsIgnoreCase("DOUBLE")) {
			dataType = "double";
		}
		if (dataType.equalsIgnoreCase("BYTE")) {
			dataType = "byte";
		}

		javaVariable += dataType + JavaGrammar.SPACE + objectName;

		switch (javaDelimeter) {
		case COMMA:
			javaVariable += ",";
			break;
		case SEMICOLON:
			javaVariable += ";";
			break;
		case DOT:
			javaVariable += ".";
			break;
		case NONE:

			javaVariable += "";
			break;
		}

		return javaVariable;
	}

	public static String parseStringDefaultParser(String variable) {
		String stringParserTemplate = SimpleTemplates.Java.STRING_DEFAULT_PARSER;
		return stringParserTemplate.replace(NativeTemplateTags.Java.JAVA_OBJECT_TAG, variable);
	}

	public static String createJavaVariableParameterLessInitStatement(String object, String datatype) {
		String javaVariableParameterLessInitStatementTemplate = SimpleTemplates.Java.JAVA_OBJECT_INIT_STATEMENT_TEMPLATE;
		return javaVariableParameterLessInitStatementTemplate.replace(TemplateTags.Java.OBJECT, object)
				.replace(TemplateTags.Java.DATATYPE, datatype);
	}

	public static String createJavaClassVariableInitStatement(String object) {
		String javaClassVariableInitStatementTemplate = SimpleTemplates.Java.JAVA_CLASS_VARIABLE_INIT_STATMENT_TEMPLATE;
		return javaClassVariableInitStatementTemplate.replace(TemplateTags.Java.OBJECT, object);
	}

	/**
	 * Creates a Java Bean class name from a string of text
	 * 
	 * @param class
	 *            name
	 * @return valid class name
	 */
	public static String toNewJavaBeansClass(String className) {
		String newClassName = "";

		// Create new parts from the class name whenever we encounter a space
		String[] parts = className.split(" ");

		for (String part : parts) {
			part = part.substring(0, 1).toUpperCase() + part.substring(1, part.length());
			newClassName += part;

		}
		// Create new parts of the class name from the new array of name
		parts = newClassName.split("_");

		// Reset class name
		newClassName = "";

		for (String part : parts) {

			// Make the first letter a capital letter
			part = part.substring(0, 1).toUpperCase() + part.substring(1, part.length());

			// rebuild the class name
			newClassName += part;

		}

		
		return newClassName;

	}

	/**
	 * Converts the given word to a variable
	 * 
	 * @param variableName
	 * @return variable name
	 */
	public static CharSequence toNewJavaBeansVariable(String variableName) {
		String className = toNewJavaBeansClass(variableName);
		return toJavaBeansVariable(className);
	}

	/**
	 * Creates a sentence from a word
	 * 
	 * @param words
	 * @return a sentence
	 */
	public static String toSentence(String words) {
		return words.replace("_", PrintingChars.SPACE);
	}

}
