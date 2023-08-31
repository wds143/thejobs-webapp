package com.thejobs.dao.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbDriverManagerMySQLImpl implements DbDriverManager {

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "root@localhost:3306/thejobs";
		String username = "root";
		String password = "15246789+";
		return DriverManager.getConnection(url, username, password);
	}
	
}
