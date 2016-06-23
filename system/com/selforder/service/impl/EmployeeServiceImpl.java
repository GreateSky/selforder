package com.selforder.service.impl;

import com.selforder.bean.Employee;
import com.selforder.dao.EmployeeDao;
import com.selforder.service.EmployeeService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.Tools;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	/**
	 * 保存员工基本信息
	 * @param employee
	 * @return
	 */
	@Override
	public String saveEmployeeInfo(Employee employee) {
		// TODO Auto-generated method stub
		employee.setCrter(new Context().getLoginUserInfo().getCode());
		employee.setPassword(Tools.MD5(employee.getPassword()+"{"+employee.getLoginname()+"}"));
		return employeeDao.saveEmployeeInfo(employee);
	}
	
	/**
	 * 修改员工密码
	 * @param employee
	 * @return
	 */
	public String updatePassword(Employee employee){
		String result = "";
		employee.setEmpid(new Context().getLoginUserInfo().getEmpid());
		employee.setOpter(new Context().getLoginUserInfo().getCode());
		employee.setLoginname(new Context().getLoginUserInfo().getLoginname());
		System.out.println(employee.getPassword()+"{"+employee.getLoginname()+"}");
		employee.setPassword(Tools.MD5(employee.getPassword()+"{"+employee.getLoginname()+"}"));
		//判断原始用户名密码准确性
		Employee temp_emp = employeeDao.getEmployeeInfo(employee);
		if(null == temp_emp){
			return JsonResultUtil.getJsonResult(-1, "fail", "原始密码错误!");
		}else{
			employee.setPassword(Tools.MD5(employee.getNewpassword()+"{"+employee.getLoginname()+"}"));
			int updatepassword = employeeDao.updatePassword(employee);
			if(updatepassword > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "密码修改成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "密码修改失败!");
			}
		}
		return result;
	}
	
	/**
	 * 获取用户基本信息
	 * @param employee
	 * @return
	 */
	public String getEmployeeInfo(Employee employee){
		String result = "";
		if(null != employee){
			Employee temp = employeeDao.getEmployeeInfo(employee);
			result = JsonResultUtil.getJsonResult(0, "success","查询成功！", temp);
		}else{
			result = JsonResultUtil.getJsonResult(-1, "fail", "获取用户基本信息参数为空！");
		}
		return result;
	}
	
	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	public String updateEmpInfo(Employee employee){
		String result = "";
		if(null != employee){
			employee.setOpter(new Context().getLoginUserInfo().getCode());
			int temp = employeeDao.updateEmpInfo(employee);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success","更新成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail","更新失败！");
			}
		}else{
			result = JsonResultUtil.getJsonResult(-1, "fail", "参数为空！");
		}
		return result;
	}

}
