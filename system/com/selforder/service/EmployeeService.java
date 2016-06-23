package com.selforder.service;

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
}
