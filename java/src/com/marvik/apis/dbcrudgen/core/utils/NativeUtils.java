package com.marvik.apis.dbcrudgen.core.utils;

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

	public static String getSystemProperty(String property) {
		return System.getProperty(property);
	}
}
