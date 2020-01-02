package com.marvik.apis.dbcrudgen.templates.android.crud.classes;

import com.marvik.apis.dbcrudgen.filepaths.templates.TemplatesFilePath;
import com.marvik.apis.dbcrudgen.templates.android.crud.AndroidCRUDTemplates;

import java.io.IOException;

public class AndroidClassTableHttpResponseTemplate extends AndroidCRUDTemplates {

    public AndroidClassTableHttpResponseTemplate() {

    }

    @Override
    public String openTemplate(String templateFilePath) throws IOException {

        return super.openTemplate(templateFilePath);
    }

    /*
     * Returns the columns CRUD template
     */
    @Override
    public String getTemplate() {
        return super.getTemplate();
    }

    /*
     * Returns the columns CRUD template file path
     */
    @Override
    public String getTemplateFilePath() {

        return TemplatesFilePath.AndroidTemplatesFilePaths.ANDROID_CLASS_TABLE_HTTP_RESPONSE_TEMPLATE_FILE_PATH;
    }
}
