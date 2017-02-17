package com.deloitte.jdbc;

import java.util.Map;

public class Row {

	private String ResponseText;
	private String ResponseCode;
	private String LastMailServer;
	private String GoodEmail;
	private String id = "";
	
	public Row(String ResponseText, String ResponseCode, String LastMailServer, String GoodEmail){
		this.ResponseText = ResponseText;
		this.ResponseCode = ResponseCode;
		this.LastMailServer = LastMailServer;
		this.GoodEmail = GoodEmail;
	}
	
	public static Row CreateRow(Map<String, String> map){
		String rt = map.get("ResponseText");
		String rc = map.get("ResponseCode");
		String lm = map.get("LastMailServer");
		String gm = map.get("GoodEmail");
		return new Row(rt,rc,lm,gm);
	}
	
	public void setId(String id){
		this.id=id;
	}
	
	public String getId(){
		return this.id;
	}
	
	
	public String getResponseText(){
		return this.ResponseText;
	}
	
	public String getResponseCode(){
		return this.ResponseCode;
	}
	
	public String getLastMailServer(){
		return this.LastMailServer;
	}
	
	public String getGoodEmail(){
		return this.GoodEmail;
	}
}
