package com.marvik.apis.dbcudgen.java.templates.php;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;

public class PHPColumnAccessorsTemplate extends CrudTemplates{
	
	public PHPColumnAccessorsTemplate() {

	}

	/*
	 * Returns the PHP columns access template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the PHP columns access template
	 */
	@Override
	public String getTemplate(){
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return TemplatesFilePath.PHP_COLUMNS_ACCESSORS_TEMPLATE_FILE_PATH;
	}
}
