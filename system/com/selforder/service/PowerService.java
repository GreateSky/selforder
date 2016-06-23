package com.selforder.service;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Resource;
import com.selforder.bean.Role;

/**
 * 权限管理
 * @author xingwanzhao
 *
 * 2016-6-2
 */
public interface PowerService {
	
	//-------------------资源管理---------------------------------
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
	public String resourceList(Resource resource);
	
	/**
	 * 获取资源详情
	 * @param business
	 * @return
	 */
	public String resourceInfo(Resource resource);
	
	/**
	 * 更新资源基本信息
	 * @param business
	 * @return
	 */
	public String updateResource(Resource resource);
	
	/**
	 * 获取待选择资源列表（除去已选择和已关联的资源）
	 * @param map
	 * @return
	 */
	public String selectResourceList(Role role);
	
	//--------------------------角色管理----------------------------
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
	public String roleList(Role role);
	
	/**
	 * 获取角色详情
	 * @param business
	 * @return
	 */
	public String roleInfo(Role role);
	
	/**
	 * 更新角色基本信息
	 * @param business
	 * @return
	 */
	public String updateRole(Role role);
	
	/**
	 * 删除角色与资源关联关系
	 * @param refid
	 * @return
	 */
	public String delRolResourceRef(String refid);
}
