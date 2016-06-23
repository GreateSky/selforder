package com.greatesky.dao.impl;

import java.io.IOException;
import java.io.Reader;

import javax.annotation.Resources;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.greatesky.bean.UserInfo;
import com.greatesky.dao.LoginDao;

public class LoginDaoImpl extends SqlSessionDaoSupport implements LoginDao {
	
	@Override
	/**
	 * 用户登录服务接口
	 * @param userinfo
	 * @return
	 */
	public UserInfo UserLogin(UserInfo userinfo){
		// TODO Auto-generated method stub
		try{
			if(userinfo != null){
				userinfo = (UserInfo)getSqlSession().selectOne("com.greatesky.system.getUserInfo", userinfo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userinfo;
	}

}
