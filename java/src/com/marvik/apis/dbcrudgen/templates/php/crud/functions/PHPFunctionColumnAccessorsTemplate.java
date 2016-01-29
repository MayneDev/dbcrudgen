package com.marvik.apis.dbcrudgen.templates.php.crud.functions;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.php.crud.PHPCrudTemplates;

public class PHPFunctionColumnAccessorsTemplate extends PHPCrudTemplates{
	
	public PHPFunctionColumnAccessorsTemplate() {

	}

	/*
	 * Returns the PHP columns access template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the PHP columns access template
	 */
	@Override
	public String getTemplate(){
		return super.getTemplate();
	}

	/*
	 * Returns the PHP columns access template file path
	 */
	@Override
	public String getTemplateFilePath() {
		
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_FUNCTION_COLUMNS_ACCESSORS_TEMPLATE_FILE_PATH;
	}
}
