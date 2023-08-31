package com.thejobs.dao;

import java.util.List;

import com.thejobs.model.Consultant;

public interface ConsultantManager {
	public boolean addConsultant(Consultant consultant);
	public boolean editConsultant(Consultant consultant);
	public boolean deleteConsultant(int conId);
	public Consultant getConsultant(int conId);
	public List<Consultant> getAllConsultant();
}
