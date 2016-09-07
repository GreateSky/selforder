package com.selforder.dao;

import com.selforder.bean.AccessToken;

public interface WeixinApiDao {
	/**
	 * 获取微信accesstoken
	 * @param weid
	 * @return
	 * @throws Exception
	 */
	public AccessToken getAccessToken(String weid) throws Exception;
	
	/**
	 * 插入token
	 * @param accesstoken
	 * @return
	 * @throws Exception
	 */
	public int insertToken(AccessToken accesstoken) throws Exception;
	
	/**
	 * 更新token
	 * @param weid
	 * @return
	 * @throws Exception
	 */
	public int updateToken(String weid)throws Exception;
}
