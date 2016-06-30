package com.selforder.service;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Employee;
import com.selforder.bean.Organization;
import com.selforder.bean.Resource;
import com.selforder.bean.Role;

/**
 * 组织架构管理Service
 * @author xingwanzhao
 *
 * 2016-6-24
 */
public interface OrganizationService {
	/**
	 * 保存组织架构
	 * @param organization
	 * @return
	 */
	public String insertOrganization(Organization  organization);
	
	/**
	 * 获取组织架构
	 * @param organization
	 * @return
	 */
	public String getOrganizationList4Tree(Organization  organization);
	
	/**
	 * 更新组织架构
	 * @param organization
	 * @return
	 */
	public String updateOrganization(Organization  organization);
	
	/**
	 * 批量插入员工与组织架构关联关系
	 * @param employeeList
	 * @return
	 */
	public String insertEmpOrg(List<Employee> employeeList);
	
	/**
	 * 获取组织架构与人员关系列表
	 * @param organization
	 * @return
	 */
	public String getEmpOrgList(Employee  employee);
	
	/**
	 * 更新组织与人员关系列
	 * @param organization
	 * @return
	 */
	public String updateEmpOrg(Employee  employee);
	
	/**
	 * 获取未关联组织架构的员工信息
	 * @param organization
	 * @return
	 */
	public String getNoEmpOrgList(Employee  employee);
	
	/**
	 * 移除部门及下属部门并部门下的员工
	 * @param organization
	 * @return
	 */
	public String removeOrgAndEmp(Organization  organization) throws Exception ;
}
