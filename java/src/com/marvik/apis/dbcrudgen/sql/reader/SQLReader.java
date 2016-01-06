package com.marvik.apis.dbcrudgen.sql.reader;

import java.io.File;
import java.io.IOException;

import com.marvik.apis.dbcrudgen.io.FilesHandler;


public class SQLReader {

	private FilesHandler fileHandler;

	public SQLReader() {
		fileHandler= new FilesHandler();
	}

	/**
	 * getFileHandler
	 * @return FileHandler
	 */
	public FilesHandler getFileHandler() {
		return fileHandler;
	}
	/*
	 * Returns all the contents of an SQL file as a string
	 */
	public String getSQL(String filePath) throws IOException {
		return getFileHandler().readFile(filePath);
	}

	/*
	 * Returns all the contents of an SQL file as a string
	 */
	public String getSQL(File file) throws IOException {
		return getSQL(file.getAbsolutePath());
	}
}
