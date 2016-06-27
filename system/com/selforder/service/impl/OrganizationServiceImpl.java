package com.selforder.service.impl;

import java.util.List;

import com.selforder.bean.Organization;
import com.selforder.dao.OrganizationDao;
import com.selforder.service.OrganizationService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

public class OrganizationServiceImpl implements OrganizationService {
	private OrganizationDao organizationDao;
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/**
	 * 保存组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public String insertOrganization(Organization organization) {
		String result = "";
		try{
			//判断参数
			if(organization == null ){
				return JsonResultUtil.getJsonResult(-1, "fail", "新增组织架构参数异常！");
			}
			String bid = new Context().getLoginUserInfo().getBid();
			String crter = new Context().getLoginUserInfo().getCode();
			organization.setBid(bid);
			organization.setCrter(crter);
			int temp = organizationDao.insertOrganization(organization);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "保存异常！");
		}
		return result;
	}

	/**
	 * 获取组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public String getOrganizationList4Tree(Organization organization) {
		String result = "";
		try{
			String bid = new Context().getLoginUserInfo().getBid();
			organization.setBid(bid);
			if("".equals(organization.getParentid())){
				organization.setLevel(organization.getLevel()+1);
			}
			organization.setParentid(organization.getOid());
			organization.setOid("");
			
			List<Organization> organizationList = organizationDao.getOrganizationList4Tree(organization);
			if(null != organizationList && organizationList.size() > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "获取成功", organizationList);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "获取失败");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "获取失败");
		}
		return result;
	}

	/**
	 * 更新组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public String updateOrganization(Organization organization) {
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			organization.setOpter(opter);
			int temp = organizationDao.updateOrganization(organization);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1, "fail", "操作异常！");
		}
		return result;
	}

}
