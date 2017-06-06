package com.marvik.apis.dbcrudgen.templates.android.crud.methods;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

public class AndroidMethodsTransactionsTableCrudClassGetterTemplate   extends AndroidCRUDTemplates {

	public AndroidMethodsTransactionsTableCrudClassGetterTemplate() {

	}

	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the method for the table crud getter in the transaction template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/*
	 * Returns the method for the table crud getter in the transaction template
	 */
	@Override
	public String getTemplateFilePath() {

		return TemplatesFilePath.AndroidTemplatesFilePaths.ANDROID_METHOD_TRANSACTIONS_TABLE_CRUD_CLASS_GETTER_TEMPLATE_FILE_PATH;
	}
}