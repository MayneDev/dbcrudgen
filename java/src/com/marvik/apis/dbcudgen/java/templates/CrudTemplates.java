package com.marvik.apis.dbcudgen.java.templates;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.io.FileStreamReader;

public abstract class CrudTemplates {

	private FileStreamReader fileStreamReader;

	public CrudTemplates() {
		fileStreamReader = new FileStreamReader();
	}

	/*
	 * Returns the tables template
	 */
	public String openTemplate(String templateFilePath) throws IOException {
		return fileStreamReader.readFile(templateFilePath);
	}

	/*
	 * Returns the tables template
	 */
	public String getTemplate() throws IOException {
		return openTemplate(getTemplateFilePath());

	}

	/*
	 * Returns the template file path
	 */
	public abstract String getTemplateFilePath();
}
