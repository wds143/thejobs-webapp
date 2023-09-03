package com.thejobs.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

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

    public boolean addAvailability(Availability availability) throws SQLException, ClassNotFoundException, ParseException {
        return getAvailabilityManager().addAvailability(availability);
    }

	public boolean editAvailability(Availability availability) {
		return getAvailabilityManager().editAvailability(availability);
	}

	public boolean deleteAvailability(int avbId) {
		return getAvailabilityManager().deleteAvailability(avbId);
	}

	public Availability fetchSingleAvailability(int apnmId) {
		return getAvailabilityManager().fetchSingleAvailability();
	}

	public List<Availability> fetchAllAvailability() {
		return getAvailabilityManager().fetchAllAvailability();
	}
}

