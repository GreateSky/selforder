package com.selforder.bean;

public class Test {
	public static void main(String[] args){
//		Student studeng = new Student();
//		studeng.setName("多态1 生成");
//		Test test = new Test();
//		test.getStudentInfo(studeng);
//		Persion persion2 = new Persion("general",20,"男");
		
		
		Persion persion = new Persion();
		persion.setName("testset");
		persion.setName("asdfasdfa", "111111111111111111111111");
		System.out.println(persion.getName());
		
		String name;
		name = "1111111111111";
		name = "111111111111"+"2222222222222222";
	}
	
	public void getStudentInfo(Student student){
		System.out.println(student.getName());
	}
	public void getStudentInfo(String name){
		Student studeng = new Student();
		studeng.setName("多态2 生成");
		System.out.println(studeng.getName());
	}
}
