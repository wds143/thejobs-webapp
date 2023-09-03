package com.thejobs.dao;

import java.sql.SQLException;
import java.util.List;

import com.thejobs.model.Consultant;

public interface ConsultantManager {
	public boolean addConsultant(Consultant consultant) throws ClassNotFoundException, SQLException;
	public boolean editConsultant(Consultant consultant) throws ClassNotFoundException, SQLException;
	public boolean deleteConsultant(int conId) throws ClassNotFoundException, SQLException;
	public Consultant getConsultant(int conId) throws ClassNotFoundException, SQLException;
	public Consultant fetchSingleConsultant(int conId) throws SQLException, ClassNotFoundException;
	public List<Consultant> fetchAllConsultant() throws ClassNotFoundException, SQLException;
}
