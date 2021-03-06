package com.marvik.apis.dbcrudgen.templates.android.crud.classes;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

public class AndroidClassTableCrudTemplate extends AndroidCRUDTemplates {

	public AndroidClassTableCrudTemplate() {

	}

	@Override
	public String openTemplate(String templateFilePath) throws IOException {

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

		return TemplatesFilePath.AndroidTemplatesFilePaths.ANDROID_CLASS_TABLE_CRUD_TEMPLATE_FILE_PATH;
	}

}
