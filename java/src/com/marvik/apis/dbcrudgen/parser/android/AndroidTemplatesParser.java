package com.marvik.apis.dbcrudgen.parser.android;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.TemplatesParser;
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
	 * AndroidTemplatesParser#parsePackageName
	 * Formats a package path to a valid package name
	 * @param packagePath
	 * @return String Package Name
	 */
	public String parsePackageName(String packagePath){
		return packagePath.replace(NativeUtils.getFileSeparator(),TemplateTags.TAG_PRINTING_CHAR_DOT);
	}

}
