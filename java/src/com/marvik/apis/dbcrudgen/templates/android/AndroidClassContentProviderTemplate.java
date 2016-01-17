/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.android;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

/**
 * @author victor
 *
 */
public class AndroidClassContentProviderTemplate extends CrudTemplates {

	public AndroidClassContentProviderTemplate() {

	}

	
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the columns CRUD template
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
	 * Returns the columns CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		
		return TemplatesFilePath.AndroidTemplatesFilePaths.ANDROID_CLASS_CONTENT_PROVIDER_TEMPLATE_FILE_PATH;
	}

}
