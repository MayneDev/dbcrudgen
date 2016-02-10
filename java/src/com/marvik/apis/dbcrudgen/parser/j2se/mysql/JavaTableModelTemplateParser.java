/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.j2se.mysql;

import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters.JavaDelimiter;
import com.marvik.apis.dbcrudgen.core.platforms.java.object.accessibility.JavaObjectAccessibility;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.TemplatesParser;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.JavaTableModelClassTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
*Created on Feb 10, 2016-4:05:13 AM by victor
*/

/**
 * @author victor
 *
 */
public class JavaTableModelTemplateParser extends TemplatesParser{
	public String createSourceCode(
			JavaObjectDefaultEncapsulationTemplateParser javaObjectDefaultEncapsulationTemplateParser,
			String tableModelPackageName, Table table) {

		String tableName = table.getTableName();

		String tableClassName = NativeUtils.toJavaBeansClass(tableName);

		String template = new JavaTableModelClassTemplate().getTemplate();

		String classVariables = "";
		String classVariablesInit = "";
		String classVariablesAccessors = "";
		String classConstructorVariables = "";

		TableColumn[] tableColumn = table.getTableColumnsAll();

		for (int i = 0; i < tableColumn.length; i++) {

			String columnDatatype = tableColumn[i].getDataType().getDataType();
			String objectName = tableColumn[i].getColumnName();
			String androidDatatype = getJavaObjectDataType(columnDatatype);

			objectName = NativeUtils.toJavaBeansVariable(objectName);

			classVariables += NativeUtils.createJavaVariable(JavaObjectAccessibility.PRIVATE, androidDatatype,
					objectName, JavaDelimiter.SEMICOLON);

			JavaDelimiter classConstructorVariablesJavaDelimeter = JavaDelimiter.COMMA;
			

			if (i >= (tableColumn.length - 1)) {
				classConstructorVariablesJavaDelimeter = JavaDelimiter.NONE;
			}

			classConstructorVariables += NativeUtils.createJavaVariable(JavaObjectAccessibility.DEFAULT,
					androidDatatype, objectName, classConstructorVariablesJavaDelimeter);

			classVariablesInit += NativeUtils.createNewJavaClassVariableInitStatement(objectName);

			classVariablesAccessors += javaObjectDefaultEncapsulationTemplateParser
					.createJavaObjectDefaultAccessorSourceCode(androidDatatype, objectName);

		}

		return template.replace(TemplateTags.Java.PACKAGE_NAME, tableModelPackageName)
				.replace(TemplateTags.Java.CLASS_NAME, tableClassName)
				.replace(TemplateTags.Java.TABLE_COLUMN_CLASS_VARIABLES, classVariables)
				.replace(TemplateTags.Java.INIT_CONSTRUCTOR_PARAMS, classVariablesInit)
				.replace(TemplateTags.Java.CONSTRUCTOR_PARAMS, classConstructorVariables)
				.replace(TemplateTags.Java.CLASS_VARIABLES_ENCAPSULATOR_METHODS, classVariablesAccessors);
	}

}
