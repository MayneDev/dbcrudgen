/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.j2se.classes;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.j2se.crud.J2SECRUDTemplates;

/**
*Created on Feb 10, 2016-8:24:45 AM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEMYSQLTableCrudTemplate extends J2SECRUDTemplates {

	public J2SEMYSQLTableCrudTemplate() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marvik.apis.dbcrudgen.templates.CrudTemplates#openTemplate(java.lang.
	 * String)
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		return super.openTemplate(templateFilePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marvik.apis.dbcrudgen.templates.CrudTemplates#getTemplateFilePath()
	 */
	@Override
	public String getTemplateFilePath() {
		return TemplatesFilePath.JavaTemplatesFilePath.J2SE_MYSQL_TABLE_CRUD_TEMPLATE_FILE_PATH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.templates.CrudTemplates#getTemplate()
	 */
	@Override
	public String getTemplate() {
		return super.getTemplate();
	}

}
