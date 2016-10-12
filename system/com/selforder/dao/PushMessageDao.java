package com.selforder.dao;

import java.util.List;
import java.util.Map;

public interface PushMessageDao {
	/**
	 * 根据权限编码和商户ID获取该权限下的员工
	 * @param param 必须参数weid：商户ID，role_code：权限编码
	 * @return
	 */
	public List<Map<String,String>> emplist(Map param)throws Exception;
}
