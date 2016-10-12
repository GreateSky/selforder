package com.selforder.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.dao.PushMessageDao;

public class PushMessageDaoImpl extends SqlSessionDaoSupport implements PushMessageDao {
	
	/**
	 * 根据权限编码和商户ID获取该权限下的员工
	 * @param param 必须参数weid：商户ID，role_code：权限编码
	 * @return
	 */
	@Override
	public List<Map<String, String>> emplist(Map param)throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("com.selforder.pushmessage.getEmpsByRoleAndWeid", param);
	}
	
}
