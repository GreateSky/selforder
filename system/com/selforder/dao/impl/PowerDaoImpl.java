package com.selforder.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Business;
import com.selforder.bean.Resource;
import com.selforder.bean.Role;
import com.selforder.dao.PowerDao;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

public class PowerDaoImpl extends SqlSessionDaoSupport implements PowerDao {

	@Override
	public String saveResource(Resource resource) {
		String result = "";
		try{
			int temp = getSqlSession().insert("com.selforder.power.insertResource", resource);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "保存资源信息失败！");
		}
		return result;
	}
	
	/**
	 * 获取资源列表
	 * @param business
	 * @return
	 */
	public List<Resource> resourceList(Resource resource){
		List<Resource> resourcelist = new ArrayList<Resource>();
		try{
			resourcelist = getSqlSession().selectList("com.selforder.power.getResourceList", resource);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resourcelist;
	}
	
	/**
	 * 查询资源统计数
	 * @param business
	 * @return
	 */
	public int getResourceCount(Resource resource){
		int count = 0;
		try{
			count = (Integer)getSqlSession().selectOne("com.selforder.power.getResourceCount", resource);
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 获取资源详情
	 * @param business
	 * @return
	 */
	public Resource resourceInfo(Resource resource){
		Resource resourceinfo = new Resource();
		try{
			if(resource != null){
				resourceinfo = (Resource)getSqlSession().selectOne("com.selforder.power.getResourceInfo", resource);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resourceinfo;
	}
	
	/**
	 * 更新资源基本信息
	 * @param business
	 * @return
	 */
	public int updateResource(Resource resource){
		int result = -1;
		try{
			result = getSqlSession().update("com.selforder.power.updateResource", resource);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取待选择资源列表（除去已选择和已关联的资源）
	 * @param map
	 * @return
	 */
	public List<Resource> selectResourceList(Map map){
		List<Resource> resourcelist = new ArrayList<Resource>();
		try{
			resourcelist = getSqlSession().selectList("com.selforder.power.selectResourceList", map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resourcelist;
	}
	
	//------------------------------角色管理----------------------------
	/**
	 * 保存角色信息
	 * @param business
	 * @return
	 */
	public String saveRole(Role role){
		String result = "";
		try{
			int temp = getSqlSession().insert("com.selforder.power.insertRole", role);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
		}
		return result;
	}
	
	/**
	 * 更新角色基本信息
	 * @param business
	 * @return
	 */
	public int updateRole(Role role){
		int result = -1;
		try{
			result = getSqlSession().update("com.selforder.power.updateRole", role);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取角色详情
	 * @param business
	 * @return
	 */
	public Role roleInfo(Role role){
		Role roleinfo = new Role();
		try{
			if(role != null){
				roleinfo = (Role)getSqlSession().selectOne("com.selforder.power.getRoleInfo", role);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return roleinfo;
	}
	
	/**
	 * 获取角色列表
	 * @param business
	 * @return
	 */
	public List<Role> roleList(Role role){
		List<Role> rolelist = new ArrayList<Role>();
		try{
			rolelist = getSqlSession().selectList("com.selforder.power.getRoleList", role);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rolelist;
	}
	
	/**
	 * 查询角色统计数
	 * @param business
	 * @return
	 */
	public int getRoleCount(Role role){
		int count = 0;
		try{
			count = (Integer)getSqlSession().selectOne("com.selforder.power.getRoleCount", role);
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 根据角色ID删除关联的资源
	 * @param rid
	 * @return
	 */
	public int delRoleResource(String rid){
		return getSqlSession().update("com.selforder.power.delRoleResource",rid);
	}

	
	//-----------------------角色与资源关联关系管理----------------------------------
	
	/**
	 * 保存角色与资源关联关系
	 */
	public String saveRoleResourceRef(List<Map> refMap){
		String result = "";
		try{
			int temp = getSqlSession().insert("com.selforder.power.insertRoleResourceRef", refMap);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
		}
		return result;
	}
	
	/**
	 * 获取角色与资源关联关系列表
	 * @param map
	 * @return
	 */
	public List<Map> getRoleResouceRefList(Map map){
		List<Map> list = new ArrayList<Map>();
		try{
			list = getSqlSession().selectList("com.selforder.power.getRolResourceList", map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 更新角色与资源关联关系
	 * @param map
	 * @return
	 */
	public String updateRoleResourceRef(Map map){
		String result = "";
		try{
			int temp = getSqlSession().update("com.selforder.power.updateRoleResourceRef", map);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "更新失败！");
		}
		return result;
	}
	
	/**
	 * 根据role_id删除所关联的资源信息
	 * @param role_id
	 * @return
	 */
	public int physicsDeleted(String role_id){
		int resurt = -1;
		try{
			resurt = getSqlSession().delete("com.selforder.power.deltedRef", role_id);
			return resurt;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 删除角色与资源关联关系
	 * @param refid
	 * @return
	 */
	public int delRolResourceRef(String refid){
		int resurt = -1;
		try{
			resurt = getSqlSession().delete("com.selforder.power.deltedRefOne", refid);
			return resurt;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 获取所有角色与资源信息(用于系统注册)
	 * @return
	 */
	public List<Map> allRoleResourceList(){
		List<Map> reflist = getSqlSession().selectList("com.selforder.power.getAllRolResourceList");
		return reflist;
	}
	
	//-----------------------商户与角色（权限）关联关系管理----------------------------------
	
	/**
	 * 查询所有商户的授权信息
	 * @param business
	 * @return
	 */
	public List<Business> businessList(Business business)throws Exception{
		List<Business> businessList = new ArrayList<Business>();
		try{
			businessList = getSqlSession().selectList("com.selforder.power.getBusinessRoleList", business);
		}catch(Exception e){
			e.printStackTrace();
		}
		return businessList;
	}
	
	/**
	 * 查询所有商户的授权信息统计数
	 * @param business
	 * @return
	 */
	public int getBusinessListCount(Business business)throws Exception{
		return (Integer)getSqlSession().selectOne("com.selforder.power.getBusinessRoleCount", business);
	}
	
	/**
	 * 根据商户获取商户已授权权限
	 * @param business
	 * @return
	 */
	public List<Business> getBusinessRoleInfo(Business business)throws Exception{
		return (List<Business>)getSqlSession().selectList("com.selforder.power.getBusinessRoleInfo", business);
	}
	
	/**
	 * 批量保存商户与权限关联关系
	 * @param businessList
	 * @return
	 */
	public int insertBusRoleRef(List<Business> businessList)throws Exception{
		return (Integer)getSqlSession().insert("com.selforder.power.insertBusRoleRef", businessList);
	}
	
	/**
	 * 删除商户与权限的关联关系
	 * @param business
	 * @return
	 */
	public int deletedBusRoleRef(Business business)throws Exception{
		return (Integer)getSqlSession().update("com.selforder.power.deletedBusRoleRef", business);
	}
	
	/**
	 * 删除商户下部门已关联权限的关联关系
	 * @param business
	 * @return
	 */
	public int deletedOrgRoleRef(Business business)throws Exception{
		return (Integer)getSqlSession().update("com.selforder.power.deletedOrgRoleRef", business);
	}
	
	/**
	 * 根据商户获取商户未授权权限
	 * @param business
	 * @return
	 */
	public List<Role> getBusNoRoleList(Business business)throws Exception{
		return getSqlSession().selectList("com.selforder.power.getBusinessNoRole", business);
	}
	
	/**
	 * 根据所有权限
	 * @return
	 */
	public List<Role> getAllRoles()throws Exception{
		return getSqlSession().selectList("com.selforder.power.getAllRoles", null);
	}
}
