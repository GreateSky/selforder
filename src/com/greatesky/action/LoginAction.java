package com.greatesky.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.greatesky.bean.UserInfo;
import com.greatesky.service.LoginService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户登录
 * @author xingwanzhao
 *
 */
public class LoginAction extends GreateSkyActionSupport {
	private static final long serialVersionUID = 8418687943974419204L;
	private UserInfo userinfo;
	private LoginService loginservice;
	private Map resultMap;
	
	public Map getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}

	public LoginService getLoginservice() {
		return loginservice;
	}

	public void setLoginservice(LoginService loginservice) {
		this.loginservice = loginservice;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 用户登录
	 * @return
	 */
	public String userLogin(){
		UserInfo temp = new UserInfo();
		temp = loginservice.UserLogin(userinfo);
		resultMap = new HashMap();
		try{
			if(temp != null){
				resultMap.put("retCode","0");
				resultMap.put("status","登录成功!");
				resultMap.put("userinfo", temp);
				
				ActionContext actionContext = ActionContext.getContext();
				Map session = actionContext.getSession();
				session.put("userinfo",temp);
			}else{
				resultMap.put("retCode","-1");
				resultMap.put("status","登录失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		this.setResultJson(resultMap);
		return this.SUCCESS;
	}
	
	/**
	 * 注销登录
	 * @return
	 */
	public String cancelAction(){
		try{
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			UserInfo temp = (UserInfo)session.get("userinfo");
			if(temp != null){
				session.remove("userinfo");
			}
			resultMap.put("retCode","0");
			resultMap.put("status","注销成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		this.setResultJson(resultMap);
		return this.SUCCESS;
	}
}
