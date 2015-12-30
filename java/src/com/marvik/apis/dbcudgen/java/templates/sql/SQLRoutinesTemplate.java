package com.marvik.apis.dbcudgen.java.templates.sql;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;

public class SQLRoutinesTemplate extends CrudTemplates {

	public SQLRoutinesTemplate() {

	}

	/*
	 * Returns the routines template
	 */

	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the routines template
	 */
	@Override
	public String getTemplate() throws IOException {
		// TODO Auto-generated method stub
		return super.getTemplate();
	}

	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.SQL_ROUTINES_TEMPLATE_FILE_PATH;
	}
}
