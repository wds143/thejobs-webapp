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
import com.thejobs.model.Jobseeker;

public class JobseekerManagerImpl implements JobseekerManager {

	private Connection getConnection() throws ClassNotFoundException, SQLException {

		DbDriverManagerFactory driverFactory = new DbDriverManagerFactory();
		DbDriverManager driverManager = driverFactory.getDbDriver("MySQL");

		return driverManager.getConnection();
	}

	@Override
	public boolean addJobseeker(Jobseeker jobseeker) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();

		String query = "INSERT INTO jobseeker (jbs_id, jbs_FirstName, jbs_LastName, jbs_Username, jbs_Email, jbs_Password) VALUES (?,?,?,?,?,?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(2, jobseeker.getJbsId());
		ps.setString(4, jobseeker.getJbsFirstName());
		ps.setString(5, jobseeker.getJbsLastName());
		ps.setString(6, jobseeker.getJbsUsername());
		ps.setString(7, jobseeker.getJbsEmail());
		ps.setString(7, jobseeker.getJbsPassword());

		boolean result = false;

		if (ps.executeUpdate() > 0) {
			result = true;
		}

		ps.close();
		connection.close();

		return result;
	}

	@Override
	public boolean editJobseeker(Jobseeker jobseeker) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();

		String query = "UPDATE jobseeker SET jbs_id=?, jbs_FirstName=?, jbs_LastName=?, jbs_Username=?, jbs_Email=?, jbs_Password=?";

		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(2, jobseeker.getJbsId());
		ps.setString(4, jobseeker.getJbsFirstName());
		ps.setString(5, jobseeker.getJbsLastName());
		ps.setString(6, jobseeker.getJbsUsername());
		ps.setString(7, jobseeker.getJbsEmail());
		ps.setString(7, jobseeker.getJbsPassword());

		boolean result = false;

		if (ps.executeUpdate() > 0) {
			result = true;
		}

		ps.close();
		connection.close();

		return result;
	}

	@Override
	public boolean deleteJobseeker(int jbsId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();

		String query = "DELETE FROM jobseeker WHERE jbs_id=?";

		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, jbsId);

		boolean result = false;

		if (ps.executeUpdate() > 0) {
			result = true;
		}

		ps.close();
		connection.close();

		return result;
	}

	@Override
	public Jobseeker getJobseeker(int jbsId) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();

		String query = "SELECT * FROM jobseeker WHERE jbs_id=?";

		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, jbsId);

		ResultSet rs = ps.executeQuery();

		Jobseeker jobseeker = null;
		while (rs.next()) {
			int jbs_id = rs.getInt("jbs_id");
			String jbs_FirstName = rs.getString("jbs_firstname");
			String jbs_LastName = rs.getString("jbs_lastname");
			String jbs_Username = rs.getString("jbs_username");
			String jbs_Email = rs.getString("jbs_email");
			String jbs_Password = rs.getString("jbs_password");

			jobseeker = new Jobseeker(jbs_id, jbs_FirstName, jbs_LastName, jbs_Username, jbs_Email, jbs_Password);
		}

		rs.close();
		ps.close();
		connection.close();

		return jobseeker;
	}

	@Override
	public List<Jobseeker> getAllJobseeker() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();

		String query = "SELECT * FROM jobseeker";

		Statement st = connection.createStatement();

		List<Jobseeker> jobseekerList = new ArrayList<Jobseeker>();

		ResultSet rs = st.executeQuery(query);

		Jobseeker jobseeker = null;

		while (rs.next()) {
			int jbs_id = rs.getInt("jbs_id");
			String jbs_FirstName = rs.getString("jbs_firstname");
			String jbs_LastName = rs.getString("jbs_lastname");
			String jbs_Username = rs.getString("jbs_username");
			String jbs_Email = rs.getString("jbs_email");
			String jbs_Password = rs.getString("jbs_password");

			jobseeker = new Jobseeker(jbs_id, jbs_FirstName, jbs_LastName, jbs_Username, jbs_Email, jbs_Password);
			jobseekerList.add(jobseeker);
		}

		st.close();
		connection.close();

		return jobseekerList;
	}

}