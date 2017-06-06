/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.j2se.classes;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.j2se.crud.J2SECRUDTemplates;

/**
*Created on Feb 10, 2016-12:17:52 AM by victor
*/

/**
 * @author victor
 *
 */
public class RecordsUpdateExceptionTemplate extends J2SECRUDTemplates {

	public RecordsUpdateExceptionTemplate() {

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
		return TemplatesFilePath.JavaTemplatesFilePath.RECORDS_UPDATE_EXCEPTION_TEMPLATE_FILE_PATH;
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
