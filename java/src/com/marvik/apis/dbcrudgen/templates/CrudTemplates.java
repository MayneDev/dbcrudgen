package com.marvik.apis.dbcrudgen.templates;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.io.FilesHandler;

public abstract class CrudTemplates {

	private FilesHandler fileHandler;

	public CrudTemplates() {
		fileHandler = new FilesHandler();
	}

	public FilesHandler getFilesHandler() {
		return fileHandler;
	}
	/*
	 * Returns the tables template
	 */
	public String openTemplate(String templateFilePath) throws IOException {
		return getFilesHandler().readFile(templateFilePath);
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
