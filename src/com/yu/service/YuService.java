package com.yu.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yu.model.ColumnModel;
import com.yu.model.ColumnValue;
import com.yu.util.JdbcUtils;

public class YuService {

	public static List<String> getTables() {
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			
			DatabaseMetaData metaData = conn.getMetaData();
			
			rs = metaData.getTables(conn.getCatalog(), "CASHIER", null, new String[]{"TABLE"});
			list = new ArrayList<String>();
			while(rs.next()) {
			   String tableName = rs.getString("TABLE_NAME");
			   tableName = tableName.toUpperCase();
			   list.add(tableName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtils.close(rs, null, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static List<ColumnModel> getColumns(String tableName){
		Connection conn = null;
		ResultSet rs = null ;
		ResultSet rsPK = null;
		List<ColumnModel> list = new ArrayList<ColumnModel>();
		try {
			conn = JdbcUtils.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();

			rs = metaData.getColumns(conn.getCatalog(), "CASHIER", tableName, null);
			
			rsPK = conn.getMetaData().getPrimaryKeys(null, null, tableName);
			list = new ArrayList<ColumnModel>();
			String pkfield = "";
			if(rsPK.next()){
				pkfield  = rsPK.getString(4);
			}
			while(rs.next()) {
				String isPrimaryKey = "0";
				if(pkfield.equals(rs.getString("COLUMN_NAME"))){
					isPrimaryKey = "1";
				}
				ColumnModel column = new ColumnModel(rs.getString("COLUMN_NAME"), rs.getString("TYPE_NAME"),
						rs.getString("COLUMN_SIZE"),rs.getString("DECIMAL_DIGITS"),isPrimaryKey , rs.getString("NULLABLE"),
						"");
				list.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtils.close(rs, null, null);
				JdbcUtils.close(rs, null, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static List<List<ColumnValue>> getDataSql(String tableName,List<ColumnModel> columnModels){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null ;
		List<List<ColumnValue>> result = new ArrayList<List<ColumnValue>>();
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement("SELECT * FROM "+tableName);
			rs = ps.executeQuery();
			
			while(rs.next()){
				List<ColumnValue> list = new ArrayList<ColumnValue>();
				for(ColumnModel c : columnModels){
					String field = c.getField();
					Object value = rs.getObject(field);
					String dataType = c.getDataType();
					
					ColumnValue cv = new ColumnValue(field, value, dataType);
					list.add(cv);
				}
				result.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


}
