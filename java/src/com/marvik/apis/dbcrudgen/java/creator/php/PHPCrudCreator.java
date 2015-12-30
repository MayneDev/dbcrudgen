package com.marvik.apis.dbcrudgen.java.creator.php;

import com.marvik.apis.dbcrudgen.java.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.java.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.java.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.java.schemamodels.tables.Table;
import com.marvik.apis.dbcudgen.java.templates.CrudTemplates;
import com.marvik.apis.dbcudgen.java.templates.php.PHPColumnsCrudTemplate;
import com.marvik.apis.dbcudgen.java.templates.php.PHPTableClassCrudTemplate;

public class PHPCrudCreator extends CrudCreator {

	private PHPTableClassCrudTemplate phpTableClassCrudTemplate;
	private PHPColumnsCrudTemplate phpColumnsCrudTemplate;

	public PHPCrudCreator() {
		phpTableClassCrudTemplate = new PHPTableClassCrudTemplate();
		phpColumnsCrudTemplate = new PHPColumnsCrudTemplate();
	}

	@Override
	public CrudTemplates getCrudTemplate() {
		return phpTableClassCrudTemplate;
	}

	public String getDatabaseCrud(Database database) {

		return null;
	}

	public String getTableName(Table table) {
		return table.getTableName();
	}

	public String generateColumnAccessorMethods(Columns columns) {
		return generateColumnGetters(columns.getColumnName()) + generateColumnSetters(columns.getColumnName());
	}

	private String generateColumnSetters(String columnName) {
		return phpColumnsCrudTemplate.getTemplate().replace("COLUMN_NAME", columnName);
	}

	private String generateColumnGetters(String columnName) {
		return phpColumnsCrudTemplate.getTemplate().replace("COLUMN_NAME", columnName);
	}
}
