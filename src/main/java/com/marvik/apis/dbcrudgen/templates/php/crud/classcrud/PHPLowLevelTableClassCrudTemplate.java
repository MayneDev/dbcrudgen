package com.marvik.apis.dbcrudgen.templates.php.crud.classcrud;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

public class PHPLowLevelTableClassCrudTemplate extends CrudTemplates {

	public PHPLowLevelTableClassCrudTemplate() {

	}

	/*
	 * Returns the table class low level CRUD template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the table class low level CRUD template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/*
	 * Returns the table class low level CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_CLASS_TABLE_CRUD_LOW_LEVEL_TEMPLATE_FILE_PATH;
	}
}
