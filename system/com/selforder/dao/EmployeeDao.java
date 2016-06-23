package com.selforder.dao;

import java.util.Map;

import com.selforder.bean.Employee;

/**
 * 員工信息管理服務接口
 * @author xingwanzhao
 *
 * 2016-5-28
 */
public interface EmployeeDao {
	
	/**
	 * 保存员工基本信息
	 * @param employee
	 * @return
	 */
	public String saveEmployeeInfo(Employee employee);
	
	/**
	 * 获取用户基本信息
	 * @param employee
	 * @return
	 */
	public Employee getEmployeeInfo(Employee employee);
	
	/**
	 * 获取下一个员工编码
	 * @param employee
	 * @return
	 */
	public int nextEmpCode(String businessid);
	
	/**
	 * 修改员工密码
	 * @param employee
	 * @return
	 */
	public int updatePassword(Employee employee);
	
	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	public int updateEmpInfo(Employee employee);
	
}
