package com.marvik.apis.dbcrudgen.templates.php.crud.dbactions;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.php.crud.PHPCrudTemplates;

public class PHPDatabaseActionsTemplate  extends PHPCrudTemplates {
	
	/**
	 * PHPDatabaseActionsTemplate : The database actions template
	 */
	public PHPDatabaseActionsTemplate() {

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
		
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_DATABASE_ACTIONS_TEMPLATE_FILE_PATH;
	}
}
