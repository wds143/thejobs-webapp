package com.thejobs.model;

public class Availability {
	private int avbId;
	private int conId;
	private String avbDate;
	private String avbTime;
	private String avbCountry;
	private String avbJob;
	
	public Availability() {
			
	}
	public Availability(int avbId, int conId, String avbDate, String avbTime, String avbCountry, String avbJob) {
		this.avbId = avbId;
		this.conId = conId;
		this.avbDate = avbDate;
		this.avbTime = avbTime;
		this.avbCountry = avbCountry;
		this.avbJob = avbJob;
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
	public String getAvbDate() {
		return avbDate;
	}
	public void setAvbDate(String avbDate) {
		this.avbDate = avbDate;
	}
	public String getAvbTime() {
		return avbTime;
	}
	public void setAvbTime(String avbTime) {
		this.avbTime = avbTime;
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
}
