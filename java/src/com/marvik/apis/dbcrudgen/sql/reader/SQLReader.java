package com.marvik.apis.dbcrudgen.sql.reader;

import java.io.File;
import java.io.IOException;

import com.marvik.apis.dbcrudgen.io.FileStreamReader;

public class SQLReader {

	private FileStreamReader fileStreamReader;

	public SQLReader() {
		fileStreamReader = new FileStreamReader();
	}

	/*
	 * Returns all the contents of an SQL file as a string
	 */
	public String getSQL(String filePath) throws IOException {
		return fileStreamReader.readFile(filePath);
	}

	/*
	 * Returns all the contents of an SQL file as a string
	 */
	public String getSQL(File file) throws IOException {
		return getSQL(file.getAbsolutePath());
	}
}
