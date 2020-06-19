package com.example.coursedesign.Model;


public class ResponseResult {

	// 服务器返回状态代码
	private int code ;
	// 服务器返回信息
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
