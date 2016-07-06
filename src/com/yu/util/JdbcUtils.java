package com.yu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

	// 表示定义数据库的用户名
	private final static String USERNAME = "root";
	// 定义数据库的密码
	private final static String PASSWORD = "123456";
	// 定义数据库的驱动信息
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	// 定义访问数据库的地址
	private final static String URL = "jdbc:mysql://localhost:3306/bipcfg";
	// 定义数据库的链接

	public JdbcUtils() {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 定义获得数据库的链接
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(Connection conn) throws SQLException{
		close(null,null,conn);
	}
	
	public static void close(ResultSet rs,Statement st,Connection conn) throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(st != null){
			st.close();
		}
		if(conn != null){
			conn.close();
		}
	}

}
