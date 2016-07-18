package com.selforder.action;

import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.greatesky.action.GreateSkyActionSupport;
import com.selforder.bean.Organization;
import com.selforder.bean.Shop;
import com.selforder.service.ShopService;

/**
 * 门店管理action
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class ShopAction extends GreateSkyActionSupport {
	private Shop shop;
	private ShopService shopService;
	public Shop getShop() {
		return shop;
	}
	public ShopService getShopService() {
		return shopService;
	}
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	/**
	 * 保存门店信息
	 * @return
	 */
	public String saveShop(){
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
			result = shopService.saveShop(shop);
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
	 * 获取门店列表
	 * @return
	 */
	public String getShopList(){
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
			if(null == shop){
				shop = new Shop();
			}
			shop.setPageSize(super.limit);
			shop.setPageStart(super.page);
			result = shopService.shopList(shop);
			System.out.println("获取门店列表========"+result);
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
	 * 获取门店详情
	 * @param Shop
	 * @return
	 */
	public String getShopInfo(){
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
			result = shopService.shopInfo(shop);
			System.out.println("获取门店详情========"+result);
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
	 * 更新门店详情
	 * @param Shop
	 * @return
	 */
	public String updateShop(){
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
			result = shopService.updateShop(shop);
			System.out.println("更新门店详情========"+result);
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
	 * 删除门店
	 * @param Shop
	 * @return
	 */
	public String delShop(){
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
			result = shopService.delShop(shop);
			System.out.println("删除门店========"+result);
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
