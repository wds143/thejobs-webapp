package com.thejobs.dao;

import java.sql.SQLException;
import java.util.List;

import com.thejobs.model.Availability;

public interface AvailabilityManager {

    boolean addAvailability(Availability availability) throws ClassNotFoundException, SQLException;

	boolean editAvailability(Availability availability);

	Availability fetchSingleAvailability();

	boolean deleteAvailability(int avbId);

	List<Availability> fetchAllAvailability();
}
