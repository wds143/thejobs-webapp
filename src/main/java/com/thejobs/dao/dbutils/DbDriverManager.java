package com.thejobs.dao.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbDriverManager {
	public Connection getConnection() throws ClassNotFoundException, SQLException;
}
