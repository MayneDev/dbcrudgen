package com.marvik.apis.dbcudgen.java.templates;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.java.io.FileStreamReader;

public abstract class CrudTemplate {

	private FileStreamReader fileStreamReader;

	public CrudTemplate() {
		fileStreamReader = new FileStreamReader();
	}

	/*
	 * Returns the tables template
	 */
	private String openTemplate(String templateFilePath) throws IOException {
		return fileStreamReader.readFile(templateFilePath);
	}

	/*
	 * Returns the tables template
	 */
	public final String getTemplate(String templateFilePath) {
		try {
			return openTemplate(templateFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Returns the template file path
	 */
	public abstract String getTemplateFilePath();
}
