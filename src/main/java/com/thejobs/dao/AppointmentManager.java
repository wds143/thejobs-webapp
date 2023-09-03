package com.thejobs.dao;

import java.sql.SQLException;
import java.util.List;

import com.thejobs.model.Appointment;

public interface AppointmentManager {
	public boolean addAppointment(Appointment appointment) throws ClassNotFoundException, SQLException;
	public boolean editAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;
	public boolean deleteAppointment(int apnmId) throws SQLException, ClassNotFoundException;
	public Appointment getAppointment(int apnmId) throws SQLException, ClassNotFoundException;
	public List<Appointment> getAllAppointment() throws ClassNotFoundException, SQLException;
	public List<Appointment> fetchAllAppointment() throws ClassNotFoundException, SQLException;
	public Appointment fetchSingleAppointment(int apnmId);
}
