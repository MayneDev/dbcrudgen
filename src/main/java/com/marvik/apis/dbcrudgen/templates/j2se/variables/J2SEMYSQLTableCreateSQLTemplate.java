/**
 * 
 */
package com.marvik.apis.dbcrudgen.templates.j2se.variables;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

/**
*Created on Feb 10, 2016-2:29:18 AM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEMYSQLTableCreateSQLTemplate  extends AndroidCRUDTemplates {

	public J2SEMYSQLTableCreateSQLTemplate() {

	}

	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return TemplatesFilePath.JavaTemplatesFilePath.J2SE_MYSQL_TABLE_CREATE_SQL_TEMPLATE_FILE_PATH;
	}

}
