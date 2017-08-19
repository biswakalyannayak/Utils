package com.example.jpadata.bean;

public class SearchCriteria {
	
	private String flat;

	private String state;
	
	private String userName;
	
	private String email;

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SearchCriteria [flat=" + flat + ", state=" + state + ", userName=" + userName + ", email=" + email
				+ "]";
	}
	
	
}
