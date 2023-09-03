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
import com.thejobs.model.Consultant;

public class ConsultantManagerImpl implements ConsultantManager {
	
	  private Connection getConnection() throws ClassNotFoundException, SQLException {

		    DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		    DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");

		    return driverManager.getConnection();
		  }

	@Override
	public boolean addConsultant(Consultant consultant) throws ClassNotFoundException, SQLException {
	    Connection connection = getConnection();

	    String query = "INSERT INTO consultant (con_id, con_first_name, con_last_name, con_username, con_email, con_password) VALUES (?,?,?,?,?,?)"; // Keep the same SQL query

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, consultant.getConId());
	    ps.setString(2, consultant.getConFirstName());
	    ps.setString(3, consultant.getConLastName());
	    ps.setString(4, consultant.getConUsername());
	    ps.setString(5, consultant.getConEmail());
	    ps.setString(6, consultant.getConPassword());

	    boolean result = false;

	    if (ps.executeUpdate() > 0) {
	        result = true;
	    }

	    ps.close();
	    connection.close();

	    return result;
	}


	@Override
	public boolean editConsultant(Consultant consultant) throws SQLException, ClassNotFoundException {
	    Connection connection = getConnection();

	    String query = "UPDATE consultant SET con_id=?, con_first_name=?, con_last_name=?, con_username=?, con_email=?, con_password=?"; // Corrected the SQL query and parameter names

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, consultant.getConId());
	    ps.setString(2, consultant.getConFirstName());
	    ps.setString(3, consultant.getConLastName());
	    ps.setString(4, consultant.getConUsername());
	    ps.setString(5, consultant.getConEmail());
	    ps.setString(6, consultant.getConPassword());

	    boolean result = false;

	    if (ps.executeUpdate() > 0) {
	        result = true;
	    }

	    ps.close();
	    connection.close();

	    return result;
	}


	@Override
	public boolean deleteConsultant(int conId) throws SQLException, ClassNotFoundException {
	    Connection connection = getConnection();

	    String query = "DELETE FROM consultant WHERE con_id=?";

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, conId);

	    boolean result = false;

	    if (ps.executeUpdate() > 0) {
	        result = true;
	    }

	    ps.close();
	    connection.close();

	    return result;
	}


	@Override
	public Consultant getConsultant(int conId) throws SQLException, ClassNotFoundException {
	    Connection connection = getConnection();

	    String query = "SELECT * FROM consultant WHERE con_id=?";

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, conId);

	    ResultSet rs = ps.executeQuery();

	    Consultant consultant = null;
	    while (rs.next()) {
	        String conFirstName = rs.getString("con_first_name");
	        String conLastName = rs.getString("con_last_name");
	        String conUsername = rs.getString("con_username");
	        String conEmail = rs.getString("con_email");
	        String conPassword = rs.getString("con_password");

	        consultant = new Consultant(conId, conFirstName, conLastName, conUsername, conEmail, conPassword);
	    }

	    rs.close();
	    ps.close();
	    connection.close();

	    return consultant;
	}



	@Override
	public List<Consultant> fetchAllConsultant() throws ClassNotFoundException, SQLException {
	    Connection connection = getConnection();

	    String query = "SELECT * FROM consulant";

	    Statement st = connection.createStatement();

	    List<Consultant> consultantList = new ArrayList<Consultant>();

	    ResultSet rs = st.executeQuery(query);

	    Consultant consultant = null;

	    while (rs.next()) {
	        int conId = rs.getInt("con_id");
	        String conFirstName = rs.getString("con_first_name");
	        String conLastName = rs.getString("con_last_name");
	        String conUsername = rs.getString("con_username");
	        String conEmail = rs.getString("con_email");
	        String conPassword = rs.getString("con_password");

	        consultant = new Consultant(conId, conFirstName, conLastName, conUsername, conEmail, conPassword);
	        consultantList.add(consultant);
	    }

	    st.close();
	    connection.close();

	    return consultantList;
	}

	@Override
	public Consultant fetchSingleConsultant(int consultantId) throws SQLException, ClassNotFoundException {
	    Connection connection = getConnection(); // Assuming you have a method to get a database connection.

	    String query = "SELECT * FROM consultant WHERE consultant_id=?";

	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, consultantId);

	    ResultSet rs = ps.executeQuery();

	    Consultant consultant = null;
	    while (rs.next()) {
	        int consultant_id = rs.getInt("consultant_id");
	        String consultant_FirstName = rs.getString("consultant_firstname");
	        String consultant_LastName = rs.getString("consultant_lastname");
	        String consultant_Username = rs.getString("consultant_username");
	        String consultant_Email = rs.getString("consultant_email");
	        String consultant_Password = rs.getString("consultant_password");

	        consultant = new Consultant(consultant_id, consultant_FirstName, consultant_LastName, consultant_Username, consultant_Email, consultant_Password);
	    }

	    rs.close();
	    ps.close();
	    connection.close();

	    return consultant;
	}
	
}