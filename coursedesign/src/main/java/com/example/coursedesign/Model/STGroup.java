package com.example.coursedesign.Model;


public class STGroup {
	private String stuId;
	private String teaId;
	public STGroup(String stuId, String teaId) {
		super();
		this.stuId = stuId;
		this.teaId = teaId;
	}
	public STGroup() {
		// TODO Auto-generated constructor stub
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getTeaId() {
		return teaId;
	}
	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}
	
}
