package com.thejobs.service;

import java.sql.SQLException;
import java.util.List;

import com.thejobs.dao.JobseekerManager;
import com.thejobs.dao.JobseekerManagerImpl;
import com.thejobs.model.Jobseeker;

public class JobseekerService {

  private static JobseekerService jobseekerServiceObj;

  private JobseekerService() {

  }

  public synchronized static JobseekerService getjobseekerService() {
    if (jobseekerServiceObj == null) {
      jobseekerServiceObj = new JobseekerService();
    }
    return jobseekerServiceObj;
  }

  private JobseekerManager getJobseekerManager() {
    return new JobseekerManagerImpl();
  }

  public boolean addJobseeker(Jobseeker jobseeker) throws SQLException, ClassNotFoundException {
	System.out.println("ServiceIN");
    return getJobseekerManager().addJobseeker(jobseeker);
  }

  public boolean editJobseeker(Jobseeker jobseeker) throws SQLException, ClassNotFoundException {
    return getJobseekerManager().editJobseeker(jobseeker);
  }

  public boolean deleteJobseeker(int jbsId) throws SQLException, ClassNotFoundException {
    return getJobseekerManager().deleteJobseeker(jbsId);
  }

  public Jobseeker fetchSingleJobseeker(int jbsId) throws ClassNotFoundException, SQLException {
		return getJobseekerManager().getJobseeker(jbsId);
  }
  
  public List<Jobseeker> fetchAllJobseeker() throws ClassNotFoundException, SQLException {
		return getJobseekerManager().getAllJobseeker();
  }


}