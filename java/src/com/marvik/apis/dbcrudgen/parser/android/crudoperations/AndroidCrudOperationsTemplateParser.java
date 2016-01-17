/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.android.crudoperations;

import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
 * AndroidCrudOperationsTemplateParser - Creates CRUD Operations Interface
 * 
 * @author victor
 * 
 */
public class AndroidCrudOperationsTemplateParser extends AndroidTemplatesParser {

	public String createSourceCode(String tablesCrudPackage) {

		String crudOperationsInterfaceTemplate = getAndroidInterfaceCrudOperations().getTemplate();

		String crudOperationsInterfaceStoragePackage = parseJavaPackage(tablesCrudPackage);

		crudOperationsInterfaceTemplate = parseTablesCrudStoragePackage(crudOperationsInterfaceTemplate,
				crudOperationsInterfaceStoragePackage);

		return crudOperationsInterfaceTemplate;
	}

	private String parseTablesCrudStoragePackage(String crudOperationsInterfaceTemplate,
			String crudOperationsInterfaceStoragePackage) {

		return crudOperationsInterfaceTemplate.replace(TemplateTags.Android.CRUD_OPERATIONS_INTERFACE_PACKAGE,
				crudOperationsInterfaceStoragePackage);
	}

}
