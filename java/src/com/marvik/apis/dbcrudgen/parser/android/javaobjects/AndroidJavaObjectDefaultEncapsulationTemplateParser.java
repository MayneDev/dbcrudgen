package com.marvik.apis.dbcrudgen.parser.android.javaobjects;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidJavaObjectDefaultEncapsulationTemplateParser extends AndroidTemplatesParser {

	public String createAndroidJavaObjectDefaultAccessorSourceCode(String androidDatatype, String objectName) {

		String template = getAndroidJavaObjectDefaultEncapsulationTemplate().getTemplate();

		return parseAndroidJavaObjectDefaultAccessorSourceCode(template, androidDatatype, objectName);
	}

	private String parseAndroidJavaObjectDefaultAccessorSourceCode(String template, String androidDatatype,
			String objectName) {
		String objectToJavaBeansClass = NativeUtils.toJavaBeansClass(objectName);

		return template.replace(TemplateTags.Android.DATATYPE, androidDatatype)
				.replace(TemplateTags.Android.JAVA_BEANS_OBJECT, objectToJavaBeansClass)
				.replace(TemplateTags.Android.OBJECT, objectName);
	}

}
