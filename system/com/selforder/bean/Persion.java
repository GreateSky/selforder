package com.selforder.bean;

public class Persion {
	private String name;
	private int age;
	private String sex;
	
	public Persion(){
		
	}
	public Persion(String name_,int age_,String sex_){
		name = name_;
		age = age_;
		sex = sex_;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getSex() {
		return sex;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setName(String name,String noti) {
		this.name = name+noti;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
