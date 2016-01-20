package com.marvik.apis.dbcrudgen.parser.android.tablecrud;

import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters.JavaDelimiter;
import com.marvik.apis.dbcrudgen.core.platforms.java.object.accessibility.JavaObjectAccessibility;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.MYSQLDataTypes;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.android.crud.statements.AndroidStatementContentValuesPutTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;
import com.marvik.apis.dbcrudgen.utilities.Utils;

public class AndroidTableCRUDTemplateParser extends AndroidTemplatesParser {

	public String createSourceCode(String packageFilePath, String tablesSchemasPackage,String tableModelsPackage, Table table) {

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

		// add table custom delete method

		return tableCrudTemplate;
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
			String columnName = columns[i].getColumnName();
			String dataType = columns[i].getDataType().getDataType();
			String objectName = NativeUtils.toJavaBeansVariable(columnName);
			JavaDelimiter javaDelimeter = JavaDelimiter.NONE;

			tableColumnsReference += createTableColumnReference(NativeUtils.toJavaBeansClass(table.getTableName()),
					columnName);

			//Append the object in the String.valueOf(#Object) template 
			tableColumnsObjects += NativeUtils.parseStringDefaultParser(objectName);

			if (i < columns.length - 1) {
				javaDelimeter = JavaDelimiter.COMMA;
				tableColumnsReference += ",";
				tableColumnsObjects += ",";
			}

			// DO NOT MOVE THIS METHOD FROM HERE - -THE GENERATED CODE WILL NOT
			// HAVE ANY JAVA DELIMETER - BECAUSE IT WILL USE THE
			// JAVA_DELIMETER#NULL
			tableColumnsVariable += NativeUtils.createJavaVariable(JavaObjectAccessibility.DEFAULT, dataType,
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
		return prepareQueriedColumnQueryArtificats(crudTemplate.getTemplate().toString(), table, tableColumn);

	}

	private String prepareQueriedColumnQueryArtificats(String template, Table table, TableColumn tableColumn) {

		// Add queried column
		String queriedColumn = tableColumn.getColumnName();

		String tableJavaBeansName = NativeUtils.toJavaBeansClass(table.getTableName());

		String queriedColumnReference = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME
				+ TemplateTags.TAG_PRINTING_CHAR_DOT + tableJavaBeansName + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ queriedColumn.toUpperCase();

		// Get the table primary key
		PrimaryKey primaryKey = table.getPrimaryKey();

		// Add primary key column parameters
		String primaryKeyParamObject = NativeUtils.parseStringDefaultParser(primaryKey.getPrimaryKey());

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

				.replace(TemplateTags.Android.FUNCTION_PARAMS_VALUES,
						methodParamsObjects)

				.replace(TemplateTags.Android.FUNCTION_PARAMS, methodParamsObjects)

				// Add queried column comment
				.replace(TemplateTags.Android.QUERIED_TABLE_COLUMN_NAME, (queriedColumn))

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
	private String parseTableModelInfoClassImport(String tableCrudTemplate, String tableModelInfoPackage, String tableName) {
		tableModelInfoPackage = parseJavaPackage(tableModelInfoPackage);
		String tableModelInfoClass = tableModelInfoPackage + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ NativeUtils.toJavaBeansClass(tableName) +TemplateTags.Android.INFO;
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
