package com.thejobs.dao;

import java.sql.SQLException;

import com.thejobs.model.Availability;

public interface AvailabilityManager {

    boolean addAvailability(Availability availability) throws ClassNotFoundException, SQLException;
}
