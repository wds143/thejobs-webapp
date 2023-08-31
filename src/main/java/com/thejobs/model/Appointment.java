package com.thejobs.model;

public class Appointment {
	private int apnmId;
	private int conId;
	private int jbsId;
	private String apnmDesc;
	private String apnmDate;
	private String apnmTime;
	private String apnmJob;
	
	public Appointment(int apnmId, int conId,int jbsId, String apnmDesc, String apnmDate, String apnmTime, String apnmJob) {
		this.apnmId = apnmId;
		this.conId = conId;
		this.jbsId = jbsId;
		this.apnmDesc = apnmDesc;
		this.apnmDate = apnmDate;
		this.apnmTime = apnmTime;
		this.apnmJob = apnmJob;
	}
	
	public int getApnmId() {
		return apnmId;
	}
	public void setApnId(int apnmId) {
		this.apnmId = apnmId;
	}
	public int getConId() {
		return conId;
	}
	public void setConId(int conId) {
		this.conId = conId;
	}
	public int getJbsId() {
		return jbsId;
	}
	public void setJbsId(int jbsId) {
		this.jbsId = jbsId;
	}
	public String getApnmDesc() {
		return apnmDesc;
	}
	public void setApnmDesc(String apnmDesc) {
		this.apnmDesc = apnmDesc;
	}
	public String getApnmDate() {
		return apnmDate;
	}
	public void setApnmDate(String apnmDate) {
		this.apnmDate = apnmDate;
	}
	public String getApnmTime() {
		return apnmTime;
	}
	public void setApnmTime(String apnmTime) {
		this.apnmTime = apnmTime;
	}
	public String getApnmJob() {
		return apnmJob;
	}
	public void setApnmJob(String apnmJob) {
		this.apnmJob = apnmJob;
	}
	
	
	
}
