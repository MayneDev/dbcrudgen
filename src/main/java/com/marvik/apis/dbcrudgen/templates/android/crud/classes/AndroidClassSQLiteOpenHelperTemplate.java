/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.android.crud.classes;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

/**
 * @author victor
 *
 */
public class AndroidClassSQLiteOpenHelperTemplate extends AndroidCRUDTemplates {

	public AndroidClassSQLiteOpenHelperTemplate() {

	}

	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the columns CRUD template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/*
	 * Returns the columns CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		// TODO Auto-generated method stub
		return TemplatesFilePath.AndroidTemplatesFilePaths.ANDROID_CLASS_SQLITE_OPEN_HELPER_TEMPLATE_FILE_PATH;
	}

}
