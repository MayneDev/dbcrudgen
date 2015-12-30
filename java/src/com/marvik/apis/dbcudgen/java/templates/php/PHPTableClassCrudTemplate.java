package com.marvik.apis.dbcudgen.java.templates.php;

import java.io.IOException;

import com.marvik.apis.dbcudgen.java.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;

public class PHPTableClassCrudTemplate extends CrudTemplates {

	public PHPTableClassCrudTemplate() {

	}

	/*
	 * Returns the table class CRUD template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the table class CRUD template
	 */
	@Override
	public String getTemplate() throws IOException {
		// TODO Auto-generated method stub
		return super.getTemplate();
	}

	/*
	 * Returns the table class CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.PHP_TABLE_CLASS_CRUD_TEMPLATE_FILE_PATH;
	}
}
