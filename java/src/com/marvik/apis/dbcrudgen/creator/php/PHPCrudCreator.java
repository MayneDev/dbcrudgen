package com.marvik.apis.dbcrudgen.creator.php;

import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.parser.PHPTemplatesParser;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;
import com.marvik.apis.dbcudgen.java.templates.php.PHPTableClassCrudTemplate;

public class PHPCrudCreator extends CrudCreator {

	PHPTableClassCrudTemplate phpTableClassCrudTemplate;
	PHPTemplatesParser phpTemplatesParser;

	public PHPCrudCreator() {
		phpTableClassCrudTemplate = new PHPTableClassCrudTemplate();
		phpTemplatesParser = new PHPTemplatesParser();
	}

	@Override
	public CrudTemplates getCrudTemplate() {
		return phpTableClassCrudTemplate;
	}
	
	public String getTableCrud(Table table){
		return phpTemplatesParser.getTableCrud(table);
	}
}
