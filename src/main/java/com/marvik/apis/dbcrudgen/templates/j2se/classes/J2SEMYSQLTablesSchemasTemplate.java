/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.j2se.classes;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.j2se.crud.J2SECRUDTemplates;

/**
*Created on Feb 10, 2016-2:17:00 AM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEMYSQLTablesSchemasTemplate extends J2SECRUDTemplates {

	public J2SEMYSQLTablesSchemasTemplate() {

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
		return TemplatesFilePath.JavaTemplatesFilePath.J2SE_MYSQL_TABLE_SCHEMAS_CLASS_TEMPLATE_FILE_PATH;
	}

}
