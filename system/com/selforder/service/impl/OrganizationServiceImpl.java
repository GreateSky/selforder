package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.selforder.bean.Employee;
import com.selforder.bean.Organization;
import com.selforder.dao.OrganizationDao;
import com.selforder.service.OrganizationService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.Tools;

public class OrganizationServiceImpl implements OrganizationService  {
	private OrganizationDao organizationDao;
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/**
	 * 保存组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public String insertOrganization(Organization organization) {
		String result = "";
		try{
			//判断参数
			if(organization == null ){
				return JsonResultUtil.getJsonResult(-1, "fail", "新增组织架构参数异常！");
			}
			String oid = Tools.getUuid();//生成主键
			String bid = new Context().getLoginUserInfo().getBid();//获取所属商户
			String crter = new Context().getLoginUserInfo().getCode();//获取操作员code
			organization.setOid(oid);
			organization.setBid(bid);
			organization.setCrter(crter);
			int temp = organizationDao.insertOrganization(organization);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "保存异常！");
		}
		return result;
	}

	/**
	 * 获取组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public String getOrganizationList4Tree(Organization organization) {
		String result = "";
		try{
			String bid = new Context().getLoginUserInfo().getBid();
			organization.setBid(bid);
			organization.setParentid(organization.getOid());
			organization.setOid("");
			
			List<Organization> organizationList = organizationDao.getOrganizationList4Tree(organization);
			if(null != organizationList && organizationList.size() > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "获取成功", organizationList);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "获取失败");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "获取失败");
		}
		return result;
	}

	/**
	 * 更新组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public String updateOrganization(Organization organization) {
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			organization.setOpter(opter);
			int temp = organizationDao.updateOrganization(organization);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "操作异常！");
		}
		return result;
	}
	
	/**
	 * 批量插入员工与组织架构关联关系
	 * @param employeeList
	 * @return
	 */
	public String insertEmpOrg(List<Employee> employeeList){
		String result = "";
		try{
			int temp = organizationDao.insertEmpOrg(employeeList);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "操作异常！");
		}
		return result;
	}
	
	/**
	 * 获取组织架构与员工关系列表
	 * @param organization
	 * @return
	 */
	@Override
	public String getEmpOrgList(Employee  employee){
		String result = "";
		Map resultMap = new HashMap();
		try{
			String bid = new Context().getLoginUserInfo().getBid();
			if( null == bid || "".equals(bid)){
				return JsonResultUtil.getJsonResult(-1, "fail", "获取员工所属公司异常！");
			}
			employee.setBid(bid);
			List<Employee> emporglist = organizationDao.getEmpOrgList(employee);
			if(null != emporglist && emporglist.size() > 0){
				//查询统计数
				int count = organizationDao.getEmpOrgListCount(employee);
				resultMap.put("rows", emporglist);
				resultMap.put("total", count);
				result = JsonResultUtil.MapToJsonStr(resultMap);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "获取失败");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "获取失败");
		}
		return result;
	}
	
	/**
	 * 更新组织与人员关系列
	 * @param organization
	 * @return
	 */
	public String updateEmpOrg(Employee  employee){
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			employee.setOpter(opter);
			int temp = organizationDao.updateEmpOrg(employee);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "操作异常！");
		}
		return result;
	}
	
	/**
	 * 获取未关联组织架构的员工信息
	 * @param organization
	 * @return
	 */
	public String getNoEmpOrgList(Employee  employee){
		String result = "";
		try{
			String bid = new Context().getLoginUserInfo().getBid();
			employee.setBid(bid);
			List<Employee> noemporglost = organizationDao.getNoEmpOrgList(employee);
			if(null != noemporglost && noemporglost.size() > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "获取成功！", noemporglost);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据！");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "获取失败");
		}
		return result;
	}
	
	/**
	 * 移除部门及下属部门并部门下的员工
	 * @param organization
	 * @return
	 */
	@SuppressWarnings("finally")
	public String removeOrgAndEmp(Organization  organization)throws Exception {
		/**
		 * 逻辑处理过程：
		 * 1、先移除部门及下属部门所关联的员工(deleted = 1)
		 * 2、再移除部门及下属部门(deleted = 1)
		 */
		String result = "";
		try{
			String bid = new Context().getLoginUserInfo().getBid();
			String opter = new Context().getLoginUserInfo().getCode();
			organization.setBid(bid);
			organization.setOpter(opter);
			//先移除部门及下属部门所关联的员工(deleted = 1)
			int temp = organizationDao.removeOrgEmp(organization);
			//再移除部门及下属部门(deleted = 1)
			temp = organizationDao.removeOrg(organization);
			result = JsonResultUtil.getJsonResult(0, "success", "操作成功!");
		}catch(Exception e){
			result = JsonResultUtil.getJsonResult(-1, "fail", "操作失败!");
			e.printStackTrace();
		}finally{
			return result;
		}
	}
}
