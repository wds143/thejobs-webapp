package com.thejobs.model;

public class ConsultantAuth {
	
	private String conUsername;
	private String conPassword;
	
	public ConsultantAuth() {
		
	}
	
	public ConsultantAuth( String conUsername, String conPassword) {
		this.conUsername = conUsername;
		this.conPassword = conPassword;
	}
	
	public String getConUsername() {
		return conUsername;
	}
	public void setConUsername(String conUsername) {
		this.conUsername = conUsername;
	}
	public String getConPassword() {
		return conPassword;
	}
	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}
	
	

}
