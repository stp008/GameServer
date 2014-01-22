package com.github.stp008.html.tags;


import java.util.HashMap;
import java.util.Map;


public class TagsMainImpl implements Tags{
		
	private Map<TagNames,String> Tags = new HashMap<TagNames, String>();
	
	public TagsMainImpl() {
		String[] tagValues = {"<!DOCTYPE html>\r\n", "\r\n", "<html>\r\n", "</html>", "<head>\r\n", "</head>\r\n", "<title>\r\n", "</title>\r\n", "<body>\r\n", "</body>\r\n", "<script>\r\n", "</script>\r\n"};
		for (int i = 0; i < tagValues.length; i++) 
			this.setTagByName(TagNamesMain.values()[i], tagValues[i]);
	}
	
	@Override
	public String getTagByName(TagNames tag) {
		// TODO Auto-generated method stub
		return Tags.get(tag);
	}
	
	@Override
	public void setTagByName(TagNames tagName, String tagValue) {
		// TODO Auto-generated method stub
		Tags.put(tagName, tagValue);
	}
	
} 
