package com.selforder.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
/**
 * 扩展spring security中默认登录实体User
 * @author xingwanzhao
 *
 * 2016-6-6
 */
public class UserInfo extends User {
	private String empid;
	private String type;
	private int status;
	private String wechatid;
	private String openid;
	private String headimgurl;
	private String phone;
	private String sid;
	private String bid;
	private String name;
	private String code;
	private String loginname;
	private String appid;
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	private int sex;
	
	public UserInfo(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -6027045911938363570L;
	public String getType() {
		return type;
	}

	public int getStatus() {
		return status;
	}

	public String getWechatid() {
		return wechatid;
	}

	public String getOpenid() {
		return openid;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public String getPhone() {
		return phone;
	}

	public String getSid() {
		return sid;
	}

	public String getBid() {
		return bid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setWechatid(String wechatid) {
		this.wechatid = wechatid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public int getSex() {
		return sex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

}
