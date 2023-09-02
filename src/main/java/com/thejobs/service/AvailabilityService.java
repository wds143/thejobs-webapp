package com.thejobs.service;

import java.sql.SQLException;

import com.thejobs.dao.AvailabilityManager;
import com.thejobs.dao.AvailabilityManagerImpl;
import com.thejobs.model.Availability;

public class AvailabilityService {
    private static AvailabilityService availabilityServiceObj;

    private AvailabilityService() {
    }

    public synchronized static AvailabilityService getAvailabilityService() {
        if (availabilityServiceObj == null) {
            availabilityServiceObj = new AvailabilityService();
        }
        return availabilityServiceObj;
    }

    private AvailabilityManager getAvailabilityManager() {
        return new AvailabilityManagerImpl();
    }

    public boolean addAvailability(Availability availability) throws SQLException, ClassNotFoundException {
        return getAvailabilityManager().addAvailability(availability);
    }
}

