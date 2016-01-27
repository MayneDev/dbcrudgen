package com.marvik.apis.dbcrudgen.templates.android.crud.classes;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

public class AndroidClassTransactionsManagerTemplate  extends AndroidCRUDTemplates {

	public AndroidClassTransactionsManagerTemplate() {

	}

	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the class transaction template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/*
	 * Returns the class transaction template file path
	 */
	@Override
	public String getTemplateFilePath() {

		return TemplatesFilePath.AndroidTemplatesFilePaths.ANDROID_CLASS_TRANSACTIONS_MANAGER_TEMPLATE_FILE_PATH;
	}
}