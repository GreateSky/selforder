package com.selforder.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Business;
import com.selforder.dao.BusinessDao;
import com.selforder.util.JsonResultUtil;

/**
 * 商户管理接口实现
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class BusinessDaoImpl extends SqlSessionDaoSupport implements BusinessDao {

	/**
	 * 保存商户信息
	 * @param business
	 * @return
	 */
	@Override
	public String saveBusiness(Business business) {
		String result = "";
		try{
			int temp = getSqlSession().insert("com.selforder.business.insertBusiness", business);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "保存商户信息失败！");
		}
		return result;
	}
	
	/**
	 * 获取商户列表
	 * @param business
	 * @return
	 */
	public List<Business> businessList(Business business){
		List<Business> businesslist = new ArrayList<Business>();
		try{
			businesslist = getSqlSession().selectList("com.selforder.business.getBusinessList", business);
		}catch(Exception e){
			e.printStackTrace();
		}
		return businesslist;
	}
	
	/**
	 * 查询商户统计数
	 * @param business
	 * @return
	 */
	public int getBusinessCount(Business business){
		int count = 0;
		try{
			count = (Integer)getSqlSession().selectOne("com.selforder.business.getBusinessCount", business);
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 获取商户详情
	 * @param business
	 * @return
	 */
	public Business businessInfo(Business business){
		Business businessinfo = new Business();
		try{
			if(business != null){
				businessinfo = (Business)getSqlSession().selectOne("com.selforder.business.getBusinessInfo", business);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return businessinfo;
	}
	
	/**
	 * 更新商户基本信息
	 * @param business
	 * @return
	 */
	public int updateBusiness(Business business){
		int result = -1;
		try{
			result = getSqlSession().update("com.selforder.business.updateBusiness", business);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
