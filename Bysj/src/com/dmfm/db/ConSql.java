package com.dmfm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConSql {
	private static String url = "jdbc:mysql://localhost:3306/bysj";
	private static String user = "root";
	private static String password = "3836575";
	private static String driver = "com.mysql.jdbc.Driver";//数据库驱动
	
	public Connection getCon() throws ClassNotFoundException, SQLException {
		Class.forName(driver);//加载驱动。
		Connection con = DriverManager.getConnection(url,user,password);
	return con;
	}
	
	public static void getClose(Connection con){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
