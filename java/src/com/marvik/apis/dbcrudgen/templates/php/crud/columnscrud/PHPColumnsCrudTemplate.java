package com.marvik.apis.dbcrudgen.templates.php.crud.columnscrud;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.php.crud.PHPCrudTemplates;

public class PHPColumnsCrudTemplate extends PHPCrudTemplates {

	public PHPColumnsCrudTemplate() {

	}

	/*
	 * Returns the columns CRUD template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the columns CRUD template
	 */
	@Override
	public String getTemplate(){
		return super.getTemplate();
	}

	/*
	 * Returns the columns CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_COLUMNS_CRUD_TEMPLATE_FILE_PATH;
	}
}
