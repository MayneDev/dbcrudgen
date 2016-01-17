package com.marvik.apis.dbcrudgen.parser.android.tableschemas;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidTableSchemasTemplatesParser extends AndroidTemplatesParser {

	/**
	 * AndroidTableSchemasTemplatesParser - Creates tables schemas template
	 */
	public AndroidTableSchemasTemplatesParser() {
		
	}
	/**
	 * Creates the schemas of all the database tables
	 * 
	 * @param androidProjectConfiguration
	 */
	public String createTablesSchemas(AndroidProjectConfiguration androidProjectConfiguration, Table[] tables) {

		AndroidContentProviderConfiguration androidContentProviderConfiguration = androidProjectConfiguration
				.getAndroidContentProviderConfiguration();

		AndroidDatabaseConfiguration androidDatabaseConfiguration = androidContentProviderConfiguration
				.getAndroidDatabaseConfiguration();

		String databaseTablesTemplate = getAndroidClassDatabaseTablesTemplate().getTemplate();

		String tablesSchemas = "";

		for (Table table : tables) {
			tablesSchemas += createTableSchemas(androidProjectConfiguration, table);
		}

		// Add table package name
		String tablesClassPackageName = androidDatabaseConfiguration.getTablesSchemasPackage();
		databaseTablesTemplate = parseTablesClassPackageName(databaseTablesTemplate, tablesClassPackageName);

		// Add content provider import
		databaseTablesTemplate = parseContentProviderImportStatements(androidContentProviderConfiguration,
				databaseTablesTemplate);

		// Add all the tables SQL
		String tablesSQLVaribles = createTablesSQLVariables(tables);
		databaseTablesTemplate = parseTablesSQLVariables(databaseTablesTemplate,tablesSQLVaribles);
				
		// Add all the the tables Schemas
		databaseTablesTemplate = parseTablesSchemasAll(databaseTablesTemplate, tablesSchemas);

		return databaseTablesTemplate;
	}

	// Add all the tables SQL
	private String parseTablesSQLVariables(String databaseTablesTemplate, String tablesSQLVaribles) {
		return databaseTablesTemplate.replace(TemplateTags.Android.DATABASE_TABLES_SQL_VARIABLE, tablesSQLVaribles);
	}

	// Add all the tables SQL reference link to a global variable
	private String createTablesSQLVariables(Table[] tables) {
		
		String tablesSQLVariable = "";
		
		for (int i = 0; i < tables.length; i++) {
			String tableReferenceTemplate = SimpleTemplates.Android.ANDROID_DATABASE_TABLE_SQL_VARIABLE;
			String javaBeansTableClassName = NativeUtils.toJavaBeansClass(tables[i].getTableName());

			tablesSQLVariable += tableReferenceTemplate.replace(TemplateTags.Android.JAVA_BEANS_CLASS_NAME,
					javaBeansTableClassName);
			
			if(i < tables.length -1){
				tablesSQLVariable += TemplateTags.TAG_PRINTING_CHAR_COMMA;
			}
		}
		return tablesSQLVariable;
	}

	// Add content provider import
	private String parseContentProviderImportStatements(
			AndroidContentProviderConfiguration androidContentProviderConfiguration, String databaseTablestemplate) {
		return parseContentProviderClassImport(androidContentProviderConfiguration, databaseTablestemplate);
	}

	// Adds a valid package name to the the tables class that holds all the
	// schemas for the database tables
	private String parseTablesClassPackageName(String databaseTablestemplate, String tablesClassPackageName) {
		tablesClassPackageName = tablesClassPackageName.replace(NativeUtils.getFileSeparator(),
				TemplateTags.TAG_PRINTING_CHAR_DOT);
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
		tableSchema = parseTableContentUri(androidProjectConfiguration.getAndroidContentProviderConfiguration(),
				tableSchema);

		// Add Table TableColumn
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

		TableColumn[] columns = table.getColumns();
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
	private String parseTableContentUri(AndroidContentProviderConfiguration androidContentProviderConfiguration,
			String tableSchema) {
		return parseContentProviderClassImport(androidContentProviderConfiguration, tableSchema);
	}

	// Add a content provider class import
	private String parseContentProviderClassImport(
			AndroidContentProviderConfiguration androidContentProviderConfiguration, String template) {
		String contentProviderPackage = androidContentProviderConfiguration.getContentProviderPackage();
		String contentProviderClass = androidContentProviderConfiguration.getContentProviderClass();

		// Remove back slashes to make a valid package name
		contentProviderPackage = contentProviderPackage.replace(NativeUtils.getFileSeparator(),
				TemplateTags.TAG_PRINTING_CHAR_DOT);

		return template.replace(TemplateTags.Android.CONTENT_PROVIDER_CLASS, contentProviderClass)
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
		String javaBeansClassName = NativeUtils.toJavaBeansClass(tableName);
		return tableSchema.replace(TemplateTags.Android.TABLE_JAVA_BEANS_CLASS_NAME, javaBeansClassName);
	}

	// Create Table TableColumn Variables
	private String createTableColumnsVariables(String tableColumnsVariableTemplate, Table table) {

		String columnVariables = "";
		TableColumn[] columns = table.getColumns();
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
