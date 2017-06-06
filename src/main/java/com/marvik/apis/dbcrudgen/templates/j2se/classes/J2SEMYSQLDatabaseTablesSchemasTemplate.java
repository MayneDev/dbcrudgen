/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.j2se.classes;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

/**
*Created on Feb 10, 2016-1:51:02 AM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEMYSQLDatabaseTablesSchemasTemplate extends AndroidCRUDTemplates {

	public J2SEMYSQLDatabaseTablesSchemasTemplate() {

	}

	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the template
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

	/*
	 * Returns the template file path
	 */
	@Override
	public String getTemplateFilePath() {

		return TemplatesFilePath.JavaTemplatesFilePath.J2SE_MYSQL_CLASS_DATABASE_TABLES_SCHEMAS_TEMPLATE_FILE_PATH;
	}
}