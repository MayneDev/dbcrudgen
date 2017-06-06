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

	/**
	 * Returns the template
	 */
	public String openTemplate(String templateFilePath) throws IOException {
		return getFilesHandler().readFile(templateFilePath);
	}

	/**
	 * Returns the template
	 */
	public String getTemplate() {
		try {
			return openTemplate(getTemplateFilePath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid Template File Path [" + getTemplateFilePath() + "]");
		}
		return null;
	}

	/**
	 * Returns the template file path
	 */
	public abstract String getTemplateFilePath();

	/**
	 * CrudTemplates#toString
	 * 
	 * @return the associated template
	 */
	@Override
	public String toString() {
		return getTemplate();
	}
}
