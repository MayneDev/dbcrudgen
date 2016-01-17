package com.marvik.apis.dbcrudgen.parser.android.tablecrud;

import com.marvik.apis.dbcrudgen.core.platforms.java.grammar.delimeters.JavaDelimiter;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.MYSQLDataTypes;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;
import com.marvik.apis.dbcrudgen.utilities.Utils;

public class AndroidTableCRUDTemplateParser extends AndroidTemplatesParser {

	public String createSourceCode(String packageFilePath, Table table) {

		String tableName = table.getTableName();

		String tableCrudTemplate = getAndroidClassTableCrudTemplate().getTemplate();

		// add package name
		tableCrudTemplate = parsePackageName(tableCrudTemplate, packageFilePath);

		// add tables schemas import
		tableCrudTemplate = parseTablesSchemasClassImport(tableCrudTemplate, packageFilePath);

		// add database class name
		tableCrudTemplate = parseTableClassName(tableCrudTemplate, tableName);

		// add table content uri link
		tableCrudTemplate = parseTableContentUri(tableCrudTemplate, tableName);

		// add column query methods
		tableCrudTemplate = parseColumnsQueryMethods(tableCrudTemplate, table);

		return tableCrudTemplate;
	}

	// add column query methods
	private String parseColumnsQueryMethods(String tableCrudTemplate, Table table) {
		String columnQueryMethods = "";

		for (TableColumn tableColumn : table.getColumns()) {
			columnQueryMethods += generateColumnQueryMethod(tableCrudTemplate, tableColumn, table);
		}
		return tableCrudTemplate.replace(TemplateTags.Android.COLUMN_SPECIFIC_QUERY_METHODS, columnQueryMethods);
	}

	// generate the query method for the column
	private String generateColumnQueryMethod(String tableCrudTemplate, TableColumn tableColumn, Table table) {
		DataType dataType = tableColumn.getDataType();
		MYSQLDataTypes mysqlDataType = Utils.parseMysqlDatatype(dataType.getDataType());
		String mClass = Utils.generateJavaQueryMethodReturnType(mysqlDataType);
		return generateQueryMethod(mClass, tableColumn, table);
	}

	// generate the query method for the column
	public String generateQueryMethod(String mClass, TableColumn tableColumn, Table table) {

		// Boolean
		if (mClass.equalsIgnoreCase("Boolean")) {
			return generateBooleanColumnQueryMethod(tableColumn, table);
		}
		// Byte
		if (mClass.equalsIgnoreCase("Byte")) {
			return generateByteColumnQueryMethod(tableColumn, table);
		}
		// Integer
		if (mClass.equalsIgnoreCase("Integer")) {
			return generateIntegerColumnQueryMethod(tableColumn, table);
		}
		// Date
		if (mClass.equalsIgnoreCase("Date")) {
			// TO DO ADD SOURCE CODE
		}
		// Double
		if (mClass.equalsIgnoreCase("Double")) {
			return generateDoubleColumnQueryMethod(tableColumn, table);
		}
		// Float
		if (mClass.equalsIgnoreCase("Float")) {
			return generateFloatColumnQueryMethod(tableColumn, table);
		}
		// Long
		if (mClass.equalsIgnoreCase("Long")) {
			return generateLongColumnQueryMethod(tableColumn, table);
		}
		// String
		if (mClass.equalsIgnoreCase("String")) {
			return generateStringColumnQueryMethod(tableColumn, table);
		}

		if (mClass.equalsIgnoreCase("Class")) {
			return generateGenericColumnQueryMethod(tableColumn, table);
		}
		return null;
	}

	private String generateGenericColumnQueryMethod(TableColumn tableColumn, Table table) {
		String template = getAndroidMethodColumnsCrudDataTypeGenericTemplate().getTemplate();
		return parseQueriedColumnQueryArtificats(template, tableColumn, table);
	}

	private String generateStringColumnQueryMethod(TableColumn tableColumn, Table table) {
		String template = getAndroidMethodColumnsCrudDataTypeStringTemplate().getTemplate();
		return parseQueriedColumnQueryArtificats(template, tableColumn, table);
	}

	private String generateLongColumnQueryMethod(TableColumn tableColumn, Table table) {

		String template = getAndroidMethodColumnsCrudDataTypeLongTemplate().getTemplate();

		return parseQueriedColumnQueryArtificats(template, tableColumn, table);
	}

	private String generateFloatColumnQueryMethod(TableColumn tableColumn, Table table) {

		String template = getAndroidMethodColumnsCrudDataTypeFloatTemplate().getTemplate();
		return parseQueriedColumnQueryArtificats(template, tableColumn, table);
	}

	private String generateDoubleColumnQueryMethod(TableColumn tableColumn, Table table) {

		String template = getAndroidMethodColumnsCrudDataTypeFloatTemplate().getTemplate();

		return parseQueriedColumnQueryArtificats(template, tableColumn, table);
	}

	private String generateIntegerColumnQueryMethod(TableColumn tableColumn, Table table) {

		String template = getAndroidMethodColumnsCrudDataTypeIntegerTemplate().getTemplate();
		return parseQueriedColumnQueryArtificats(template, tableColumn, table);
	}

	private String generateByteColumnQueryMethod(TableColumn tableColumn, Table table) {

		String template = getAndroidMethodColumnsCrudDataTypeIntegerTemplate().getTemplate();
		return parseQueriedColumnQueryArtificats(template, tableColumn, table);
	}

	private String generateBooleanColumnQueryMethod(TableColumn tableColumn, Table table) {
		String template = getAndroidMethodColumnsCrudDataTypeIntegerTemplate().getTemplate();
		return parseQueriedColumnQueryArtificats(template, tableColumn, table);
	}

	private String parseQueriedColumnQueryArtificats(String template, TableColumn tableColumn, Table table) {

		PrimaryKey primaryKey = table.getPrimaryKey();

		String queriedColumn = tableColumn.getColumnName();

		String tableJavaBeansName = NativeUtils.toJavaBeansClass(table.getTableName());

		String queriedColumnReference = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME
				+ TemplateTags.TAG_PRINTING_CHAR_DOT + tableJavaBeansName + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ queriedColumn.toUpperCase();

		String primaryKeyColumnReference = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME
				+ TemplateTags.TAG_PRINTING_CHAR_DOT + tableJavaBeansName + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ primaryKey.getPrimaryKey().toUpperCase();

		String primaryKeyParamVariableDeclaration = NativeUtils.createJavaVariable(
				primaryKey.getDataType().getDataType(), primaryKey.getPrimaryKey(), JavaDelimiter.NONE);

		String primaryKeyParamObject = primaryKey.getPrimaryKey();

		return

		// Add primary key variable
		template.replace(TemplateTags.Android.FUNCTION_PARAMS_VARIABLES, primaryKeyParamVariableDeclaration)

				// Add primary key column reference
				.replace(TemplateTags.Android.FUNCTION_PARAMS_KEYS, primaryKeyColumnReference)

				// Add primary key column parameters
				.replace(TemplateTags.Android.FUNCTION_PARAMS_VALUES,
						NativeUtils.parseStringDefaultParser(primaryKeyParamObject))

				// Add queried column
				.replace(TemplateTags.Android.QUERIED_COLUMN, NativeUtils.toJavaBeansClass(queriedColumn))

				// Add queried column reference
				.replace(TemplateTags.Android.QUERIED_TABLE_COLUMN_REFERENCE, queriedColumnReference);
	}

	private String parseTableContentUri(String tableCrudTemplate, String tableName) {
		String tableDefinitionLink = AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME
				+ TemplateTags.TAG_PRINTING_CHAR_DOT + tableName.toUpperCase();
		return tableCrudTemplate.replace(TemplateTags.Android.TABLE_DEFINITION_LINK, tableDefinitionLink);
	}

	// add database class name
	private String parseTableClassName(String tableCrudTemplate, String tableName) {
		String javaBeansTableClass = NativeUtils.toJavaBeansClass(tableName);
		return tableCrudTemplate.replace(TemplateTags.Android.DATABASE_TABLE_CLASS, javaBeansTableClass);
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
