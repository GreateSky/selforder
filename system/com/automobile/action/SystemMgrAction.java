package com.automobile.action;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.automobile.bean.Parts;
import com.automobile.bean.WeiXin;
import com.automobile.service.SystemMgrService;
import com.automobile.util.JsonResultUtil;
import com.greatesky.action.GreateSkyActionSupport;

/**
 * 系统基础维护功能
 * @author xingwanzhao
 *
 * 2016-4-2
 */
public class SystemMgrAction extends GreateSkyActionSupport {
	private static final long serialVersionUID = 243431721841890220L;
	private SystemMgrService systemMgrService;
	private Parts parts;
	private WeiXin weixin;

	public WeiXin getWeixin() {
		return weixin;
	}

	public void setWeixin(WeiXin weixin) {
		this.weixin = weixin;
	}

	public Parts getParts() {
		return parts;
	}

	public void setParts(Parts parts) {
		this.parts = parts;
	}

	public SystemMgrService getSystemMgrService() {
		return systemMgrService;
	}

	public void setSystemMgrService(SystemMgrService systemMgrService) {
		this.systemMgrService = systemMgrService;
	}
	
	/**
	 * 获取业务字典列表
	 * @return
	 */
	public String partsList(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			List<Map> partsList = systemMgrService.getPartsList(parts);
			Map datas = new HashMap();
			datas.put("rows", partsList);
			datas.put("total", 10);
			String resultstr = JsonResultUtil.MapToJsonStr(datas);
			out.write(resultstr);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 保存配件信息
	 * @return
	 */
	public String insertParts(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = systemMgrService.insertParts(parts);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 更新配件
	 * @return
	 */
	public	String updateParts(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = systemMgrService.updateParts(parts);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 获取微信JsApi信息
	 * @return
	 */
	public String getJsApiInfo(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			Map<String,String> res = systemMgrService.getJsApiInfo(weixin.getUrl());
			result = JsonResultUtil.MapToJsonStr(res);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
}
