package com.selforder.dao;

import java.util.List;

import com.selforder.bean.Employee;
import com.selforder.bean.Role;

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
	
	/**
	 * 获取员工列表
	 * @param employee
	 * @return
	 */
	public List<Employee> getEmployeeList(Employee employee);
	
	/**
	 * 获取列表统计数
	 * @param employee
	 * @return
	 */
	public int getEmployeeCount(Employee employee);
	
	/**
	 * 查询员工已关联权限
	 * @param employee
	 * @return
	 */
	public List<Role> getEmpRoles(Employee employee)throws Exception;
	
	/**
	 * 获取员工信息列表(不带分页)
	 * @param employee
	 * @return
	 */
	public List<Employee> getEmployeeListWithNoPage(Employee employee)throws Exception;
	
}
