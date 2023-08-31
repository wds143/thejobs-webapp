package com.thejobs.dao;

import java.util.List;

import com.thejobs.model.Appointment;

public interface AppointmentManager {
	public boolean addAppointment(Appointment appointment);
	public boolean editAppointment(Appointment appointment);
	public boolean deleteAppointment(int apnmId);
	public Appointment getAppointment(int apnmId);
	public List<Appointment> getAllAppointment();
}
