package com.qt.util;

import java.util.HashMap;

public class ExcelRow {
	
	private HashMap<String, Object> row;
	
	public ExcelRow(){
		row=new HashMap<String, Object>();
	}

	public Object getValue(String key){
		if(row.containsKey(key)){
			return row.get(key);
		}else{
			return null;
		}
	}
	
	public void addValue(String key, Object value){
		row.put(key, value);
	}

}
