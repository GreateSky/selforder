package com.selforder.util;

import java.security.MessageDigest;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 工具类
 * @author xingwanzhao
 *
 * 2016-6-19
 */
public class Tools {

	/**
	 * 获取访问者的IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	} 
	
	/**
	 * 生产UUID
	 * @return
	 */
	public static String getUuid(){
		String result = "";
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println("uuid============="+uuidStr);
		return uuidStr;
	}
	
	/**
	 * 字符串进行MD5加密
	 * @param plainText
	 * @return
	 */
	public static String  MD5(String plainText) {
		 String md5Str = "";
	     try {
	        //生成实现指定摘要算法的 MessageDigest 对象。
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        //使用指定的字节数组更新摘要。
	        md.update(plainText.getBytes());
	        //通过执行诸如填充之类的最终操作完成哈希计算。
	        byte b[] = md.digest();
	        //生成具体的md5密码到buf数组
	        int i;
	        StringBuffer buf = new StringBuffer("");
	        for (int offset = 0; offset < b.length; offset++) {
	          i = b[offset];
	          if (i < 0)
	            i += 256;
	          if (i < 16)
	            buf.append("0");
	          buf.append(Integer.toHexString(i));
	        }
	        md5Str = buf.toString();
	        System.out.println("32位: " + buf.toString());// 32位的加密
	        System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
	     } 
	     catch (Exception e) {
	       e.printStackTrace();
	     }
	     return md5Str;
	   }
	  
	   public static void main(String agrs[]) {
		   MD5("000000{superadmin}");//加密LXD
	   }
}
