package com.marvik.apis.dbcudgen.java.templates.php;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.java.io.FileStreamReader;
import com.marvik.apis.dbcudgen.java.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;

public class PHPColumnsCrudTemplate extends CrudTemplates {

	public PHPColumnsCrudTemplate() {

	}

	/*
	 * Returns the columns CRUD template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the columns CRUD template
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
	 * Returns the columns CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.PHP_COLUMNS_CRUD_TEMPLATE_FILE_PATH;
	}
}
