package com.selforder.service;

import java.util.List;

import com.selforder.bean.Employee;

public interface EmployeeService {
	
	/**
	 * 保存员工基本信息
	 * @param employee
	 * @return
	 */
	public String saveEmployeeInfo(Employee employee);
	
	/**
	 * 修改员工密码
	 * @param employee
	 * @return
	 */
	public String updatePassword(Employee employee);
	
	/**
	 * 获取用户基本信息
	 * @param employee
	 * @return
	 */
	public String getEmployeeInfo(Employee employee);
	
	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	public String updateEmpInfo(Employee employee);
	
	/**
	 * 获取员工列表
	 * @param employee
	 * @return
	 */
	public String getEmployeeList(Employee employee);
	
	/**
	 * 获取当前登录员工已授权的权限
	 * @param employee
	 * @return
	 */
	public String getEmployeeRole(Employee employee);
	
	/**
	 * 获取当前员工已授权权限（用于运营人员）
	 * @param employee
	 * @return
	 */
	public String getEmployeeRole4Admin(Employee employee);
	
	/**
	 * 获取员工信息列表(不带分页)
	 * @param employee
	 * @return
	 */
	public String getEmployeeListWithNoPage(Employee employee);
}
