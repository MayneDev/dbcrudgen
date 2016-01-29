package com.marvik.apis.dbcrudgen.templates.php.crud.functions;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.php.crud.PHPCrudTemplates;

public class PHPFunctionHighLevelFetchAssocGettersTemplate extends PHPCrudTemplates {

	/**
	 * Creates the PHP Functions to get the data held by the fetch assoc object
	 * in the high level table crud class
	 */
	public PHPFunctionHighLevelFetchAssocGettersTemplate() {

	}

	/*
	 * Returns the high level fetch assoc getter template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the high level fetch assoc getter template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/*
	 * Returns the high level fetch assoc getter template file path
	 */
	@Override
	public String getTemplateFilePath() {

		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_FUNCTION_HIGH_LEVEL_FETCH_ASSOC_DATA_GETTERS;
	}
}
