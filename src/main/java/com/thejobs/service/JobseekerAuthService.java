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
        // You can implement the authentication logic here using the JobSeekerAuthManager
        JobseekerAuthManager jobseekerAuthManager = getJobseekerAuthManager(); // Assuming you have a JobSeekerAuthManager class.

        // Example: Authenticate based on username and password
        boolean authenticatedJobseeker = jobseekerAuthManager.authenticateJobseeker(username, password); // Assuming authenticateJobSeeker method exists in JobSeekerAuthManager.

        return authenticatedJobseeker;
    }
}
