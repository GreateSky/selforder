package com.selforder.bean;

import java.util.Date;

import com.automobile.bean.baseBean;
import com.selforder.util.Tools;

/**
 * 员工基础信息实体类
 * @author xingwanzhao
 *
 * 2016-5-28
 */
public class Employee extends baseBean {
	private static final long serialVersionUID = 3036068015464838511L;
	private String empid;
	private int empcode;
	private String loginname;
	private String password;
	private String empname;
	private int sex;
	private int status;
	private String type;
	private String phone;
	private String homeaddress;
	private String wechatid;
	private String openid;
	private String headimgurl;
	private String contactname;
	private String contactphone;
	private int loginnum;
	private Date lastlogindate;
	private String sid;
	private String bid;
	private String crter;
	private Date crtdate;
	private String upder;
	private Date upddate;
	private String deleted;
	private String bname;
	private String sname;
	private String role;
	private String newpassword;
	private String opter;
	private String oid;//所属组织架构ID
	private String oeid;//组织架构与人员关联表ID
	private String oname;//组织架构名称
	private String isadmin;//是否系统管理员
	private String keyword;//搜索关键字
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getEmpcode() {
		return empcode;
	}
	public int getSex() {
		return sex;
	}
	public int getStatus() {
		return status;
	}
	public int getLoginnum() {
		return loginnum;
	}
	public void setEmpcode(int empcode) {
		this.empcode = empcode;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setLoginnum(int loginnum) {
		this.loginnum = loginnum;
	}
	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	public String getEmpid() {
		return empid;
	}
	public String getLoginname() {
		return loginname;
	}
	public String getPassword() {
		return password;
	}
	public String getEmpname() {
		return empname;
	}
	public String getType() {
		return type;
	}
	public String getPhone() {
		return phone;
	}
	public String getHomeaddress() {
		return homeaddress;
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
	public String getContactname() {
		return contactname;
	}
	public String getContactphone() {
		return contactphone;
	}
	public Date getLastlogindate() {
		return lastlogindate;
	}
	public String getSid() {
		return sid;
	}
	public String getBid() {
		return bid;
	}
	public String getCrter() {
		return crter;
	}
	public Date getCrtdate() {
		return crtdate;
	}
	public String getUpder() {
		return upder;
	}
	public Date getUpddate() {
		return upddate;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public void setPassword(String password) {
		//String securityPassword = Tools.MD5(password+"{"+this.loginname+"}");
		this.password = password;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
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
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}
	public void setUpder(String upder) {
		this.upder = upder;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getBname() {
		return bname;
	}
	public String getSname() {
		return sname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		//String securityPassword = Tools.MD5(password+"{"+this.loginname+"}");
		this.newpassword = newpassword;
	}
	public String getOpter() {
		return opter;
	}
	public void setOpter(String opter) {
		this.opter = opter;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOeid() {
		return oeid;
	}
	public void setOeid(String oeid) {
		this.oeid = oeid;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
}
