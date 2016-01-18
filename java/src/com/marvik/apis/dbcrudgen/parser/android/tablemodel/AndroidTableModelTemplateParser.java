package com.marvik.apis.dbcrudgen.parser.android.tablemodel;

import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters.JavaDelimiter;
import com.marvik.apis.dbcrudgen.core.platforms.java.object.accessibility.JavaObjectAccessibility;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.parser.android.javaobjects.AndroidJavaObjectDefaultEncapsulationTemplateParser;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.android.javaobject.encapsulation.AndroidJavaObjectDefaultEncapsulationTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidTableModelTemplateParser extends AndroidTemplatesParser {

	public String createSourceCode(
			AndroidJavaObjectDefaultEncapsulationTemplateParser androidJavaObjectDefaultEncapsulationTemplateParser,
			String tableModelPackageName, Table table) {

		String tableName = table.getTableName();

		String tableClassName = NativeUtils.toJavaBeansClass(tableName);

		String template = getAndroidClassTableModelTemplate().getTemplate();

		String classVariables = "";
		String classVariablesInit = "";
		String classVariablesAccessors = "";
		String classConstructorVariables = "";

		TableColumn[] tableColumn = table.getColumns();

		for (int i = 0; i < tableColumn.length; i++) {

			String columnDatatype = tableColumn[i].getDataType().getDataType();
			String objectName = tableColumn[i].getColumnName();
			String androidDatatype = getAndroidObjectDataType(columnDatatype);

			classVariables += NativeUtils.createJavaVariable(JavaObjectAccessibility.PRIVATE, androidDatatype,
					objectName, JavaDelimiter.SEMICOLON);
			
			classConstructorVariables += NativeUtils.createJavaVariable(JavaObjectAccessibility.DEFAULT,
					androidDatatype, objectName, JavaDelimiter.COMMA);
			if (i == (tableColumn.length - 1)) {
				classConstructorVariables += NativeUtils.createJavaVariable(JavaObjectAccessibility.DEFAULT,
						androidDatatype, objectName, JavaDelimiter.NONE);
			}
			
			classVariablesInit += NativeUtils.createJavaClassVariableInitStatement(objectName);

			classVariablesAccessors += androidJavaObjectDefaultEncapsulationTemplateParser
					.createAndroidJavaObjectDefaultAccessorSourceCode(androidDatatype, objectName);
		}

		return template.replace(TemplateTags.Android.PACKAGE_NAME, tableModelPackageName)
				.replace(TemplateTags.Android.CLASS_NAME, tableClassName)
				.replace(TemplateTags.Android.TABLE_COLUMN_CLASS_VARIABLES, classVariables)
				.replace(TemplateTags.Android.INIT_CONSTRUCTOR_PARAMS, classVariablesInit)
				.replace(TemplateTags.Android.CONSTRUCTOR_PARAMS, classConstructorVariables)
				.replace(TemplateTags.Android.CLASS_VARIABLES_ENCAPSULATOR_METHODS, classVariablesAccessors);
	}
}