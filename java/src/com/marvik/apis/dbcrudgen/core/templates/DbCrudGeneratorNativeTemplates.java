package com.marvik.apis.dbcrudgen.core.templates;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.core.filepaths.templates.TemplateNativeFilePaths;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

public class DbCrudGeneratorNativeTemplates extends CrudTemplates {

	public DbCrudGeneratorNativeTemplates() {

	}

	/*
	 * Returns the PHP class file name template
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the PHP class file name template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/*
	 * Returns the PHP class file name template file path
	 */
	@Override
	public String getTemplateFilePath() {
		return TemplateNativeFilePaths.DB_CRUD_GEN_INFO_TEMPLATE_FILE_PATH;
	}
}
