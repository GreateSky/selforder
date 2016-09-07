package com.selforder.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.AccessToken;
import com.selforder.dao.WeixinApiDao;

public class WeixinApiDaoImpl extends SqlSessionDaoSupport implements WeixinApiDao {

	//*****************************************token处理start***********************************
	/**
	 * 查询当前有效的accesstoken
	 * @param weid
	 * @return
	 * @throws Exception
	 */
	@Override
	public AccessToken getAccessToken(String weid) throws Exception {
		return (AccessToken)getSqlSession().selectOne("com.selforder.weixin.getAccesstoken", weid);
	}
	
	/**
	 * 插入token
	 * @param accesstoken
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertToken(AccessToken accesstoken) throws Exception{
		return getSqlSession().insert("com.selforder.weixin.insertAccessToken", accesstoken);
	}
	
	/**
	 * 更新token
	 * @param weid
	 * @return
	 * @throws Exception
	 */
	public int updateToken(String weid)throws Exception{
		return getSqlSession().update("com.selforder.weixin.updateToken", weid);
	}
	//*****************************************token处理end***********************************

}
