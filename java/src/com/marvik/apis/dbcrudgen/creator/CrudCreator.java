package com.marvik.apis.dbcrudgen.creator;

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
}
