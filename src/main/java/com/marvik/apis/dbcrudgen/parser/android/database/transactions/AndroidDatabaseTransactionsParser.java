package com.marvik.apis.dbcrudgen.parser.android.database.transactions;

import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.templates.android.crud.classes.AndroidClassTransactionsManagerTemplate;
import com.marvik.apis.dbcrudgen.templates.android.crud.methods.AndroidMethodsTransactionsTableCrudClassGetterTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

public class AndroidDatabaseTransactionsParser extends AndroidTemplatesParser {

	public AndroidDatabaseTransactionsParser() {

	}

	public String createSourceCode(String androidProjectPackageName,String transactionManagerPackage, String transactionManagerClass,
			String tablesCRUDStorageLocation, Table[] tables) {
		AndroidClassTransactionsManagerTemplate transactionsManagerTemplate = new AndroidClassTransactionsManagerTemplate();
		String template = transactionsManagerTemplate.getTemplate();

		String tableCrudClassImports = "";
		String tableCrudClassGetterMethods = "";

		for (Table table : tables) {

			tableCrudClassImports += createTableImportStatements(tablesCRUDStorageLocation,androidProjectPackageName, table.getTableName());
			tableCrudClassGetterMethods += createTableCrudClassInitMethods(table.getTableName());
		}

		// add package name
		String classPackageName = androidProjectPackageName + NativeTemplateTags.DOT +  NativeUtils.parseJavaPackage(transactionManagerPackage);
		template = parsePackageName(template,classPackageName);

		// add table class name
		template = parseClassName(template, transactionManagerClass);

		// Add import statements
		template = parseCrudTablesImportStatements(template, tableCrudClassImports);

		// add table crud init statements
		template = parseTableCrudClassInitMethods(template, tableCrudClassGetterMethods);

		return template;
	}

	// add table crud class init methods
	private String parseTableCrudClassInitMethods(String template, String tableCrudClassGetterMethods) {

		return template.replace(TemplateTags.Android.TABLES_CRUD_CLASSES_GETTERS, tableCrudClassGetterMethods);
	}

	// add crud class imports
	private String parseCrudTablesImportStatements(String template, String tableCrudClassImports) {
		return template.replace(TemplateTags.Android.TABLES_CRUD_CLASSES_IMPORT, tableCrudClassImports);
	}

	// add class name
	private String parseClassName(String template, String transactionManagerClass) {
		return template.replace(TemplateTags.Android.TRANSACTION_CLASS, transactionManagerClass);
	}

	// Add package name
	private String parsePackageName(String template, String transactionManagerPackage) {
		return template.replace(TemplateTags.Android.PACKAGE_NAME, transactionManagerPackage);
	}

	private String createTableImportStatements(String tablesCrudStorageLocation,String androidProjectPackageName, String tableName) {

		String androidClassImportTemplate = SimpleTemplates.Android.STATEMENT_CLASS_IMPORT;
		String tablePackage = NativeUtils.parseJavaPackage(tablesCrudStorageLocation);
		String tableClass = NativeUtils.toJavaBeansClass(tableName);

		tablePackage = androidProjectPackageName + NativeTemplateTags.DOT + tablePackage;
		
		return androidClassImportTemplate.replace(TemplateTags.Android.CLASS_PACKAGE, tablePackage)
				.replace(TemplateTags.Android.CLASS_NAME, tableClass);
	}

	private String createTableCrudClassInitMethods(String tableName) {
		String template = new AndroidMethodsTransactionsTableCrudClassGetterTemplate().getTemplate();
		String tableClass = NativeUtils.toJavaBeansClass(tableName);
		String tableClassObject = NativeUtils.toJavaBeansVariable(tableClass);
		return template.replace(TemplateTags.Android.TABLE_CRUD_CLASS, tableClass)
				.replace(TemplateTags.Android.CLASS_OBJECT, tableClassObject);
	}

}
