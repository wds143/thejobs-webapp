package com.thejobs.service;

import java.sql.SQLException;

import com.thejobs.dao.ConsultantManagerImpl;
import com.thejobs.dao.ConsultantManager;
import com.thejobs.model.Consultant;

public class ConsultantService {
    private static ConsultantService consultantServiceObj;

    private ConsultantService() {
    }

    public synchronized static ConsultantService getConsultantService() {
        if (consultantServiceObj == null) {
            consultantServiceObj = new ConsultantService();
        }
        return consultantServiceObj;
    }

    private ConsultantManager getConsultantManager() {
        return new ConsultantManagerImpl();
    }

    public boolean addConsultant(Consultant consultant) throws SQLException, ClassNotFoundException {
        return getConsultantManager().addConsultant(consultant);
    }

    public boolean editConsultant(Consultant consultant) throws SQLException, ClassNotFoundException {
        return getConsultantManager().editConsultant(consultant);
    }

    public boolean deleteConsultant(int conId) throws SQLException, ClassNotFoundException {
        return getConsultantManager().deleteConsultant(conId);
    }
}

