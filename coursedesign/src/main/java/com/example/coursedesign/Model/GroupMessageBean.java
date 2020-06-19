package com.example.coursedesign.Model;

public class GroupMessageBean {

	String gMContent;
	String teaId;
	public String getgMContent() {
		return gMContent;
	}
	public void setgMContent(String gMContent) {
		this.gMContent = gMContent;
	}
	public String getTeaId() {
		return teaId;
	}
	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}
	public GroupMessageBean(String gMContent, String teaId) {
		super();
		this.gMContent = gMContent;
		this.teaId = teaId;
	}

	public GroupMessageBean() {
		// TODO Auto-generated constructor stub
	}

}
