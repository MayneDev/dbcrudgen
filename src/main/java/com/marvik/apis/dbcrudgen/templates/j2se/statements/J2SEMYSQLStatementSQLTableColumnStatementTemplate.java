package com.marvik.apis.dbcrudgen.templates.j2se.statements;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.j2se.crud.J2SECRUDTemplates;

public class J2SEMYSQLStatementSQLTableColumnStatementTemplate extends J2SECRUDTemplates {

	public J2SEMYSQLStatementSQLTableColumnStatementTemplate() {

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
		// TODO Auto-generated method stub
		return TemplatesFilePath.JavaTemplatesFilePath.J2SE_MYSQl_STATEMENT_SQL_TABLE_COLUMN_STATEMENT_TEMPLATE_FILE_PATH;
	}

}