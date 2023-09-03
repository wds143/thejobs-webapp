package com.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

import com.thejobs.controller.AvailabilityController;
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
	    
	    Date avbDate = availability.getAvbDate();
	    Time avbTime = availability.getAvbTime();
	    String datetimeString = avbDate + " " + avbTime;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
	    Date parsedDate = dateFormat.parse(datetimeString);
	    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, availability.getConId());
	    ps.setDate(2, availability.getAvbDate());
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
	
    public boolean acceptApnm(int conId) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        boolean result = false;

        try {
            String query = "UPDATE con_availability SET avb_booked=? WHERE con_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBoolean(1, true);
            ps.setInt(2, conId);

            if (ps.executeUpdate() > 0) {
                result = true;
            }

            ps.close();
        } finally {
            connection.close();
        }

        return result;
    }
	
	@Override
	public List<Availability> fetchAllAvailability() throws ClassNotFoundException, SQLException {
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	    List<Availability> availabilityList = new ArrayList<>();

	    try {
	        connection = getConnection();
	        String query = "SELECT * FROM con_availability";
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(query);

	        while (resultSet.next()) {
	        	System.out.println("fetchAllAvailabilityIMPL");
                Timestamp timestamp = resultSet.getTimestamp("avb_time");

                Date date = new Date(timestamp.getTime());

                // Extract the date and time components
                java.sql.Date sqlDate = new java.sql.Date(date.getTime()); // Date
                java.sql.Time sqlTime = new java.sql.Time(date.getTime()); // Time

	        	        	
	            int conId = resultSet.getInt("con_id");
	            int avbId = resultSet.getInt("avb_id");
	            String avbCountry = resultSet.getString("avb_country");
	            String avbJob = resultSet.getString("avb_job");
	            boolean avbBooked = resultSet.getBoolean("avb_booked");

	            Availability availability = new Availability();
	            availability.setConId(conId);
	            availability.setAvbId(avbId);
	            availability.setAvbCountry(avbCountry);
	            availability.setAvbJob(avbJob);
	            availability.setAvbDate(sqlDate);
	            availability.setAvbTime(sqlTime);
	            availability.setAvbBooked(avbBooked);
	            System.out.println("availabilityList");
	            availabilityList.add(availability);
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        // Handle exceptions appropriately
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            // Handle exceptions appropriately
	            e.printStackTrace();
	        }
	    }
	    return availabilityList;
	}


}
