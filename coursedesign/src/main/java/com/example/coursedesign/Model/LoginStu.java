package com.example.coursedesign.Model;

public class LoginStu {

	private String stuId;
	private String password;

	public LoginStu(String stuId, String password) {
		this.stuId = stuId;
		this.password = password;
	}
	public LoginStu() {
		// TODO Auto-generated constructor stub
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStuId() {
		return stuId;
	}

	public void setstuId(String stuId) {
		this.stuId = stuId;
	}


}
