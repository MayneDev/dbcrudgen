package com.marvik.apis.dbcudgen.java.templates.php;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;

public class PHPDatabaseConnectionTemplate extends CrudTemplates {

	/**
	 * PHPDatabaseConnectionTemplate : The database connection template
	 */
	public PHPDatabaseConnectionTemplate() {

	}

	/**
	 * Returns the PHP database connection template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/**
	 * Returns the PHP database connection template
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

	/**
	 * Returns the PHP database connection template file path
	 */
	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.PHP_DATABASE_CONNECTION_TEMPLATE_FILE_PATH;
	}
}
