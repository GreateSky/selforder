package com.greatesky.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.greatesky.bean.UserInfo;
import com.greatesky.dao.LoginDao;
import com.greatesky.service.LoginService;

public class LoginServiceImpl implements LoginService {
	private LoginDao logindao;

	public LoginDao getLogindao() {
		return logindao;
	}

	public void setLogindao(LoginDao logindao) {
		this.logindao = logindao;
	}

	@Override
	/**
	 * 用户登录服务接口
	 * @param userinfo
	 * @return
	 */
	public UserInfo UserLogin(UserInfo userinfo) {
		return logindao.UserLogin(userinfo);
	}
}
