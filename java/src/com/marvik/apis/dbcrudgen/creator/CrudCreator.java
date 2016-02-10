package com.marvik.apis.dbcrudgen.creator;

import java.io.File;

import com.marvik.apis.dbcrudgen.io.FilesHandler;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;

public abstract class CrudCreator {

	public CrudCreator() {
		filesHandler = new FilesHandler();
	}

	public abstract CrudTemplates getCrudTemplate();

	/**
	 * Files Handler
	 */
	private FilesHandler filesHandler;

	/**
	 * 
	 * @return File Handler
	 */
	public FilesHandler getFilesHandler() {
		return filesHandler;
	}

	/**
	 * Creates a source file
	 * 
	 * @param absoluteFileName
	 * @param sourceCode
	 */
	public boolean createSourceFile(String absoluteFileName, String sourceCode) {
		return getFilesHandler().createByteWeighedFile(absoluteFileName, sourceCode);
	}
	/**
	 * Creates a directory
	 * 
	 * @param directory
	 */
	public boolean createDirectory(String directory) {
		return getFilesHandler().createDirectories(new File(directory));
	}

}
