package com.automobile.action;

import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.automobile.bean.BizDict;
import com.automobile.service.BizDictService;
import com.automobile.util.JsonResultUtil;
import com.greatesky.action.GreateSkyActionSupport;
/**
 * 业务字典管理类
 * @author xingwanzhao
 *
 * 2016-3-25
 */
public class BizDictAction extends GreateSkyActionSupport {
	private BizDict bizdict;
	private Map resultMap;
	private BizDictService bizDictService;
	public BizDictService getBizDictService() {
		return bizDictService;
	}
	public void setBizDictService(BizDictService bizDictService) {
		this.bizDictService = bizDictService;
	}
	public BizDict getBizdict() {
		return bizdict;
	}
	public void setBizdict(BizDict bizdict) {
		this.bizdict = bizdict;
	}
	public Map getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}
	
	/**
	 * 获取业务字典列表
	 * @return
	 */
	public String bizDictList(){
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
			List<Map> bizDictList = bizDictService.getBizDictList(bizdict);
			String resultstr = JsonResultUtil.ArrayListToJsonStr(bizDictList);
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
	 * 保存业务字典
	 * @return
	 */
	public String insertBizDict(){
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
			result = bizDictService.insertBizDict(bizdict);
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
	 * 更新业务字典
	 * @return
	 */
	public	String updateBizDict(){
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
			result = bizDictService.updateBizDict(bizdict);
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
