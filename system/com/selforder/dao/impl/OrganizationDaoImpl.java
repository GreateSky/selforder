package com.selforder.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Organization;
import com.selforder.dao.OrganizationDao;

public class OrganizationDaoImpl extends SqlSessionDaoSupport implements OrganizationDao {
	/**
	 * 保存组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public int insertOrganization(Organization organization) {
		// TODO Auto-generated method stub
		int temp = getSqlSession().insert("com.selforder.organization.insertOrganization", organization);
		return temp;
	}

	/**
	 * 获取组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public List<Organization> getOrganizationList4Tree(Organization organization) {
		// TODO Auto-generated method stub
	
		return getSqlSession().selectList("com.selforder.organization.getOrganizationList4Tree", organization);
	}

	/**
	 * 更新组织架构
	 * @param organization
	 * @return
	 */
	@Override
	public int updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return getSqlSession().update("com.selforder.organization.updateOrganization", organization);
	}

}
