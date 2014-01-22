package com.github.stp008.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
	public static String getContent(String filename){
		String content = null;
		   File file = new File(filename); 
		   try {
		       FileReader reader = new FileReader(file);
		       char[] chars = new char[(int) file.length()];
		       reader.read(chars);
		       content = new String(chars);
		       reader.close();
		   } catch (IOException e) {
		       e.printStackTrace();
		   }
		   return content;
	}

}
