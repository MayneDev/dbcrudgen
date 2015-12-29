package com.marvik.apis.dbcudgen.java.templates;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.java.io.FileStreamReader;

public final class TablesTemplate {

	private FileStreamReader fileStreamReader;
	
	
	public TablesTemplate(){
		fileStreamReader = new FileStreamReader();
	}
	/*
	 * Returns the tables template
	 */
	private String openTablesTemplate(FileStreamReader fileStreamReader) throws IOException{
		return fileStreamReader.readFile("res/templates/template_tables.txt");
	}
	/*
	 * Returns the tables template
	 */
	public final String getTablesTemplate(){
		try {
			return openTablesTemplate(fileStreamReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
