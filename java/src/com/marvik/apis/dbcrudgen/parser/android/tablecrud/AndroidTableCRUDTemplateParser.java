package com.marvik.apis.dbcrudgen.parser.android.tablecrud;

import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters.JavaDelimiter;
import com.marvik.apis.dbcrudgen.core.platforms.java.object.accessibility.JavaObjectAccessibility;
import com.marvik.apis.dbcrudgen.core.platforms.sql.grammar.SQLGrammar;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.natives.syntax.Syntax;
import com.marvik.apis.dbcrudgen.natives.syntax.Syntax.PrintingChars;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.MYSQLDataTypes;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementContentValuesPutTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;
import com.marvik.apis.dbcrudgen.utilities.Utils;

public class AndroidTableCRUDTemplateParser extends AndroidTemplatesParser {

    public String createSourceCode(String packageFilePath, String tablesSchemasPackage, String tableModelsPackage,
                                   Table table) {

        String tableName = table.getTableName();

        String tableCrudTemplate = getAndroidClassTableCrudTemplate().getTemplate();

        // add package name
        tableCrudTemplate = parsePackageName(tableCrudTemplate, packageFilePath);

        // add tables schemas import
        tableCrudTemplate = parseTablesSchemasClassImport(tableCrudTemplate, tablesSchemasPackage);

        // add tables model info class import
        tableCrudTemplate = parseTableModelInfoClassImport(tableCrudTemplate, tableModelsPackage, table.getTableName());

        // add database class name
        tableCrudTemplate = parseTableClassName(tableCrudTemplate, tableName);

        // add table content uri link
        tableCrudTemplate = parseTableContentUri(tableCrudTemplate, tableName);

        // add column query methods
        tableCrudTemplate = parseColumnsQueryMethods(tableCrudTemplate, table);

        // add table custom insert method
        tableCrudTemplate = parseTableCustomInsertMethod(tableCrudTemplate, table);

        // add table custom update method

        // add table custom query method
        tableCrudTemplate = prepareTableCustomQueryMethods(tableCrudTemplate, table);

        // add table custom search method
        tableCrudTemplate = prepareTableCustomSearchMethod(tableCrudTemplate, table);

        // add table custom delete method

        // add missing table model class statements in the query and the search
        // methods-- > BY NOW THE TEMPLATE IS
        // MISSING THIS --> POOR PROTOTYPE
        tableCrudTemplate = prepareTableModelClassParametersInQueryAndSearchMethods(tableCrudTemplate, table);

        // add table row query method
        tableCrudTemplate = prepareTableColumnQueryMethod(tableCrudTemplate, table);

        return tableCrudTemplate;
    }

    /**
     * This method adds the method used to query a specific row from a table
     * using the rows primary key
     *
     * @param tableCrudTemplate
     * @param table
     * @return
     */
    private String prepareTableColumnQueryMethod(String tableCrudTemplate, Table table) {

        // Add primary key parameters in the query method
        tableCrudTemplate = parsePrimaryKeyDatatypeAndParameters(tableCrudTemplate, table);

        // Add primary key selection statement
        tableCrudTemplate = parsePrimaryKeySelectionStatement(tableCrudTemplate, table);

        return tableCrudTemplate;
    }

    /**
     * Add primary key selection statement
     *
     * @param tableCrudTemplate
     * @param table
     * @return
     */
    private String parsePrimaryKeySelectionStatement(String tableCrudTemplate, Table table) {
        String primaryKeyReference = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME + PrintingChars.DOT
                + NativeUtils.toJavaBeansClass(table.getTableName()) + PrintingChars.DOT
                + table.getPrimaryKey().getColumnName().toUpperCase();
        return tableCrudTemplate.replace(TemplateTags.Android.COLUMN_REFERENCE, primaryKeyReference);
    }

    /**
     * Adds the primary key datatype and object to the method used to fetch a
     * row from the database
     *
     * @param tableCrudTemplate
     * @param table
     * @return
     */
    private String parsePrimaryKeyDatatypeAndParameters(String tableCrudTemplate, Table table) {

        String primaryKeyObject = table.getPrimaryKey().getColumnName();
        String primaryKeyDatatype = table.getPrimaryKey().getDataType().getDataType();
        String primaryKeyJavaDatatype = getAndroidObjectDataType(primaryKeyDatatype);

        return tableCrudTemplate.replace(TemplateTags.Android.PRIMARY_KEY_DATATYPE, primaryKeyJavaDatatype)
                .replace(TemplateTags.Android.PRIMARY_KEY_OBJECT, primaryKeyObject);
    }

    private String prepareTableModelClassParametersInQueryAndSearchMethods(String tableCrudTemplate, Table table) {
        String tableName = table.getTableName();
        String tableModelClass = NativeUtils.toJavaBeansClass(tableName) + TemplateTags.Android.INFO;

        TableColumn[] tableColumns = table.getTableColumnsAll();

        String constructorParams = "";
        for (int i = 0; i < tableColumns.length; i++) {
            String columnName = tableColumns[i].getColumnName();
            String columnObject = NativeUtils.toJavaBeansVariable(columnName);
            constructorParams += columnObject;

            if (i < tableColumns.length - 1) {
                constructorParams += ",";
            }
        }
        return tableCrudTemplate.replace(TemplateTags.Android.TABLE_INFO_CLASS, tableModelClass)
                .replace(TemplateTags.Android.CONTRUCTOR_PARAMS, constructorParams);
    }

    // prepare table custom query method
    private String prepareTableCustomQueryMethods(String tableCrudTemplate, Table table) {
        return parseTableColumnCursorItemGetMethods(tableCrudTemplate, table);
    }

    // prepare table custom search method
    private String prepareTableCustomSearchMethod(String tableCrudTemplate, Table table) {
        String sqlSearchStatement = SimpleTemplates.Android.ANDROID_DATABASE_SQL_SEARCH_SELECTION_TEMPLATE;
        String searchStatement = createAndroidSqlSelectionSearchStatement(sqlSearchStatement, table);
        return tableCrudTemplate.replace(TemplateTags.Android.ANDROID_SQL_SEARCH_SELECTION_STATEMENT, searchStatement);
    }

    // Create android sql selection search statement
    private String createAndroidSqlSelectionSearchStatement(String sqlSearchStatement, Table table) {
        String sqlSearchSelection = "";

        TableColumn[] tableColumns = table.getTableColumnsAll();

        for (int i = 0; i < tableColumns.length; i++) {

            String tablesSchemasClass = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME;
            String tableName = NativeUtils.toJavaBeansClass(table.getTableName());
            String columnName = tableColumns[i].getColumnName();

            sqlSearchSelection += parseSQLSelectionStatement(sqlSearchStatement, tablesSchemasClass, tableName,
                    columnName);

            if (i < tableColumns.length - 1) {
                sqlSearchSelection += Syntax.ArithmeticChars.ADD + Syntax.PrintingChars.ESCAPED_DOUBLE_QUOTES
                        + SQLGrammar.Keywords.OR + Syntax.PrintingChars.ESCAPED_DOUBLE_QUOTES
                        + Syntax.ArithmeticChars.ADD;
            }
        }
        return sqlSearchSelection;
    }

    // parse the sql selection statement to the template
    private String parseSQLSelectionStatement(String sqlSearchStatement, String tablesSchemasClass, String tableName,
                                              String columnName) {

        return sqlSearchStatement.replace(TemplateTags.Android.TABLES_SCHEMAS_CLASS, tablesSchemasClass)
                .replace(TemplateTags.Android.TABLE_NAME, tableName)
                .replace(TemplateTags.Android.COLUMN_NAME, columnName.toUpperCase());
    }

    // parse table columns cursor item get methods to the template
    private String parseTableColumnCursorItemGetMethods(String tableCrudTemplate, Table table) {
        String columnsCursorItemGetMethods = createColumnsCursorItemsGetMethods(table);
        return tableCrudTemplate.replace(TemplateTags.Android.CURSOR_ITEMS_GETTER_METHODS, columnsCursorItemGetMethods);
    }

    // Create Methods to read cursor items
    private String createColumnsCursorItemsGetMethods(Table table) {

        TableColumn[] tableColumns = table.getTableColumnsAll();

        String tableColumnsCursorGetters = "";

        for (TableColumn tableColumn : tableColumns) {
            String tableName = table.getTableName();
            String dataType = tableColumn.getDataType().getDataType();
            String columnName = tableColumn.getColumnName();

            tableColumnsCursorGetters += createColumnsCursorItemsGetMethod(tableName, dataType, columnName);
        }
        return tableColumnsCursorGetters;
    }

    // Create Method to read cursor items
    private String createColumnsCursorItemsGetMethod(String tableName, String dataType, String columnName) {

        String template = getColumnsCursorItemsGetMethodTemplate(dataType);
        String tablesSchemasClass = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME;
        String tableClassName = NativeUtils.toJavaBeansClass(tableName);
        String columnObject = NativeUtils.toJavaBeansVariable(columnName);

        return template.replace(TemplateTags.Android.COLUMN_OBJECT, columnObject)
                .replace(TemplateTags.Android.TABLES_SCHEMAS_CLASS, tablesSchemasClass)
                .replace(TemplateTags.Android.TABLE_NAME, tableClassName)
                .replace(TemplateTags.Android.COLUMN_NAME, columnName.toUpperCase());
    }

    // add table custom insert method
    private String parseTableCustomInsertMethod(String tableCrudTemplate, Table table) {

        String tableName = table.getTableName();
        String tableModelInfoClass = NativeUtils.toJavaBeansClass(tableName) + TemplateTags.Android.INFO;
        String tableModelInfoClassObject = NativeUtils.toJavaBeansVariable(tableModelInfoClass);
        String tableColumnsContentValuesPutStatements = prepareTableColumnContentValuesPutStatements(table,
                tableModelInfoClassObject);
        return tableCrudTemplate.replace(TemplateTags.Android.TABLE_MODEL_INFO_CLASS, tableModelInfoClass)
                .replace(TemplateTags.Android.CONTENT_VALUES_PUT_STATEMENTS, tableColumnsContentValuesPutStatements)
                .replace(TemplateTags.Android.TABLE_MODEL_OBJECT, tableModelInfoClassObject);
    }

    // Prepare table column content values put statements
    private String prepareTableColumnContentValuesPutStatements(Table table, String tableModelInfoClassObject) {

        String tableName = table.getTableName();

        String databaseTableReference = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME
                + TemplateTags.TAG_PRINTING_CHAR_DOT + NativeUtils.toJavaBeansClass(tableName);

        String contentValuesPutStatements = "";

        for (TableColumn tableColumn : table.getColumns()) {
            String columnName = tableColumn.getColumnName();
            String tableColumnReference = databaseTableReference + TemplateTags.TAG_PRINTING_CHAR_DOT
                    + columnName.toUpperCase();
            String tableColumnObject = tableModelInfoClassObject;

            contentValuesPutStatements += parseTableColumnContentValuesPutStatements(tableColumnReference,
                    tableColumnObject, NativeUtils.toJavaBeansClass(columnName));
        }
        return contentValuesPutStatements;
    }

    private String parseTableColumnContentValuesPutStatements(String tableColumnReference, String tableModelObject,
                                                              String columnName) {

        String template = new AndroidStatementContentValuesPutTemplate().getTemplate();

        return template.replace(TemplateTags.Android.TABLE_COLUMN_DEFINITION, tableColumnReference)
                .replace(TemplateTags.Android.TABLE_MODEL_OBJECT, tableModelObject)
                .replace(TemplateTags.Android.COLUMN_NAME, columnName);
    }

    // add column query methods
    private String parseColumnsQueryMethods(String tableCrudTemplate, Table table) {

        String columnQueryMethods = "";

        // query methods for non - keys column in a table
        for (TableColumn tableColumn : table.getColumns()) {
            //Skip the primary key column
            if (tableColumn.isPrimaryKey()) continue;

            columnQueryMethods += generateColumnQueryMethod(tableColumn, table);
        }

        // query methods for primary keys column in a table
        columnQueryMethods += generatePrimaryKeyColumnQueryMethod(table);

        return tableCrudTemplate.replace(TemplateTags.Android.COLUMN_SPECIFIC_QUERY_METHODS, columnQueryMethods);
    }

    // query methods for primary keys column in a table
    private String generatePrimaryKeyColumnQueryMethod(Table table) {

        PrimaryKey primaryKey = table.getPrimaryKey();
        String primaryKeyColumn = primaryKey.getColumnName();
        String primaryKeyDataType = primaryKey.getDataType().getDataType();

        String primaryKeyColumnReference = createTableColumnReference(
                NativeUtils.toJavaBeansClass(table.getTableName()), primaryKeyColumn);

        // Create variables for all the table columns
        String tableColumnsVariable = "";
        String tableColumnsReference = "";
        String tableColumnsObjects = "";

        TableColumn[] columns = table.getColumns();

        for (int i = 0; i < columns.length; i++) {

            //Skip the primary key column
            if (columns[i].isPrimaryKey()) continue;

            String columnName = columns[i].getColumnName();
            String dataType = columns[i].getDataType().getDataType();
            MYSQLDataTypes mysqlDataType = Utils.parseMysqlDatatype(dataType);
            String javaDataType = Utils.generateJavaQueryMethodReturnType(mysqlDataType);

            String objectName = NativeUtils.toJavaBeansVariable(columnName);
            JavaDelimiter javaDelimeter = JavaDelimiter.NONE;

            tableColumnsReference += createTableColumnReference(NativeUtils.toJavaBeansClass(table.getTableName()),
                    columnName);

            // Append the object in the String.valueOf(#Object) template
            tableColumnsObjects += NativeUtils.parseStringDefaultParser(objectName);

            if (i < columns.length - 1) {
                javaDelimeter = JavaDelimiter.COMMA;
                tableColumnsReference += ",";
                tableColumnsObjects += ",";
            }

            // DO NOT MOVE THIS METHOD FROM HERE - -THE GENERATED CODE WILL NOT
            // HAVE ANY JAVA DELIMETER - BECAUSE IT WILL USE THE
            // JAVA_DELIMETER#NULL
            tableColumnsVariable += NativeUtils.createJavaVariable(JavaObjectAccessibility.DEFAULT, javaDataType,
                    objectName, javaDelimeter);
        }

        String template = getAndroidColumnQueryCrudTemplate(primaryKeyDataType).getTemplate();

        return parseQueriedColumnQueryArtificats(template, primaryKeyColumn, primaryKeyColumnReference,
                tableColumnsReference, tableColumnsVariable, tableColumnsObjects);
    }

    // generate the query method for the column hierarchy
    private String generateColumnQueryMethod(TableColumn tableColumn, Table table) {
        DataType dataType = tableColumn.getDataType();
        MYSQLDataTypes mysqlDataType = Utils.parseMysqlDatatype(dataType.getDataType());
        String mClass = Utils.generateJavaQueryMethodReturnType(mysqlDataType);
        CrudTemplates crudTemplate = getAndroidColumnQueryCrudTemplate(mClass);
        return prepareQueriedColumnQueryArtifacts(crudTemplate.getTemplate().toString(), table, tableColumn);

    }

    private String prepareQueriedColumnQueryArtifacts(String template, Table table, TableColumn tableColumn) {

        // Add queried column
        String queriedColumn = tableColumn.getColumnName();

        String tableJavaBeansName = NativeUtils.toJavaBeansClass(table.getTableName());

        String queriedColumnReference = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME
                + TemplateTags.TAG_PRINTING_CHAR_DOT + tableJavaBeansName + TemplateTags.TAG_PRINTING_CHAR_DOT
                + tableColumn.getColumnName().toUpperCase();

        // Get the table primary key
        PrimaryKey primaryKey = table.getPrimaryKey();
        if(primaryKey == null){
            System.err.println("The table "+table.getTableName()+" does not have a primary key column");
        }
        String primaryKeyColumnName = primaryKey.getColumnName();

        // Add primary key column parameters
        String primaryKeyParamObject = NativeUtils
                .parseStringDefaultParser(NativeUtils.toJavaBeansVariable(primaryKeyColumnName));

        // Add primary key column reference
        String primaryKeyColumnReference = createTableColumnReference(tableJavaBeansName, primaryKey.getPrimaryKey());

        // Add primary key variable
        String primaryKeyParamVariableDeclaration = NativeUtils.createJavaVariable(JavaObjectAccessibility.DEFAULT,
                primaryKey.getDataType().getDataType(), primaryKey.getPrimaryKey(), JavaDelimiter.NONE);

        return parseQueriedColumnQueryArtificats(template, queriedColumn, queriedColumnReference,
                primaryKeyColumnReference, primaryKeyParamVariableDeclaration, primaryKeyParamObject);
    }

    private String parseQueriedColumnQueryArtificats(String template, String queriedColumn,
                                                     String queriedColumnReference, String indexedColumnReference, String methodParamsVariables,
                                                     String methodParamsObjects) {

        return template.replace(TemplateTags.Android.FUNCTION_PARAMS_VARIABLES, methodParamsVariables)

                .replace(TemplateTags.Android.FUNCTION_PARAMS_KEYS, indexedColumnReference)

                .replace(TemplateTags.Android.FUNCTION_PARAMS_VALUES, methodParamsObjects)

                .replace(TemplateTags.Android.FUNCTION_PARAMS, methodParamsObjects)

                // Add queried column comment
                .replace(TemplateTags.Android.QUERIED_TABLE_COLUMN_NAME, NativeUtils.toJavaBeansVariable(queriedColumn))

                // Add queried column reference
                .replace(TemplateTags.Android.QUERIED_TABLE_COLUMN_REFERENCE, queriedColumnReference)

                // Add queried column
                .replace(TemplateTags.Android.QUERIED_COLUMN, NativeUtils.toJavaBeansClass(queriedColumn));
    }

    private String parseTableContentUri(String tableCrudTemplate, String tableName) {
        String tableDefinitionLink = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME
                + TemplateTags.TAG_PRINTING_CHAR_DOT + NativeUtils.toJavaBeansClass(tableName);
        return tableCrudTemplate.replace(TemplateTags.Android.TABLE_DEFINITION_LINK, tableDefinitionLink);
    }

    // add database class name
    private String parseTableClassName(String tableCrudTemplate, String tableName) {
        String javaBeansTableClass = NativeUtils.toJavaBeansClass(tableName);
        return tableCrudTemplate.replace(TemplateTags.Android.DATABASE_TABLE_CLASS, javaBeansTableClass);
    }

    // add tables schemas import
    private String parseTableModelInfoClassImport(String tableCrudTemplate, String tableModelInfoPackage,
                                                  String tableName) {
        tableModelInfoPackage = parseJavaPackage(tableModelInfoPackage);
        String tableModelInfoClass = tableModelInfoPackage + TemplateTags.TAG_PRINTING_CHAR_DOT
                + NativeUtils.toJavaBeansClass(tableName) + TemplateTags.Android.INFO;
        return tableCrudTemplate.replace(TemplateTags.Android.TABLE_MODEL_CLASS, tableModelInfoClass);
    }

    // add tables schemas import
    private String parseTablesSchemasClassImport(String tableCrudTemplate, String tableSchemasPackage) {
        tableSchemasPackage = parseJavaPackage(tableSchemasPackage);
        String tableSchemasClass = tableSchemasPackage + TemplateTags.TAG_PRINTING_CHAR_DOT
                + AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME;
        return tableCrudTemplate.replace(TemplateTags.Android.DATABASE_TABLES_CLASS, tableSchemasClass);
    }

    // add package name
    private String parsePackageName(String tableCrudTemplate, String packageFilePath) {
        String packageName = parseJavaPackage(packageFilePath);
        return tableCrudTemplate.replace(TemplateTags.Android.PACKAGE_NAME, packageName);
    }

}
