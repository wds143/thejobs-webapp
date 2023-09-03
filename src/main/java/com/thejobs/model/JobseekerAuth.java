package com.thejobs.model;

public class JobseekerAuth {
	private String jbsUsername;
	private String jbsPassword;
	
	public JobseekerAuth() {}
	
	public JobseekerAuth(String jbsUsername, String jbsPassword) {
		this.jbsUsername = jbsUsername;
		this.jbsPassword = jbsPassword;
	}

	public String getJbsUsername() {
		return jbsUsername;
	}

	public void setJbsUsername(String jbsUsername) {
		this.jbsUsername = jbsUsername;
	}

	public String getJbsPassword() {
		return jbsPassword;
	}

	public void setJbsPassword(String jbsPassword) {
		this.jbsPassword = jbsPassword;
	}
	
}
