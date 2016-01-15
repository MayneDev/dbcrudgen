package com.marvik.apis.dbcrudgen.templates.php;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

public class PHPColumnAccessorsTemplate extends CrudTemplates{
	
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
		
		try {
			return super.getTemplate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Returns the PHP columns access template file path
	 */
	@Override
	public String getTemplateFilePath() {
		
		return TemplatesFilePath.PHPTemplatesFilePaths.PHP_COLUMNS_ACCESSORS_TEMPLATE_FILE_PATH;
	}
}
