package com.marvik.apis.dbcudgen.java.templates;

import java.io.IOException;

import com.marvik.apis.dbcudgen.java.filepaths.templates.TemplatesFilePath;

public class RoutinesTemplate extends CrudTemplates {

	public RoutinesTemplate() {

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
		return TemplatesFilePath.ROUTINES_TEMPLATE_FILE_PATH;
	}
}
