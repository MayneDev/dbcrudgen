package com.marvik.apis.dbcrudgen.parser.php;

import java.util.Locale;

import com.marvik.apis.dbcrudgen.core.templates.DbCrudGeneratorNativeTemplates;
import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.parser.TemplatesParser;
import com.marvik.apis.dbcrudgen.projects.php.configuration.PHPProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.php.filenames.PHPProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.KeyColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.ForeignKeys;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.UniqueKeys;
import com.marvik.apis.dbcrudgen.schemamodels.constraints.Constraints;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.php.PHPClassFileNameTemplate;
import com.marvik.apis.dbcrudgen.templates.php.PHPColumnAccessorsTemplate;
import com.marvik.apis.dbcrudgen.templates.php.PHPColumnsCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.php.PHPDatabaseActionsTemplate;
import com.marvik.apis.dbcrudgen.templates.php.PHPDatabaseConnectionTemplate;
import com.marvik.apis.dbcrudgen.templates.php.PHPDatabaseUtilsTemplate;
import com.marvik.apis.dbcrudgen.templates.php.PHPTableClassCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.sql.SQLTableFilenameTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags.DatabaseConnection;

public class PHPTemplatesParser extends TemplatesParser {

	private PHPTableClassCrudTemplate phpTableClassCrudTemplate;
	private PHPColumnsCrudTemplate phpColumnsCrudTemplate;
	private PHPColumnAccessorsTemplate phpColumnAccessorsTemplate;
	private PHPClassFileNameTemplate phpClassFileNameTemplate;
	private PHPDatabaseActionsTemplate phpDatabaseActionsTemplate;
	private PHPDatabaseConnectionTemplate phpDatabaseConnectionTemplate;
	private PHPDatabaseUtilsTemplate phpDatabaseUtilsTemplate;

	private SQLTableFilenameTemplate sqlTableFilenameTemplate;

	/**
	 * PHPTemplatesParser - Parse templates
	 */
	public PHPTemplatesParser() {

		phpTableClassCrudTemplate = new PHPTableClassCrudTemplate();

		phpColumnsCrudTemplate = new PHPColumnsCrudTemplate();

		phpColumnAccessorsTemplate = new PHPColumnAccessorsTemplate();

		phpClassFileNameTemplate = new PHPClassFileNameTemplate();

		phpDatabaseActionsTemplate = new PHPDatabaseActionsTemplate();

		phpDatabaseConnectionTemplate = new PHPDatabaseConnectionTemplate();

		phpDatabaseUtilsTemplate = new PHPDatabaseUtilsTemplate();

		sqlTableFilenameTemplate = new SQLTableFilenameTemplate();
	}

	/**
	 * PHPColumnAccessorsTemplate
	 * 
	 * @return PHPColumnAccessorsTemplate
	 */
	public PHPColumnAccessorsTemplate getPhpColumnAccessorsTemplate() {
		return phpColumnAccessorsTemplate;
	}

	/**
	 * 
	 * @return PHPColumnsCrudTemplate
	 */
	public PHPColumnsCrudTemplate getPhpColumnsCrudTemplate() {
		return phpColumnsCrudTemplate;
	}

	/**
	 * 
	 * @return PHPTableClassCrudTemplate
	 */
	public PHPTableClassCrudTemplate getPhpTableClassCrudTemplate() {
		return phpTableClassCrudTemplate;
	}

	/**
	 * 
	 * @return PHPClassFileNameTemplate
	 */
	public PHPClassFileNameTemplate getPhpClassFileNameTemplate() {
		return phpClassFileNameTemplate;
	}

	/**
	 * PHPDatabaseActionsTemplate getPhpDatabaseActionsTemplate()
	 * 
	 * @return PHPDatabaseActionsTemplate
	 */
	public PHPDatabaseActionsTemplate getPhpDatabaseActionsTemplate() {
		return phpDatabaseActionsTemplate;
	}

	/**
	 * PHPDatabaseConnectionTemplate getPhpDatabaseConnectionTemplate()
	 * 
	 * @return phpDatabaseConnectionTemplate
	 */
	public PHPDatabaseConnectionTemplate getPhpDatabaseConnectionTemplate() {
		return phpDatabaseConnectionTemplate;
	}

	/**
	 * PHPDatabaseUtilsTemplate getPhpDatabaseUtilsTemplate()
	 * 
	 * @return phpDatabaseUtilsTemplate
	 */
	public PHPDatabaseUtilsTemplate getPhpDatabaseUtilsTemplate() {
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
	 * 
	 * @param table
	 * @return
	 */
	public String getTableCrud(PHPProjectConfiguration phpProjectConfiguration,
			ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties, Table table) {

		String tableCrudSQL = getPhpTableClassCrudTemplate().getTemplate();

		String tableName = table.getTableName();
		TableColumn[] columns = table.getColumns();

		String columnsCrud = generateColumnsCrudFunctions(table, columns);
		String columnsAccessor = generateColumnAccessorMethods(columns);

		// Parse column accessors - replaces the column accessor templates with
		// the actual functions
		tableCrudSQL = addColumnAccessorFunctions(tableCrudSQL, columnsAccessor);

		// Parse column crud functions - replaces the column crud templates with
		// the actual crud functions
		tableCrudSQL = addColumnsQueryFunctions(tableCrudSQL, columnsCrud);

		// Replace table name tag with the actual table name
		tableCrudSQL = addTableName(tableCrudSQL, tableName);

		// Replace the Dependencies URIs file path.
		tableCrudSQL = addDependencieURIs(phpProjectConfiguration, tableCrudSQL);

		// Replace the class name tag with the actual class name which is
		// derived from the table name
		tableCrudSQL = addClassName(tableCrudSQL, table.getTableName());

		// Replace the template info with the template info
		tableCrudSQL = addPHPDbCrudTemplateInfo(tableCrudSQL);

		return tableCrudSQL;
	}

	/**
	 * Add Template Info
	 * 
	 * @param tableCrudSQL
	 * @return PHP Db Crud Generator Info
	 */
	private String addPHPDbCrudTemplateInfo(String tableCrudSQL) {
		DbCrudGeneratorNativeTemplates dbCrudGeneratorNativeTemplates = new DbCrudGeneratorNativeTemplates();
		String templateInfo = dbCrudGeneratorNativeTemplates.getTemplate();
		return parsePHPDbCrudTemplateInfo(templateInfo, tableCrudSQL);
	}

	/**
	 * Adds the Db Crud Generator info to the generated script.
	 * 
	 * @param templateInfo
	 * @param tableCrudSQL
	 * @return PHP Db Crud Generator Template Info to the table sql
	 */
	private String parsePHPDbCrudTemplateInfo(String templateInfo, String tableCrudSQL) {
		// TODO Auto-generated method stub
		return tableCrudSQL.replace(TemplateTags.PHP.DB_CRUD_GENERATOR_TEMPLATE, templateInfo).replace(
				NativeTemplateTags.SYSTEM_WRITE_TIME,
				NativeUtils.getCurrentTime("EEE hh:mm:ss  dd/MM/yyy", System.currentTimeMillis()));
	}

	/**
	 * addDependencieURIs
	 * 
	 * @param projectDatabaseConnectionProperties
	 * @param tableCrudSQL
	 * @return
	 */
	private String addDependencieURIs(PHPProjectConfiguration phpProjectConfiguration, String tableCrudSQL) {

		// Add dependencies uris for database utils
		String databaseUtilsStorageDirectory = phpProjectConfiguration.getPhpDatabaseAPIScriptsStorageDirectory();
		String databaseUtilsFileName = databaseUtilsStorageDirectory
				+ PHPProjectFileNames.DATABASE_UTILS_SCRIPT_FILENAME;
		String dataActionsFileName = databaseUtilsStorageDirectory + PHPProjectFileNames.DATA_ACTIONS_SCRIPT_FILENAME;

		tableCrudSQL = tableCrudSQL.replace(TemplateTags.PHP.DATABASE_UTILS_FILE_PATH, databaseUtilsFileName);

		// Add dependencies uris for database connection files

		// Add dependencies for data actions scripts
		tableCrudSQL = tableCrudSQL.replace(TemplateTags.PHP.DATA_ACTIONS_FILE_PATH, dataActionsFileName);

		return tableCrudSQL;
	}

	/**
	 * Replaces the class name tag with the actual class name
	 */
	private String addClassName(String tableCrudSQL, String tableName) {
		String className = parseJavaBeansClassName(tableName);
		return tableCrudSQL.replace(TemplateTags.PHP.CLASS_NAME, className);
	}

	/**
	 * Creates a java beans class name derived from the class name
	 * 
	 * @param tableName
	 * @return
	 */
	public String parseJavaBeansClassName(String tableName) {
		tableName = tableName.toLowerCase(Locale.getDefault());
		if (tableName.length() <= 1) {
			return tableName.toUpperCase(Locale.getDefault());
		}
		String firstChar = String.valueOf(tableName.charAt(0));
		firstChar = firstChar.toUpperCase();

		String otherChars = tableName.substring(1, tableName.length());
		return firstChar + otherChars;
	}

	/**
	 * Replaces the table name tag with the actual table name
	 */
	private String addTableName(String tableCrudSQL, String tableName) {
		return tableCrudSQL.replace(TemplateTags.PHP.TABLE_NAME, tableName);
	}

	/**
	 * Adds the column query functions to the class template
	 */
	private String addColumnsQueryFunctions(String tableCrudSQL, String columnsQueryFunctions) {
		return tableCrudSQL.replace(TemplateTags.PHP.TABLE_COLUMNS_CRUD_FUNCTIONS, columnsQueryFunctions);
	}

	/**
	 * Adds the column accessor functions to the class template
	 */
	private String addColumnAccessorFunctions(String tableCrudSQL, String columnsAccessors) {
		return tableCrudSQL.replace(TemplateTags.PHP.TABLE_COLUMNS_ACCESSOR_FUNCTIONS, columnsAccessors);
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
	 * Parse TableColumn Accessors -> Replaces the column name in the template with
	 * the passed column name
	 */
	private String parseColumnAccesssors(String columnName, String template) {
		return template.replace(TemplateTags.PHP.COLUMN_NAME_TEMPLATE_TAG, columnName);
	}

	/**
	 * generate the columns accessor methods
	 */
	public String generateColumnAccessorMethods(TableColumn[] columns) {
		String columnsAccessor = "";
		for (TableColumn tableColumn : columns) {
			String columnName = tableColumn.getColumnName();
			columnsAccessor += parseColumnAccesssors(columnName, getPhpColumnAccessorsTemplate().getTemplate());
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
	 * @param columns
	 * @return
	 */
	private String generateColumnsCrudFunctions(Table table, TableColumn[] columns) {

		String columnsCrudFunction = "";

		// Generate crud functions for table columns that hold special keys
		columnsCrudFunction += generateTableKeysColumnsCrudFunctions(table, columns);

		// Generate crud functions for table columns that do not hold special
		// keys
		columnsCrudFunction += generateNonTableKeysColumnsCrudFunctions(table, columns);

		return columnsCrudFunction;
	}

	/**
	 * Generate crud functions for table columns that hold special keys
	 */

	private String generateTableKeysColumnsCrudFunctions(Table table, TableColumn[] columns) {
		String columnsCrudFunction = "";

		// Generate crud functions for primary key columns
		columnsCrudFunction += generatePrimaryKeysCrudFunctions(table.getPrimaryKey(), columns);

		// Generate crud functions for foreign key columns
		columnsCrudFunction += generateForeignKeysCrudFunctions(table.getForeignKeys(), columns);

		// Generate crud functions for unique key columns
		columnsCrudFunction += generateUniqueKeysCrudFunctions(table.getUniqueKeys(), columns);

		return columnsCrudFunction;
	}

	/**
	 * Generate crud functions for table columns that do not hold special keys
	 */

	private String generateNonTableKeysColumnsCrudFunctions(Table table, TableColumn[] columns) {
		String columnsCrudFunction = "";

		// Generate crud functions for other database columns
		for (TableColumn tableColumn : columns) {
			columnsCrudFunction += generateColumnCrudFunctions(table.getPrimaryKey(), tableColumn);
		}
		return columnsCrudFunction;
	}

	/**
	 * Generate the crud to get the primary keys for the table
	 */
	private String generatePrimaryKeysCrudFunctions(PrimaryKey primaryKey, TableColumn[] columns) {
		return generateColumnKeysCrudFunctions(primaryKey, columns);
	}

	/**
	 * Generate the crud to get the foreign keys for the table
	 */
	private String generateForeignKeysCrudFunctions(ForeignKeys foreignKeys, TableColumn[] columns) {
		return generateColumnKeysCrudFunctions(foreignKeys, columns);
	}

	/**
	 * Generate the crud to get the unique keys for the table
	 */
	private String generateUniqueKeysCrudFunctions(UniqueKeys uniqueKeys, TableColumn[] columns) {
		return generateColumnKeysCrudFunctions(uniqueKeys, columns);
	}

	private String generateColumnKeysCrudFunctions(KeyColumn keyColumn, TableColumn[] columns) {
		if (keyColumn == null) {
			return "";
		}
		/*
		 * SWAP THE COLUMNKEY AND THE COLUMNS, AND PASS THEM TO THIS METHOD
		 * generateColumnCrudFunctions(KeyColumn columnKeys, TableColumn columns);
		 */
		String columnKeysCrudFunctions = "";
		for (String columnKey : keyColumn.getColumnKeys()) {
			columnKeysCrudFunctions += generateColumnKeysCrudFunction(columnKey, columns);
		}
		return columnKeysCrudFunctions;
	}

	/**
	 * Generates crud functions for columns that hold keys for the database
	 * table
	 */
	private final String generateColumnKeysCrudFunction(String columnKey, TableColumn[] columns) {

		String[] columnKeys = new String[columns.length];

		for (int i = 0; i < columns.length; i++) {
			columnKeys[i] = columns[i].getColumnName();
		}

		return generateColumnCrudFunctions(new KeyColumn(columnKeys),
				new TableColumn(columnKey, new DataType(columnKey, new Constraints(null))));
	}

	/**
	 * Generates column crud functions from a template
	 */
	private String generateColumnCrudFunctions(KeyColumn keyColumn, TableColumn tableColumn) {
		String columnsCrudTemplate = getPhpColumnsCrudTemplate().getTemplate();
		String columnName = tableColumn.getColumnName();

		String[] mColumnKeys = keyColumn.getColumnKeys();

		String keyParams = "";
		String keyParamsValues = "";

		for (int i = 0; i < mColumnKeys.length; i++) {

			keyParams += "'" + mColumnKeys[i] + "'";
			keyParamsValues += "$" + mColumnKeys[i];

			if (i < mColumnKeys.length - 1) {
				keyParams += ",";
				keyParamsValues += ",";
			}
		}
		return parseColumnQueryFunction(columnName, keyParams, keyParamsValues, columnsCrudTemplate);
	}

	/**
	 * Takes a column name,the table FUNCTION_PARAMS_KEYS and replaces it with
	 * the FUNCTION_PARAMS_VALUES, FUNCTION_PARAMS_KEYS
	 * 
	 * @Return the data found in the column
	 */

	private String parseColumnQueryFunction(String columnName, String functionParams, String functionParamsValues,
			String template) {
		return template.replace(TemplateTags.PHP.QUERIED_COLUMN, columnName)
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
	public String parsePHPClassFileName(String tablesCrudScriptsStorageDirectory, String className) {
		String template = getPhpClassFileNameTemplate().getTemplate();
		return parsePHPClassFileName(tablesCrudScriptsStorageDirectory, className, template);
	}

	/**
	 * Parse SQL Table FileName - > Creates an absolute storage path for SQL
	 * Table Script file
	 * 
	 * @param tablesSQLScriptsStorageDirectory
	 * @param className
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
	 * @param phpProjectConfiguration
	 * 
	 * @param projectDatabaseConnectionProperties
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
}
