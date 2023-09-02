package com.thejobs.service;

import java.sql.SQLException;
import java.util.List;

import com.thejobs.dao.AppointmentManager;
import com.thejobs.dao.AppointmentManagerImpl;
import com.thejobs.model.Appointment;

public class AppointmentService {

  private static AppointmentService AppointmentServiceObj;

  private AppointmentService() {

  }

  public synchronized static AppointmentService getAppointmentService() {
    if (AppointmentServiceObj == null) {
    	AppointmentServiceObj = new AppointmentService();
    }
    return AppointmentServiceObj;
  }

  private AppointmentManager getAppoinmentManager() {
    return new AppointmentManagerImpl();
  }

  public boolean addAppointment(Appointment appoinment) throws SQLException, ClassNotFoundException {
    return getAppoinmentManager().addAppointment(appoinment);
  }

  public boolean editAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
    return getAppoinmentManager().editAppointment(appointment);
  }

  public boolean deleteAppointment(int apnmId) throws SQLException, ClassNotFoundException {
    return getAppoinmentManager().deleteAppointment(apnmId);
  }

  public Appointment fetchSingleAppointment(int apnmId) throws SQLException, ClassNotFoundException {
	    return getAppoinmentManager().fetchSingleAppointment(apnmId);
  }

  public List<Appointment> fetchAllAppointment() throws SQLException, ClassNotFoundException {
	    return getAppoinmentManager().fetchAllAppoinment();
  }
}