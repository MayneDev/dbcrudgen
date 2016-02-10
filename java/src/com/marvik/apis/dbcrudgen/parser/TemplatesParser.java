package com.marvik.apis.dbcrudgen.parser;

import com.marvik.apis.dbcrudgen.io.FilesHandler;

/**
 * Templates Parser - Used for parsing templates into meaningful source files
 * 
 * @author victor
 *
 */
public class TemplatesParser {

	private FilesHandler filesHandler;

	public TemplatesParser() {
		filesHandler = new FilesHandler();
	}

	/**
	 * @return the filesHandler
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
}
