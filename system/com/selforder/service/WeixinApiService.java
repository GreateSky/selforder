package com.selforder.service;

/**
 * 微信api服务类
 * @author xingwanzhao
 *
 * 2016-9-6
 */
public interface WeixinApiService {
	/**
	 * 获取微信服务号AccessToken
	 * @param weid
	 * @return
	 */
	public String getAccessToken(String weid);
}
