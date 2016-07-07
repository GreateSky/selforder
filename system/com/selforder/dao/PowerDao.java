package com.selforder.dao;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Business;
import com.selforder.bean.Resource;
import com.selforder.bean.Role;

/**
 * 权限管理dao
 * @author xingwanzhao
 *
 * 2016-6-2
 */
public interface PowerDao {
	
	//--------------资源管理----------------------------
	/**
	 * 保存资源信息
	 * @param business
	 * @return
	 */
	public String saveResource(Resource resource);
	
	/**
	 * 获取资源列表
	 * @param business
	 * @return
	 */
	public List<Resource> resourceList(Resource resource);
	
	/**
	 * 查询资源统计数
	 * @param business
	 * @return
	 */
	public int getResourceCount(Resource resource);
	
	/**
	 * 获取资源详情
	 * @param business
	 * @return
	 */
	public Resource resourceInfo(Resource resource);
	
	/**
	 * 更新资源基本信息
	 * @param business
	 * @return
	 */
	public int updateResource(Resource resource);
	
	/**
	 * 获取待选择资源列表（除去已选择和已关联的资源）
	 * @param map
	 * @return
	 */
	public List<Resource> selectResourceList(Map map);
	
	//-----------------------角色管理----------------------------------
	/**
	 * 保存角色信息
	 * @param business
	 * @return
	 */
	public String saveRole(Role role);
	
	/**
	 * 获取角色列表
	 * @param business
	 * @return
	 */
	public List<Role> roleList(Role role);
	
	/**
	 * 查询角色统计数
	 * @param business
	 * @return
	 */
	public int getRoleCount(Role role);
	
	/**
	 * 获取角色详情
	 * @param business
	 * @return
	 */
	public Role roleInfo(Role role);
	
	/**
	 * 更新角色基本信息
	 * @param business
	 * @return
	 */
	public int updateRole(Role role);
	
	//-----------------------角色与资源关联关系管理----------------------------------
	
	/**
	 * 保存角色与资源关联关系
	 */
	public String saveRoleResourceRef(List<Map> refMap);
	
	/**
	 * 获取角色与资源关联关系列表
	 * @param map
	 * @return
	 */
	public List<Map> getRoleResouceRefList(Map map);
	
	/**
	 * 更新角色与资源关联关系
	 * @param map
	 * @return
	 */
	public String updateRoleResourceRef(Map map);
	
	/**
	 * 根据role_id删除所关联的资源信息
	 * @param role_id
	 * @return
	 */
	public int physicsDeleted(String role_id);
	
	/**
	 * 删除角色与资源关联关系
	 * @param refid
	 * @return
	 */
	public int delRolResourceRef(String refid);
	
	/**
	 * 获取所有角色与资源信息(用于系统注册)
	 * @return
	 */
	public List<Map> allRoleResourceList();
	
	//-----------------------商户与角色（权限）关联关系管理----------------------------------

	/**
	 * 查询所有商户的授权信息
	 * @param business
	 * @return
	 */
	public List<Business> businessList(Business business)throws Exception;
	
	/**
	 * 查询所有商户的授权信息统计数
	 * @param business
	 * @return
	 */
	public int getBusinessListCount(Business business)throws Exception;
	
	/**
	 * 根据商户获取商户已授权权限
	 * @param business
	 * @return
	 */
	public List<Business> getBusinessRoleInfo(Business business)throws Exception;
	
	/**
	 * 批量保存商户与权限关联关系
	 * @param businessList
	 * @return
	 */
	public int insertBusRoleRef(List<Business> businessList)throws Exception;
	
	/**
	 * 删除商户与权限的关联关系
	 * @param business
	 * @return
	 */
	public int deletedBusRoleRef(Business business)throws Exception;
	
	/**
	 * 删除商户下部门已关联权限的关联关系
	 * @param business
	 * @return
	 */
	public int deletedOrgRoleRef(Business business)throws Exception;
	
	/**
	 * 根据商户获取商户未授权权限
	 * @param business
	 * @return
	 */
	public List<Role> getBusNoRoleList(Business business)throws Exception;
	
	
}
