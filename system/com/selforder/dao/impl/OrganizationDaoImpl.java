package com.selforder.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.selforder.bean.Employee;
import com.selforder.bean.Organization;
import com.selforder.bean.Role;
import com.selforder.dao.OrganizationDao;

public class OrganizationDaoImpl extends SqlSessionDaoSupport implements OrganizationDao {
	/**
	 * 保存组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public int insertOrganization(Organization organization) {
		// TODO Auto-generated method stub
		int temp = getSqlSession().insert("com.selforder.organization.insertOrganization", organization);
		return temp;
	}

	/**
	 * 获取组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public List<Organization> getOrganizationList4Tree(Organization organization) {
		// TODO Auto-generated method stub
	
		return getSqlSession().selectList("com.selforder.organization.getOrganizationList4Tree", organization);
	}

	/**
	 * 更新组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public int updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return getSqlSession().update("com.selforder.organization.updateOrganization", organization);
	}
	
	/**
	 * 批量插入员工与组织架构关联关系
	 * @param employeeList
	 * @return
	 */
	public int insertEmpOrg(List<Employee> employeeList){
		int temp = -1;
		temp = getSqlSession().insert("com.selforder.organization.insertEmpOrg", employeeList);
		return temp;
	}
	
	/**
	 * 获取组织架构与人员关系列表
	 * @param organization
	 * @return
	 */
	public List<Employee> getEmpOrgList(Employee  employee){
		return getSqlSession().selectList("com.selforder.organization.getEmpOrgList", employee);
	}
	
	/**
	 * 获取组织架构与人员关系列表统计数
	 * @param organization
	 * @return
	 */
	public int getEmpOrgListCount(Employee  employee){
		return (Integer)getSqlSession().selectOne("com.selforder.organization.getEmployeeCount", employee);
	}
	
	/**
	 * 更新组织与人员关系
	 * @param organization
	 * @return
	 */
	public int updateEmpOrg(Employee  employee){
		return getSqlSession().update("com.selforder.organization.updateEmpOrg", employee);
	}
	
	/**
	 * 获取未关联组织架构的员工信息
	 * @param organization
	 * @return
	 */
	public List<Employee> getNoEmpOrgList(Employee  employee){
		return getSqlSession().selectList("com.selforder.organization.getNoEmpOrgList", employee);
	}
	
	/**
	 * 移除部门及下属部门
	 * @param organization
	 * @return
	 */
	public int removeOrg(Organization  organization){
		return getSqlSession().update("com.selforder.organization.removeOrg",organization);
	}
	
	/**
	 * 移除部门及下属部门员工
	 * @param organization
	 * @return
	 */
	public int removeOrgEmp(Organization  organization){
		return getSqlSession().update("com.selforder.organization.removeOrgEmp",organization);
	}
	
	
	/********************************部门与权限管理***************************/
	
	/**
	 * 获取部门已关联权限列表
	 */
	public List<Role> getOrgRoleList(Role role){
		return getSqlSession().selectList("com.selforder.organization.getOrgRoleList",role);
	}
	
	/**
	 * 删除关联关系
	 * @param role
	 * @return
	 */
	public int deletedOrgRole(Role role){
		return getSqlSession().update("com.selforder.organization.deletedOrgRole", role);
	}
	
	/**
	 * 插入关联关系
	 * @param role
	 * @return
	 */
	public int insertOrgRole(List<Role> roleList){
		return getSqlSession().insert("com.selforder.organization.insertOrgRole", roleList);
	}
	
	/**
	 * 获取部门未关联权限列表
	 */
	public List<Role> getOrgRoleNoList(Role role){
		return getSqlSession().selectList("com.selforder.organization.getOrgNoRoleList",role);
	}
	
	/**
	 * 获取商户已关联权限列表
	 */
	public List<Role> getBusinessList(Role role){
		return getSqlSession().selectList("com.selforder.organization.getBusinessRoleList", role);
	}

}
