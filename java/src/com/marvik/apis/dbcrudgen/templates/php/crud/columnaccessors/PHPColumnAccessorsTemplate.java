package com.marvik.apis.dbcrudgen.templates.php.crud.columnaccessors;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.php.crud.PHPCrudTemplates;

public class PHPColumnAccessorsTemplate extends PHPCrudTemplates{
	
	public PHPColumnAccessorsTemplate() {

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
		
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_COLUMNS_ACCESSORS_TEMPLATE_FILE_PATH;
	}
}
