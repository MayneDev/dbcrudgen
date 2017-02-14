package com.marvik.apis.dbcrudgen.parser.android;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.TemplatesParser;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassContentProviderTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassDatabaseTablesTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassSQLTableTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassSQLiteOpenHelperTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassTableCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassTableModelTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.interfaces.AndroidInterfaceCrudOperationsTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.methods.AndroidMethodColumnsCrudDataTypeFloatTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.methods.AndroidMethodColumnsCrudDataTypeGenericTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.methods.AndroidMethodColumnsCrudDataTypeIntTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.methods.AndroidMethodColumnsCrudDataTypeLongTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.methods.AndroidMethodColumnsCrudDataTypeStringTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.methods.AndroidMethodColumnsCrudDefaultTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementAddUriMatcherTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementContentProviderSQLDeleteTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementContentProviderSQLInsertTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementContentProviderSQLQueryTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementContentProviderSQLUpdateTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementContentValuesPutTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementSQLTableColumnStatementTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.variables.AndroidJavaVariableTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.variables.AndroidVariableSQLTableColumnTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.variables.AndroidVariableSQLTableCreateSQLTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.variables.AndroidVariableUriMatcherCodeTemplate;
import com.marvik.apis.dbcrudgen.templates.android.javaobject.encapsulation.AndroidJavaObjectDefaultEncapsulationTemplate;
import com.marvik.apis.dbcrudgen.templates.android.javaobject.encapsulation.AndroidJavaObjectGetterEncapsulationTemplate;
import com.marvik.apis.dbcrudgen.templates.android.javaobject.encapsulation.AndroidJavaObjectSetterEncapsulationTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidTemplatesParser extends TemplatesParser {

    /**
     * AndroidClassContentProviderTemplate
     */
    private AndroidClassContentProviderTemplate androidClassContentProviderTemplate;

    /**
     * AndroidClassDatabaseTablesTemplate
     */
    private AndroidClassDatabaseTablesTemplate androidClassDatabaseTablesTemplate;

    /**
     * AndroidClassTableModelTemplate
     */
    private AndroidClassTableModelTemplate androidClassTableModelTemplate;

    /**
     * AndroidClassSQLiteOpenHelperTemplate
     */
    private AndroidClassSQLiteOpenHelperTemplate androidClassSQLiteOpenHelperTemplate;

    /**
     * AndroidClassSQLTableTemplate
     */
    private AndroidClassSQLTableTemplate androidClassSQLTableTemplate;

    /**
     * AndroidClassTableCrudTemplate
     */
    private AndroidClassTableCrudTemplate androidClassTableCrudTemplate;

    /**
     * AndroidInterfaceCrudOperationsTemplate
     */
    private AndroidInterfaceCrudOperationsTemplate androidInterfaceCrudOperationsTemplate;

    /**
     * AndroidMethodColumnsCrudDataTypeFloatTemplate
     */
    private AndroidMethodColumnsCrudDataTypeFloatTemplate androidMethodColumnsCrudDataTypeFloatTemplate;

    /**
     * AndroidMethodColumnsCrudDataTypeGenericTemplate
     */
    private AndroidMethodColumnsCrudDataTypeGenericTemplate androidMethodColumnsCrudDataTypeGenericTemplate;

    /**
     * AndroidMethodColumnsCrudDataTypeIntTemplate
     */
    private AndroidMethodColumnsCrudDataTypeIntTemplate androidMethodColumnsCrudDataTypeIntTemplate;

    /**
     * AndroidMethodColumnsCrudDataTypeLongTemplate
     */
    private AndroidMethodColumnsCrudDataTypeLongTemplate androidMethodColumnsCrudDataTypeLongTemplate;

    /**
     * AndroidMethodColumnsCrudDataTypeStringTemplate
     */
    private AndroidMethodColumnsCrudDataTypeStringTemplate androidMethodColumnsCrudDataTypeStringTemplate;

    /**
     * AndroidMethodColumnsCrudDefaultTemplate
     */
    private AndroidMethodColumnsCrudDefaultTemplate androidMethodColumnsCrudDefaultTemplate;

    /**
     * AndroidStatementAddUriMatcherTemplate
     */
    private AndroidStatementAddUriMatcherTemplate androidStatementAddUriMatcherTemplate;

    /**
     * AndroidStatementContentProviderSQLDeleteTemplate
     */
    private AndroidStatementContentProviderSQLDeleteTemplate androidStatementContentProviderSQLDeleteTemplate;

    /**
     * AndroidStatementContentProviderSQLInsertTemplate
     */
    private AndroidStatementContentProviderSQLInsertTemplate androidStatementContentProviderSQLInsertTemplate;

    /**
     * AndroidStatementContentProviderSQLQueryTemplate
     */
    private AndroidStatementContentProviderSQLQueryTemplate androidStatementContentProviderSQLQueryTemplate;

    /**
     * AndroidStatementContentProviderSQLUpdateTemplate
     */
    private AndroidStatementContentProviderSQLUpdateTemplate androidStatementContentProviderSQLUpdateTemplate;

    /**
     * J2SEMYSQLStatementSQLTableColumnStatementTemplate
     */
    private AndroidStatementSQLTableColumnStatementTemplate androidStatementSQLTableColumnStatementTemplate;

    /**
     * AndroidStatementContentValuesPutTemplate
     */
    private AndroidStatementContentValuesPutTemplate androidStatementContentValuesPutTemplate;

    /**
     * AndroidVariableSQLTableColumnTemplate
     */
    private AndroidVariableSQLTableColumnTemplate androidVariableSQLTableColumnTemplate;

    /**
     * AndroidVariableSQLTableCreateSQLTemplate
     */
    private AndroidVariableSQLTableCreateSQLTemplate androidVariableSQLTableCreateSQLTemplate;

    /**
     * AndroidVariableUriMatcherCodeTemplate
     */
    private AndroidVariableUriMatcherCodeTemplate androidVariableUriMatcherCodeTemplate;

    /**
     * AndroidJavaVariableTemplate
     */
    AndroidJavaVariableTemplate androidJavaVariableTemplate;

    /**
     * AndroidJavaObjectDefaultEncapsulationTemplate
     */
    AndroidJavaObjectDefaultEncapsulationTemplate androidJavaObjectDefaultEncapsulationTemplate;

    /**
     * AndroidJavaObjectGetterEncapsulationTemplate
     */
    AndroidJavaObjectGetterEncapsulationTemplate androidJavaObjectGetterEncapsulationTemplate;

    /**
     * AndroidJavaObjectSetterEncapsulationTemplate
     */
    AndroidJavaObjectSetterEncapsulationTemplate androidJavaObjectSetterEncapsulationTemplate;

    /**
     * AndroidTemplatesParser - Class that parses templates into actual
     * data/source code
     */

    public AndroidTemplatesParser() {
        androidClassContentProviderTemplate = new AndroidClassContentProviderTemplate();
        androidClassDatabaseTablesTemplate = new AndroidClassDatabaseTablesTemplate();
        androidClassSQLiteOpenHelperTemplate = new AndroidClassSQLiteOpenHelperTemplate();
        androidClassSQLTableTemplate = new AndroidClassSQLTableTemplate();
        androidClassTableCrudTemplate = new AndroidClassTableCrudTemplate();
        androidClassTableModelTemplate = new AndroidClassTableModelTemplate();
        androidJavaObjectDefaultEncapsulationTemplate = new AndroidJavaObjectDefaultEncapsulationTemplate();
        androidJavaObjectGetterEncapsulationTemplate = new AndroidJavaObjectGetterEncapsulationTemplate();
        androidJavaObjectSetterEncapsulationTemplate = new AndroidJavaObjectSetterEncapsulationTemplate();
        androidJavaVariableTemplate = new AndroidJavaVariableTemplate();
        androidInterfaceCrudOperationsTemplate = new AndroidInterfaceCrudOperationsTemplate();
        androidMethodColumnsCrudDataTypeFloatTemplate = new AndroidMethodColumnsCrudDataTypeFloatTemplate();
        androidMethodColumnsCrudDataTypeGenericTemplate = new AndroidMethodColumnsCrudDataTypeGenericTemplate();
        androidMethodColumnsCrudDataTypeIntTemplate = new AndroidMethodColumnsCrudDataTypeIntTemplate();
        androidMethodColumnsCrudDataTypeLongTemplate = new AndroidMethodColumnsCrudDataTypeLongTemplate();
        androidMethodColumnsCrudDataTypeStringTemplate = new AndroidMethodColumnsCrudDataTypeStringTemplate();
        androidMethodColumnsCrudDefaultTemplate = new AndroidMethodColumnsCrudDefaultTemplate();
        androidStatementAddUriMatcherTemplate = new AndroidStatementAddUriMatcherTemplate();
        androidStatementContentProviderSQLDeleteTemplate = new AndroidStatementContentProviderSQLDeleteTemplate();
        androidStatementContentProviderSQLInsertTemplate = new AndroidStatementContentProviderSQLInsertTemplate();
        androidStatementContentProviderSQLQueryTemplate = new AndroidStatementContentProviderSQLQueryTemplate();
        androidStatementContentProviderSQLUpdateTemplate = new AndroidStatementContentProviderSQLUpdateTemplate();
        androidStatementSQLTableColumnStatementTemplate = new AndroidStatementSQLTableColumnStatementTemplate();
        androidStatementContentValuesPutTemplate = new AndroidStatementContentValuesPutTemplate();
        androidVariableSQLTableColumnTemplate = new AndroidVariableSQLTableColumnTemplate();
        androidVariableSQLTableCreateSQLTemplate = new AndroidVariableSQLTableCreateSQLTemplate();
        androidVariableUriMatcherCodeTemplate = new AndroidVariableUriMatcherCodeTemplate();

    }

    /**
     * @return the androidClassContentProviderTemplate
     */
    public AndroidClassContentProviderTemplate getAndroidClassContentProviderTemplate() {
        return androidClassContentProviderTemplate;
    }

    /**
     * @return the androidClassDatabaseTablesTemplate
     */
    public AndroidClassDatabaseTablesTemplate getAndroidClassDatabaseTablesTemplate() {
        return androidClassDatabaseTablesTemplate;
    }

    /**
     * @return the androidClassSQLiteOpenHelperTemplate
     */
    public AndroidClassSQLiteOpenHelperTemplate getAndroidClassSQLiteOpenHelperTemplate() {
        return androidClassSQLiteOpenHelperTemplate;
    }

    /**
     * @return the androidClassSQLTableTemplate
     */
    public AndroidClassSQLTableTemplate getAndroidClassSQLTableTemplate() {
        return androidClassSQLTableTemplate;
    }

    /**
     * @return the androidClassTableCrudTemplate
     */
    public AndroidClassTableCrudTemplate getAndroidClassTableCrudTemplate() {
        return androidClassTableCrudTemplate;
    }

    /**
     * @return the AndroidClassTableModelTemplate
     */
    public AndroidClassTableModelTemplate getAndroidClassTableModelTemplate() {
        return androidClassTableModelTemplate;
    }

    /**
     * @return the androidInterfaceCrudOperationsTemplate
     */
    public AndroidInterfaceCrudOperationsTemplate getAndroidInterfaceCrudOperations() {
        return androidInterfaceCrudOperationsTemplate;
    }

    /**
     * @return the androidMethodColumnsCrudDataTypeFloatTemplate
     */
    public AndroidMethodColumnsCrudDataTypeFloatTemplate getAndroidMethodColumnsCrudDataTypeFloatTemplate() {
        return androidMethodColumnsCrudDataTypeFloatTemplate;
    }

    /**
     * @return the androidMethodColumnsCrudDataTypeGenericTemplate
     */
    public AndroidMethodColumnsCrudDataTypeGenericTemplate getAndroidMethodColumnsCrudDataTypeGenericTemplate() {
        return androidMethodColumnsCrudDataTypeGenericTemplate;
    }

    /**
     * @return the androidMethodColumnsCrudDataTypeIntTemplate
     */
    public AndroidMethodColumnsCrudDataTypeIntTemplate getAndroidMethodColumnsCrudDataTypeIntegerTemplate() {
        return androidMethodColumnsCrudDataTypeIntTemplate;
    }

    /**
     * @return the androidMethodColumnsCrudDataTypeLongTemplate
     */
    public AndroidMethodColumnsCrudDataTypeLongTemplate getAndroidMethodColumnsCrudDataTypeLongTemplate() {
        return androidMethodColumnsCrudDataTypeLongTemplate;
    }

    /**
     * @return the androidMethodColumnsCrudDataTypeStringTemplate
     */
    public AndroidMethodColumnsCrudDataTypeStringTemplate getAndroidMethodColumnsCrudDataTypeStringTemplate() {
        return androidMethodColumnsCrudDataTypeStringTemplate;
    }

    /**
     * @return the androidMethodColumnsCrudDefaultTemplate
     */
    public AndroidMethodColumnsCrudDefaultTemplate getAndroidMethodColumnsCrudDefaultTemplate() {
        return androidMethodColumnsCrudDefaultTemplate;
    }

    /**
     * @return the androidStatementAddUriMatcherTemplate
     */
    public AndroidStatementAddUriMatcherTemplate getAndroidStatementAddUriMatcherTemplate() {
        return androidStatementAddUriMatcherTemplate;
    }

    /**
     * @return the androidStatementContentProviderSQLDeleteTemplate
     */
    public AndroidStatementContentProviderSQLDeleteTemplate getAndroidStatementContentProviderSQLDeleteTemplate() {
        return androidStatementContentProviderSQLDeleteTemplate;
    }

    /**
     * @return the androidStatementContentProviderSQLInsertTemplate
     */
    public AndroidStatementContentProviderSQLInsertTemplate getAndroidStatementContentProviderSQLInsertTemplate() {
        return androidStatementContentProviderSQLInsertTemplate;
    }

    /**
     * @return the androidStatementContentProviderSQLQueryTemplate
     */
    public AndroidStatementContentProviderSQLQueryTemplate getAndroidStatementContentProviderSQLQueryTemplate() {
        return androidStatementContentProviderSQLQueryTemplate;
    }

    /**
     * @return the androidStatementContentProviderSQLUpdateTemplate
     */
    public AndroidStatementContentProviderSQLUpdateTemplate getAndroidStatementContentProviderSQLUpdateTemplate() {
        return androidStatementContentProviderSQLUpdateTemplate;
    }

    /**
     * @return the androidStatementSQLTableColumnStatementTemplate
     */
    public AndroidStatementSQLTableColumnStatementTemplate getAndroidStatementSQLTableColumnStatementTemplate() {
        return androidStatementSQLTableColumnStatementTemplate;
    }

    /**
     * @return the androidStatementContentValuesPutTemplate
     */
    public AndroidStatementContentValuesPutTemplate getAndroidStatmentContentValuesPutTemplate() {
        return androidStatementContentValuesPutTemplate;
    }

    /**
     * @return the androidVariableSQLTableColumnTemplate
     */
    public AndroidVariableSQLTableColumnTemplate getAndroidVariableSQLTableColumnTemplate() {
        return androidVariableSQLTableColumnTemplate;
    }

    /**
     * @return the androidVariableSQLTableCreateSQLTemplate
     */
    public AndroidVariableSQLTableCreateSQLTemplate getAndroidVariableSQLTableCreateSQLTemplate() {
        return androidVariableSQLTableCreateSQLTemplate;
    }

    /**
     * @return the androidVariableUriMatcherCodeTemplate
     */
    public AndroidVariableUriMatcherCodeTemplate getAndroidVariableUriMatcherCodeTemplate() {
        return androidVariableUriMatcherCodeTemplate;
    }

    /**
     * @return AndroidJavaVariableTemplate
     */
    public AndroidJavaVariableTemplate getAndroidJavaVariableTemplate() {
        return androidJavaVariableTemplate;
    }

    /**
     * @return AndroidInterfaceCrudOperationsTemplate
     */
    public AndroidInterfaceCrudOperationsTemplate getAndroidInterfaceCrudOperationsTemplate() {
        return androidInterfaceCrudOperationsTemplate;
    }

    /**
     * @return the androidStatementContentValuesPutTemplate
     */
    public AndroidStatementContentValuesPutTemplate getAndroidStatementContentValuesPutTemplate() {
        return androidStatementContentValuesPutTemplate;
    }

    /**
     * @return the androidJavaObjectDefaultEncapsulationTemplate
     */
    public AndroidJavaObjectDefaultEncapsulationTemplate getAndroidJavaObjectDefaultEncapsulationTemplate() {
        return androidJavaObjectDefaultEncapsulationTemplate;
    }

    /**
     * @return the androidJavaObjectGetterEncapsulationTemplate
     */
    public AndroidJavaObjectGetterEncapsulationTemplate getAndroidJavaObjectGetterEncapsulationTemplate() {
        return androidJavaObjectGetterEncapsulationTemplate;
    }

    /**
     * @return the androidJavaObjectSetterEncapsulationTemplate
     */
    public AndroidJavaObjectSetterEncapsulationTemplate getAndroidJavaObjectSetterEncapsulationTemplate() {
        return androidJavaObjectSetterEncapsulationTemplate;
    }

    /**
     * @return AndroidMethodColumnsCrudDataTypeIntTemplate
     */
    public AndroidMethodColumnsCrudDataTypeIntTemplate getAndroidMethodColumnsCrudDataTypeIntTemplate() {
        return androidMethodColumnsCrudDataTypeIntTemplate;
    }

    /**
     * AndroidTemplatesParser#parsePackageName Formats a package path to a valid
     * package name
     *
     * @param packagePath
     * @return String Package Name
     */
    public String parseJavaPackage(String packageFilePath) {
        return NativeUtils.parseJavaPackage(packageFilePath);
    }

    /**
     * AndroidTemplatesParser#createTableColumnReference - Creates a reference
     * to the table column
     *
     * @param tableJavaBeansName
     * @param columnName
     * @return
     */
    public String createTableColumnReference(String tableJavaBeansName, String columnName) {
        return AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME + TemplateTags.TAG_PRINTING_CHAR_DOT
                + tableJavaBeansName + TemplateTags.TAG_PRINTING_CHAR_DOT + columnName.toUpperCase();
    }

    /**
     * AndroidTemplatesParser#getAndroidColumnQueryCrudTemplate
     * <p>
     * Return the right CRUD for a table column based on the data type of the
     * column
     *
     * @param columnDatatype
     * @return CrudTemplates
     */
    protected CrudTemplates getAndroidColumnQueryCrudTemplate(String columnDatatype) {

        columnDatatype = NativeUtils.toLetters(columnDatatype);

        // Boolean
        if (columnDatatype.equalsIgnoreCase("Boolean")) {
            return new AndroidMethodColumnsCrudDataTypeIntTemplate();
        }
        // Byte
        if (columnDatatype.equalsIgnoreCase("Byte")) {
            return new AndroidMethodColumnsCrudDataTypeIntTemplate();
        }
        // Integer
        if (columnDatatype.equalsIgnoreCase("Integer")) {
            return new AndroidMethodColumnsCrudDataTypeIntTemplate();
        }
        // Integer
        if (columnDatatype.equalsIgnoreCase("Int")) {
            return new AndroidMethodColumnsCrudDataTypeIntTemplate();
        }
        // Date
        if (columnDatatype.equalsIgnoreCase("Date")) {
            // TO DO ADD SOURCE CODE
        }
        // Double
        if (columnDatatype.equalsIgnoreCase("Double")) {
            return new AndroidMethodColumnsCrudDataTypeFloatTemplate();
        }
        // Float
        if (columnDatatype.equalsIgnoreCase("Float")) {
            return new AndroidMethodColumnsCrudDataTypeFloatTemplate();
        }
        // Long
        if (columnDatatype.equalsIgnoreCase("Long")) {
            return new AndroidMethodColumnsCrudDataTypeLongTemplate();
        }
        // String
        if (columnDatatype.equalsIgnoreCase("String")) {
            return new AndroidMethodColumnsCrudDataTypeStringTemplate();
        }
        // String
        if (columnDatatype.equalsIgnoreCase("VARCHAR")) {
            return new AndroidMethodColumnsCrudDataTypeStringTemplate();
        }

        if (columnDatatype.equalsIgnoreCase("Class")) {
            return new AndroidMethodColumnsCrudDataTypeGenericTemplate();
        }
        return new AndroidMethodColumnsCrudDataTypeGenericTemplate();
    }

    /**
     * AndroidTemplatesParser#getAndroidColumnQueryCrudTemplate
     * <p>
     * Return the right CRUD for a table column based on the data type of the
     * column
     *
     * @param columnDatatype
     * @return CrudTemplates
     */
    protected String getAndroidObjectDataType(String columnDatatype) {

        columnDatatype = NativeUtils.toLetters(columnDatatype);

        // Boolean
        if (columnDatatype.equalsIgnoreCase("Boolean")) {
            return "boolean";
        }
        // Byte
        if (columnDatatype.equalsIgnoreCase("Byte")) {
            return "byte";
        }
        // Integer
        if (columnDatatype.equalsIgnoreCase("Integer")) {
            return "int";
        }
        // Integer
        if (columnDatatype.equalsIgnoreCase("Int")) {
            return "int";

        }
        // Date
        if (columnDatatype.equalsIgnoreCase("Date")) {
            // TO DO ADD SOURCE CODE
            return "String";
        }
        // Double
        if (columnDatatype.equalsIgnoreCase("Double")) {
            return "double";
        }
        // Float
        if (columnDatatype.equalsIgnoreCase("Float")) {
            return "float";
        }
        // Long
        if (columnDatatype.equalsIgnoreCase("Long")) {
            return "long";
        }
        // MYSQL Long
        if (columnDatatype.equalsIgnoreCase("mediumtext")) {
            return "long";
        }
        // String
        if (columnDatatype.equalsIgnoreCase("String")) {
            return "String";
        }
        // Varchar
        if (columnDatatype.equalsIgnoreCase("varchar")) {
            return "String";
        }
        // Text
        if (columnDatatype.equalsIgnoreCase("text")) {
            return "String";
        }
        // Timestamp
        if (columnDatatype.equalsIgnoreCase("timestamp")) {
            return "String";
        }

        System.err.println("COULD NOT GET THE DATATYPE FOR [" + columnDatatype + "]");

        return "Object";
    }

    protected String getColumnsCursorItemsGetMethodTemplate(String dataType) {

        dataType = NativeUtils.toLetters(dataType);

        if (dataType.equalsIgnoreCase("BOOLEAN")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_BOOLEAN;
        }
        if (dataType.equalsIgnoreCase("INT")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_INT;
        }
        if (dataType.equalsIgnoreCase("SMALLINT")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_INT;
        }
        if (dataType.equalsIgnoreCase("BIGINT")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_INT;
        }
        if (dataType.equalsIgnoreCase("INTEGER")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_INT;
        }
        if (dataType.equalsIgnoreCase("LONG")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_LONG;
        }
        if (dataType.equalsIgnoreCase("STRING")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING;
        }
        if (dataType.equalsIgnoreCase("TEXT")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING;
        }
        if (dataType.equalsIgnoreCase("DATE")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING;
        }
        if (dataType.equalsIgnoreCase("DATETIME")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING;
        }
        if (dataType.equalsIgnoreCase("TIMESTAMP")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING;
        }
        if (dataType.equalsIgnoreCase("MEDIUMTEXT")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_LONG;
        }
        if (dataType.equalsIgnoreCase("VARCHAR")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING;
        }
        if (dataType.equalsIgnoreCase("FLOAT")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_FLOAT;
        }
        if (dataType.equalsIgnoreCase("DOUBLE")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_FLOAT;
        }
        if (dataType.equalsIgnoreCase("BYTE")) {
            return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_INT;
        }

        return SimpleTemplates.Android.ANDROID_TABLE_COLUMNS_CURSOR_ITEMS_GETTER_METHOD_STRING;
    }


}
