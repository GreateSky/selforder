package com.selforder.dao;

import java.util.List;

import com.selforder.bean.Employee;
import com.selforder.bean.Organization;
import com.selforder.bean.Role;

/**
 * 组织机构管理Dao
 * @author xingwanzhao
 *
 * 2016-6-24
 */
public interface OrganizationDao {
	/**
	 * 保存组织架构
	 * @param organization
	 * @return
	 */
	public int insertOrganization(Organization  organization);
	
	/**
	 * 获取组织架构
	 * @param organization
	 * @return
	 */
	public List<Organization> getOrganizationList4Tree(Organization  organization);
	
	/**
	 * 更新组织架构
	 * @param organization
	 * @return
	 */
	public int updateOrganization(Organization  organization);
	
	/**
	 * 批量插入员工与组织架构关联关系
	 * @param employeeList
	 * @return
	 */
	public int insertEmpOrg(List<Employee> employeeList);
	
	/**
	 * 获取组织架构与人员关系列
	 * @param organization
	 * @return
	 */
	public List<Employee> getEmpOrgList(Employee  employee);
	
	/**
	 * 获取组织架构与人员关系列表统计数
	 * @param organization
	 * @return
	 */
	public int getEmpOrgListCount(Employee  employee);
	
	/**
	 * 更新组织与人员关系列
	 * @param organization
	 * @return
	 */
	public int updateEmpOrg(Employee  employee);
	
	/**
	 * 获取未关联组织架构的员工信息
	 * @param organization
	 * @return
	 */
	public List<Employee> getNoEmpOrgList(Employee  employee);
	
	/**
	 * 移除部门及下属部门
	 * @param organization
	 * @return
	 */
	public int removeOrg(Organization  organization);
	
	/**
	 * 移除部门及下属部门员工
	 * @param organization
	 * @return
	 */
	public int removeOrgEmp(Organization  organization);
	
	/********************************部门与权限管理***************************/
	
	/**
	 * 获取部门已关联权限列表
	 */
	public List<Role> getOrgRoleList(Role role);
	
	/**
	 * 删除关联关系
	 * @param role
	 * @return
	 */
	public int deletedOrgRole(Role role);
	
	/**
	 * 插入关联关系
	 * @param role
	 * @return
	 */
	public int insertOrgRole(List<Role> roleList);
	
	/**
	 * 获取部门未关联权限列表
	 */
	public List<Role> getOrgRoleNoList(Role role);
	
	/**
	 * 获取商户已关联权限列表
	 */
	public List<Role> getBusinessList(Role role);
}
