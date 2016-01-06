package com.marvik.apis.dbcrudgen.templates.sql;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;

public class SQLTableFilenameTemplate extends CrudTemplates {

	public SQLTableFilenameTemplate() {

	}

	/*
	 * Returns the tables template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the tables file name template
	 */

	@Override
	public String getTemplate() {

		return SimpleTemplates.FileNameTemplates.SQL.SQL_TABLES_FILE_NAME_TEMPLATE;
	}

	/*
	 * Returns the tables template file path
	 */
	@Override
	public String getTemplateFilePath() {

		return TemplatesFilePath.SQL_TABLES_FILE_NAME_TEMPLATE_FILE_PATH;
	}

}
