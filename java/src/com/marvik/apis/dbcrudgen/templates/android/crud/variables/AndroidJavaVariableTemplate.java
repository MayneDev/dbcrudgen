package com.marvik.apis.dbcrudgen.templates.android.crud.variables;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.templates.java.JavaVariableGrammarTemplate;

public class AndroidJavaVariableTemplate extends JavaVariableGrammarTemplate {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marvik.apis.dbcrudgen.templates.java.JavaVariableGrammarTemplate#
	 * getTemplateFilePath()
	 */
	@Override
	public String getTemplateFilePath() {

		return super.getTemplateFilePath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marvik.apis.dbcrudgen.templates.java.JavaVariableGrammarTemplate#
	 * openTemplate(java.lang.String)
	 */
	@Override
	public String openTemplate(String templateFilePath) throws IOException {

		return super.openTemplate(templateFilePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marvik.apis.dbcrudgen.templates.java.JavaVariableGrammarTemplate#
	 * getTemplate()
	 */
	@Override
	public String getTemplate() {
		try {
			return super.getTemplate();
		} catch (IOException e) {
			e.initCause(new Throwable("Invalid Template Path [" + getTemplateFilePath() + "]"));
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marvik.apis.dbcrudgen.templates.java.JavaVariableGrammarTemplate#
	 * toString()
	 */
	@Override
	public String toString() {

		return super.toString();
	}

}
