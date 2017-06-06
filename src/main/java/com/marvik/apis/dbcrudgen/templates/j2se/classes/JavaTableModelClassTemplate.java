/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.j2se.classes;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.j2se.crud.J2SECRUDTemplates;

/**
*Created on Feb 10, 2016-3:52:26 AM by victor
*/

/**
 * @author victor
 *
 */
public class JavaTableModelClassTemplate  extends J2SECRUDTemplates {

	public JavaTableModelClassTemplate() {

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

		return TemplatesFilePath.JavaTemplatesFilePath.JAVA_CLASS_TABLE_MODEL_TEMPLATE_FILE_PATH;
	}
}
