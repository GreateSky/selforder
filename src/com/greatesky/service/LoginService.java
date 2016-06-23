package com.greatesky.service;

import com.greatesky.bean.UserInfo;

/**
 * 用户登录服务类接口
 * @author xingwanzhao
 *
 */
public interface LoginService {
	/**
	 * 用户登录服务接口
	 * @param userinfo
	 * @return
	 */
	public UserInfo UserLogin(UserInfo userinfo);
}
