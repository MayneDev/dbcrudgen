package com.marvik.apis.dbcudgen.java.templates;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.java.io.FileStreamReader;
import com.marvik.apis.dbcudgen.java.filepaths.templates.TemplatesFilePath;

public class ColumnsCrudTemplate extends CrudTemplates {

	public ColumnsCrudTemplate() {

	}

	/*
	 * Returns the columns CRUD template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the columns CRUD template
	 */
	@Override
	public String getTemplate() throws IOException {
		// TODO Auto-generated method stub
		return super.getTemplate();
	}

	/*
	 * Returns the columns CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.COLUMNS_CRUD_TEMPLATE_FILE_PATH;
	}
}
