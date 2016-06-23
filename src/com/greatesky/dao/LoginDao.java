package com.greatesky.dao;

import com.greatesky.bean.UserInfo;

public interface LoginDao {
	/**
	 * 用户登录服务接口
	 * @param userinfo
	 * @return
	 */
	public UserInfo UserLogin(UserInfo userinfo);
}
