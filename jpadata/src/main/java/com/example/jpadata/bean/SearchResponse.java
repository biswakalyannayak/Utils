package com.example.jpadata.bean;

public class SearchResponse {
	
	private Long userId;
	
	private String flat;

	private String state;
	
	private String userName;
	
	private String email;
	
	public SearchResponse(Long userId,String flat,String state,String userName,String email){
		this.userId = userId;
		this.flat = flat;
		this.state = state;
		this.userName = userName;
		this.email = email;
	}
	
	public SearchResponse(Long userId,String userName,String email){
		this.userId = userId;
		this.userName = userName;
		this.email = email;
	}

	public SearchResponse() {
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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
		return "SearchResponse [userId=" + userId + ", flat=" + flat + ", state=" + state + ", userName=" + userName
				+ ", email=" + email + "]";
	}

	
}
