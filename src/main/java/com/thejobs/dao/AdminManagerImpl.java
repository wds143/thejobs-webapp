package com.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thejobs.dao.dbutils.DbDriverManager;
import com.thejobs.dao.dbutils.DbDriverManagerFactory;
import com.thejobs.model.Admin;

public class AdminManagerImpl implements AdminManager {

  private Connection getConnection() throws ClassNotFoundException, SQLException {
    DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
    DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");

    return driverManager.getConnection();
  }

  @Override
  public boolean authenticateAdmin(String username, String password) throws SQLException, ClassNotFoundException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = getConnection();

      String query = "SELECT * FROM admin WHERE admin_username = ? AND admin_password = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, password);
      System.out.println("AdminDatabeReturn");

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
