package com.marvik.apis.dbcrudgen.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class FileStreamReader {

	/*
	 * Reads the contents of a file
	 */
	public final String readFile(File file) throws IOException {
		if (!file.exists()) {
			throw new IOException("The file [" + file.getAbsolutePath() + "] does not exist");
		}

		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder stringBuilder = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		String newLine = "\n";
		String readString = "";
		while ((readString = bufferedReader.readLine()) != null) {
			stringBuilder.append(readString + lineSeparator + newLine);
		}
		bufferedReader.close();
		inputStreamReader.close();
		fileInputStream.close();
		return stringBuilder.toString();
	}
	/*
	 * Reads the contents of a file
	 */
	public final String readFile(String filePath) throws IOException {
		return readFile(new File(filePath));
	}
	/*
	 * Creates a file if the file does not exist
	 */
	public final File createFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (file.exists()) {
			return file;
		}
		file.createNewFile();

		return file;
	}
	/*
	 * Deletes a file if the file exists
	 */
	public final boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return true;
		}
		return file.delete();
	}
}
