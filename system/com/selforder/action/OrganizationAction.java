package com.selforder.action;

import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.greatesky.action.GreateSkyActionSupport;
import com.selforder.bean.Organization;
import com.selforder.bean.Resource;
import com.selforder.bean.Role;
import com.selforder.service.OrganizationService;
import com.selforder.service.PowerService;
/**
 * 组织架构管理action
 * @author xingwanzhao
 *
 * 2016-6-24
 */
public class OrganizationAction extends GreateSkyActionSupport {
	private static final long serialVersionUID = -2231221642896790264L;
	private Organization organization;
	private OrganizationService organizationService;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Organization getOrganization() {
		return organization;
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	/**
	 * 保存组织架构
	 * @return
	 */
	public String saveOrganization(){
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
			result = organizationService.insertOrganization(organization);
			System.out.println("保存组织架构结果================"+result);
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
	 * 获取组织架构列表
	 * @return
	 */
	public String getorganizationList4Tree(){
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
			if(null == organization){
				organization = new Organization();
			}
			organization.setPageSize(super.limit);
			organization.setPageStart(super.page);
			result = organizationService.getOrganizationList4Tree(organization);
			System.out.println("获取组织架构列表========"+result);
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
	 * 更新组织架构
	 * @param business
	 * @return
	 */
	public String updateOrganization(){
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
			result = organizationService.updateOrganization(organization);
			System.out.println("更新组织架构========"+result);
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
