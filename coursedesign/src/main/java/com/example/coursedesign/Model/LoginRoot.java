package com.example.coursedesign.Model;

public class LoginRoot {

	private String rootId;
	private String password;

	public LoginRoot() {
	}

	public LoginRoot(String rootId, String password) {
		this.rootId = rootId;
		this.password = password;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
