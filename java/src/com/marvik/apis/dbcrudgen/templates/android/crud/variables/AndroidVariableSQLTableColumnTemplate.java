package com.marvik.apis.dbcrudgen.templates.android.crud.variables;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

public class AndroidVariableSQLTableColumnTemplate extends AndroidCRUDTemplates {

	public AndroidVariableSQLTableColumnTemplate() {

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
		return TemplatesFilePath.AndroidTemplatesFilePaths.ANDROID_VARIABLE_SQL_TABLE_COLUMN_TEMPLATE_FILE_PATH;
	}

}
