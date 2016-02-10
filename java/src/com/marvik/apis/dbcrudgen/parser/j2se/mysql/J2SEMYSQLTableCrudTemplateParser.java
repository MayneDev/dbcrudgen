/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.j2se.mysql;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.java.j2se.J2SETemplatesParser;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectMYSQLDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.java.filenames.JavaProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.J2SEMYSQLTableCrudTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
*Created on Feb 10, 2016-8:28:29 AM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEMYSQLTableCrudTemplateParser extends J2SETemplatesParser {

	/**
	 * @param j2seProjectConfiguration
	 * @param table
	 * @return
	 */
	public String createSourceCode(J2SEProjectConfiguration j2seProjectConfiguration, Table table) {

		String template = new J2SEMYSQLTableCrudTemplate().getTemplate();

		// Project package name
		String projectPackageName = j2seProjectConfiguration.getPackageName();

		// J2SE Project Database Configuration
		J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration = j2seProjectConfiguration
				.getJ2SEProjectMYSQLDatabaseConfiguration();

		String tableCrudStorageDir = j2seProjectMYSQLDatabaseConfiguration.getTablesCRUDSrcDir();

		String thisTableCrudClassPackageName = NativeUtils
				.parseJavaPackage(projectPackageName + NativeUtils.getFileSeparator() + tableCrudStorageDir
						+ NativeUtils.getFileSeparator() + table.getTableName().toLowerCase());

		template = addImports(table, template, projectPackageName, j2seProjectMYSQLDatabaseConfiguration,
				thisTableCrudClassPackageName);

		String tableName = table.getTableName();

		template = addTableNameAndClass(template, tableName);

		template = addCrudMethods(template, table);

		return template;
	}

	/**
	 * 
	 * Adds all the crud methods for the table
	 * 
	 * @param template
	 * @param table
	 * @return template
	 */
	private String addCrudMethods(String template, Table table) {

		template = addInsertMethodStatements(template, table);

		template = addDeleteMethodStatements(template, table);

		template = addSearchMethodStatements(template, table);

		template = addUpdateMethodStatements(template, table);

		template = addTableModelConstructorParams(template, table.getTableColumnsAll());
		
		template = addColumnValueGetters(template, table, false);

		template = addColumnValueGetters(template, table, true);

		template = addPrimaryKeyColumn(template, table);

		return template;
	}

	/**
	 * 
	 * Add primary key on method params where the primary key is used for
	 * querying columns
	 * 
	 * @param template
	 * @param table
	 * @return template
	 */
	private String addPrimaryKeyColumn(String template, Table table) {
		String tableName = table.getTableName();
		PrimaryKey primaryKey = table.getPrimaryKey();
		String columnName = primaryKey.getColumnName();
		String columnNameDeclarationTemplate = SimpleTemplates.Java.TABLE_COLUMN_NAME_REFERENCE;
		String primaryKeyReference = parseTableColumns(columnNameDeclarationTemplate, tableName, columnName);
		return template.replace(TemplateTags.Java.PRIMARY_KEY_REFERENCE, primaryKeyReference)
				.replace(TemplateTags.Java.PRIMARY_KEY_OBJECT, NativeUtils.toJavaBeansVariable(columnName))
				.replace(TemplateTags.Java.PRIMARY_KEY_DATATYPE,
						getJavaObjectDataType(primaryKey.getDataType().getDataType()));
	}

	/**
	 * @param template
	 * @param columns
	 * @return template
	 */
	private String addColumnValueGetters(String template, Table table, boolean includePrimaryKeyColumn) {

		String tableName = table.getTableName();

		TableColumn[] columns = includePrimaryKeyColumn ? table.getTableColumnsAll() : table.getColumns();

		String resultSetGetters = "";

		for (TableColumn tableColumn : columns) {

			String columnName = tableColumn.getColumnName();

			String dataType = tableColumn.getDataType().getDataType();

			String resultSetGetterTemplate = getResultSetGetterTemplate(dataType);

			resultSetGetters += parseResultSetGetterTemplate(resultSetGetterTemplate, tableName, columnName);

		}
		return includePrimaryKeyColumn
				? template.replace(TemplateTags.Java.RESULT_SET_COLUMNS_ALL_VALUES_GETTERS, resultSetGetters)
				: template.replace(TemplateTags.Java.RESULT_SET_COLUMNS_VALUES_GETTERS, resultSetGetters);

	}

	/**
	 * Parses a result set getter template and adds the right bindings
	 * 
	 * @param resultSetGetterTemplate
	 * @param tableColumn
	 * @return resultSetGetterTemplate
	 */
	private String parseResultSetGetterTemplate(String resultSetGetterTemplate, String tableName, String columnName) {
		String resultSetGetter = resultSetGetterTemplate.replace(TemplateTags.Java.COLUMN_OBJECT,
				NativeUtils.toJavaBeansVariable(columnName));

		return parseTableColumns(resultSetGetter, tableName, columnName);
	}

	/**
	 * 
	 * Adds the update statements
	 * 
	 * @param template
	 * @param table
	 * @return template
	 */
	private String addUpdateMethodStatements(String template, Table table) {

		String tableColumnsReferences = "";
		String oldTableColumnsValues = "";
		String newTableColumnsValues = "";

		String columnNameDeclarationTemplate = SimpleTemplates.Java.TABLE_COLUMN_NAME_REFERENCE;

		TableColumn[] tableColumns = table.getColumns();
		for (int i = 0; i < tableColumns.length; i++) {

			tableColumnsReferences += parseTableColumns(columnNameDeclarationTemplate, table.getTableName(),
					tableColumns[i].getColumnName());
			oldTableColumnsValues += parseUpdateTableValues(TemplateTags.SQL.VERSION_OLD, table.getTableName(),
					tableColumns[i].getColumnName());
			newTableColumnsValues += parseUpdateTableValues(TemplateTags.SQL.VERSION_NEW, table.getTableName(),
					tableColumns[i].getColumnName());

			if (i < tableColumns.length - 1) {
				tableColumnsReferences += ",";
				oldTableColumnsValues += ",";
				newTableColumnsValues += ",";
			}
		}
		return template.replace(TemplateTags.Java.TABLE_COLUMNS, tableColumnsReferences)
				.replace(TemplateTags.Java.TABLE_UPDATE_OLD_VALUES, oldTableColumnsValues)
				.replace(TemplateTags.Java.TABLE_UPDATE_NEW_VALUES, newTableColumnsValues);

	}

	/**
	 * Adds the table model constructor parameters
	 * 
	 * @param template
	 * @param columns
	 * @return
	 */
	private String addTableModelConstructorParams(String template, TableColumn[] columns) {
		return template.replace(TemplateTags.Java.TABLE_MODEL_CONSTRUCTOR_OBJECTS,
				getTableModelConstructorParams(columns));
	}

	/**
	 * Gets the table model constructor params
	 * 
	 * @param template
	 * @param columns
	 * @return template
	 */
	private String getTableModelConstructorParams(TableColumn[] columns) {

		String constructorParams = "";

		for (int i = 0; i < columns.length; i++) {

			constructorParams += NativeUtils.toJavaBeansVariable(columns[i].getColumnName());

			if (i < columns.length - 1) {
				constructorParams += ",";
			}
		}
		return constructorParams;
	}

	/**
	 * @param template
	 * @param table
	 * @return template
	 */
	private String addSearchMethodStatements(String template, Table table) {
		String tableSearchColumns = "";
		String tableSearchValues = "";

		String columnNameDeclarationTemplate = SimpleTemplates.Java.TABLE_COLUMN_NAME_REFERENCE;

		TableColumn[] tableColumns = table.getColumns();
		for (int i = 0; i < tableColumns.length; i++) {

			tableSearchColumns += parseTableColumns(columnNameDeclarationTemplate, table.getTableName(),
					tableColumns[i].getColumnName());
			tableSearchValues += parseTableValues(table.getTableName(), tableColumns[i].getColumnName());

			if (i < tableColumns.length - 1) {
				tableSearchColumns += ",";
				tableSearchValues += ",";
			}
		}
		return template.replace(TemplateTags.Java.TABLE_SEARCH_COLUMNS, tableSearchColumns)
				.replace(TemplateTags.Java.TABLE_SEARCH_VALUES, tableSearchValues);
	}

	/**
	 * @param template
	 * @param table
	 * @return
	 */
	private String addDeleteMethodStatements(String template, Table table) {
		String tableDeleteColumns = "";
		String tableDeleteValues = "";

		String columnNameDeclarationTemplate = SimpleTemplates.Java.TABLE_COLUMN_NAME_REFERENCE;

		TableColumn[] tableColumns = table.getColumns();
		for (int i = 0; i < tableColumns.length; i++) {

			tableDeleteColumns += parseTableColumns(columnNameDeclarationTemplate, table.getTableName(),
					tableColumns[i].getColumnName());
			tableDeleteValues += parseTableValues(table.getTableName(), tableColumns[i].getColumnName());

			if (i < tableColumns.length - 1) {
				tableDeleteColumns += ",";
				tableDeleteValues += ",";
			}
		}
		return template.replace(TemplateTags.Java.TABLE_DELETE_COLUMNS, tableDeleteColumns)
				.replace(TemplateTags.Java.TABLE_DELETE_VALUES, tableDeleteValues);
	}

	/**
	 * Add the insert method
	 * 
	 * @param template
	 * @param table
	 * @return template
	 */
	private String addInsertMethodStatements(String template, Table table) {

		String tableInsertColumns = "";
		String tableInsertValues = "";

		String columnNameDeclarationTemplate = SimpleTemplates.Java.TABLE_COLUMN_NAME_REFERENCE;

		TableColumn[] tableColumns = table.getColumns();
		for (int i = 0; i < tableColumns.length; i++) {

			tableInsertColumns += parseTableColumns(columnNameDeclarationTemplate, table.getTableName(),
					tableColumns[i].getColumnName());
			tableInsertValues += parseTableValues(table.getTableName(), tableColumns[i].getColumnName());

			if (i < tableColumns.length - 1) {
				tableInsertColumns += ",";
				tableInsertValues += ",";
			}
		}
		return template.replace(TemplateTags.Java.TABLE_INSERT_COLUMNS, tableInsertColumns)
				.replace(TemplateTags.Java.TABLE_INSERT_VALUES, tableInsertValues);
	}

	/**
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	private String parseTableValues(String tableName, String columnName) {
		String genericStringParser = SimpleTemplates.Java.STRING_GENERIC_PARSER;

		String javaBeansGetterTemplate = SimpleTemplates.Java.JAVA_BEANS_GETTER_STATEMENT_TEMPLATE;

		String columnNameAsClassName = NativeUtils.toJavaBeansClass(columnName);

		String classObject = NativeUtils.toJavaBeansVariable(tableName) + TemplateTags.Java.INFO;

		String tableModelPropertyGetter = javaBeansGetterTemplate
				.replace(TemplateTags.Java.PROPERTY, columnNameAsClassName)
				.replace(TemplateTags.Java.OBJECT, classObject);

		// Wrap the property getter in the default string parser method, this
		// helps in supporting non string data type objects
		return genericStringParser.replace(TemplateTags.Java.OBJECT, tableModelPropertyGetter);
	}

	/**
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	private String parseUpdateTableValues(String version, String tableName, String columnName) {
		String genericStringParser = SimpleTemplates.Java.STRING_GENERIC_PARSER;

		String javaBeansGetterTemplate = SimpleTemplates.Java.JAVA_BEANS_GETTER_STATEMENT_TEMPLATE;

		String columnNameAsClassName = NativeUtils.toJavaBeansClass(columnName);

		String classObject = version + NativeUtils.toJavaBeansClass(tableName) + TemplateTags.Java.INFO;

		String tableModelPropertyGetter = javaBeansGetterTemplate
				.replace(TemplateTags.Java.PROPERTY, columnNameAsClassName)
				.replace(TemplateTags.Java.OBJECT, classObject);

		// Wrap the property getter in the default string parser method, this
		// helps in supporting non string data type objects
		return genericStringParser.replace(TemplateTags.Java.OBJECT, tableModelPropertyGetter);
	}

	/**
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	private String parseTableColumns(String columnNameDeclarationTemplate, String tableName, String columnName) {

		return columnNameDeclarationTemplate
				.replace(TemplateTags.Java.TABLES_SCHEMAS_CLASS, JavaProjectFileNames.TABLE_SCHEMAS_CLASS_NAME)
				.replace(TemplateTags.Java.TABLE_NAME, NativeUtils.toJavaBeansClass(tableName))
				.replace(TemplateTags.Java.COLUMN_NAME, columnName.toUpperCase());
	}

	/**
	 * @param template
	 * @param tableName
	 * @return
	 */
	private String addTableNameAndClass(String template, String tableName) {

		// Add Class Name;
		template = template.replace(TemplateTags.Java.TABLE_CLASS, NativeUtils.toJavaBeansClass(tableName));

		// Add table Name
		template = template.replace(TemplateTags.Java.TABLE_NAME, tableName);

		return template;
	}

	/**
	 * @param table
	 * @param template
	 * @param projectPackageName
	 * @param j2seProjectMYSQLDatabaseConfiguration
	 * @param thisTableCrudClassPackageName
	 * @return
	 */
	private String addImports(Table table, String template, String projectPackageName,
			J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration,
			String thisTableCrudClassPackageName) {
		// Add package name to template
		template = addPackageName(template, thisTableCrudClassPackageName);

		// Add project package name
		template = addProjectPackageName(template, projectPackageName);

		// Add table models package name
		String tableModelsSrcDir = j2seProjectMYSQLDatabaseConfiguration.getTableModelsSrcDir();
		template = addTableModelsPackageName(template, NativeUtils.parseJavaPackage(tableModelsSrcDir));

		// Add table models package name
		String tableModelSrcDir = table.getTableName();
		template = addTableModelPackageName(template, NativeUtils.parseJavaPackage(tableModelSrcDir));

		// Add table models package name
		String tableModelClass = NativeUtils.toJavaBeansClass(table.getTableName()) + TemplateTags.Java.INFO;
		template = addTableModelClass(template, tableModelClass);
		template = addTableModelObject(template, NativeUtils.toJavaBeansVariable(tableModelClass));

		// Add table schemas package
		String tableSchemasSrcDir = j2seProjectMYSQLDatabaseConfiguration.getTableSchemasSrcDir();
		String tableSchemasPackage = NativeUtils.parseJavaPackage(tableSchemasSrcDir);
		template = addTableSchemasPackageName(template, tableSchemasPackage);
		return template;
	}

	/**
	 * @param template
	 * @param tableSchemasPackage
	 * @return template
	 */
	private String addTableSchemasPackageName(String template, String tableSchemasPackage) {
		return template.replace(TemplateTags.Java.TABLES_SCHEMAS_PACKAGE, tableSchemasPackage);
	}

	/**
	 * @param template
	 * @param tableModelObject
	 * @return template
	 */
	private String addTableModelObject(String template, String tableModelObject) {
		return template.replace(TemplateTags.Java.TABLE_MODEL_OBJECT, tableModelObject);
	}

	/**
	 * @param template
	 * @param tableModelClass
	 * @return template
	 */
	private String addTableModelClass(String template, String tableModelClass) {
		return template.replace(TemplateTags.Java.TABLE_MODEL_CLASS, tableModelClass);
	}

	/**
	 * 
	 * Adds the table model package name
	 * 
	 * @param template
	 * @param tableModelPackage
	 * @return template
	 */
	private String addTableModelPackageName(String template, String tableModelPackage) {
		return template.replace(TemplateTags.Java.TABLE_MODEL_PACKAGE, tableModelPackage);
	}

	/**
	 * 
	 * Adds the table models package name
	 * 
	 * @param template
	 * @param tablesModelsPackage
	 * @return template
	 */
	private String addTableModelsPackageName(String template, String tablesModelsPackage) {
		return template.replace(TemplateTags.Java.TABLES_MODELS_PACKAGE, tablesModelsPackage);
	}

	/**
	 * Adds the project package name
	 * 
	 * @param template
	 * @param projectPackageName
	 * @return template
	 */
	private String addProjectPackageName(String template, String projectPackageName) {

		return template.replace(TemplateTags.Java.PROJECT_PACKAGE_NAME, projectPackageName);
	}

	/**
	 * Add this table crud class package name to the template
	 * 
	 * @param template
	 * @param thisTableCrudClassPackageName
	 * @return template with valid package name
	 */
	private String addPackageName(String template, String thisTableCrudClassPackageName) {
		return template.replace(TemplateTags.Java.PACKAGE_NAME, thisTableCrudClassPackageName);
	}

}
