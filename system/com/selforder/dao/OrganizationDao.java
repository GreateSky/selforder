package com.selforder.dao;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Organization;
import com.selforder.bean.Resource;
import com.selforder.bean.Role;

/**
 * 组织机构管理Dao
 * @author xingwanzhao
 *
 * 2016-6-24
 */
public interface OrganizationDao {
	/**
	 * 保存组织架构
	 * @param organization
	 * @return
	 */
	public int insertOrganization(Organization  organization);
	
	/**
	 * 获取组织架构
	 * @param organization
	 * @return
	 */
	public List<Organization> getOrganizationList4Tree(Organization  organization);
	
	/**
	 * 更新组织架构
	 * @param organization
	 * @return
	 */
	public int updateOrganization(Organization  organization);
}
