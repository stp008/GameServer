package com.github.stp008.html.pages;

import com.github.stp008.html.tags.TagNamesMain;
import com.github.stp008.html.tags.Tags;
import com.github.stp008.html.tags.TagsMainImpl;


public abstract class HTMLPage {
	   
	private Tags tags;	
	private String body;
	private StringBuilder JS = new StringBuilder();
	
	protected HTMLPage() {
		this.setTags(new TagsMainImpl());
	}
	
	public void setBody(String body){
		this.body = body + tags.getTagByName(TagNamesMain.NEW_LINE);
	}
	
	public void addJSCode(String JSCode) {
		
		if (JSCode.toLowerCase().contains(tags.getTagByName(TagNamesMain.SCRIPT_OPEN)) && 
				JSCode.toLowerCase().contains(tags.getTagByName(TagNamesMain.SCRIPT_CLOSE))) {
				this.JS.append(JSCode + tags.getTagByName(TagNamesMain.NEW_LINE));			
		} else {
				this.JS.append(tags.getTagByName(TagNamesMain.SCRIPT_OPEN) + 
				JSCode + 
				tags.getTagByName(TagNamesMain.NEW_LINE) + 
				tags.getTagByName(TagNamesMain.SCRIPT_CLOSE));
		}
	}
	
	public String getPage() {
	    return  tags.getTagByName(TagNamesMain.DOCTYPE) +
	    		tags.getTagByName(TagNamesMain.HTML_OPEN) + 	    		 
	    		tags.getTagByName(TagNamesMain.HEAD_OPEN) +
	    		tags.getTagByName(TagNamesMain.TITLE_OPEN) +
	    		"Java8" +
	    		tags.getTagByName(TagNamesMain.TITLE_CLOSE) + 
	    		tags.getTagByName(TagNamesMain.HEAD_CLOSE) +
	    		tags.getTagByName(TagNamesMain.BODY_OPEN) +
	    		this.JS +
	    		this.body + 
	    		tags.getTagByName(TagNamesMain.BODY_CLOSE) + 
	    		tags.getTagByName(TagNamesMain.HTML_CLOSE);	
	}
		
	
	public void setTags(Tags tags) {
		this.tags = tags;
				
	}
	
	public Tags getTags() {
		return this.tags;
	}
	   
	}