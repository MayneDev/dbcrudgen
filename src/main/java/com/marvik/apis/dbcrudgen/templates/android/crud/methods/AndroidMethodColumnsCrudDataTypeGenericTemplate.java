package com.marvik.apis.dbcrudgen.templates.android.crud.methods;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

public class AndroidMethodColumnsCrudDataTypeGenericTemplate extends AndroidCRUDTemplates {

	public AndroidMethodColumnsCrudDataTypeGenericTemplate()
	{

	}

	@Override
	public String openTemplate(String templateFilePath) throws IOException {
		
		return super.openTemplate(templateFilePath);
	}

	/*
	 * Returns the columns CRUD template
	 */
	@Override
	public String getTemplate(){return super.getTemplate();}

	/*
	 * Returns the columns CRUD template file path
	 */
	@Override
	public String getTemplateFilePath() {
		
		return TemplatesFilePath.AndroidTemplatesFilePaths.ANDROID_METHOD_COLUMNS_CRUD_DATATYPE_GENERIC_TEMPLATE_FILE_PATH;
	}

}
