package com.marvik.apis.dbcrudgen.core.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		return getSystemProperty("file.separator")!= null ? getSystemProperty("file.separator") :File.separator;
	}

	public static String toJavaBeans(String className) {
		return className.substring(0, 1).toUpperCase() + className.substring(1, className.length());
	}
}
