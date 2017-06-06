package com.marvik.apis.dbcrudgen.templates.android.javaobject.encapsulation;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.java.object.encapsulation.JavaObjectSetterEncapsulationTemplate;

public class AndroidJavaObjectSetterEncapsulationTemplate extends JavaObjectSetterEncapsulationTemplate {

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
	 * @see com.marvik.apis.dbcrudgen.templates.CrudTemplates#getTemplate()
	 */
	@Override
	public String getTemplate() {

		return super.getTemplate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.templates.CrudTemplates#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String getTemplateFilePath() {
		return TemplatesFilePath.JavaTemplatesFilePath.JAVA_OBJECT_SETTER_TEMPLATE_FILEPATH;
	}

}
