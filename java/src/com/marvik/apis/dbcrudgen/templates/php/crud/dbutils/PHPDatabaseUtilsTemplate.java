package com.marvik.apis.dbcrudgen.templates.php.crud.dbutils;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

public class PHPDatabaseUtilsTemplate extends CrudTemplates {

	/**
	 * PHPDatabaseUtilsTemplate : The database utils template
	 */
	public PHPDatabaseUtilsTemplate() {

	}

	/**
	 * Returns the PHP utils template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/**
	 * Returns the PHP utils template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/**
	 * Returns the PHP utils template file path
	 */
	@Override
	public String getTemplateFilePath() {

		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_DATABASE_UTILS_TEMPLATE_FILE_PATH;
	}
}
