package com.thejobs.model;

public class Availability {
	private int avbId;
	private int conId;
	private String avbDate;
	private String avbTime;
	
	public Availability(int avbId, int conId, String avbDate, String avbTime) {
		this.avbId = avbId;
		this.conId = conId;
		this.avbDate = avbDate;
		this.avbTime = avbTime;
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
	
	
}
