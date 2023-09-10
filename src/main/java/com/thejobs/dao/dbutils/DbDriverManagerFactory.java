package com.thejobs.dao.dbutils;

public class DbDriverManagerFactory {
	public DbDriverManager getDbDriver(String databaseType) {
	    if (databaseType.equals("MySQL")) {
	      return new DbDriverManagerMySQLImpl();
	    } else {
	      return null;
	    }
	  }
}
