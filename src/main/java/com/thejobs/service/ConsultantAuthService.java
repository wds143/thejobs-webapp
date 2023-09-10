package com.thejobs.service;

import java.sql.SQLException;

import com.thejobs.dao.ConsultantAuthManagerImpl;
import com.thejobs.dao.ConsultantAuthManager;

public class ConsultantAuthService {
    private static ConsultantAuthService consultantAuthServiceObj;

    private ConsultantAuthService() {
    }

    public synchronized static ConsultantAuthService getConsultantAuthService() {
        if (consultantAuthServiceObj == null) {
            consultantAuthServiceObj = new ConsultantAuthService();
        }
        return consultantAuthServiceObj;
    }

    private ConsultantAuthManager getConsultantAuthManager() {
        return new ConsultantAuthManagerImpl();
    }

    public boolean authenticateConsultant(String username, String password) throws SQLException, ClassNotFoundException {
    	ConsultantAuthManager consultantAuthManager = getConsultantAuthManager();

        boolean authenticatedConsultant = consultantAuthManager.authenticateConsultant(username, password);

        return authenticatedConsultant;
    }
}