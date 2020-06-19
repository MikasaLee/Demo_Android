package com.example.coursedesign.Model;


public class StudentBean {
	private String stuId;
	private String stuName;		
	private String collegeName;
	private String stuClass;


    public StudentBean() { }

    public StudentBean(String stuId, String stuName, String collegeName, String stuClass) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.collegeName = collegeName;
        this.stuClass = stuClass;
    }

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getStuClass() {
		return stuClass;
	}

	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}

   
}
