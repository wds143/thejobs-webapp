package com.thejobs.dao.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbDriverManagerMySQLImpl implements DbDriverManager {

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/thejobs";
		String username = "root";
		String password = "15246789+";
		System.out.println("DBCon");
		return DriverManager.getConnection(url, username, password);
	}
	
}
