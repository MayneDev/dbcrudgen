/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.android.crudoperations;

import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
 * AndroidCrudOperationsTemplateParser - Creates CRUD Operations Interface
 * 
 * @author victor
 * 
 */
public class AndroidCrudOperationsTemplateParser extends AndroidTemplatesParser {

	public String createSourceCode(AndroidProjectConfiguration androidProjectConfiguration) {

		String packageName = androidProjectConfiguration.getPackageName();
		String crudOperationsInterfaceStoragePackage = androidProjectConfiguration.getAndroidContentProviderConfiguration().getAndroidDatabaseConfiguration().getTablesCRUDPackage();
		
		String crudOperationsInterfaceTemplate = getAndroidInterfaceCrudOperations().getTemplate();

		crudOperationsInterfaceStoragePackage = parseJavaPackage(crudOperationsInterfaceStoragePackage);

		crudOperationsInterfaceTemplate = parseTablesCrudStoragePackage(crudOperationsInterfaceTemplate,
				packageName + NativeTemplateTags.DOT + crudOperationsInterfaceStoragePackage);

		return crudOperationsInterfaceTemplate;
	}

	private String parseTablesCrudStoragePackage(String crudOperationsInterfaceTemplate,
			String crudOperationsInterfaceStoragePackage) {

		return crudOperationsInterfaceTemplate.replace(TemplateTags.Android.CRUD_OPERATIONS_INTERFACE_PACKAGE,
				crudOperationsInterfaceStoragePackage);
	}

}
