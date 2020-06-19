package com.example.coursedesign.Model;
import java.sql.Date;

public class QuestionBean {

	private int quesId;
    private String stuId;
    private String stuContent;
	
    public QuestionBean() {
		// TODO Auto-generated constructor stub
	}
    public QuestionBean(int quesId, String stuId, String stuContent) {
		super();
		this.quesId = quesId;
		this.stuId = stuId;
		this.stuContent = stuContent;
	}
    public QuestionBean(String stuId, String stuContent) {
		super();
		this.stuId = stuId;
		this.stuContent = stuContent;
	}
    
	public int getQuesId() {
		return quesId;
	}
	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuContent() {
		return stuContent;
	}
	public void setStuContent(String stuContent) {
		this.stuContent = stuContent;
	}

    
}
