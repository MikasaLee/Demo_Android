package com.example.coursedesign.Model;

import java.sql.Date;

public class TeacherBean {
	private String teaId;
	private String teaName;
	private String collegeName;
	private String teaGrade;
	public String getTeaId() {
		return teaId;
	}
	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getTeaGrade() {
		return teaGrade;
	}
	public void setTeaGrade(String teaGrade) {
		this.teaGrade = teaGrade;
	}
	public TeacherBean(String teaId, String teaName, String collegeName, String teaGrade) {
		super();
		this.teaId = teaId;
		this.teaName = teaName;
		this.collegeName = collegeName;
		this.teaGrade = teaGrade;
	}

	public TeacherBean() {
		// TODO Auto-generated constructor stub
	}
	
}
