package com.selforder.util;

import java.util.UUID;

/**
 * uuid 生产工具类
 * @author xingwanzhao
 *
 * 2016-6-14
 */
public class Uuid {
	public static void main(String[] args) {
		getUuid();
	}
	public static String getUuid(){
		String result = "";
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println("uuid============="+uuidStr);
		return uuidStr;
	}
}
