package com.marvik.apis.dbcrudgen.templates.sql;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

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
	public String getTemplate() {

		try {
			return super.getTemplate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.SQLTemplatesFilePaths.SQL_ROUTINES_TEMPLATE_FILE_PATH;
	}
}
