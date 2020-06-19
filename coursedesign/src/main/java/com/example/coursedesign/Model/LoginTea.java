package com.example.coursedesign.Model;

public class LoginTea {

	private String teaId;
	private String password;
	
	public LoginTea(String teaId, String password) {
		super();
		this.teaId = teaId;
		this.password = password;
	}

	public LoginTea() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
