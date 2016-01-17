package com.marvik.apis.dbcrudgen.templates.java;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

public class JavaVariableGrammarTemplate extends CrudTemplates {

	@Override
	public String getTemplateFilePath() {
		return TemplatesFilePath.JavaTemplatesFilePath.JAVA_VARIABLE_GRAMMAR_TEMPLATE_FILEPATH;
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
		// TODO Auto-generated method stub
		return super.openTemplate(templateFilePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.templates.CrudTemplates#getTemplate()
	 */
	@Override
	public String getTemplate() throws IOException {
		try {
			return super.getTemplate();
		} catch (IOException e) {
			throw new IOException("Invalid Template Path [" + getTemplateFilePath() + "]");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.templates.CrudTemplates#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
