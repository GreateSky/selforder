package com.selforder.service;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Organization;
import com.selforder.bean.Resource;
import com.selforder.bean.Role;

/**
 * 组织架构管理Service
 * @author xingwanzhao
 *
 * 2016-6-24
 */
public interface OrganizationService {
	/**
	 * 保存组织架构
	 * @param organization
	 * @return
	 */
	public String insertOrganization(Organization  organization);
	
	/**
	 * 获取组织架构
	 * @param organization
	 * @return
	 */
	public String getOrganizationList4Tree(Organization  organization);
	
	/**
	 * 更新组织架构
	 * @param organization
	 * @return
	 */
	public String updateOrganization(Organization  organization);
}
