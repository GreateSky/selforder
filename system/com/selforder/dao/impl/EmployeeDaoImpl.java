package com.selforder.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Business;
import com.selforder.bean.Employee;
import com.selforder.bean.Role;
import com.selforder.dao.EmployeeDao;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

public class EmployeeDaoImpl extends SqlSessionDaoSupport implements EmployeeDao {

	/**
	 * 保存员工基本信息
	 * @param employee
	 * @return
	 */
	@Override
	public String saveEmployeeInfo(Employee employee) {
		String result = "";
		try{
			int temp = getSqlSession().insert("com.selforder.employee.insertEmployee", employee);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "保存员工信息失败！");
		}
		return result;
	}
	
	/**
	 * 获取用户基本信息
	 * @param employee
	 * @return
	 */
	public Employee getEmployeeInfo(Employee employee){
		Employee emp = new Employee();
		try{
			emp = (Employee)getSqlSession().selectOne("com.selforder.employee.getEmployeeInfo",employee);
		}catch(Exception e){
			
		}
		return emp;
	}
	
	/**
	 * 获取下一个员工编码
	 * @param employee
	 * @return
	 */
	public int nextEmpCode(String businessid){
		int nextcode = -1;
		try{
			String maxCode = getSqlSession().selectOne("com.selforder.employee.getMaxEmpCode",businessid).toString();
			if(maxCode != null  && !"".equals(maxCode)){
				nextcode = Integer.parseInt(maxCode)+1;
			}else{
				nextcode = 0;
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return nextcode;
	}
	
	/**
	 * 修改员工密码
	 * @param employee
	 * @return
	 */
	public int updatePassword(Employee employee){
		int result = -1;
		try{
			result = getSqlSession().update("com.selforder.employee.updatePassword", employee);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return result;
	}
	
	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	public int updateEmpInfo(Employee employee){
		int result = -1;
		try{
			result = getSqlSession().update("com.selforder.employee.updateEmployee", employee);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return result;
	}
	
	/**
	 * 获取员工列表
	 * @param employee
	 * @return
	 */
	public List<Employee> getEmployeeList(Employee employee){
		List<Employee> employeelist = new ArrayList<Employee>();
		try{
			employeelist = getSqlSession().selectList("com.selforder.employee.getEmployeeList", employee);
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeelist;
	}
	
	/**
	 * 获取列表统计数
	 * @param employee
	 * @return
	 */
	public int getEmployeeCount(Employee employee){
		int count = 0;
		try{
			count = (Integer)getSqlSession().selectOne("com.selforder.employee.getEmployeeCount", employee);
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询员工已关联权限
	 * @param employee
	 * @return
	 */
	public List<Role> getEmpRoles(Employee employee)throws Exception{
		return getSqlSession().selectList("com.selforder.employee.getEmpRoles", employee);
	}
}
