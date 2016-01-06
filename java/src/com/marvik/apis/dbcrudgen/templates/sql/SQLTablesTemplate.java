package com.marvik.apis.dbcrudgen.templates.sql;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

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
	public String getTemplate() {

		try {
			return super.getTemplate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
