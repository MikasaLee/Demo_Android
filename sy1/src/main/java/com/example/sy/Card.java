package com.example.sy;

public class Card {
    private String name;
    private String tel;
    private String department;
    private String title;

    public Card(){}
    public Card(String name,String tel,String department,String title){
        this.name = name;
        this.tel = tel;
        this.department = department;
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    public String getTel() {
        return tel;
    }
    public String getTitle() {
        return title;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.name+" "+this.tel+" "+this.department+" "+this.title+"\n";
    }
}
