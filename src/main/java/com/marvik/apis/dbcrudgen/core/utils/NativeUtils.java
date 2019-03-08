package com.marvik.apis.dbcrudgen.core.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLUtils;
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

    public static String getFileSeparator() {
        return getSystemProperty("file.separator") != null ? getSystemProperty("file.separator") : File.separator;
    }

    /**
     * Convert a class name to a java bean variable name This method is
     */
    public static String toJavaBeansVariable(String className) {
        className = toJavaBeansClass(className);
        if (className.length() < 2) {
            return className;
        }
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
        packageFilePath = packageFilePath.replace("_", "");
        return packageFilePath.replace(NativeUtils.getFileSeparator(), TemplateTags.TAG_PRINTING_CHAR_DOT);
    }

    /**
     * NativeUtils#parsePackagePath Formats package name to a valid file path
     *
     * @param packageName
     * @return
     */
    public static String parsePackagePath(String packageName) {
        return packageName.replace(NativeTemplateTags.DOT, NativeUtils.getFileSeparator());
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
        if (dataType.equalsIgnoreCase("BIGINT")) {
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
        if (dataType.equalsIgnoreCase("DATE")) {
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

        javaVariable += dataType + JavaGrammar.SPACE + toJavaBeansVariable(objectName);

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

    @Deprecated
    public static String createJavaClassVariableInitStatement(String object) {
        String javaClassVariableInitStatementTemplate = SimpleTemplates.Java.JAVA_CLASS_VARIABLE_INIT_STATMENT_TEMPLATE;
        return javaClassVariableInitStatementTemplate.replace(TemplateTags.Java.OBJECT, object);
    }

    public static String createNewJavaClassVariableInitStatement(String object) {
        String javaClassVariableInitStatementTemplate = SimpleTemplates.Java.NEW_JAVA_CLASS_VARIABLE_INIT_STATMENT_TEMPLATE;
        return javaClassVariableInitStatementTemplate.replace(TemplateTags.Java.OBJECT, object);
    }

    /**
     * Creates a Java Bean class name from a string of text
     *
     * @param className
     * @return valid class name
     */
    public static String toJavaBeansClass(String className) {

        className = className.substring(0, 1).toUpperCase() + className.substring(1, className.length());

        String newClassName = "";

        String[] parts = null;

        if (className.contains(" ")) {
            // Create new parts from the class name whenever we encounter a
            // space
            parts = className.split(" ");

            for (String part : parts) {
                if (part.length() != 0) {
                    part = part.substring(0, 1).toUpperCase() + part.substring(1, part.length());
                    newClassName += part;
                }
            }
        } else {
            newClassName = className;
        }

        if (newClassName.contains("_")) {
            // Create new parts of the class name from the new array of name
            parts = newClassName.split("_");

            // Reset class name
            newClassName = "";

            for (String part : parts) {

                if (part.length() != 0) {
                    // Make the first letter a capital letter
                    part = part.substring(0, 1).toUpperCase() + part.substring(1, part.length());

                    // rebuild the class name
                    newClassName += part;
                }
            }
        }
        return newClassName;

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

    /**
     * Returns alphabetic characters only found in the text
     *
     * @param text
     * @return
     */
    @Deprecated
    public static String toLetters(String text) {
        return toLettersOnly(text);
    }

    /**
     * Returns alphabetic characters only found in the text
     *
     * @param text
     * @return
     */
    public static String toLettersOnly(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (Character.isLetter(character)) {
                builder.append(character);
            }
        }
        return builder.toString();
    }

    /**
     * Splits the passed mysql data type to get the data type and strip other params
     *
     * @param dataType mysql data type
     * @return
     */
    public static String toMYSQLDataType(String dataType) {
        return MYSQLUtils.parseDataType(dataType);
    }

    /**
     * Print an error messageF
     *
     * @param message
     */
    public static void printError(String message) {
        System.err.println(message);
    }

    /**
     * Transform a path template to a path
     *
     * @param placeholder
     * @param separator
     * @param pathTemplate
     * @return
     */
    public static String transformPath(String placeholder, String separator, String pathTemplate) {
        return pathTemplate.replace(placeholder, separator);
    }
}
