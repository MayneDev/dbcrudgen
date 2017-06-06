package com.marvik.apis.dbcrudgen.parser.php;

import com.marvik.apis.dbcrudgen.core.platforms.php.grammar.PHPGrammar;
import com.marvik.apis.dbcrudgen.core.templates.DbCrudGeneratorNativeTemplates;
import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.parser.TemplatesParser;
import com.marvik.apis.dbcrudgen.projects.php.configuration.PHPProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.php.filenames.PHPProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.php.crud.classcrud.PHPClassDatabaseConnectionTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.classcrud.PHPClassDatabaseUtilsTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.classcrud.PHPHighLevelTableClassCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.classcrud.PHPLowLevelTableClassCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.functions.PHPFunctionColumnAccessorsTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.functions.PHPFunctionColumnsCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.functions.PHPFunctionHighLevelFetchAssocGettersTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.functions.PHPFunctionsHighLevelPreparedInsertTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.functions.PHPFunctionsLowLevelPreparedInsertTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.statements.PHPStatementClassFileNameTemplate;
import com.marvik.apis.dbcrudgen.templates.php.crud.variables.PHPVariablesDatabaseActionsTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.sql.SQLTableFilenameTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags.DatabaseConnection;

public class PHPTemplatesParser extends TemplatesParser {

    private PHPLowLevelTableClassCrudTemplate phpTableClassCrudTemplate;
    private PHPFunctionColumnsCrudTemplate phpColumnsCrudTemplate;
    private PHPFunctionColumnAccessorsTemplate phpColumnAccessorsTemplate;
    private PHPStatementClassFileNameTemplate phpClassFileNameTemplate;
    private PHPVariablesDatabaseActionsTemplate phpDatabaseActionsTemplate;
    private PHPClassDatabaseConnectionTemplate phpDatabaseConnectionTemplate;
    private PHPClassDatabaseUtilsTemplate phpDatabaseUtilsTemplate;

    private SQLTableFilenameTemplate sqlTableFilenameTemplate;

    /**
     * PHPTemplatesParser - Parse templates
     */
    public PHPTemplatesParser() {

        phpTableClassCrudTemplate = new PHPLowLevelTableClassCrudTemplate();

        phpColumnsCrudTemplate = new PHPFunctionColumnsCrudTemplate();

        phpColumnAccessorsTemplate = new PHPFunctionColumnAccessorsTemplate();

        phpClassFileNameTemplate = new PHPStatementClassFileNameTemplate();

        phpDatabaseActionsTemplate = new PHPVariablesDatabaseActionsTemplate();

        phpDatabaseConnectionTemplate = new PHPClassDatabaseConnectionTemplate();

        phpDatabaseUtilsTemplate = new PHPClassDatabaseUtilsTemplate();

        sqlTableFilenameTemplate = new SQLTableFilenameTemplate();
    }

    /**
     * PHPFunctionColumnAccessorsTemplate
     *
     * @return PHPFunctionColumnAccessorsTemplate
     */
    public PHPFunctionColumnAccessorsTemplate getPhpColumnAccessorsTemplate() {
        return phpColumnAccessorsTemplate;
    }

    /**
     * @return PHPFunctionColumnsCrudTemplate
     */
    public PHPFunctionColumnsCrudTemplate getPhpColumnsCrudTemplate() {
        return phpColumnsCrudTemplate;
    }

    /**
     * @return PHPLowLevelTableClassCrudTemplate
     */
    public PHPLowLevelTableClassCrudTemplate getPhpTableClassCrudTemplate() {
        return phpTableClassCrudTemplate;
    }

    /**
     * @return PHPStatementClassFileNameTemplate
     */
    public PHPStatementClassFileNameTemplate getPhpClassFileNameTemplate() {
        return phpClassFileNameTemplate;
    }

    /**
     * PHPVariablesDatabaseActionsTemplate getPhpDatabaseActionsTemplate()
     *
     * @return PHPVariablesDatabaseActionsTemplate
     */
    public PHPVariablesDatabaseActionsTemplate getPhpDatabaseActionsTemplate() {
        return phpDatabaseActionsTemplate;
    }

    /**
     * PHPClassDatabaseConnectionTemplate getPhpDatabaseConnectionTemplate()
     *
     * @return phpDatabaseConnectionTemplate
     */
    public PHPClassDatabaseConnectionTemplate getPhpDatabaseConnectionTemplate() {
        return phpDatabaseConnectionTemplate;
    }

    /**
     * PHPClassDatabaseUtilsTemplate getPhpDatabaseUtilsTemplate()
     *
     * @return phpDatabaseUtilsTemplate
     */
    public PHPClassDatabaseUtilsTemplate getPhpDatabaseUtilsTemplate() {
        return phpDatabaseUtilsTemplate;
    }

    /**
     * SQLTableFilenameTemplate getSqlTableFilenameTemplate()
     *
     * @return sqlTableFilenameTemplate
     */
    public SQLTableFilenameTemplate getSQLTableFilenameTemplate() {
        return sqlTableFilenameTemplate;
    }

    /**
     * getDatabaseName
     *
     * @param database
     * @return DATABASE NAME
     */
    public String getDatabaseName(Database database) {
        return database.getDatabaseName();
    }

    /**
     * getTableName
     *
     * @param table
     * @return table name
     */
    public String getTableName(Table table) {
        return table.getTableName();
    }

    /**
     * getColumnName
     *
     * @param tableColumn
     * @return the column name
     */
    public String getColumnName(TableColumn tableColumn) {
        return tableColumn.getColumnName();
    }

    /**
     * getTableCrud -> Generates all the crud for a table
     *
     * @param projectDatabaseConnectionProperties
     * @param phpProjectConfiguration
     * @param table
     * @return
     */
    public String getTableCrud(PHPProjectConfiguration phpProjectConfiguration,
                               ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties, Table table) {

        String tableCrudTemplate = getPhpTableClassCrudTemplate().getTemplate();

        String tableName = table.getTableName();
        TableColumn[] columns = table.getColumns();

        String columnsCrud = generateColumnsCrudFunctions(table);

        String columnsAccessor = generateColumnAccessorMethods(columns);

        // Parse column accessors - replaces the column accessor templates with
        // the actual functions
        tableCrudTemplate = addColumnAccessorFunctions(tableCrudTemplate, columnsAccessor);

        String lowLevelPreparedInsertFunctionTemplate = new PHPFunctionsLowLevelPreparedInsertTemplate().getTemplate();
        String preparedInsertFunction = generatePreparedInsertFunction(lowLevelPreparedInsertFunctionTemplate, columns);

        tableCrudTemplate = parsePreparedInsertFunction(tableCrudTemplate, preparedInsertFunction);

        // Parse column crud functions - replaces the column crud templates with
        // the actual crud functions
        tableCrudTemplate = addColumnsQueryFunctions(tableCrudTemplate, columnsCrud);

        // Replace table name tag with the actual table name
        tableCrudTemplate = addTableName(tableCrudTemplate, tableName);

        // Replace the Dependencies URIs file path.
        tableCrudTemplate = addDependenciesURIs(phpProjectConfiguration, tableCrudTemplate);

        // Replace the class name tag with the actual class name which is
        // derived from the table name
        tableCrudTemplate = addClassName(tableCrudTemplate, table.getTableName());

        // Replace the template info with the template info
        tableCrudTemplate = addPHPDbCrudTemplateInfo(tableCrudTemplate);

        return tableCrudTemplate;
    }

    /**
     * Add Template Info
     *
     * @param tableCrudTemplate
     * @return PHP Db Crud Generator Info
     */
    private String addPHPDbCrudTemplateInfo(String tableCrudTemplate) {
        DbCrudGeneratorNativeTemplates dbCrudGeneratorNativeTemplates = new DbCrudGeneratorNativeTemplates();
        String templateInfo = dbCrudGeneratorNativeTemplates.getTemplate();
        return parsePHPDbCrudTemplateInfo(templateInfo, tableCrudTemplate);
    }

    /**
     * Adds the Db Crud Generator info to the generated script.
     *
     * @param templateInfo
     * @param tableCrudTemplate
     * @return PHP Db Crud Generator Template Info to the table sql
     */
    private String parsePHPDbCrudTemplateInfo(String templateInfo, String tableCrudTemplate) {

        return tableCrudTemplate.replace(TemplateTags.PHP.DB_CRUD_GENERATOR_TEMPLATE, templateInfo).replace(
                NativeTemplateTags.SYSTEM_WRITE_TIME,
                NativeUtils.getCurrentTime("EEE hh:mm:ss  dd/MM/yyy", System.currentTimeMillis()));
    }

    /**
     * addDependenciesURIs
     *
     * @param phpProjectConfiguration
     * @param tableCrudTemplate
     * @return
     */
    private String addDependenciesURIs(PHPProjectConfiguration phpProjectConfiguration, String tableCrudTemplate) {

        // Add dependencies uris for database utils
        String databaseUtilsStorageDirectory = phpProjectConfiguration.getPhpDatabaseAPIScriptsStorageDirectory();
        String databaseUtilsFileName = databaseUtilsStorageDirectory
                + PHPProjectFileNames.DATABASE_UTILS_SCRIPT_FILENAME;
        String dataActionsFileName = databaseUtilsStorageDirectory + PHPProjectFileNames.DATA_ACTIONS_SCRIPT_FILENAME;

        tableCrudTemplate = tableCrudTemplate.replace(TemplateTags.PHP.DATABASE_UTILS_FILE_PATH, databaseUtilsFileName);

        // Add dependencies uris for database connection files

        // Add dependencies for data actions scripts
        tableCrudTemplate = tableCrudTemplate.replace(TemplateTags.PHP.DATA_ACTIONS_FILE_PATH, dataActionsFileName);

        return tableCrudTemplate;
    }

    /**
     * Replaces the class name tag with the actual class name
     */
    private String addClassName(String tableCrudTemplate, String tableName) {
        String className = NativeUtils.toJavaBeansClass(tableName);
        return tableCrudTemplate.replace(TemplateTags.PHP.CLASS_NAME, className);
    }

    /**
     * Replaces the table name tag with the actual table name
     */
    private String addTableName(String tableCrudTemplate, String tableName) {
        return tableCrudTemplate.replace(TemplateTags.PHP.TABLE_NAME, tableName);
    }

    /**
     * Replaces the table name tag with the actual table name
     */
    private String parseTableClassName(String template, String tableName) {
        return template.replace(TemplateTags.PHP.TABLE_CLASS, tableName);
    }

    /**
     * Adds the column query functions to the class template
     */
    private String addColumnsQueryFunctions(String tableCrudTemplate, String columnsQueryFunctions) {
        return tableCrudTemplate.replace(TemplateTags.PHP.TABLE_COLUMNS_CRUD_FUNCTIONS, columnsQueryFunctions);
    }

    /**
     * Parse prepared insert method
     *
     * @param tableCrudTemplate
     * @param preparedInsertFunction
     * @return table crud template with the prepared insert method added
     */
    private String parsePreparedInsertFunction(String tableCrudTemplate, String preparedInsertFunction) {
        return tableCrudTemplate.replace(TemplateTags.PHP.TABLE_PREPARED_INSERT_FUNCTION, preparedInsertFunction);
    }

    /**
     * Creates a prepares insert function that can be used to insert records
     *
     * @param columns
     * @return prepares insert function
     */
    private String generatePreparedInsertFunction(String template, TableColumn[] columns) {

        String phpObjectDeclarationSyntax = SimpleTemplates.PHP.PHP_OBJECT_DECLARATION_SYNTAX;
        String stringParameter = SimpleTemplates.PHP.PHP_STRING_PARAMETER_OBJECT;

        String tableColumnsNames = "";
        String recordsObjects = "";

        for (int i = 0; i < columns.length; i++) {

            String columnName = columns[i].getColumnName();

            tableColumnsNames += stringParameter.replace(TemplateTags.PHP.OBJECT, columnName);
            recordsObjects += phpObjectDeclarationSyntax.replace(TemplateTags.PHP.OBJECT, columnName);

            if (i < columns.length - 1) {
                tableColumnsNames += ",";
                recordsObjects += ",";
            }
        }

        return template.replace(TemplateTags.PHP.RECORDS, recordsObjects).replace(TemplateTags.PHP.COLUMNS,
                tableColumnsNames);
    }

    /**
     * Adds the column accessor functions to the class template
     */
    private String addColumnAccessorFunctions(String tableCrudTemplate, String columnsAccessors) {
        return tableCrudTemplate.replace(TemplateTags.PHP.TABLE_COLUMNS_ACCESSOR_FUNCTIONS, columnsAccessors);
    }

    /**
     * Takes a column name, replaces it with the COLUMN_NAME_TEMPLATE_TAG
     *
     * @Return the new string
     */

    public String createColumnsAccessorFunctions(TableColumn[] columns) {
        String columnsAccessorFunctions = "";
        for (TableColumn tableColumn : columns) {
            columnsAccessorFunctions += generateColumnAccessorMethods(tableColumn);
        }
        return columnsAccessorFunctions;
    }

    /**
     * Parse TableColumn Accessors -> Replaces the column name in the template
     * with the passed column name
     */
    private String parseColumnAccessors(String columnName, String template) {
        return template.replace(TemplateTags.PHP.COLUMN_NAME_TEMPLATE_TAG, NativeUtils.toJavaBeansClass(columnName))
                .replace(TemplateTags.PHP.OBJECT, NativeUtils.toJavaBeansVariable(columnName));
    }

    /**
     * generate the columns accessor methods
     */
    public String generateColumnAccessorMethods(TableColumn[] columns) {
        String columnsAccessor = "";
        for (TableColumn tableColumn : columns) {
            String columnName = tableColumn.getColumnName();
            columnsAccessor += parseColumnAccessors(columnName, getPhpColumnAccessorsTemplate().getTemplate());
        }
        return columnsAccessor;
    }

    /**
     * generate the columns accessor methods
     */
    public String generateColumnAccessorMethods(TableColumn tableColumn) {
        return generateColumnGetters(tableColumn.getColumnName()) + generateColumnSetters(tableColumn.getColumnName());
    }

    /**
     * generate the columns name setters
     */
    private String generateColumnSetters(String columnName) {
        return phpColumnsCrudTemplate.getTemplate().replace("COLUMN_NAME", columnName);
    }

    /**
     * generate the columns name getters
     */
    private String generateColumnGetters(String columnName) {
        return phpColumnsCrudTemplate.getTemplate().replace("COLUMN_NAME", columnName);
    }

    /**
     * Generate TableColumn Crud Functions
     *
     * @param table
     * @return
     */
    private String generateColumnsCrudFunctions(Table table) {

        TableColumn[] columns = table.getColumns();

        String columnsCrudFunction = "";

        // Generate crud functions for table columns that hold special keys
        columnsCrudFunction += generateTableKeysColumnsCrudFunctions(table);

        // Generate crud functions for table columns that do not hold special
        // keys
        columnsCrudFunction += generateNonTableKeysColumnsCrudFunctions(table, columns);

        return columnsCrudFunction;
    }

    /**
     * Generate crud functions for table columns that hold special keys
     */

    private String generateTableKeysColumnsCrudFunctions(Table table) {

        TableColumn[] columns = table.getColumns();

        String columnsCrudFunction = "";

        // Generate crud functions for primary key columns
        columnsCrudFunction += generatePrimaryKeyCrudFunctions(table);

        // Generate crud functions for foreign key columns
        // columnsCrudFunction +=
        // generateForeignKeysCrudFunctions(table.getForeignKeys(), columns);

        // Generate crud functions for unique key columns
        // columnsCrudFunction +=
        // generateUniqueKeysCrudFunctions(table.getUniqueKeys(), columns);

        return columnsCrudFunction;
    }

    /**
     * Generate crud functions for table columns that do not hold special keys
     */

    private String generateNonTableKeysColumnsCrudFunctions(Table table, TableColumn[] columns) {

        String columnsCrudFunction = "";

        // Generate crud functions for other database columns
        for (TableColumn tableColumn : columns) {

            if (table.getPrimaryKey() != null) {
                if (tableColumn.getColumnName().equals(table.getPrimaryKey().getColumnName())) {
                    continue;
                }

                columnsCrudFunction += generateColumnCrudFunctions(table.getPrimaryKey(), tableColumn);

            } else {
                columnsCrudFunction += generateColumnSupportCrudFunctions(table.getColumns(), tableColumn);

            }
        }
        return columnsCrudFunction;
    }

    /**
     * Generate the crud to get the primary keys for the table
     */

    private String generatePrimaryKeyCrudFunctions(Table table) {

        String columnsCrudTemplate = getPhpColumnsCrudTemplate().getTemplate();
        try {
            String primaryKeyColumnName = table.getPrimaryKey().getColumnName();

            String functionParams = "";
            String functionParamsValues = "";

            TableColumn[] tableColumns = table.getColumns();

            for (int i = 0; i < tableColumns.length; i++) {
                String columnName = tableColumns[i].getColumnName();
                functionParams += "'" + columnName + "'";
                functionParamsValues += PHPGrammar.Variables.PHP_VARIABLE_PREFIX + columnName;

                if (i < (tableColumns.length - 1)) {
                    functionParams += ",";
                    functionParamsValues += ",";
                }
            }
            return parseColumnQueryFunction(primaryKeyColumnName, functionParams, functionParamsValues,
                    columnsCrudTemplate);
        } catch (NullPointerException e) {
            NativeUtils.printError(e.getMessage());
        }
        return "";
    }

    /**
     * Generate the crud to get the foreign keys for the table
     */
    /*
     * private String generateForeignKeysCrudFunctions(ForeignKeys []
	 * foreignKeys, TableColumn[] columns) { return
	 * generateColumnKeysCrudFunctions(foreignKeys, columns); }
	 */

    /**
     * Generate the crud to get the unique keys for the table
     */
    /*
     * private String generateUniqueKeysCrudFunctions(UniqueKeys [] uniqueKeys,
	 * TableColumn[] columns) { return
	 * generateColumnKeysCrudFunctions(uniqueKeys, columns); }
	 */

	/*
     * private String generateColumnKeysCrudFunctions(KeyColumn [] keyColumn,
	 * TableColumn[] columns) { if (keyColumn == null) { return ""; }
	 * 
	 * SWAP THE COLUMNKEY AND THE COLUMNS, AND PASS THEM TO THIS METHOD
	 * generateColumnCrudFunctions(KeyColumn columnKeys, TableColumn columns);
	 * 
	 * String columnKeysCrudFunctions = ""; String [] columnNames =
	 * keyColumn.getColumnNames(); for (int i = 0; i <columnNames.length; i++ )
	 * { String columnKey = keyColumn.getColumnNames()[i]; String dataType =
	 * keyColumn.getDataType().getDataType(); columnKeysCrudFunctions +=
	 * generateColumnKeysCrudFunction(columnKey,dataType ,columns); } return
	 * columnKeysCrudFunctions; }
	 */

    /**
     * Generates crud functions for columns that hold keys for the database
     * table
     */
    /*
     * private final String generateColumnKeysCrudFunction(String
	 * columnKey,String dataType, TableColumn[] columns) {
	 * 
	 * String[] columnKeys = new String[columns.length];
	 * 
	 * for (int i = 0; i < columns.length; i++) { columnKeys[i] =
	 * columns[i].getColumnName(); }
	 * 
	 * return generateColumnCrudFunctions(new KeyColumn(columnKeys,dataType),
	 * new TableColumn(columnKey, new DataType(columnKey, new
	 * Constraints(null)))); }
	 */

    /**
     * Generates column crud functions from a template
     */
    private String generateColumnCrudFunctions(PrimaryKey primaryKey, TableColumn tableColumn) {

        String columnsCrudTemplate = getPhpColumnsCrudTemplate().getTemplate();

        String columnName = tableColumn.getColumnName();

        String primaryKeyColumn = primaryKey.getColumnName();

        String phpPrimaryKeyColumnVariable = PHPGrammar.Variables.PHP_VARIABLE_PREFIX + primaryKeyColumn;

        String functionParams = "'" + primaryKeyColumn + "'";

        String functionParamsValues = phpPrimaryKeyColumnVariable;

        return parseColumnQueryFunction(columnName, functionParams, functionParamsValues, columnsCrudTemplate);
    }

    /**
     * Generates column crud functions from a template
     * This method is used for tables without a primary key
     */
    private String generateColumnSupportCrudFunctions(TableColumn[] tableColumns, TableColumn tableColumn) {

        String columnsCrudTemplate = getPhpColumnsCrudTemplate().getTemplate();

        String columnName = tableColumn.getColumnName();

        String functionParams = "";
        String functionParamsValues = "";

        for (int i = 0; i < tableColumns.length; i++) {
            if (tableColumns[i].getColumnName().equals(columnName)) {
                continue;
            }
            String column = tableColumns[i].getColumnName();
            functionParams += "'" + column + "'";
            functionParamsValues += PHPGrammar.Variables.PHP_VARIABLE_PREFIX + column;
            if (i < tableColumns.length) {
                functionParams += ",";
                functionParamsValues += ",";
            }
        }
        return parseColumnQueryFunction(columnName, functionParams, functionParamsValues, columnsCrudTemplate);
    }

    /**
     * Takes a column name,the table FUNCTION_PARAMS_KEYS and replaces it with
     * the FUNCTION_PARAMS_VALUES, FUNCTION_PARAMS_KEYS
     *
     * @Return the data found in the column
     */

    private String parseColumnQueryFunction(String columnName, String functionParams, String functionParamsValues,
                                            String template) {
        return template.replace(TemplateTags.PHP.QUERIED_COLUMN,columnName)
                .replace(TemplateTags.PHP.OBJECT, NativeUtils.toJavaBeansClass(columnName))
                .replace(TemplateTags.PHP.QUERY_RESULTS, columnName + "_")
                .replace(TemplateTags.PHP.FUNCTION_PARAMS_KEYS, functionParams)
                .replace(TemplateTags.PHP.FUNCTION_PARAMS_VALUES, functionParamsValues);
    }

    /**
     * Parse PHP Class FileName - > Creates an absolute storage path for PHP
     * Table Crud Script file
     *
     * @param tablesCrudScriptsStorageDirectory
     * @param className
     * @return
     */
    public String parseTableCrudLowLevelScriptsFileName(String tablesCrudScriptsStorageDirectory, String className) {
        String template = getPhpClassFileNameTemplate().getTemplate();
        return parsePHPClassFileName(tablesCrudScriptsStorageDirectory, className, template);
    }

    /**
     * Parse SQL Table FileName - > Creates an absolute storage path for SQL
     * Table Script file
     *
     * @param tablesSQLScriptsStorageDirectory
     * @param tableName
     * @return SQL Table FileName
     */
    public String parseSQLFileName(String tablesSQLScriptsStorageDirectory, String tableName) {
        String template = getSQLTableFilenameTemplate().getTemplate();

        return template.replace(TemplateTags.SQL.DIRECTORY, tablesSQLScriptsStorageDirectory)
                .replace(TemplateTags.SQL.TABLE_NAME, tableName);
    }

    /**
     * Parse PHP Class FileName - > Creates an absolute storage path for PHP
     * Table Crud Script file
     *
     * @param tablesCrudScriptsStorageDirectory
     * @param className
     * @return PHP Class FileName
     */
    public String parsePHPClassFileName(String tablesCrudScriptsStorageDirectory, String className, String template) {
        return template.replace(TemplateTags.PHP.DIRECTORY, tablesCrudScriptsStorageDirectory)
                .replace(TemplateTags.PHP.JAVA_BEANS_CLASSNAME, className);
    }

    /**
     * Create Data Actions Template
     *
     * @return Data Action Template
     */
    public String createDataActionsTemplate() {
        return parseDataActionsTemplate();
    }

    /**
     * Parse Data Actions Template
     *
     * @return Data Action Template
     */
    public String parseDataActionsTemplate() {
        String dataActionsTemplate = getPhpDatabaseActionsTemplate().getTemplate();
        return dataActionsTemplate;
    }

    /**
     * Parse Database Utils Script Template
     *
     * @param projectDatabaseConnectionProperties
     * @param databaseConnectionScriptFilePath
     * @return a fully created database connection script
     */
    public String parseDatabaseUtilsScriptTemplate(
            ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties,
            String databaseConnectionScriptFilePath) {
        String template = getPhpDatabaseUtilsTemplate().getTemplate();
        return template.replace(DatabaseConnection.SERVER_HOST, projectDatabaseConnectionProperties.getDatabaseHost())
                .replace(DatabaseConnection.DATABASE_USER, projectDatabaseConnectionProperties.getDatabaseUser())
                .replace(DatabaseConnection.USER_PASSWORD,
                        projectDatabaseConnectionProperties.getDatabaseUserPassword())
                .replace(DatabaseConnection.DATABASE_NAME, projectDatabaseConnectionProperties.getDatabaseName())
                .replace(DatabaseConnection.DATABASE_CONNECTION_INC_FILE, databaseConnectionScriptFilePath);
    }

    public String parseTableCrudHighLevelScripts(String tablesCrudHighLevelScriptsStorageDirectory, String className) {
        String fileName = tablesCrudHighLevelScriptsStorageDirectory + className + TemplateTags.PHP.CLASS_INFO
                + PHPProjectFileNames.PHP_FILE_EXTENSION;
        return fileName;
    }

    /**
     * Create table high level crud scripts
     *
     * @param phpProjectConfiguration
     * @param table
     * @return high level crud script
     */
    public String getTableHighLevelCrud(PHPProjectConfiguration phpProjectConfiguration, Table table) {

        String tablesCrudLowLevelScriptsStorageDirectory = phpProjectConfiguration
                .getLowLevelCrudScriptsStorageDirectory();

        String tableName = table.getTableName();

        String template = new PHPHighLevelTableClassCrudTemplate().getTemplate();

        // add class name
        String className = NativeUtils.toJavaBeansClass(tableName) + TemplateTags.PHP.CLASS_INFO;
        template = parsePHPHighLevelTableClassName(template, className);

        // Add low level table crud include statement
        String lowLevelTableCrudFileName = NativeUtils.toJavaBeansClass(tableName)
                + PHPProjectFileNames.PHP_CLASS_EXTENSION;
        String lowLevelClassPath = (tablesCrudLowLevelScriptsStorageDirectory + lowLevelTableCrudFileName);
        template = parsePHPLowLevelTableClassInclude(template, lowLevelClassPath);

        String highLevelPreparedInsertFunctionTemplate = new PHPFunctionsHighLevelPreparedInsertTemplate().getTemplate();
        String preparedInsertFunction = generatePreparedInsertFunction(highLevelPreparedInsertFunctionTemplate, table.getColumns());
        template = parsePreparedInsertFunction(template, preparedInsertFunction);

        String columnFetchAssocFunctions = "";
        for (TableColumn tableColumn : table.getColumns()) {
            columnFetchAssocFunctions += createTableColumnHighLevelFetchAssocFunctions(tableColumn, tableName);
        }

        // Add the fetch assoc function to the template
        template = parseColumnFetchAssocFunctions(template, columnFetchAssocFunctions);

        // Add the table name to the
        template = addTableName(template, tableName);

        // Add table class name
        template = parseTableClassName(template, NativeUtils.toJavaBeansClass(tableName));

        return template;
    }

    /**
     * Add Low level table class include
     *
     * @param template
     * @param lowLevelClassPath
     * @return
     */
    private String parsePHPLowLevelTableClassInclude(String template, String lowLevelClassPath) {
        return template.replace(TemplateTags.PHP.TABLE_LOW_LEVEL_CRUD_CLASS, lowLevelClassPath);
    }

    /**
     * Parses the php high level table class name
     *
     * @param template
     * @param className
     * @return
     */
    private String parsePHPHighLevelTableClassName(String template, String className) {
        return template.replace(TemplateTags.PHP.CLASS_NAME, className);
    }

    /**
     * @param template
     * @param columnFetchAssocFunctions
     * @return Template with appended fetch assoc functions
     */
    private String parseColumnFetchAssocFunctions(String template, String columnFetchAssocFunctions) {
        return template.replace(TemplateTags.PHP.ROW_ITEMS_FETCH_ASSOC_VALUES_STATEMENTS, columnFetchAssocFunctions);
    }

    /**
     * Create table column high level fetch assoc functions
     *
     * @param tableColumn
     * @param tableName
     * @return the table column high level fetch assoc funcions
     */
    private String createTableColumnHighLevelFetchAssocFunctions(TableColumn tableColumn, String tableName) {
        String template = new PHPFunctionHighLevelFetchAssocGettersTemplate().getTemplate();
        String columnName = tableColumn.getColumnName().trim();
        return template.replace(TemplateTags.PHP.TABLE_NAME, tableName).replace(TemplateTags.PHP.COLUMN_NAME,
                columnName);
    }

}
