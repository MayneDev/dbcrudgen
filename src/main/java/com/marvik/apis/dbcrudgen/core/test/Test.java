package com.marvik.apis.dbcrudgen.core.test;

import java.io.File;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;

public class Test {

	public static void main(String[] args) {
		File file  = new File("G:\\MarvikApps2016\\WTND\\app\\src\\main\\assets\\json");
		for(String filename : file.list()){
			createJsonGetter(filename);
		}
	}

	private static void createJsonGetter(String filename) {
		String template = " public String getTest"+NativeUtils.toJavaBeansClass(filename).replace(".json", "")+"JSON() {return getUtilities().readAssetsStringStream(\"json/"+filename+"\");}";
		System.out.println(template);
	}

	
}
