package com.marvik.apis.dbcrudgen.core.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

	public static String toJavaBeansClass(String className) {
		return className.substring(0, 1).toUpperCase() + className.substring(1, className.length());
	}

	public static String toJavaBeansVariable(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
	}

	public static String getString(int integer) {
		return String.format("%d", integer);
	}

	/**
	 * NativeUtils#parsePackageName
	 * Formats a file path into a java package url
	 * @param packageFilePath
	 * @return
	 */
	public static String parseJavaPackage(String packageFilePath) {
		return packageFilePath.replace(NativeUtils.getFileSeparator(), TemplateTags.TAG_PRINTING_CHAR_DOT);
	}
}
