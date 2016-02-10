/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.j2se.mysql;

import com.marvik.apis.dbcrudgen.core.platforms.sql.grammar.SQLGrammar;
import com.marvik.apis.dbcrudgen.core.platforms.sqlite.grammar.SQLiteGrammar;
import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.natives.Natives;
import com.marvik.apis.dbcrudgen.natives.syntax.Syntax.PrintingChars;
import com.marvik.apis.dbcrudgen.parser.java.j2se.J2SETemplatesParser;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectMYSQLDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.J2SEMYSQLDatabaseTablesSchemasTemplate;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.J2SEMYSQLTablesSchemasTemplate;
import com.marvik.apis.dbcrudgen.templates.j2se.statements.J2SEMYSQLStatementSQLTableColumnStatementTemplate;
import com.marvik.apis.dbcrudgen.templates.j2se.variables.J2SEMYSQLTableColumnVariableDefinitionTemplate;
import com.marvik.apis.dbcrudgen.templates.j2se.variables.J2SEMYSQLTableCreateSQLTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
*Created on Feb 10, 2016-1:39:51 AM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEMYSQLTableSchemasTemplatesParser extends J2SETemplatesParser {

	/**
	 * Create all the table schemas for the J2SE MYSQL Tables
	 */
	public J2SEMYSQLTableSchemasTemplatesParser() {

	}

	/**
	 * Creates the schemas of all the database tables
	 * 
	 * @param androidProjectConfiguration
	 */
	public String createTablesSchemas(J2SEProjectConfiguration j2seProjectConfiguration, Table[] tables) {

		String packageName = j2seProjectConfiguration.getPackageName();

		J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration = j2seProjectConfiguration
				.getJ2SEProjectMYSQLDatabaseConfiguration();

		String databaseTablesTemplate = new J2SEMYSQLDatabaseTablesSchemasTemplate().getTemplate();

		String tablesSchemas = "";

		for (Table table : tables) {
			tablesSchemas += createTableSchemas(j2seProjectMYSQLDatabaseConfiguration, table);
		}
		String databaseTablesSchemasPackage = j2seProjectConfiguration.getJ2SEProjectMYSQLDatabaseConfiguration().getTableSchemasSrcDir();
		
		// Add table package name
		String tablesSchemasClassPackageName = packageName + NativeTemplateTags.DOT + NativeUtils.parseJavaPackage(databaseTablesSchemasPackage);
		
		databaseTablesTemplate = parseTablesClassPackageName(databaseTablesTemplate, tablesSchemasClassPackageName);


		// Add all the tables SQL
		String tablesSQLVaribles = createTablesSQLVariables(tables);
		databaseTablesTemplate = parseTablesSQLVariables(databaseTablesTemplate, tablesSQLVaribles);

		// Add all the the tables Schemas
		databaseTablesTemplate = parseTablesSchemasAll(databaseTablesTemplate, tablesSchemas);

		return databaseTablesTemplate;
	}

	// Add all the tables SQL
	private String parseTablesSQLVariables(String databaseTablesTemplate, String tablesSQLVaribles) {
		return databaseTablesTemplate.replace(TemplateTags.Java.DATABASE_TABLES_SQL_VARIABLE, tablesSQLVaribles);
	}

	// Add all the tables SQL reference link to a global variable
	private String createTablesSQLVariables(Table[] tables) {

		String tablesSQLVariable = "";

		for (int i = 0; i < tables.length; i++) {
			String tableReferenceTemplate = SimpleTemplates.Java.J2SE_MYSQL_DATABASE_TABLE_SQL_VARIABLE;
			String javaBeansTableClassName = NativeUtils.toJavaBeansClass(tables[i].getTableName());

			tablesSQLVariable += tableReferenceTemplate.replace(TemplateTags.Java.JAVA_BEANS_CLASS_NAME,
					javaBeansTableClassName);

			if (i < tables.length - 1) {
				tablesSQLVariable += TemplateTags.TAG_PRINTING_CHAR_COMMA;
			}
		}
		return tablesSQLVariable;
	}

	// Adds a valid package name to the the tables class that holds all the
	// schemas for the database tables
	private String parseTablesClassPackageName(String databaseTablestemplate, String tablesClassPackageName) {
		tablesClassPackageName = tablesClassPackageName.replace(NativeUtils.getFileSeparator(),
				TemplateTags.TAG_PRINTING_CHAR_DOT);
		return databaseTablestemplate.replace(TemplateTags.Java.PACKAGE_NAME, tablesClassPackageName);
	}

	// Add all the the tables Schemas
	private String parseTablesSchemasAll(String databaseTablestemplate, String tablesSchemas) {
		return databaseTablestemplate.replace(TemplateTags.Java.DATABASE_TABLES_SCHEMAS, tablesSchemas);
	}

	/**
	 * Creates the schemas of a database tables
	 * 
	 * @param androidProjectConfiguration
	 */
	private String createTableSchemas(J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration, Table table) {

		// Set the original schema for the table to be the template, the replace
		// with actual data
		String tableSchema = new J2SEMYSQLTablesSchemasTemplate().getTemplate();

		// Add java beans class name
		tableSchema = parseJavaBeansClassName(tableSchema, table.getTableName());

		// Add Table name
		tableSchema = parseTableName(tableSchema, table.getTableName());

		// Add Table TableColumn
		String tableColumnsVariableTemplate = new J2SEMYSQLTableColumnVariableDefinitionTemplate().getTemplate();
		String tableColumnsVariables = createTableColumnsVariables(tableColumnsVariableTemplate, table);
		tableSchema = parseTableColumns(tableSchema, tableColumnsVariables);

		// Add table SQL
		String tableSQL = generateTableSQL(table);
		
		tableSchema = parseTableSQL(tableSchema, tableSQL);


		return tableSchema;

	}

	
	private String generateTableSQL(Table table) {
		
		String tableSQLTemplate = new J2SEMYSQLTableCreateSQLTemplate().getTemplate();

		// Add table name
		String tableName = table.getTableName();
		tableSQLTemplate = parseTableName(tableSQLTemplate, tableName);

		String tableColumns = "";

		TableColumn[] columns = table.getTableColumnsAll();

		for (int i = 0; i < columns.length; i++) {

			DataType dataType = columns[i].getDataType();
			String columnName = columns[i].getColumnName();
			String _datatype = dataType.getDataType();
			String constraints = dataType.getConstraints().getConstraint();
			String qualifiedColumnName = _datatype + PrintingChars.SPACE + constraints;

			String tableColumnStatementTemplate = new J2SEMYSQLStatementSQLTableColumnStatementTemplate().getTemplate();

			boolean isLastColumn = (i == columns.length - 1);
			tableColumnStatementTemplate = parseTableColumnsAndDataTypes(tableColumnStatementTemplate, columnName,
					qualifiedColumnName, isLastColumn);

			tableColumns += Natives.Math.ADD + tableColumnStatementTemplate;

		}

		// Add table columns to the sql template
		tableSQLTemplate = parseTableColumnsAndDataTypesAll(tableSQLTemplate, tableColumns);
		
		return tableSQLTemplate;
	}

	private String parseTableColumnsAndDataTypesAll(String tableSQLTemplate, String tableColumns) {
		return tableSQLTemplate.replace(TemplateTags.Java.TABLE_COLUMNS, tableColumns);
	}

	private String parseTableColumnsAndDataTypes(String tableColumnStatementTemplate, String columnName,
			String dataType, boolean isLastColumn) {
		if (isLastColumn) {
			tableColumnStatementTemplate = tableColumnStatementTemplate
					.replace(TemplateTags.Java.TABLE_COLUMNS_COMMA_SEPARATOR, TemplateTags.TAG_EMPTY_STRING);
		} else {
			tableColumnStatementTemplate = tableColumnStatementTemplate
					.replace(TemplateTags.Java.TABLE_COLUMNS_COMMA_SEPARATOR, TemplateTags.TAG_PRINTING_CHAR_COMMA);
		}
		return tableColumnStatementTemplate
				.replace(TemplateTags.Java.TABLE_COLUMN_DEFINITION, columnName.toUpperCase())
				.replace(TemplateTags.Java.TABLE_COLUMN_DATATYPE, dataType);
	}

	// Add table sql
	private String parseTableSQL(String tableSchema, String tableSQL) {
		return tableSchema.replace(TemplateTags.Java.TABLE_CREATE_SQL, tableSQL);
	}

	
	private String parseTableColumns(String tableSchema, String tableColumnsVariables) {
		return tableSchema.replace(TemplateTags.Java.TABLE_COLUMNS, tableColumnsVariables);

	}

	private String parseTableName(String tableSchema, String tableName) {
		return tableSchema.replace(TemplateTags.Java.TABLE_NAME, tableName);
	}

	private String parseJavaBeansClassName(String tableSchema, String tableName) {
		if (tableName.length() <= 1) {
			return tableName.toUpperCase();
		}
		String javaBeansClassName = NativeUtils.toJavaBeansClass(tableName);
		return tableSchema.replace(TemplateTags.Java.TABLE_JAVA_BEANS_CLASS_NAME, javaBeansClassName);
	}

	// Create Table TableColumn Variables
	private String createTableColumnsVariables(String tableColumnsVariableTemplate, Table table) {

		String columnVariables = "";
		TableColumn[] columns = table.getColumns();
		for (int i = 0; i < columns.length; i++) {
			String columnName = columns[i].getColumnName();
			columnVariables += parseColumnVariables(tableColumnsVariableTemplate, columnName);
		}

		// Dont forget to Add primary key column and other key columns
		PrimaryKey primaryKey = table.getPrimaryKey();
		String primaryKeyColumnName = primaryKey.getColumnName();
		columnVariables += parseColumnVariables(tableColumnsVariableTemplate, primaryKeyColumnName);
		return columnVariables;
	}

	private String parseColumnVariables(String tableColumnsVariableTemplate, String columnName) {

		return tableColumnsVariableTemplate
				.replace(TemplateTags.Java.TAG_TABLE_COLUMN_DEFINITION, columnName.toUpperCase())
				.replace(TemplateTags.Java.TAG_TABLE_COLUMN_REFERENCE, columnName);
	}
}
