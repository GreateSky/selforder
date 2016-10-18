package com.selforder.bean;

public class test {
	public static String name;
	public static String address;
	public test(){
		
	}
	public test(String name){
		this.name = name;
	}
	
	public static void main(String args[]){
		test t1 = new test();
		test t2 = new test("t2");
		t1.address = "大河豚";
		t2.address = "陈营";
		System.out.println("t1.name=============="+t1.name);
		System.out.println("t2.name=============="+t2.name);
		System.out.println("t1.address=============="+t1.address);
		System.out.println("t2.address=============="+t2.address);
		System.out.println("t2.address==========++++++++++++++++++------------===="+t2.address);
		System.out.println("提价测试"+t2.address);
		System.out.println("提价测试2"+t2.address);
	}
}
