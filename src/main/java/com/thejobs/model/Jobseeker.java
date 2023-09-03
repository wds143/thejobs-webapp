package com.thejobs.model;

public class Jobseeker {
	private int jbsId;
	private String jbsFirstName;
	private String jbsLastName;
	private String jbsUsername;
	private String jbsEmail;
	private String jbsPassword;
	
	public Jobseeker() {
		
	}
	
	public Jobseeker(int jbsId, String jbsFirstName, String jbsLastName, String jbsUsername, String jbsEmail, String jbsPassword) {
		this.jbsId = jbsId;
		this.jbsFirstName = jbsFirstName;
		this.jbsLastName = jbsLastName;
		this.jbsUsername = jbsUsername;
		this.jbsEmail = jbsEmail;
		this.jbsPassword = jbsPassword;
	}
	
	public int getJbsId() {
		return jbsId;
	}
	public void setJbsId(int jbsId) {
		this.jbsId = jbsId;
	}
	public String getJbsFirstName() {
		return jbsFirstName;
	}
	public void setJbsFirstName(String jbsFirstName) {
		this.jbsFirstName = jbsFirstName;
	}
	public String getJbsLastName() {
		return jbsLastName;
	}
	public void setJbsLastName(String jbsLastName) {
		this.jbsLastName = jbsLastName;
	}
	public String getJbsUsername() {
		return jbsUsername;
	}
	public void setJbsUsername(String jbsUsername) {
		this.jbsUsername = jbsUsername;
	}
	public String getJbsEmail() {
		return jbsEmail;
	}
	public void setJbsEmail(String jbsEmail) {
		this.jbsEmail = jbsEmail;
	}
	public String getJbsPassword() {
		return jbsPassword;
	}
	public void setJbsPassword(String jbsPassword) {
		this.jbsPassword = jbsPassword;
	}
	
	
}
