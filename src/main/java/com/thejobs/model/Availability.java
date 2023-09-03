package com.thejobs.model;

import java.sql.Date;
import java.sql.Time;

public class Availability {
	private int avbId;
	private int conId;
	private Date avbDate;
	private Time avbTime;
	private String avbCountry;
	private String avbJob;
	private boolean avbBooked;
	
	public Availability() {
			
	}
	public Availability(int avbId, int conId, Date avbDate, Time avbTime, String avbCountry, String avbJob, boolean avbBooked) {
		this.avbId = avbId;
		this.conId = conId;
		this.avbDate = avbDate;
		this.avbTime = avbTime;
		this.avbCountry = avbCountry;
		this.avbJob = avbJob;
		this.avbBooked = avbBooked;
	}
	
	public int getAvbId() {
		return avbId;
	}
	public void setAvbId(int avbId) {
		this.avbId = avbId;
	}
	public int getConId() {
		return conId;
	}
	public void setConId(int conId) {
		this.conId = conId;
	}
	public Date getAvbDate() {
		return avbDate;
	}
	public void setAvbDate(Date sqlDate) {
		this.avbDate = sqlDate;
	}
	public Time getAvbTime() {
		return avbTime;
	}
	public void setAvbTime(Time sqlTime) {
		this.avbTime = sqlTime;
	}
	public String getAvbCountry() {
		return avbCountry;
	}
	public void setAvbCountry(String avbCountry) {
		this.avbCountry = avbCountry;
	}
	public String getAvbJob() {
		return avbJob;
	}
	public void setAvbJob(String avbJob) {
		this.avbJob = avbJob;
	}
	public boolean getAvbBooked() {
		return avbBooked;
	}
	public void setAvbBooked(boolean avbBooked) {
		this.avbBooked = avbBooked;
	}
}
