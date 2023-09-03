package com.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.thejobs.dao.dbutils.DbDriverManager;
import com.thejobs.dao.dbutils.DbDriverManagerFactory;

public class ConsultantAuthManagerImpl implements ConsultantAuthManager {

	  private Connection getConnection() throws ClassNotFoundException, SQLException {
	    DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
	    DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");

	    return driverManager.getConnection();
	  }

	  @Override
	  public boolean authenticateConsultant(String username, String password) throws SQLException, ClassNotFoundException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	      connection = getConnection();

	      String query = "SELECT * FROM consultant WHERE con_username = ? AND con_password = ?";
	      preparedStatement = connection.prepareStatement(query);
	      preparedStatement.setString(1, username);
	      preparedStatement.setString(2, password);
	      System.out.println("ConsultantDatabeReturn");

	      resultSet = preparedStatement.executeQuery();

	      return resultSet.next();
	    } finally {
	      if (resultSet != null) {
	        resultSet.close();
	      }
	      if (preparedStatement != null) {
	        preparedStatement.close();
	      }
	      if (connection != null) {
	        connection.close();
	      }
	    }
	  }
}