package com.marvik.apis.dbcrudgen.templates.php.crud.variables;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.php.crud.PHPCrudTemplates;

public class PHPVariablesDatabaseActionsTemplate  extends PHPCrudTemplates {
	
	/**
	 * PHPVariablesDatabaseActionsTemplate : The database actions template
	 */
	public PHPVariablesDatabaseActionsTemplate() {

	}

	/**
	 * Returns the PHP Database actions template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		
		return super.openTemplate(templateFilePath);
	}

	/**
	 * Returns the PHP Database actions template
	 */
	@Override
	public String getTemplate(){
		return super.getTemplate();
	}

	/**
	 * Returns the PHP Database actions template file path
	 */
	@Override
	public String getTemplateFilePath() {
		
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_STATEMENTS_DATABASE_ACTIONS_TEMPLATE_FILE_PATH;
	}
}
