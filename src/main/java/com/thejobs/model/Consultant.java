package com.thejobs.model;

public class Consultant {
	private int conId;
	private String conFirstName;
	private String conLastName;
	private String conUsername;
	private String conEmail;
	private String conPassword;
	
	public Consultant() {
		
	}
	
	public Consultant(int conId, String conFirstName, String conLastName, String conUsername, String conEmail, String conPassword) {
		this.conId = conId;
		this.conFirstName = conFirstName;
		this.conLastName = conLastName;
		this.conUsername = conUsername;
		this.conEmail = conEmail;
		this.conPassword = conPassword;
	}
	
	public int getConId() {
		return conId;
	}
	public void setConId(int conId) {
		this.conId = conId;
	}
	public String getConFirstName() {
		return conFirstName;
	}
	public void setConFirstName(String conFirstName) {
		this.conFirstName = conFirstName;
	}
	public String getConLastName() {
		return conLastName;
	}
	public void setConLastName(String conLastName) {
		this.conLastName = conLastName;
	}
	public String getConUsername() {
		return conUsername;
	}
	public void setConUsername(String conUsername) {
		this.conUsername = conUsername;
	}
	public String getConEmail() {
		return conEmail;
	}
	public void setConEmail(String conEmail) {
		this.conEmail = conEmail;
	}
	public String getConPassword() {
		return conPassword;
	}
	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}
	
	
}
