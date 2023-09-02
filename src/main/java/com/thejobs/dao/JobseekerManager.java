package com.thejobs.dao;

import java.sql.SQLException;
import java.util.List;

import com.thejobs.model.Jobseeker;

public interface JobseekerManager {
	public boolean addJobseeker(Jobseeker jobseeker) throws ClassNotFoundException, SQLException;
	public boolean editJobseeker(Jobseeker jobseeker) throws ClassNotFoundException, SQLException;
	public boolean deleteJobseeker(int jbsId) throws ClassNotFoundException, SQLException;
	public Jobseeker getJobseeker(int jbsId) throws ClassNotFoundException, SQLException;
	public List<Jobseeker> getAllJobseeker() throws ClassNotFoundException, SQLException;
	public Jobseeker getSingleJobseeker();
}
