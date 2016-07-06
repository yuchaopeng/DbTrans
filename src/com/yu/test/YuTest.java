package com.yu.test;

import java.sql.SQLException;
import java.util.List;

import com.yu.model.ColumnModel;
import com.yu.model.ColumnValue;
import com.yu.service.YuGenSql;
import com.yu.service.YuService;

public class YuTest {

	public static void main(String[] args) throws SQLException {
		YuService.getTables();
		
		String tableName = "testtest";
		
		List<ColumnModel> columnModels = YuService.getColumns(tableName);
		List<List<ColumnValue>> columnValues  = YuService.getDataSql(tableName,columnModels);
		
		String createSql = YuGenSql.genCreateTableSql(tableName, columnModels);
		
		String dataSql = YuGenSql.genDataSql(tableName, columnValues);
		
		System.out.println(createSql);
		System.out.println(dataSql);
	}
}
