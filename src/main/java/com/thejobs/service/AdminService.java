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

  public boolean authenticateAdmin(String username, String password) throws SQLException, ClassNotFoundException {
    AdminManager adminManager = getAdminManager();
    
    boolean authenticatedAdmin = adminManager.authenticateAdmin(username, password);
    
    return authenticatedAdmin;
  }
}
