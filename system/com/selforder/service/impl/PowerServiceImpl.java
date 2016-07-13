package com.selforder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.selforder.bean.Business;
import com.selforder.bean.Resource;
import com.selforder.bean.Role;
import com.selforder.dao.PowerDao;
import com.selforder.service.PowerService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.Uuid;

/**
 * 权限管理服务
 * @author xingwanzhao
 *
 * 2016-6-2
 */
public class PowerServiceImpl implements PowerService {
	private PowerDao powerDao;
	public PowerDao getPowerDao() {
		return powerDao;
	}
	public void setPowerDao(PowerDao powerDao) {
		this.powerDao = powerDao;
	}
	
	//-------------------资源管理--------------------------
	/**
	 * 保存资源信息
	 * @param business
	 * @return
	 */
	@Override
	public String saveResource(Resource resource) {
		resource.setRid(Uuid.getUuid());
		resource.setCrter(new Context().getLoginUserInfo().getCode());
		return powerDao.saveResource(resource);
	}
	@Override
	public String resourceList(Resource resource) {
		String result = "";
		Map resultMap = new HashMap();
		try{
			//查询资源列表
			List<Resource> resourcelist = powerDao.resourceList(resource);
			if(resourcelist != null && resourcelist.size()>0){
				//查询统计数
				int count = powerDao.getResourceCount(resource);
				resultMap.put("rows", resourcelist);
				resultMap.put("total", count);
				result = JsonResultUtil.MapToJsonStr(resultMap);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return result;
	}
	
	@Override
	public String resourceInfo(Resource resource) {
		String result = "";
		try{
			Resource resourceinfo = powerDao.resourceInfo(resource);
			if(resourceinfo != null){
				result = JsonResultUtil.getJsonResult(0, "success", "", resourceinfo);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "未查询到资源详情");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public String updateResource(Resource resource) {
		String result = "";
		if(resource != null && !"".equals(resource.getRid())){
			String optcode = new Context().getLoginUserInfo().getCode();
			int updateRes = powerDao.updateResource(resource);
			if(updateRes <=0){
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新资源信息失败!");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "更新资源信息成功!");
			}
		}else{
			result = JsonResultUtil.getJsonResult(-1, "fail", "更新资源信息参数异常!");
		}
		return result;
	}
	
	/**
	 * 获取待选择资源列表（除去已选择和已关联的资源）
	 * @param map
	 * @return
	 */
	public String selectResourceList(Role role){
		String result = "";
		//组装查询参数
		Map map = new HashMap();
		map.put("role_id",role.getRid());
		map.put("selectRole", role.getResourceid());
		map.put("keyword", role.getKeyword());
		List<Resource> resourcelist = powerDao.selectResourceList(map);
		result = JsonResultUtil.getJsonResult(0, "success", "查询成功", resourcelist);
		return result;
	}
	
	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	public String delRole(Role role){
		/**
		 * 逻辑过程：
		 * 	清除角色与资源关联关系
		 * 	清除角色
		 */
		String result = "";
		String usercode = new Context().getLoginUserInfo().getCode();
		try{
			//清除角色与资源关联关系
			int delRef = powerDao.delRoleResource(role.getRid());
			if(delRef<0){
				result = JsonResultUtil.getJsonResult(-1, "fail", "删除权限与资源关联关系失败!");
			}else{
				//清除角色
				role.setDeleted(1);
				role.setOpter(usercode);
				int delrole = powerDao.updateRole(role);
				if(delrole < 0){
					result = JsonResultUtil.getJsonResult(-1, "fail", "删除权限失败!");
				}else{
					result = JsonResultUtil.getJsonResult(0, "success", "操作成功!");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作失败!");
		}
		return result;
	}
	//-------------------角色管理------------------------------------
	/**
	 * 保存角色信息
	 * @param business
	 * @return
	 */
	public String saveRole(Role role){
		String result = "";
		String uuid = Uuid.getUuid();
		role.setRid(uuid);
		role.setCrter(new Context().getLoginUserInfo().getCode());
		//保存角色信息
		String saveroleRel = powerDao.saveRole(role);
		if(saveroleRel.indexOf("fail") != -1){
			return saveroleRel;
		}
		//批量保存角色与资源关联关系
		String resourceids = role.getResourceid();
		if(null != resourceids && !"".equals(resourceids)){
			String resourceidArray [] = resourceids.split(",");
			List<Map> reflist = new ArrayList<Map>();
			for(int i=0;i<resourceidArray.length;i++){
				Map map = new HashMap();
				map.put("role_id", role.getRid());
				map.put("resource_id",resourceidArray[i]);
				map.put("crter",new Context().getLoginUserInfo().getCode());
				map.put("opter",new Context().getLoginUserInfo().getCode());
				reflist.add(map);
			}
			result = powerDao.saveRoleResourceRef(reflist);
		}else{
			result = JsonResultUtil.getJsonResult(0, "success", "操作成功！");
		}
		return result;
	}
	
	/**
	 * 更新角色基本信息
	 * @param business
	 * @return
	 */
	public String updateRole(Role role){
		String result = "";
		//更新角色信息
		if(role != null && !"".equals(role.getRid())){
			role.setOpter(new Context().getLoginUserInfo().getCode());
			int updateRes = powerDao.updateRole(role);
			if(updateRes <=0){
				return result = JsonResultUtil.getJsonResult(-1, "fail", "更新角色信息失败!");
			}
			//清除角色与资源关联关系
			int delref = powerDao.physicsDeleted(role.getRid());
			//批量保存角色与资源关联关系
			String resourceids = role.getResourceid();
			if(null != resourceids && !"".equals(resourceids)){
				String resourceidArray [] = resourceids.split(",");
				List<Map> reflist = new ArrayList<Map>();
				for(int i=0;i<resourceidArray.length;i++){
					Map map = new HashMap();
					map.put("role_id", role.getRid());
					map.put("resource_id",resourceidArray[i]);
					map.put("crter",new Context().getLoginUserInfo().getCode());
					map.put("opter",new Context().getLoginUserInfo().getCode());
					reflist.add(map);
				}
				result = powerDao.saveRoleResourceRef(reflist);
				
			}
		}else{
			result = JsonResultUtil.getJsonResult(-1, "fail", "更新角色信息参数异常!");
		}
		return result;
	}
	
	/**
	 * 获取角色详情
	 * @param business
	 * @return
	 */
	public String roleInfo(Role role){
		String result = "";
		try{
			//获取资源详情
			Role roleInfo= powerDao.roleInfo(role);
			Map map = new HashMap();
			if(roleInfo != null){
				//获取角色与资源关联关系
				map.put("rid",role.getRid());
				List<Map> list = powerDao.getRoleResouceRefList(map);
				Map data = new HashMap();
				data.put("roleinfo", roleInfo);
				data.put("reflist",list);
				result = JsonResultUtil.getJsonResult(0, "success", "查询成功！", data);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "未查询到资源详情");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取角色列表
	 * @param business
	 * @return
	 */
	public String roleList(Role role){
		String result = "";
		Map resultMap = new HashMap();
		try{
			//查询资源列表
			List<Role> roleList = powerDao.roleList(role);
			//查询统计数
			int count = powerDao.getRoleCount(role);
			resultMap.put("rows", roleList);
			resultMap.put("total", count);
			result = JsonResultUtil.MapToJsonStr(resultMap);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return result;
	}
	
	/**
	 * 删除角色与资源关联关系
	 * @param refid
	 * @return
	 */
	public String delRolResourceRef(String refid){
		String result = "";
		int temp = powerDao.delRolResourceRef(refid);
		if(temp > 0){
			result = JsonResultUtil.getJsonResult(0, "success","操作成功！");
		}else{
			result = JsonResultUtil.getJsonResult(-1, "fail", "操作失败！");
		}
		return result;
	}
	
	//-----------------------商户与角色（权限）关联关系管理----------------------------------
	
	/**
	 * 查询所有商户的授权信息
	 * @param business
	 * @return
	 */
	public String businessList(Business business){
		String result = "";
		Map resultMap = new HashMap();
		try{
			//查询资源列表
			List<Business> businesslist = powerDao.businessList(business);
			if(businesslist != null && businesslist.size()>0){
				//查询统计数
				int count = powerDao.getBusinessListCount(business);
				resultMap.put("rows", businesslist);
				resultMap.put("total", count);
				result = JsonResultUtil.MapToJsonStr(resultMap);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return result;
	}
	
	/**
	 * 根据商户获取商户已授权权限
	 * @param business
	 * @return
	 */
	public String getBusinessRoleInfo(Business business){
		String result = "";
		try{
			List<Business> businessRoleList = powerDao.getBusinessRoleInfo(business);
			if(null == businessRoleList || businessRoleList.size()<=0){
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据!");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "查询成功!", businessRoleList);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 批量保存商户与权限关联关系
	 * @param businessList
	 * @return
	 */
	public String insertBusRoleRef(List<Business> businessList){
		String result = "";
		try{
			int temp = powerDao.insertBusRoleRef(businessList);
			if(temp < 0){
				result = JsonResultUtil.getJsonResult(-1, "fail", "无操作数据!");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 删除商户与权限的关联关系
	 * @param business
	 * @return
	 */
	public String deletedBusRoleRef(Business business){
		/**
		 * 逻辑处理过程：
		 * 	1、删除商户下部门与被取消权限的关联关系
		 *  2、删除商户与权限的关联关系
		 */
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			business.setOpter(opter);
			int temp = powerDao.deletedOrgRoleRef(business);
			//先删除商户下部门与被取消权限的关联关系
			if(temp < 0){
				result = JsonResultUtil.getJsonResult(-1, "fail", "取消商户下部门与权限关联关系失败!");
			}else{
				//再删除商户与权限的关联关系
				temp = powerDao.deletedBusRoleRef(business);
				if(temp < 0){
					result = JsonResultUtil.getJsonResult(-1, "fail", "取消商户与权限的关联关系失败!");
				}else{
					result = JsonResultUtil.getJsonResult(0, "success", "操作成功!");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 根据商户获取商户未授权权限
	 * @param business
	 * @return
	 */
	public String getBusNoRoleList(Business business){
		String result = "";
		try{
			List<Role> rolelist = powerDao.getBusNoRoleList(business);
			if(null != rolelist && rolelist.size()>0){
				result = JsonResultUtil.getJsonResult(0, "success", "查询成功!",rolelist);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据!",rolelist);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
}
