package com.github.stp008.html.pages;

import com.github.stp008.utils.ReadFile;


public class HTMLPageImplId extends HTMLPage {

	private int refreshTime = 10000;

	public HTMLPageImplId() {	
		this.addJSCode("setTimeout(function () { location.reload(1); }," + refreshTime +");");	
	}
			
	public String getPage(int id) {					
				this.setBody("ID вашей сессии:" + id + 
					 ReadFile.getContent("src/com/github/stp008/html/pages/HTMLBodies/authorization.body"));
				return super.getPage();
	}
	

}


