package com.thejobs.dao.dbutils;

public class DbDriverManagerFactory {
	public DbDriverManager getDbDriver(String databaseType) {
	    if (databaseType.equals("MySQL")) {
	      return new DbDriverManagerMySQLImpl();
	    } else {
	      // replace with another databaseType like MS SQL or any other if exist
	      return null;
	    }
	  }
}
