package com.thejobs.dao;

import java.sql.SQLException;

public interface JobseekerAuthManager {

	boolean authenticateJobseeker(String username, String password) throws SQLException, ClassNotFoundException;

}
