package com.yu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

	// ��ʾ�������ݿ���û���
	private final static String USERNAME = "root";
	// �������ݿ������
	private final static String PASSWORD = "123456";
	// �������ݿ��������Ϣ
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	// ����������ݿ�ĵ�ַ
	private final static String URL = "jdbc:mysql://localhost:3306/bipcfg";
	// �������ݿ������

	public JdbcUtils() {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���������ݿ������
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
