package com.thejobs.service;

import java.sql.SQLException;
import com.thejobs.dao.AdminManager;
import com.thejobs.dao.AdminManagerImpl;

public class AdminService {

  private static AdminService adminServiceObj;

  private AdminService() {

  }

  public synchronized static AdminService getAdminService() {
    if (adminServiceObj == null) {
      adminServiceObj = new AdminService();
    }
    return adminServiceObj;
  }

  private AdminManager getAdminManager() {
    return new AdminManagerImpl();
  }

  // Add an authentication method
  public boolean authenticateAdmin(String username, String password) throws SQLException, ClassNotFoundException {
    // You can implement the authentication logic here using the AdminManager
    AdminManager adminManager = getAdminManager();
    
    // Example: Authenticate based on username and password
    boolean authenticatedAdmin = adminManager.authenticateAdmin(username, password);
    
    return authenticatedAdmin;
  }
}
