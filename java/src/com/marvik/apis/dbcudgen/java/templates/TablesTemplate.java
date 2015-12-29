package com.marvik.apis.dbcudgen.java.templates;

import java.io.IOException;

import com.marvik.apis.dbcudgen.java.filepaths.templates.TemplatesFilePath;

public final class TablesTemplate extends CrudTemplates {

	public TablesTemplate() {

	}

	/*
	 * Returns the tables template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the tables template
	 */
	@Override
	public String getTemplate() throws IOException {
		// TODO Auto-generated method stub
		return super.getTemplate();
	}

	/*
	 * Returns the tables template file path
	 */
	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.TABLES_TEMPLATE_FILE_PATH;
	}

}
