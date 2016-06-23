package com.selforder.security.context;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.selforder.bean.Employee;
import com.selforder.bean.UserInfo;
import com.selforder.dao.EmployeeDao;

public class MyUserDetailsService implements UserDetailsService  {
	private EmployeeDao employeeDao;
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	//登录验证
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		System.out.println("show login name================："+name+" ");
		if(null == name || "".equals(name)){
			return null;
		}
		//根据登录名获取登录账户的基本信息
		Employee employee = new Employee();
		employee.setLoginname(name);
		Employee emp = employeeDao.getEmployeeInfo(employee);
		//判断是否有当前登录信息
		if(null != emp){
			emp.setRole("ROLE_LOGIN,ROLE_ADMIN,ROLE_BUSINESS");//暂未提供权限功能写固定值
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
	
	public static void main(String[] args) {
		int[] arr = new int[]{8,2,1,0,3};
		int[] index = new int[]{2,0,3,2,4,0,1,3,2,3,3};
		String tel = "";
		for(int i:index){
			tel += arr[i];
		}
		System.out.println(tel);
	}


}
