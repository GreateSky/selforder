package com.selforder.security.context;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.selforder.bean.Employee;
import com.selforder.bean.UserInfo;
import com.selforder.dao.EmployeeDao;
import com.selforder.service.EmployeeService;
import com.selforder.util.BeanToMapUtil;

public class MyUserDetailsService implements UserDetailsService  {
	private EmployeeService employeeService;
	private EmployeeDao employeeDao;
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	//登录验证
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException{
		System.out.println("show login name================："+name+" ");
		if(null == name || "".equals(name)){
			return null;
		}
		//根据登录名获取登录账户的基本信息
		Employee employee = new Employee();
		employee.setLoginname(name);
		String empinfo = employeeService.getEmployeeInfo(employee);
		if(empinfo.indexOf("succcess") != -1){//查询员工信息失败
			return null;
		}
		//將返回的json字符串转换成对象
		Gson gson = new Gson();
		HashMap res = gson.fromJson(empinfo, HashMap.class);
		LinkedTreeMap map = (LinkedTreeMap)res.get("data");
		//String empStr = empinfo.substring(empinfo.indexOf("data")+6,empinfo.length()-1);
		//System.out.println(empStr);
		//Map empMap = (Map)map.get("data");
		//Employee test  = gson.fromJson(empStr, Employee.class);
		Employee emp = new Employee();
		try {
			emp = (Employee)BeanToMapUtil.convertMap(Employee.class, map);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//判断是否有当前登录信息
		if(null != emp){
			/**
			 * 获取当前登录员工的权限列表
			 * 	 判断员工类型  运营人员、商户人员
			 * 		商户人员：查询商户人员的权限
			 * 		运营人员：查询运营人员的权限
			 */
			String type = emp.getType();//员工类型
			String empRoleStr = "";//已授权的权限字符串，多个权限用","隔开
			if("A".equals(type)){
				//运营人员
				empRoleStr = employeeService.getEmployeeRole4Admin(emp);
			}else if("B".equals(type) || "S".equals(type)){ //B.商户员工	S.门店员工
				//商户和门店员工
				empRoleStr = employeeService.getEmployeeRole(emp);//查询商户员工权限
			}
			String role = "";//员工绑定的权限
			if(null == empRoleStr || "".equals(empRoleStr) || empRoleStr.indexOf("success") == -1){
				role = "";
			}else{
				Gson roleGson = new Gson();
				Map roleMap = roleGson.fromJson(empRoleStr, new TypeToken<Map>(){}.getType());
				Map roles = (Map)roleMap.get("data");
				role = roles.get("role").toString();
			}
			emp.setRole(role);//为员工权限赋值
			int status = emp.getStatus();
			boolean enables = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			if(status == -1){//判断是否离职
				enables = false;
			}
			//配置权限列表
			Set<GrantedAuthority> grantedAuths=obtionGrantedAuthorities(emp.getRole());
			//封装成spring security的userInfo(扩展的User)
			UserInfo userinfo = new UserInfo(emp.getLoginname(), emp.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
			userinfo.setBid(emp.getBid());
			userinfo.setSid(emp.getSid());
			userinfo.setCode(emp.getEmpcode()+"");
			userinfo.setHeadimgurl(emp.getHeadimgurl());
			userinfo.setName(emp.getEmpname());
			userinfo.setOpenid(emp.getOpenid());
			userinfo.setPhone(emp.getPhone());
			userinfo.setSex(emp.getSex());
			userinfo.setStatus(emp.getStatus());
			userinfo.setType(emp.getType());
			userinfo.setWechatid(emp.getWechatid());
			userinfo.setEmpid(emp.getEmpid());
			userinfo.setLoginname(emp.getLoginname());
			userinfo.setAppid(emp.getAppid());
			return userinfo;
			
		}else{
			return null;
		}
	}
	
	/**
	 * 根据权限字符串配置权限列表
	 * @param roles
	 * @return
	 */
	public Set<GrantedAuthority> obtionGrantedAuthorities(String  roles){
		Set<GrantedAuthority> authSet=new HashSet<GrantedAuthority>();
		String[] rolesArray=roles.split(",");
		for(String role:rolesArray){
			authSet.add(new GrantedAuthorityImpl(role));
		}
		return authSet;
	}
}
