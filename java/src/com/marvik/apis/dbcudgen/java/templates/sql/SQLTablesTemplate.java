package com.marvik.apis.dbcudgen.java.templates.sql;

import java.io.IOException;

import com.marvik.apis.dbcudgen.java.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;

public final class SQLTablesTemplate extends CrudTemplates {

	public SQLTablesTemplate() {

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
		return TemplatesFilePath.SQL_TABLES_TEMPLATE_FILE_PATH;
	}

}
