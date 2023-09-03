package com.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thejobs.dao.dbutils.DbDriverManager;
import com.thejobs.dao.dbutils.DbDriverManagerFactory;
import com.thejobs.model.Availability;

public class AvailabilityManagerImpl implements AvailabilityManager {
    private Connection getConnection() throws ClassNotFoundException, SQLException {

		    DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		    DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");

		    return driverManager.getConnection();
		  }

    public boolean addAvailability(Availability availability) throws ClassNotFoundException, SQLException, ParseException {
        Connection connection = getConnection();

	    String query = "INSERT INTO con_availability (con_id, avb_date, avb_time, avb_country, avb_job) VALUES (?,?,?,?,?)";
	    
	    String avbDate = availability.getAvbDate();
	    String avbTime = availability.getAvbTime();
	    String datetimeString = avbDate + " " + avbTime;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
	    Date parsedDate = dateFormat.parse(datetimeString);
	    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, availability.getConId());
	    ps.setString(2, availability.getAvbDate());
	    ps.setTimestamp(3, timestamp);
	    ps.setString(4, availability.getAvbCountry());
	    ps.setString(5, availability.getAvbJob());
		

	    boolean result = false;

	    if (ps.executeUpdate() > 0) {
	        result = true;
	    }

	    ps.close();
	    connection.close();

	    return result;
    }

	@Override
	public boolean editAvailability(Availability availability) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Availability fetchSingleAvailability() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAvailability(int avbId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Availability> fetchAllAvailability() {
		// TODO Auto-generated method stub
		return null;
	};

}
