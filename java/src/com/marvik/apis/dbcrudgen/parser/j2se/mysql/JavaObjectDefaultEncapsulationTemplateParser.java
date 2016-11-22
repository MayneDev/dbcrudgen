/**
 *
 */
package com.marvik.apis.dbcrudgen.parser.j2se.mysql;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.java.j2se.J2SETemplatesParser;
import com.marvik.apis.dbcrudgen.templates.java.object.encapsulation.JavaObjectDefaultEncapsulationTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
 * Created on Feb 10, 2016-3:43:40 AM by victor
 */

/**
 * @author victor
 */
public class JavaObjectDefaultEncapsulationTemplateParser extends J2SETemplatesParser {

    public String createJavaObjectDefaultAccessorSourceCode(String className, String androidDatatype, String objectName) {

        String template = new JavaObjectDefaultEncapsulationTemplate().getTemplate();

        return parseJavaObjectDefaultAccessorSourceCode(template, className, androidDatatype, objectName);
    }

    private String parseJavaObjectDefaultAccessorSourceCode(String template, String className, String javaDataType, String objectName) {
        String objectToJavaBeansClass = NativeUtils.toJavaBeansClass(objectName);

        return template.replace(TemplateTags.Java.CLASS_NAME, className)
                .replace(TemplateTags.Java.DATATYPE, javaDataType)
                .replace(TemplateTags.Java.JAVA_BEANS_OBJECT, objectToJavaBeansClass)
                .replace(TemplateTags.Java.OBJECT, objectName);
    }

}
