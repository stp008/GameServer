package com.github.stp008.html.pages;

import com.github.stp008.utils.ReadFile;


public class HTMLPageImplJava8 extends HTMLPage{
		
	public HTMLPageImplJava8() {
		this.setBody(ReadFile.getContent("src/com/github/stp008/html/pages/HTMLBodies/java8.body"));
	}
			
}
