package com.marvik.apis.dbcrudgen.templates.php.crud.classcrud;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.php.crud.PHPCrudTemplates;

public class PHPClassDatabaseConnectionTemplate extends PHPCrudTemplates {

	/**
	 * PHPClassDatabaseConnectionTemplate : The database connection template
	 */
	public PHPClassDatabaseConnectionTemplate() {

	}

	/**
	 * Returns the PHP database connection template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/**
	 * Returns the PHP database connection template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/**
	 * Returns the PHP database connection template file path
	 */
	@Override
	public String getTemplateFilePath() {

		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_CLASS_DATABASE_CONNECTION_TEMPLATE_FILE_PATH;
	}
}
