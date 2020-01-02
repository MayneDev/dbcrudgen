package com.marvik.apis.dbcrudgen.parser.android.httpresponse;

import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.JavaGrammar;
import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters.JavaDelimiter;
import com.marvik.apis.dbcrudgen.core.platforms.java.object.accessibility.JavaObjectAccessibility;
import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.parser.android.javaobjects.AndroidJavaObjectDefaultEncapsulationTemplateParser;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidTableHttpResponseTemplateParser extends AndroidTemplatesParser {

    public String createSourceCode(AndroidProjectConfiguration androidProjectConfiguration,
                                   AndroidJavaObjectDefaultEncapsulationTemplateParser javaObjectDefaultEncapsulationTemplateParser,
                                   String tableModelPackageName, Table table) {

        String tableName = table.getTableName();

        String tableClassName = NativeUtils.toJavaBeansClass(tableName);

        String template = getAndroidClassTableHttpResponseTemplate().getTemplate();

        String classVariablesAccessors = "";
        String serializedCols = "";
        String constructorParams = "";

        TableColumn[] tableColumn = table.getTableColumnsAll();

        for (int i = 0; i < tableColumn.length; i++) {

            String columnName = tableColumn[i].getColumnName();
            String columnDatatype = tableColumn[i].getDataType().getDataType();

            String androidDatatype = getAndroidObjectDataType(columnDatatype);
            String objectName = NativeUtils.toJavaBeansVariable(columnName);


            JavaDelimiter classConstructorVariablesJavaDelimeter = JavaDelimiter.COMMA;

            String tableColumnSerializedTemplate = SimpleTemplates.Android.COLUMN_SERIALIZER;
            serializedCols += createSerializedColumn(tableColumnSerializedTemplate, columnName, androidDatatype, objectName);

            if (i >= (tableColumn.length - 1)) {
                classConstructorVariablesJavaDelimeter = JavaDelimiter.NONE;
            }

            constructorParams += createConstructorParam(objectName, classConstructorVariablesJavaDelimeter);


            classVariablesAccessors += javaObjectDefaultEncapsulationTemplateParser
                    .createAndroidJavaObjectDefaultAccessorSourceCode(tableClassName + "HttpResponse", androidDatatype, objectName);
        }

        String tableModelClass = getTableModelInfoClass(androidProjectConfiguration, table.getTableName());

        return template.replace(TemplateTags.Android.PACKAGE_NAME, tableModelPackageName)
                .replace(TemplateTags.Android.CLASS_NAME, tableClassName)
                .replace(TemplateTags.Android.TABLE_MODEL_INFO_CLASS, tableModelClass)
                .replace(TemplateTags.Android.TABLE_COLUMN_CLASS_VARIABLES, classVariablesAccessors)
                .replace(TemplateTags.Android.CONSTRUCTOR_OBJECTS, constructorParams)
                .replace(TemplateTags.Android.TABLE_COLUMNS_SERIALIZED, serializedCols)
                .replace(TemplateTags.Android.CLASS_VARIABLES_ENCAPSULATOR_METHODS, classVariablesAccessors);
    }

    private String getTableModelInfoClass(AndroidProjectConfiguration androidProjectConfiguration, String tableName) {
        String packageName = androidProjectConfiguration.getPackageName();
        AndroidDatabaseConfiguration androidDatabaseConfiguration = androidProjectConfiguration.getAndroidContentProviderConfiguration().getAndroidDatabaseConfiguration();
        String modelClassesPackage = androidDatabaseConfiguration.getTablesInfosModelClassesPackage();

        String tableClassName = NativeUtils.toJavaBeansClass(tableName);
        return packageName + NativeTemplateTags.DOT + modelClassesPackage + NativeTemplateTags.DOT + tableClassName + "Info";
    }


    private String createConstructorParam(String objectName, JavaDelimiter javaDelimeter) {

        String javaVariable = objectName;

        switch (javaDelimeter) {
            case COMMA:
                javaVariable += ",";
                break;
            case SEMICOLON:
                javaVariable += ";";
                break;
            case DOT:
                javaVariable += ".";
                break;
            case NONE:

                javaVariable += "";
                break;
        }

        return javaVariable;

    }

    private String createSerializedColumn(String template, String columnName, String androidDatatype, String objectName) {
        return template.replace(TemplateTags.Android.COLUMN_NAME, columnName)
                .replace(TemplateTags.Android.DATATYPE, androidDatatype)
                .replace(TemplateTags.Android.OBJECT_NAME, objectName);
    }
}