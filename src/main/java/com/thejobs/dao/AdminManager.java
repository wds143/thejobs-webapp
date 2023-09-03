package com.thejobs.dao;

import java.sql.SQLException;
import java.util.List;

import com.thejobs.model.Admin;

public interface AdminManager {
	
	boolean authenticateAdmin(String username, String password) throws SQLException, ClassNotFoundException;
}
