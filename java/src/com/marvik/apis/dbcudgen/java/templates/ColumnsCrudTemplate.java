package com.marvik.apis.dbcudgen.java.templates;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.java.io.FileStreamReader;

public class ColumnsCrudTemplate {
	private FileStreamReader fileStreamReader;

	public ColumnsCrudTemplate() {
		fileStreamReader = new FileStreamReader();
	}

	/*
	 * Returns the columns CRUD  template
	 */
	private String openTablesTemplate(FileStreamReader fileStreamReader) throws IOException {
		return fileStreamReader.readFile("res/templates/template_columns_crud.txt");
	}

	/*
	 * Returns the columns CRUD template
	 */
	public final String getTablesTemplate() {
		try {
			return openTablesTemplate(fileStreamReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
