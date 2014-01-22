package com.github.stp008.html.pages;

import com.github.stp008.utils.ReadFile;


public class HTMLPageImplJava8Features extends HTMLPage {
	
	public HTMLPageImplJava8Features() {
		this.setBody(ReadFile.getContent("src/com/github/stp008/html/pages/HTMLBodies/java8features.body"));
	}
	
}
