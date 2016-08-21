package com.selforder.bean;

public class Room extends baseRoom {
	public String kongtiao = "空调关闭";
	public static boolean chufang = true;
	
	public String getRoomNo(String no){
		return no;
	}
	public String getRoomNo(){
		return super.roomNo;
	}
	
	public static void main(String args[]){
		Room room = new Room();
		System.out.println(room.getRoomNo());
		System.out.println(room.getRoomNo("11111111111111"));
	}
	
}
