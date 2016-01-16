package com.marvik.apis.dbcrudgen.parser.android;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.TemplatesParser;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassContentProviderTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassDatabaseTablesTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassSQLTableTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassSQLiteOpenHelperTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidClassTableCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidInterfaceCrudOperations;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeFloatTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeGenericTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeIntTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeLongTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDataTypeStringTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidMethodColumnsCrudDefaultTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementAddUriMatcherTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementContentProviderSQLDeleteTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementContentProviderSQLInsertTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementContentProviderSQLQueryTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementContentProviderSQLUpdateTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatementSQLTableColumnStatementTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidStatmentContentValuesPutTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidVariableSQLTableColumnTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidVariableSQLTableCreateSQLTemplate;
import com.marvik.apis.dbcrudgen.templates.android.AndroidVariableUriMatcherCodeTemplate;
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
	 * AndroidInterfaceCrudOperations
	 */
	private AndroidInterfaceCrudOperations androidInterfaceCrudOperations;

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
	 * AndroidStatementSQLTableColumnStatementTemplate
	 */
	private AndroidStatementSQLTableColumnStatementTemplate androidStatementSQLTableColumnStatementTemplate;

	/**
	 * AndroidStatmentContentValuesPutTemplate
	 */
	private AndroidStatmentContentValuesPutTemplate androidStatmentContentValuesPutTemplate;

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
	 * AndroidTemplatesParser - Class that parses templates into actual
	 * data/source code
	 */
	public AndroidTemplatesParser() {
		androidClassContentProviderTemplate = new AndroidClassContentProviderTemplate();
		androidClassDatabaseTablesTemplate = new AndroidClassDatabaseTablesTemplate();
		androidClassSQLiteOpenHelperTemplate = new AndroidClassSQLiteOpenHelperTemplate();
		androidClassSQLTableTemplate = new AndroidClassSQLTableTemplate();
		androidClassTableCrudTemplate = new AndroidClassTableCrudTemplate();
		androidInterfaceCrudOperations = new AndroidInterfaceCrudOperations();
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
		androidStatmentContentValuesPutTemplate = new AndroidStatmentContentValuesPutTemplate();
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
	 * @return the androidInterfaceCrudOperations
	 */
	public AndroidInterfaceCrudOperations getAndroidInterfaceCrudOperations() {
		return androidInterfaceCrudOperations;
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
	public AndroidMethodColumnsCrudDataTypeIntTemplate getAndroidMethodColumnsCrudDataTypeIntTemplate() {
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
	 * @return the androidStatmentContentValuesPutTemplate
	 */
	public AndroidStatmentContentValuesPutTemplate getAndroidStatmentContentValuesPutTemplate() {
		return androidStatmentContentValuesPutTemplate;
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
	 * Creates the schemas of all the database tables
	 * 
	 * @param androidProjectConfiguration
	 */
	public void createTablesSchemas(AndroidProjectConfiguration androidProjectConfiguration, Table[] tables) {

		AndroidContentProviderConfiguration androidContentProviderConfiguration = androidProjectConfiguration.getAndroidContentProviderConfiguration();
		
		AndroidDatabaseConfiguration androidDatabaseConfiguration = androidContentProviderConfiguration.getAndroidDatabaseConfiguration();
		
		
		String databaseTablestemplate = getAndroidClassDatabaseTablesTemplate().getTemplate();

		String tablesSchemas = "";

		for (Table table : tables) {
			tablesSchemas += createTableSchemas(androidProjectConfiguration, table);
		}

		
		//Add table package name
		String tablesClassPackageName = androidDatabaseConfiguration.getTablesSchemasPackage();
		databaseTablestemplate = parseTablesClassPackageName(databaseTablestemplate, tablesClassPackageName);
		
		//Add content provider import
		
		//Add all the tables SQL
		databaseTablestemplate = addTablesSQLVariable(databaseTablestemplate, tablesSchemas);
		
		// Add all the the tables Schemas
		databaseTablestemplate = parseTablesSchemasAll(databaseTablestemplate, tablesSchemas);

		System.out.println(databaseTablestemplate);
	}

	//Adds a valid package name to the the tables class that holds all the schemas for the database tables
	private String parseTablesClassPackageName(String databaseTablestemplate, String tablesClassPackageName) {
		tablesClassPackageName = tablesClassPackageName.replace(NativeUtils.getFileSeparator(), TemplateTags.TAG_PRINTING_CHAR_DOT);
		return databaseTablestemplate.replace(TemplateTags.Android.TABLES_PACKAGE_DEFINITION, tablesClassPackageName);
	}

	// Add all the the tables Schemas
	private String parseTablesSchemasAll(String databaseTablestemplate, String tablesSchemas) {
		return databaseTablestemplate.replace(TemplateTags.Android.DATABASE_TABLES_SCHEMAS, tablesSchemas);
	}

	/**
	 * Creates the schemas of a database tables
	 * 
	 * @param androidProjectConfiguration
	 */
	private String createTableSchemas(AndroidProjectConfiguration androidProjectConfiguration, Table table) {

		// Set the original schema for the table to be the template, the replace
		// with actual data
		String tableSchema = getAndroidClassSQLTableTemplate().getTemplate();

		// Add java beans class name
		tableSchema = parseJavaBeansClassName(tableSchema, table.getTableName());

		// Add Table name
		tableSchema = parseTableName(tableSchema, table.getTableName());

		// Add Content Uri
		tableSchema = parseTableContentUri(androidProjectConfiguration, tableSchema);

		// Add Table Columns
		String tableColumnsVariableTemplate = getAndroidVariableSQLTableColumnTemplate().getTemplate();
		String tableColumnsVariables = createTableColumnsVariables(tableColumnsVariableTemplate, table);
		tableSchema = parseTableColumns(tableSchema, tableColumnsVariables);

		// Add table SQL
		String tableSQL = generateTableSQL(table);
		tableSchema = parseTableSQL(tableSchema, tableSQL);

		return tableSchema;

	}

	private String generateTableSQL(Table table) {
		String tableSQLTemplate = getAndroidVariableSQLTableCreateSQLTemplate().getTemplate();

		// Add table name
		String tableName = table.getTableName();
		tableSQLTemplate = parseTableName(tableSQLTemplate, tableName);

		String tableColumns = "";

		Columns[] columns = table.getColumns();
		for (int i = 0; i < columns.length; i++) {
			String columnName = columns[i].getColumnName();
			String dataType = columns[i].getDataType().getDataType();
			String tableColumnStatementTemplate = getAndroidStatementSQLTableColumnStatementTemplate().getTemplate();

			boolean isLastColumn = (i == columns.length - 1);
			tableColumnStatementTemplate = parseTableColumnsAndDataTypes(tableColumnStatementTemplate, columnName,
					dataType, isLastColumn);

			tableColumns += tableColumnStatementTemplate;

		}

		// Add table columns to the sql template
		tableSQLTemplate = parseTableColumnsAndDataTypesAll(tableSQLTemplate, tableColumns);

		return tableSQLTemplate;
	}

	private String parseTableColumnsAndDataTypesAll(String tableSQLTemplate, String tableColumns) {
		return tableSQLTemplate.replace(TemplateTags.Android.TABLE_COLUMNS, tableColumns);
	}

	private String parseTableColumnsAndDataTypes(String tableColumnStatementTemplate, String columnName,
			String dataType, boolean isLastColumn) {
		if (isLastColumn) {
			tableColumnStatementTemplate = tableColumnStatementTemplate
					.replace(TemplateTags.Android.TABLE_COLUMNS_COMMA_SEPARATOR, TemplateTags.TAG_EMPTY_STRING);
		} else {
			tableColumnStatementTemplate = tableColumnStatementTemplate
					.replace(TemplateTags.Android.TABLE_COLUMNS_COMMA_SEPARATOR, TemplateTags.TAG_PRINTING_CHAR_COMMA);
		}
		return tableColumnStatementTemplate
				.replace(TemplateTags.Android.TABLE_COLUMN_DEFINITION, columnName.toUpperCase())
				.replace(TemplateTags.Android.TABLE_COLUMN_DATATYPE, dataType);
	}

	// Add table sql
	private String parseTableSQL(String tableSchema, String tableSQL) {

		return tableSchema.replace(TemplateTags.Android.TABLE_CREATE_SQL, tableSQL);
	}

	// Add Content Uri
	private String parseTableContentUri(AndroidProjectConfiguration androidProjectConfiguration, String tableSchema) {

		String contentProviderPackage = androidProjectConfiguration.getAndroidContentProviderConfiguration()
				.getContentProviderPackage();
		String contentProviderClass = androidProjectConfiguration.getAndroidContentProviderConfiguration()
				.getContentProviderClass();

		// Remove back slashes to make a valid package name
		contentProviderPackage = contentProviderPackage.replace(NativeUtils.getFileSeparator(), TemplateTags.TAG_PRINTING_CHAR_DOT);

		return tableSchema.replace(TemplateTags.Android.CONTENT_PROVIDER_CLASS, contentProviderClass)
				.replace(TemplateTags.Android.CONTENT_PROVIDER_PACKAGE, contentProviderPackage);
	}

	private String parseTableColumns(String tableSchema, String tableColumnsVariables) {
		return tableSchema.replace(TemplateTags.Android.TABLE_COLUMNS, tableColumnsVariables);

	}

	private String parseTableName(String tableSchema, String tableName) {
		return tableSchema.replace(TemplateTags.Android.TABLE_NAME, tableName);
	}

	private String parseJavaBeansClassName(String tableSchema, String tableName) {
		if (tableName.length() <= 1) {
			return tableName.toUpperCase();
		}
		String javaBeansClassName = tableName.substring(0, 1).toUpperCase()
				+ tableName.substring(1, tableName.length());
		return tableSchema.replace(TemplateTags.Android.TABLE_JAVA_BEANS_CLASS_NAME, javaBeansClassName);
	}

	// Create Table Columns Variables
	private String createTableColumnsVariables(String tableColumnsVariableTemplate, Table table) {

		String columnVariables = "";
		Columns[] columns = table.getColumns();
		for (int i = 0; i < columns.length; i++) {
			String columnName = columns[i].getColumnName();
			columnVariables += parseColumnVariables(tableColumnsVariableTemplate, columnName);
		}
		return columnVariables;
	}

	private String parseColumnVariables(String tableColumnsVariableTemplate, String columnName) {

		return tableColumnsVariableTemplate
				.replace(TemplateTags.Android.TAG_TABLE_COLUMN_DEFINITION, columnName.toUpperCase())
				.replace(TemplateTags.Android.TAG_TABLE_COLUMN_REFERENCE, columnName);
	}

}
