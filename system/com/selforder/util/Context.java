package com.selforder.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.selforder.bean.UserInfo;

/**
 * 系统信息工具类
 * @author xingwanzhao
 *
 * 2016-6-6
 */
public class Context {
	/**
	 * 获取session中当前登录用户的基本信息
	 * @return
	 */
	public UserInfo getLoginUserInfo(){
		UserInfo userinfo = null;
		try{
		 userinfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
		}catch(Exception e){
			e.printStackTrace();
		}
		return userinfo;
	}
}
