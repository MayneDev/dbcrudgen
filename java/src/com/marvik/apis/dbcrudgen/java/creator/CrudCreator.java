package com.marvik.apis.dbcrudgen.java.creator;

import com.marvik.apis.dbcudgen.java.templates.ColumnsCrudTemplate;
import com.marvik.apis.dbcudgen.java.templates.TablesTemplate;

public class CrudCreator {

	public static void main(String[] args) {
		
		TablesTemplate tablesTemplate = new TablesTemplate();
		System.out.println(tablesTemplate.getTablesTemplate());
		
		ColumnsCrudTemplate columnsCrudTemplate = new ColumnsCrudTemplate();
		System.out.println(columnsCrudTemplate.getTablesTemplate());
	}
}
