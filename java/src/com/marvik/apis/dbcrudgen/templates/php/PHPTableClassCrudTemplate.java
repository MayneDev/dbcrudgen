package com.marvik.apis.dbcrudgen.templates.php;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

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
	public String getTemplate() {
		// TODO Auto-generated method stub
		try {
			return super.getTemplate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Returns the table class CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_TABLE_CLASS_CRUD_TEMPLATE_FILE_PATH;
	}
}
