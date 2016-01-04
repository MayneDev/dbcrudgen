package com.marvik.apis.dbcrudgen.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileStreamWriter {

	public void writeStream(File file, String text) throws IOException {
		
		// Check if directory exists
		if (!file.exists()) {

			// Create all missing directories
			createDirectories(file);
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		if(text == null){
			return;
		}
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		osw.write(text);
		osw.flush();
		osw.close();
		fos.close();
	}

	public boolean createDirectories(File file) {
		return file.mkdirs();
	}

	public boolean createDirectories(String directoryPath) {
		return createDirectories(new File(directoryPath));
	}
}
