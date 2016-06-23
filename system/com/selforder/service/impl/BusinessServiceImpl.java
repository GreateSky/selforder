package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.selforder.bean.Business;
import com.selforder.bean.Employee;
import com.selforder.bean.UserInfo;
import com.selforder.dao.BusinessDao;
import com.selforder.dao.EmployeeDao;
import com.selforder.service.BusinessService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.Tools;

public class BusinessServiceImpl implements BusinessService {
	private BusinessDao businessDao;
	private EmployeeDao employeeDao;
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	public BusinessDao getBusinessDao() {
		return businessDao;
	}
	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}
	
	/**
	 * 保存商户信息
	 * @param business
	 * @return
	 */
	public String saveBusiness(Business business){
		String result = "";
		//保存商户基本信息
		business.setBid(Tools.getUuid());
		business.setCrter(new Context().getLoginUserInfo().getCode());
		String temp = businessDao.saveBusiness(business);
		//保存系统管理员信息
		if(temp.indexOf("fail") == -1){
			//创建系统管理员
			String sysadmin	 = business.getSysadmin();
			String password = business.getPassword();
			String securityPassword = Tools.MD5(password+"{"+sysadmin+"}");
			if( null != sysadmin && !"".equals(sysadmin)){
				Employee emp = new Employee();
				emp.setLoginname(sysadmin);
				emp.setPassword(securityPassword);
				emp.setType("B");//商户人员
				emp.setBid(business.getBid());//所属商户
				emp.setEmpname("系统管理员");
				String saveEmp = employeeDao.saveEmployeeInfo(emp);
				if(saveEmp.indexOf("fail") != -1){
					result = JsonResultUtil.getJsonResult(-1, "fail", "保存商户系统管理员失败！");
				}else{
					result = temp;
				}
				
			}else{
				result = temp;
			}
		}
		return result;
	}
	
	/**
	 * 获取商户列表
	 * @param business
	 * @return
	 */
	public String businessList(Business business){
		String result = "";
		Map resultMap = new HashMap();
		try{
			//判断当前登录人的类型  A：运营人员查询全部商户   B：商户管理人员查询自己的商户  其他：无查询商户权限
			UserInfo user = new Context().getLoginUserInfo();
			String type = user.getType();
			if("A".equals(type)){//查询全部商户
				//查询商户列表
				List<Business> businesslist = businessDao.businessList(business);
				if(businesslist != null && businesslist.size()>0){
					//查询统计数
					int count = businessDao.getBusinessCount(business);
					resultMap.put("rows", businesslist);
					resultMap.put("total", count);
					result = JsonResultUtil.MapToJsonStr(resultMap);
				}
			}else if("B".equals(type)){
				String bid = user.getBid();
				business.setBid(bid);
				//查询商户列表
				List<Business> businesslist = businessDao.businessList(business);
				if(businesslist != null && businesslist.size()>0){
					//查询统计数
					int count = businessDao.getBusinessCount(business);
					resultMap.put("rows", businesslist);
					resultMap.put("total", count);
					result = JsonResultUtil.MapToJsonStr(resultMap);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return result;
	}
	
	/**
	 * 获取商户详情
	 * @param business
	 * @return
	 */
	public String businessInfo(Business business){
		String result = "";
		try{
			Business businessinfo = businessDao.businessInfo(business);
			if(businessinfo != null){
				result = JsonResultUtil.getJsonResult(0, "success", "", businessinfo);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "未查询到商户详情");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更新商户基本信息
	 * @param business
	 * @return
	 */
	public String updateBusiness(Business business){
		String result = "";
		if(business != null && !"".equals(business.getBid())){
			int updateRes = businessDao.updateBusiness(business);
			if(updateRes <=0){
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新商户信息失败!");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "更新商户信息成功!");
			}
		}else{
			result = JsonResultUtil.getJsonResult(-1, "fail", "更新商户信息参数异常!");
		}
		return result;
	}
	
	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.setLoginname("sysadmin");
		emp.setPassword("000000");
		System.out.println("md5加密后的密码==============:"+Tools.MD5("000000{sysadmin}"));
		System.out.println("set password===============:"+emp.getPassword());
	}

}
