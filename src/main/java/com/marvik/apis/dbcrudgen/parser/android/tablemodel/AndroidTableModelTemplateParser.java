package com.marvik.apis.dbcrudgen.parser.android.tablemodel;

import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters.JavaDelimiter;
import com.marvik.apis.dbcrudgen.core.platforms.java.object.accessibility.JavaObjectAccessibility;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.parser.android.javaobjects.AndroidJavaObjectDefaultEncapsulationTemplateParser;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidTableModelTemplateParser extends AndroidTemplatesParser {

    public String createSourceCode(
            AndroidJavaObjectDefaultEncapsulationTemplateParser javaObjectDefaultEncapsulationTemplateParser,
            String tableModelPackageName, Table table) {

        String tableName = table.getTableName();

        String tableClassName = NativeUtils.toJavaBeansClass(tableName);

        String template = getAndroidClassTableModelTemplate().getTemplate();

        String classVariables = "";
        String classVariablesInit = "";
        String classVariablesAccessors = "";
        String classConstructorVariables = "";

        TableColumn[] tableColumn = table.getTableColumnsAll();

        for (int i = 0; i < tableColumn.length; i++) {

            String columnDatatype = tableColumn[i].getDataType().getDataType();
            String objectName = tableColumn[i].getColumnName();
            String androidDatatype = getAndroidObjectDataType(columnDatatype);

            objectName = NativeUtils.toJavaBeansVariable(objectName);


            classVariables += NativeUtils.createJavaVariable(JavaObjectAccessibility.PRIVATE, androidDatatype,
                    objectName, JavaDelimiter.SEMICOLON);

            JavaDelimiter classConstructorVariablesJavaDelimeter = JavaDelimiter.COMMA;


            if (i >= (tableColumn.length - 1)) {
                classConstructorVariablesJavaDelimeter = JavaDelimiter.NONE;
            }

            classConstructorVariables += NativeUtils.createJavaVariable(JavaObjectAccessibility.DEFAULT,
                    androidDatatype, objectName, classConstructorVariablesJavaDelimeter);

            classVariablesInit += NativeUtils.createJavaClassVariableInitStatement(objectName);

            classVariablesAccessors += javaObjectDefaultEncapsulationTemplateParser
                    .createAndroidJavaObjectDefaultAccessorSourceCode(tableClassName + "Info", androidDatatype, objectName);

        }

        return template.replace(TemplateTags.Android.PACKAGE_NAME, tableModelPackageName)
                .replace(TemplateTags.Android.CLASS_NAME, tableClassName)
                .replace(TemplateTags.Android.TABLE_COLUMN_CLASS_VARIABLES, classVariables)
                .replace(TemplateTags.Android.INIT_CONSTRUCTOR_PARAMS, classVariablesInit)
                .replace(TemplateTags.Android.CONSTRUCTOR_PARAMS, classConstructorVariables)
                .replace(TemplateTags.Android.CLASS_VARIABLES_ENCAPSULATOR_METHODS, classVariablesAccessors);
    }
}