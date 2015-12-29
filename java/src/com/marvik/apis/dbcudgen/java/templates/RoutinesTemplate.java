package com.marvik.apis.dbcudgen.java.templates;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.java.io.FileStreamReader;

public class RoutinesTemplate {

	public RoutinesTemplate(){
		
		
	}
	/*
	 * Returns the routines template
	 */
	private String openRoutinesTemplate(FileStreamReader fileStreamReader) throws IOException{
		return fileStreamReader.readFile("res/templates/template_routines.txt");
	}
	/*
	 * Returns the routines template
	 */
	public final String getRoutinesTemplate(){
		return null;
		
	}
}
