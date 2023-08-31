package com.thejobs.dao;

import java.util.List;

import com.thejobs.model.Jobseeker;

public interface JobseekerManager {
	public boolean addJobseeker(Jobseeker jobseeker);
	public boolean editJobseeker(Jobseeker jobseeker);
	public boolean deleteJobseeker(int jbsId);
	public Jobseeker getJobseeker(int jbsId);
	public List<Jobseeker> getAllJobseeker();
}
