package com.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thejobs.dao.dbutils.DbDriverManager;
import com.thejobs.dao.dbutils.DbDriverManagerFactory;
import com.thejobs.model.Appointment;

public class AppointmentManagerImpl implements AppointmentManager {
	
	  private Connection getConnection() throws ClassNotFoundException, SQLException {

		    DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		    DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");

		    return driverManager.getConnection();
		  }

	@Override
	public boolean addAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
	    Connection connection = getConnection();

	    String query = "INSERT INTO appointment ( cond_id, jbs_id, avb_id, apnm_decs, apnm_country, apnm_job) VALUES (?,?,?,?,?,?)";

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, appointment.getConId());
	    ps.setInt(2, appointment.getJbsId());
	    ps.setInt(3, appointment.getAvbId());
	    ps.setString(4, appointment.getApnmDesc());
	    ps.setString(5, appointment.getApnmCountry());
	    ps.setString(6, appointment.getApnmJob());

	    boolean result = false;

	    if (ps.executeUpdate() > 0) {
	      result = true;
	    }

	    ps.close();
	    connection.close();

	    return result;
	}

	@Override
	public boolean editAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
		   Connection connection = getConnection();

		    String query = "UPDATE product SET con_id=?, jbs_id=?, avb_id=?, apnm_decs=?, apnm_country=?, apnm_job=?  WHERE apnm_id=?";

		    PreparedStatement ps = connection.prepareStatement(query);
		    ps.setInt(1, appointment.getConId());
		    ps.setInt(2, appointment.getJbsId());
		    ps.setInt(3, appointment.getAvbId());
		    ps.setString(4, appointment.getApnmDesc());
		    ps.setString(5, appointment.getApnmCountry());
		    ps.setString(6, appointment.getApnmJob());
		    ps.setInt(7, appointment.getApnmId());

		    boolean result = false;

		    if (ps.executeUpdate() > 0) {
		      result = true;
		    }

		    ps.close();
		    connection.close();

		    return result;
	}

	@Override
	public boolean deleteAppointment(int apnmId) throws SQLException, ClassNotFoundException {
		   Connection connection = getConnection();

		    String query = "DELETE FROM product WHERE apnmtId=?";

		    PreparedStatement ps = connection.prepareStatement(query);
		    ps.setInt(1, apnmId);

		    boolean result = false;

		    if (ps.executeUpdate() > 0) {
		      result = true;
		    }

		    ps.close();
		    connection.close();

		    return result;
	}

	@Override
	public Appointment getAppointment(int apnmId) throws SQLException, ClassNotFoundException {
	    Connection connection = getConnection();

	    String query = "SELECT * FROM product WHERE apnmId=?";

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, apnmId);

	    ResultSet rs = ps.executeQuery();

	    Appointment appointment = null;
	    while (rs.next()) {
	        int conId = rs.getInt("con_id");
	        int jbsId = rs.getInt("jbs_id");
	        int avbId = rs.getInt("avb_id");
	        String apnmDesc = rs.getString("apmn_desc");
	        String apnmDate = rs.getString("apnm_date");
	        String apnmTime = rs.getString("apnm_time");
	        String apnmCountry = rs.getString("apnm_country");
	        String apnmJob = rs.getString("apnm_job");

	        appointment = new Appointment(apnmId, conId, jbsId, avbId, apnmDesc, apnmDate, apnmTime, apnmCountry, apnmJob);
	    }

	    rs.close();
	    ps.close();
	    connection.close();

	    return appointment;
	}


	@Override
	public List<Appointment> getAllAppointment() throws ClassNotFoundException, SQLException {
	    Connection connection = getConnection();

	    String query = "SELECT * FROM appointment";

	    Statement st = connection.createStatement();

	    List<Appointment> appointmentList = new ArrayList<Appointment>();

	    ResultSet rs = st.executeQuery(query);

	    Appointment appointment = null;
	    while (rs.next()) {
	    	int apnmId = rs.getInt("apnm_id");
	        int conId = rs.getInt("con_id");
	        int jbsId = rs.getInt("jbs_id");
	        int avbId = rs.getInt("avb_id");
	        String apnmDesc = rs.getString("apmn_desc");
	        String apnmDate = rs.getString("apnm_date");
	        String apnmTime = rs.getString("apnm_time");
	        String apnmCountry = rs.getString("apnm_country");
	        String apnmJob = rs.getString("apnm_job");

	        appointment = new Appointment(apnmId, conId, jbsId, avbId, apnmDesc, apnmDate, apnmTime, apnmCountry, apnmJob);
	        appointmentList.add(appointment);
	    }

	    st.close();
	    connection.close();

	    return appointmentList;
	}

}
