package com.marvik.apis.dbcrudgen.parser;

import java.util.Locale;

import com.marvik.apis.dbcrudgen.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.ColumnKeys;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.ForeignKeys;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKeys;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.UniqueKeys;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;
import com.marvik.apis.dbcudgen.java.templates.php.PHPColumnAccessorsTemplate;
import com.marvik.apis.dbcudgen.java.templates.php.PHPColumnsCrudTemplate;
import com.marvik.apis.dbcudgen.java.templates.php.PHPTableClassCrudTemplate;

public class PHPTemplatesParser {

	private PHPTableClassCrudTemplate phpTableClassCrudTemplate;
	private PHPColumnsCrudTemplate phpColumnsCrudTemplate;
	private PHPColumnAccessorsTemplate phpColumnAccessorsTemplate;

	public PHPTemplatesParser() {
		phpTableClassCrudTemplate = new PHPTableClassCrudTemplate();
		phpColumnsCrudTemplate = new PHPColumnsCrudTemplate();
		phpColumnAccessorsTemplate = new PHPColumnAccessorsTemplate();
	}

	public PHPColumnAccessorsTemplate getPhpColumnAccessorsTemplate() {
		return phpColumnAccessorsTemplate;
	}

	public PHPColumnsCrudTemplate getPhpColumnsCrudTemplate() {
		return phpColumnsCrudTemplate;
	}

	public PHPTableClassCrudTemplate getPhpTableClassCrudTemplate() {
		return phpTableClassCrudTemplate;
	}

	public String getDatabaseName(Database database) {
		return database.getDatabaseName();
	}

	public String getTableName(Table table) {
		return table.getTableName();
	}

	public String getColumnName(Columns columns) {
		return columns.getColumnName();
	}

	public String getTableCrud(Table table) {

		String tableCrudSQL = getPhpTableClassCrudTemplate().getTemplate();

		String tableName = table.getTableName();
		Columns[] columns = table.getColumns();

		String columnsCrud = generateColumnsCrudFunctions(table, columns);
		String columnsAccessor = generateColumnAccessorMethods(columns);

		// Parse column accessors - replaces the column accessor templates with
		// the actual functions
		tableCrudSQL = addColumnAccessorFunctions(tableCrudSQL, columnsAccessor);

		// Parse column crud functions - replaces the column crud templates with
		// the actual crud functions
		tableCrudSQL = addColumnsQueryFunctions(tableCrudSQL, columnsCrud);

		// Replace table name tag with the actual table name
		tableCrudSQL = addTableName(tableCrudSQL, table.getTableName());

		// Replace the class name tag with the actual class name which is
		// derived from the table name
		tableCrudSQL = addClassName(tableCrudSQL, table.getTableName());

		return tableCrudSQL;
	}

	/*
	 * Replaces the class name tag with the actual class name
	 */
	private String addClassName(String tableCrudSQL, String tableName) {
		String className = parseJavaBeansClassName(tableName);
		return tableCrudSQL.replace(TemplateTags.PHP.CLASS_NAME, className);
	}

	private String parseJavaBeansClassName(String tableName) {
		tableName = tableName.toLowerCase(Locale.getDefault());
		if (tableName.length() <= 1) {
			return tableName.toUpperCase(Locale.getDefault());
		}
		String firstChar = String.valueOf(tableName.charAt(0));
		firstChar = firstChar.toUpperCase();

		String otherChars = tableName.substring(1, tableName.length());
		return firstChar + otherChars;
	}

	/*
	 * Replaces the table name tag with the actual table name
	 */
	private String addTableName(String tableCrudSQL, String tableName) {
		return tableCrudSQL.replace(TemplateTags.PHP.TABLE_NAME, tableName);
	}

	/*
	 * Adds the column query functions to the class template
	 */
	private String addColumnsQueryFunctions(String tableCrudSQL, String columnsQueryFunctions) {
		return tableCrudSQL.replace(TemplateTags.PHP.TABLE_COLUMNS_CRUD_FUNCTIONS, columnsQueryFunctions);
	}

	/*
	 * Adds the column accessor functions to the class template
	 */
	private String addColumnAccessorFunctions(String tableCrudSQL, String columnsAccessors) {
		return tableCrudSQL.replace(TemplateTags.PHP.TABLE_COLUMNS_ACCESSOR_FUNCTIONS, columnsAccessors);
	}

	/*
	 * Takes a column name, replaces it with the COLUMN_NAME_TEMPLATE_TAG
	 * 
	 * @Return the new string
	 */

	public String createColumnsAccessorFunctions(Columns[] columns) {
		String columnsAccessorFunctions = "";
		for (Columns column : columns) {
			columnsAccessorFunctions += generateColumnAccessorMethods(column);
		}
		return columnsAccessorFunctions;
	}

	/*
	 * Parse Column Accessors -> Replaces the column name in the template with
	 * the passed column name
	 */
	private String parseColumnAccesssors(String columnName, String template) {
		return template.replace(TemplateTags.PHP.COLUMN_NAME_TEMPLATE_TAG, columnName);
	}

	/*
	 * generate the columns accessor methods
	 */
	public String generateColumnAccessorMethods(Columns[] columns) {
		String columnsAccessor = "";
		for (Columns column : columns) {
			String columnName = column.getColumnName();
			columnsAccessor += parseColumnAccesssors(columnName, getPhpColumnAccessorsTemplate().getTemplate());
		}
		return columnsAccessor;
	}

	/*
	 * generate the columns accessor methods
	 */
	public String generateColumnAccessorMethods(Columns columns) {
		return generateColumnGetters(columns.getColumnName()) + generateColumnSetters(columns.getColumnName());
	}

	/*
	 * generate the columns name setters
	 */
	private String generateColumnSetters(String columnName) {
		return phpColumnsCrudTemplate.getTemplate().replace("COLUMN_NAME", columnName);
	}

	/*
	 * generate the columns name getters
	 */
	private String generateColumnGetters(String columnName) {
		return phpColumnsCrudTemplate.getTemplate().replace("COLUMN_NAME", columnName);
	}

	private String generateColumnsCrudFunctions(Table table, Columns[] columns) {
		String columnsCrudFunction = "";

		// Generate crud functions for primary key columns
		columnsCrudFunction += generatePrimaryKeysCrudFunctions(table.getPrimaryKeys(), columns);

		// Generate crud functions for foreign key columns
		columnsCrudFunction += generateForeignKeysCrudFunctions(table.getForeignKeys(), columns);

		// Generate crud functions for unique key columns
		columnsCrudFunction += generateUniqueKeysCrudFunctions(table.getUniqueKeys(), columns);

		// Generate crud functions for other database columns
		for (Columns column : columns) {
			columnsCrudFunction += generateColumnCrudFunctions(table.getPrimaryKeys(), column);
		}
		return columnsCrudFunction;
	}

	/*
	 * Generate the crud to get the primary keys for the table
	 */
	private String generatePrimaryKeysCrudFunctions(PrimaryKeys primaryKeys, Columns[] columns) {
		return generateColumnKeysCrudFunctions(primaryKeys, columns);
	}

	/*
	 * Generate the crud to get the foreign keys for the table
	 */
	private String generateForeignKeysCrudFunctions(ForeignKeys foreignKeys, Columns[] columns) {
		return generateColumnKeysCrudFunctions(foreignKeys, columns);
	}

	/*
	 * Generate the crud to get the unique keys for the table
	 */
	private String generateUniqueKeysCrudFunctions(UniqueKeys uniqueKeys, Columns[] columns) {
		return generateColumnKeysCrudFunctions(uniqueKeys, columns);
	}

	private String generateColumnKeysCrudFunctions(ColumnKeys columnKeys, Columns[] columns) {
		String columnKeysCrudFunctions = "";
		for(String columnKey : columnKeys.getColumnKeys()){
			columnKeysCrudFunctions += generateColumnKeysCrudFunction(columnKey,columns);
		}
		return columnKeysCrudFunctions;
	}

	private String generateColumnKeysCrudFunction(String columnKey, Columns[] columns) {
		String columnKeysCrudFunction = "";
		for(Columns column : columns){
			columnKeysCrudFunction += "";
		}
		return columnKeysCrudFunction;
	}

	/*
	 * Generates column crud functions from a template
	 */
	private String generateColumnCrudFunctions(PrimaryKeys primaryKeys, Columns columns) {
		String columnsCrudTemplate = getPhpColumnsCrudTemplate().getTemplate();
		String columnName = columns.getColumnName();

		String[] mPrimaryKeys = primaryKeys.getPrimaryKeys();

		String primaryKeyParams = "";
		String primaryKeyParamsValues = "";

		for (int i = 0; i < mPrimaryKeys.length; i++) {

			primaryKeyParams += "'" + mPrimaryKeys[i] + "'";
			primaryKeyParamsValues += "$" + mPrimaryKeys[i];

			if (i < mPrimaryKeys.length - 1) {
				primaryKeyParams += ",";
				primaryKeyParamsValues += ",";
			}
		}
		return parseColumnQueryFunction(columnName, primaryKeyParams, primaryKeyParamsValues, columnsCrudTemplate);
	}
	/*
	 * Takes a column name,the table PRIMARY_KEYS_COLUMN_PARAMS and replaces it
	 * with the PRIMARY_KEYS_COLUMN_PARAMS, PRIMARY_KEYS_PARAM_VALUES
	 * 
	 * @Return the data found in the column
	 */

	private String parseColumnQueryFunction(String columnName, String primaryKeyParams, String primaryKeyParamsValues,
			String template) {
		return template.replace(TemplateTags.PHP.QUERIED_COLUMN, columnName)
				.replace(TemplateTags.PHP.QUERY_RESULTS, columnName + "_")
				.replace(TemplateTags.PHP.PRIMARY_KEYS_COLUMN_PARAMS, primaryKeyParams)
				.replace(TemplateTags.PHP.PRIMARY_KEYS_PARAM_VALUES, primaryKeyParamsValues);
	}
}
