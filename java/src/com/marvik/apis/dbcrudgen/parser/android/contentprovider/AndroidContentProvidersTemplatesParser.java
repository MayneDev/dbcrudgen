package com.marvik.apis.dbcrudgen.parser.android.contentprovider;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates.FileNameTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidContentProvidersTemplatesParser extends AndroidTemplatesParser {

	/**
	 * {@link AndroidContentProvidersTemplatesParser#AndroidContentProvidersTemplatesParser()}
	 * Creates source code for android content providers
	 */
	public AndroidContentProvidersTemplatesParser() {

	}

	public String createContentProviderSourceFile(AndroidProjectConfiguration androidProjectConfiguration,
			Table[] tables) {
		
		String packageName = androidProjectConfiguration.getPackageName();
		
		String contentProvidersTemplate = getAndroidClassContentProviderTemplate().toString();

		AndroidContentProviderConfiguration androidContentProviderConfiguration = androidProjectConfiguration
				.getAndroidContentProviderConfiguration();

		// add content provider package name
		String projectPackageName = androidProjectConfiguration.getPackageName();
		String contentProviderStorageDirs = androidContentProviderConfiguration.getContentProviderPackage();
		String contentProviderPackage = projectPackageName + TemplateTags.TAG_PRINTING_CHAR_DOT + parseJavaPackage(contentProviderStorageDirs);
		contentProvidersTemplate = parseContentProviderPackageName(contentProvidersTemplate,
				contentProviderPackage);

		// add content provider class name
		contentProvidersTemplate = parseContentProviderClassName(contentProvidersTemplate,
				androidContentProviderConfiguration.getContentProviderClass());

		// add SQLite open helper class import
		contentProvidersTemplate = parseSQLiteOpenHelperClassImport(contentProvidersTemplate,packageName,
				androidContentProviderConfiguration.getAndroidDatabaseConfiguration());

		// add tables schemas import
		contentProvidersTemplate = parseTablesSchemasClassImport(contentProvidersTemplate,packageName,
				androidContentProviderConfiguration.getAndroidDatabaseConfiguration());

		// add tables URI's
		contentProvidersTemplate = addTablesContentUriMatchersAndCrudMethods(contentProvidersTemplate,
				androidContentProviderConfiguration.getAndroidDatabaseConfiguration(), tables);

		// add SQLite open helper class
		contentProvidersTemplate = parseSQLiteOpenHelperClass(contentProvidersTemplate,
				androidContentProviderConfiguration.getAndroidDatabaseConfiguration());

		// Add database name and database version
		contentProvidersTemplate = parseDatabaseNameAndVersion(contentProvidersTemplate,
				androidContentProviderConfiguration.getAndroidDatabaseConfiguration());

		return contentProvidersTemplate;
	}

	// Add database name and database version
	private String parseDatabaseNameAndVersion(String contentProvidersTemplate,
			AndroidDatabaseConfiguration androidDatabaseConfiguration) {

		return contentProvidersTemplate
				.replace(TemplateTags.Android.DATABASE_NAME, androidDatabaseConfiguration.getDatabaseName())
				.replace(TemplateTags.Android.DATABASE_VERSION,
						NativeUtils.getString(androidDatabaseConfiguration.getDatabaseVersion()));
	}

	// add SQLite open helper class
	private String parseSQLiteOpenHelperClass(String contentProvidersTemplate,
			AndroidDatabaseConfiguration androidDatabaseConfiguration) {
		String sqliteOpenHelperClass = androidDatabaseConfiguration.getSqliteOpenHelperClass();
		String sqliteOpenHelperClassVariable = NativeUtils.toJavaBeansVariable(sqliteOpenHelperClass);

		return contentProvidersTemplate
				.replace(TemplateTags.Android.SQLITE_OPEN_HELPER_SUBCLASS_DATA_TYPE_VARIABLE,
						sqliteOpenHelperClassVariable)
				.replace(TemplateTags.Android.SQLITE_OPEN_HELPER_SUBCLASS, sqliteOpenHelperClass);
	}

	// Add the content uri matchers variable declaration and initialization
	private String addTablesContentUriMatchersAndCrudMethods(String contentProvidersTemplate,
			AndroidDatabaseConfiguration androidDatabaseConfiguration, Table[] tables) {

		String tableUriMatcherTemplate = getAndroidVariableUriMatcherCodeTemplate().getTemplate();
		String tableUriMatchersVariables = "";

		String tableUriMatcherInitTemplate = getAndroidStatementAddUriMatcherTemplate().getTemplate();

		String tableUriMatchersInitStatements = "";

		String tableDeleteStatement = "";
		String tableInsertStatement = "";
		String tableQueryStatement = "";
		String tableUpdateStatement = "";

		for (int i = 0; i < tables.length; i++) {
			
			int matchCode = i;
			
			String tableName = tables[i].getTableName();
			
			String tableUriMatchersVariable = parseTableUriMatcherVariables(tableUriMatcherTemplate, tableName.toUpperCase(),
					matchCode);
			
			tableUriMatchersVariables += tableUriMatchersVariable;
			
			tableUriMatchersInitStatements += parseTableUriMatcherInitStatements(tableUriMatcherInitTemplate, tableName,
					matchCode);

			// Create CRUD Bindings for inserting,querying,updating and deleting
			// table rows
			String tableUriMatcherVariable = parseTableUriMatcherVariable(tableUriMatchersVariable);
			tableDeleteStatement += createTableDeleteStatements(tableName, tableUriMatchersVariable);
			tableInsertStatement += createTableInsertStatements(tableName, tableUriMatchersVariable);
			tableQueryStatement += createTableQueryStatements(tableName, tableUriMatchersVariable);
			tableUpdateStatement += createTableUpdateStatements(tableName, tableUriMatchersVariable);
		}

		// add uri matcher codes
		contentProvidersTemplate = contentProvidersTemplate.replace(TemplateTags.Android.URI_MATCHER_CODES,
				tableUriMatchersVariables);

		// Add uri matcher initializers
		contentProvidersTemplate = contentProvidersTemplate.replace(TemplateTags.Android.INIT_URI_MATCHES,
				tableUriMatchersInitStatements);

		//Add Table delete CRUD Bindings
		contentProvidersTemplate = contentProvidersTemplate.replace(TemplateTags.Android.TABLES_ROWS_DELETE_STATEMENTS, tableDeleteStatement);
		
		//Add Table insert CRUD Bindings
		contentProvidersTemplate = contentProvidersTemplate.replace(TemplateTags.Android.TABLES_ROWS_INSERT_STATEMENTS, tableInsertStatement);
		
		//Add Table query CRUD Bindings
		contentProvidersTemplate = contentProvidersTemplate.replace(TemplateTags.Android.TABLES_ROWS_QUERY_STATEMENTS, tableQueryStatement);
		
		//Add Table update CRUD Bindings
		contentProvidersTemplate = contentProvidersTemplate.replace(TemplateTags.Android.TABLES_ROWS_UPDATE_STATEMENTS, tableUpdateStatement);
		
		return contentProvidersTemplate;
	}

	private String createTableDeleteStatements(String tableName, String tableUriMatcherVariable) {
		String tableDeleteStatementsTemplate = getAndroidStatementContentProviderSQLDeleteTemplate().getTemplate();
		return parseCRUDBindigsStatement(tableDeleteStatementsTemplate, tableName, tableUriMatcherVariable);
	}

	private String createTableInsertStatements(String tableName, String tableUriMatcherVariable) {
		String tableInsertStatementsTemplate = getAndroidStatementContentProviderSQLInsertTemplate().getTemplate();
		return parseCRUDBindigsStatement(tableInsertStatementsTemplate, tableName, tableUriMatcherVariable);
	}

	private String createTableQueryStatements(String tableName, String tableUriMatcherVariable) {
		String tableQueryStatementsTemplate = getAndroidStatementContentProviderSQLQueryTemplate().getTemplate();
		return parseCRUDBindigsStatement(tableQueryStatementsTemplate, tableName, tableUriMatcherVariable);
	}

	private String createTableUpdateStatements(String tableName, String tableUriMatcherVariable) {
		String tableUpdateStatementsTemplate = getAndroidStatementContentProviderSQLUpdateTemplate().getTemplate();
		return parseCRUDBindigsStatement(tableUpdateStatementsTemplate, tableName, tableUriMatcherVariable);
	}

	private String parseCRUDBindigsStatement(String crudBindingTemplate, String tableName, String uriMatcherCode) {
		String tableDefinitionLink = parseTableDefinitionLink(tableName);
		crudBindingTemplate = crudBindingTemplate.replace(TemplateTags.Android.TABLE_URI_MATCHER_CODE, uriMatcherCode);
		crudBindingTemplate = crudBindingTemplate.replace(TemplateTags.Android.TABLE_DEFINITION_LINK,
				tableDefinitionLink);
		return crudBindingTemplate;
	}

	// Get table uri matcher variable from variable declaration
	private String parseTableUriMatcherVariable(String tableUriMatchersVariables) {
		return tableUriMatchersVariables.replace(SimpleTemplates.Java.PRIVATE_STATIC_FINAL_INT_MODIFIER,
				TemplateTags.TAG_EMPTY_STRING).replace(SimpleTemplates.Java.STATEMENT_DELIMETER,
						TemplateTags.TAG_EMPTY_STRING);
	}

	// Init statements for uri matcher variables
	private String parseTableUriMatcherInitStatements(String tableUriMatcherTemplate, String tableName, int matchCode) {

		// Add matcher code
		tableUriMatcherTemplate = tableUriMatcherTemplate.replace(TemplateTags.Android.MATCH_CODE,
				String.format("%d", matchCode));

		// Add table definition link
		String tableDefinitionLink = parseTableDefinitionLink(tableName);
		tableUriMatcherTemplate = tableUriMatcherTemplate.replace(TemplateTags.Android.TABLE_DEFINITION_LINK,
				tableDefinitionLink);

		// Add table uri matcher code variable
		String uriMatcherCodeSuffix = SimpleTemplates.Android.URI_MATCHER_CODE_SUFFIX;
		tableUriMatcherTemplate = tableUriMatcherTemplate.replace(TemplateTags.Android.TABLE_URI_MATCHER_CODE,
				tableName.toUpperCase() + uriMatcherCodeSuffix);

		return tableUriMatcherTemplate;
	}

	/**
	 * parseTableDefinitionLink
	 * 
	 * @param tableName
	 * @return table definition link
	 */
	private String parseTableDefinitionLink(String tableName) {
		return AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ NativeUtils.toJavaBeansClass(tableName) + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ TemplateTags.Android.TABLE_NAME_TAG;
	}

	// Creates uri matcher variables
	private String parseTableUriMatcherVariables(String tableUriMatcherTemplate, String tableName, int matchCode) {
		tableUriMatcherTemplate = tableUriMatcherTemplate.replace(TemplateTags.Android.TABLE_NAME, tableName);
		tableUriMatcherTemplate = tableUriMatcherTemplate.replace(TemplateTags.Android.MATCH_CODE,
				String.format("%d", matchCode));
		return tableUriMatcherTemplate;
	}

	// add tables schemas import
	private String parseTablesSchemasClassImport(String contentProvidersTemplate,
			String packageName, AndroidDatabaseConfiguration androidDatabaseConfiguration) {
		String tableSchemasPackage = androidDatabaseConfiguration.getTablesSchemasPackage();
		tableSchemasPackage = parseJavaPackage(tableSchemasPackage);
		String tableSchemasClass = packageName +TemplateTags.TAG_PRINTING_CHAR_DOT + tableSchemasPackage + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ AndroidProjectFileNames.TABLE_SCHEMAS_CLASS_NAME;
		return contentProvidersTemplate.replace(TemplateTags.Android.DATABASE_TABLES_CLASS, tableSchemasClass);
	}

	// add SQLite open helper class
	private String parseSQLiteOpenHelperClassImport(String contentProvidersTemplate,String packageName,
			AndroidDatabaseConfiguration androidDatabaseConfiguration) {
		String sqliteOpenHelperPackage = androidDatabaseConfiguration.getSqliteOpenHelperClassPackage();
		sqliteOpenHelperPackage = parseJavaPackage(sqliteOpenHelperPackage);

		String sqliteOpenHelperClass = androidDatabaseConfiguration.getSqliteOpenHelperClass();

		String sqliteOpenHelperClassImport = packageName +TemplateTags.TAG_PRINTING_CHAR_DOT +sqliteOpenHelperPackage + TemplateTags.TAG_PRINTING_CHAR_DOT
				+ sqliteOpenHelperClass;

		return contentProvidersTemplate.replace(TemplateTags.Android.SQLITE_OPENHELPER_CLASS,
				sqliteOpenHelperClassImport);
	}

	// add content provider class name
	private String parseContentProviderClassName(String contentProvidersTemplate, String contentProviderClassName) {
		return contentProvidersTemplate.replace(TemplateTags.Android.CONTENT_PROVIDER_CLASS, contentProviderClassName);
	}

	// add content provider package name
	private String parseContentProviderPackageName(String contentProvidersTemplate, String contentProviderPackage) {
		contentProviderPackage = parseJavaPackage(contentProviderPackage);
		return contentProvidersTemplate = contentProvidersTemplate.replace(TemplateTags.Android.PACKAGE_NAME,
				contentProviderPackage);

	}
}
