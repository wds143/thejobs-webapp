package com.thejobs.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.thejobs.model.Availability;

public interface AvailabilityManager {

    boolean addAvailability(Availability availability) throws ClassNotFoundException, SQLException, ParseException;
	boolean editAvailability(Availability availability);
	Availability fetchSingleAvailability();
	boolean deleteAvailability(int avbId);
	List<Availability> fetchAllAvailability();
}
