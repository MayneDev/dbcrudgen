/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.php.crud.functions;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.php.crud.PHPCrudTemplates;

/**
*Created on Feb 8, 2016-2:39:42 AM by victor
*/

/**
 * @author victor
 *
 */
public class PHPFunctionsHighLevelPreparedInsertTemplate extends PHPCrudTemplates {

	public PHPFunctionsHighLevelPreparedInsertTemplate() {

	}

	/*
	 * Returns the PHP prepared insert template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the PHP prepared insert template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/*
	 * Returns the PHP prepared insert template file path
	 */
	@Override
	public String getTemplateFilePath() {
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_FUNCTION_HIGH_LEVEL_PREPARED_INSERT_TEMPLATE_FILE_PATH;
	}
}
