package com.thejobs.dao;

import java.sql.SQLException;

public interface ConsultantAuthManager {

	boolean authenticateConsultant(String username, String password) throws SQLException, ClassNotFoundException;

}
