package com.marvik.apis.dbcrudgen.creator.android;

import java.io.File;
import java.util.Properties;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
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

public class AndroidCRUDCreator extends CrudCreator {

	/**
	 * AndroidProjectConfiguration
	 */
	private AndroidProjectConfiguration androidProjectConfiguration;

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

	public AndroidCRUDCreator() {
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

	@Deprecated
	@Override
	public CrudTemplates getCrudTemplate() {
		return getAndroidClassTableCrudTemplate();
	}

	/**
	 * Returns the android project configuration
	 * {@link AndroidCRUDCreator#getAndroidProjectConfiguration()}
	 * 
	 * @return androidProjectConfiguration
	 */
	public AndroidProjectConfiguration getAndroidProjectConfiguration() {
		return androidProjectConfiguration;
	}

	/**
	 * Sets the android project configuration
	 * {@link AndroidCRUDCreator#setAndroidProjectConfiguration(AndroidProjectConfiguration)}
	 * 
	 * @param androidProjectConfiguration
	 */
	public void setAndroidProjectConfiguration(AndroidProjectConfiguration androidProjectConfiguration) {
		this.androidProjectConfiguration = androidProjectConfiguration;
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
	 * Creates the android database crud source code
	 * {@link AndroidCRUDCreator#createProject(Database)}
	 * 
	 * @param database
	 */
	public void createProject(Database database) {

		if (androidProjectConfiguration == null) {
			throw new NullPointerException("Android Project Configuration cannot be null");
		}

		// Project storage directory
		String projectStorageDir = getAndroidProjectConfiguration().getProjectStorageDir();
		createDirectory(projectStorageDir);

		// Content provider class package
		String contentProviderPackage = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getContentProviderPackage();
		createDirectory(contentProviderPackage);

		// Content provide class name
		String contentProviderClass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getContentProviderClass();

		// SQLite open helper class package
		String sqliteOpenHelperSubclassPackage = getAndroidProjectConfiguration()
				.getAndroidContentProviderConfiguration().getAndroidDatabaseConfiguration()
				.getSqliteOpenHelperClassPackage();
		createDirectory(sqliteOpenHelperSubclassPackage);

		// SQLite open helper class package
		String sqliteOpenHelperSubclass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getSqliteOpenHelperClass();
		createDirectory(sqliteOpenHelperSubclass);

		// Database table package
		String databaseTablesPackage = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getTablesSchemasPackage();
		createDirectory(databaseTablesPackage);

		// Database name
		String databaseName = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getDatabaseName();

		// Database version
		int databaseVersion = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration()
				.getAndroidDatabaseConfiguration().getDatabaseVersion();

		// Create table schemas
		createTablesSchemas(database.getTables());

	}

	/**
	 * Creates the schemas of all the database tables
	 */
	private void createTablesSchemas(Table[] tables) {

		String tablesSchemas = "";

		for (Table table : tables) {
			tablesSchemas += createTableSchemas(table);
		}

		System.out.println(tablesSchemas);
	}

	/**
	 * Creates the schemas of a database tables
	 */
	private String createTableSchemas(Table table) {

		// Set the original schema for the table to be the template, the replace
		// with actual data
		String tableSchema = getAndroidClassSQLTableTemplate().getTemplate();

		String tableColumnsVariableTemplate = getAndroidVariableSQLTableColumnTemplate().getTemplate();

		String tableColumnsVariables = createTableColumnsVariables(tableColumnsVariableTemplate, table);

		// Add Table name
		tableSchema = parseTableName(tableSchema, table.getTableName());

		// Add Table Columns
		tableSchema = parseTableColumns(tableSchema, tableColumnsVariables);

		//Add Content Uri
		tableSchema = parseTableContentUri(tableSchema);
		
		return tableSchema;

	}

	
	//Add Content Uri
	private String parseTableContentUri(String tableSchema) {
		
		String contentProviderPackage = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration().getContentProviderPackage();
		String contentProviderClass = getAndroidProjectConfiguration().getAndroidContentProviderConfiguration().getContentProviderClass();
		
		//Remove back slashes to make a valid package name
		contentProviderPackage = contentProviderPackage.replace(NativeUtils.getSystemProperty("file.separator"), ".");
		
		return tableSchema.replace(TemplateTags.Android.CONTENT_PROVIDER_CLASS, contentProviderClass)
				.replace(TemplateTags.Android.CONTENT_PROVIDER_PACKAGE, contentProviderPackage);
	}

	private String parseTableColumns(String tableSchema, String tableColumnsVariables) {
		return tableSchema.replace(TemplateTags.Android.TABLE_COLUMNS, tableColumnsVariables);

	}
	
	private String parseTableName(String tableSchema, String tableName) {
		return tableSchema.replace(TemplateTags.Android.TABLE_NAME, tableName);
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

	/**
	 * Creates a directory
	 * 
	 * @param directory
	 */
	private void createDirectory(String directory) {
		getFilesHandler().createDirectories(new File(directory));
	}

}
