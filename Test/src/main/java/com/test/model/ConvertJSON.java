package com.test.model;

import java.sql.Ref;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConvertJSON {
  
	public String CJSON_S(JSONObject objParam,String strKey) {
		String strData = objParam.get(strKey).toString();//Implement object mapper
		return strData;
	}
	
	public JSONArray CJSON_JA(JSONObject objParam,String strKey) {
		JSONArray jaData = objParam.getJSONArray(strKey);
		return jaData;
	}
}
