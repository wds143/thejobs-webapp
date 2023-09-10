package com.thejobs.service;

import java.sql.SQLException;

import com.thejobs.dao.JobseekerAuthManagerImpl;
import com.thejobs.dao.JobseekerAuthManager;

public class JobseekerAuthService {
    private static JobseekerAuthService jobseekerAuthServiceObj;

    private JobseekerAuthService() {
    }

    public synchronized static JobseekerAuthService getJobseekerAuthService() {
        if (jobseekerAuthServiceObj == null) {
            jobseekerAuthServiceObj = new JobseekerAuthService();
        }
        return jobseekerAuthServiceObj;
    }

    private JobseekerAuthManager getJobseekerAuthManager() {
        return new JobseekerAuthManagerImpl();
    }

    public boolean authenticateJobseeker(String username, String password) throws SQLException, ClassNotFoundException {
        JobseekerAuthManager jobseekerAuthManager = getJobseekerAuthManager();

        boolean authenticatedJobseeker = jobseekerAuthManager.authenticateJobseeker(username, password);

        return authenticatedJobseeker;
    }
}
