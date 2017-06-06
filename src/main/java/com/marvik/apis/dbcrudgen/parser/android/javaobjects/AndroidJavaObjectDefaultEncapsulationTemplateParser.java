package com.marvik.apis.dbcrudgen.parser.android.javaobjects;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidJavaObjectDefaultEncapsulationTemplateParser extends AndroidTemplatesParser {

    public String createAndroidJavaObjectDefaultAccessorSourceCode(String className, String androidDatatype, String objectName) {

        String template = getAndroidJavaObjectDefaultEncapsulationTemplate().getTemplate();

        return parseAndroidJavaObjectDefaultAccessorSourceCode(template, className, androidDatatype, objectName);
    }

    private String parseAndroidJavaObjectDefaultAccessorSourceCode(String template, String className, String androidDatatype,
                                                                   String objectName) {
        String objectToJavaBeansClass = NativeUtils.toJavaBeansClass(objectName);

        return template.replace(TemplateTags.Android.CLASS_NAME, className)
                .replace(TemplateTags.Android.DATATYPE, androidDatatype)
                .replace(TemplateTags.Android.JAVA_BEANS_OBJECT, objectToJavaBeansClass)
                .replace(TemplateTags.Android.OBJECT, objectName);
    }

}
