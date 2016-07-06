package com.yu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yu.model.ColumnModel;
import com.yu.model.ColumnValue;

public class YuGenSql {
	public static Map<String,String> fieldMapping = new HashMap<String,String>();
	
	public static List<String> dataTypes = new ArrayList<String>();
	
	static{
		fieldMapping.put("INT", "NUMBER");
		fieldMapping.put("", "");
		fieldMapping.put("", "");
		fieldMapping.put("", "");
		fieldMapping.put("", "");
		fieldMapping.put("", "");
		fieldMapping.put("", "");
	}
	
	private static String getTransDataType(String dataType){
		String type = fieldMapping.get(dataType);
		if(type == null){
			type = dataType;
		}
		return type;
	}
	
	
	public static String genCreateTableSql(String tableName,List<ColumnModel> columnModels){
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE ").append(tableName).append("(");
		sql.append("\r\n");
		int i=0;
		for(ColumnModel c : columnModels){
			String field = c.getField();
			String dataType = c.getDataType();
			String length = c.getLength();
			String digits = c.getDigits();
			String isPrimaryKey = c.getIsPrimaryKey();
			String isNotNull = c.getIsNotNull();
			
			String newDataTypeStr = getTransDataType(dataType);
			String lengthStr = length;
			if(digits != null && !"".equals(digits) && !"0".equals(digits)){
				lengthStr = length+","+digits;
			}
			sql.append(field).append(" ").append(newDataTypeStr).append("(").append(lengthStr).append(")");
			
			if("1".equals(isPrimaryKey)){
				sql.append(" PRIMARY KEY");
			}
			
			if("0".equals(isNotNull)){
				sql.append(" NOT NULL");
			}
			if(i < columnModels.size()-1){
				sql.append(",");
			}
			sql.append("\r\n");
			i++;
		}
		sql.append(");");
		return sql.toString();
	}
	
	public static String genDataSql(String tableName,List<List<ColumnValue>> columnValues){
		StringBuffer sql = new StringBuffer();
		
		for(List<ColumnValue> cv : columnValues){
			sql.append("INSERT INTO ").append(tableName).append(" (");
			StringBuffer fieldSql = new StringBuffer();
			StringBuffer valueSql = new StringBuffer();
			int i = 0;
			for(ColumnValue c : cv){
				fieldSql.append(c.getField());
				Object value = c.getValue();
				System.out.println("1"+c.getDataType()+"1");
				if(value != null){
					System.out.println("2"+value.getClass().getName()+"2");
				}
				if(value instanceof String){
					value = "'"+value+"'";
				}else if(value instanceof Integer){
					
				}
				
				valueSql.append(value);
				
				if(i < cv.size()-1){
					fieldSql.append(",");
					valueSql.append(",");
				}
				i++;
			}
			sql.append(fieldSql.toString()).append(")");
			sql.append(" VALUES (").append(valueSql).append(");").append("\r\n");
		}
		
		
		return sql.toString();
	}
}
