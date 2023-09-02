package com.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.thejobs.dao.dbutils.DbDriverManager;
import com.thejobs.dao.dbutils.DbDriverManagerFactory;
import com.thejobs.model.Availability;

public class AvailabilityManagerImpl implements AvailabilityManager {
    private Connection getConnection() throws ClassNotFoundException, SQLException {

		    DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		    DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");

		    return driverManager.getConnection();
		  }

    public boolean addAvailability(Availability availability) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();

	    String query = "INSERT INTO availability (con_id, avb_id, avb_date, avb_time) VALUES (?,?,?,?)";

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, availability.getConId());
	    ps.setInt(2, availability.getAvbId());
	    ps.setString(3, availability.getAvbDate());
	    ps.setString(4, availability.getAvbTime());
		

	    boolean result = false;

	    if (ps.executeUpdate() > 0) {
	        result = true;
	    }

	    ps.close();
	    connection.close();

	    return result;
    };

}
