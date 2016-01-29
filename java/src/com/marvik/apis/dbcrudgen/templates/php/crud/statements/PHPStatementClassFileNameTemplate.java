package com.marvik.apis.dbcrudgen.templates.php.crud.statements;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;

public class PHPStatementClassFileNameTemplate extends CrudTemplates {

	@Deprecated
	public PHPStatementClassFileNameTemplate() {

	}

	/*
	 * Returns the PHP class file name template
	 */
	@Deprecated
	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the PHP class file name template
	 */
	@Deprecated
	@Override
	public String getTemplate() {
		return SimpleTemplates.FileNameTemplates.PHP.PHP_CLASS_FILENAME_TEMPLATE_FILE_PATH;
	}

	/*
	 * Returns the PHP class file name template file path
	 */
	@Deprecated
	@Override
	public String getTemplateFilePath() {
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_CLASS_FILENAME_TEMPLATE_FILE_PATH;
	}
}
