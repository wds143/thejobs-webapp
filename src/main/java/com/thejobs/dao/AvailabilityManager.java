package com.thejobs.dao;

import com.thejobs.model.Availability;

public interface AvailabilityManager {

    boolean addAvailability(Availability availability) throws ClassNotFoundException;

}
