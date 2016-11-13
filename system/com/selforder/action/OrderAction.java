package com.selforder.action;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.greatesky.action.GreateSkyActionSupport;
import com.selforder.bean.Order;
import com.selforder.bean.OrderDetail;
import com.selforder.service.OrderService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

/**
 * 订单管理action
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class OrderAction extends GreateSkyActionSupport {
	private Order order;
	private OrderDetail orderDetail;
	private OrderService orderService;
	private List<OrderDetail> orderDetailList;
	private List<OrderDetail> addDetailList;//新增订单明细集合
	private List<OrderDetail> updateDetailList;//修改订单明细集合
	public List<OrderDetail> getAddDetailList() {
		return addDetailList;
	}

	public List<OrderDetail> getUpdateDetailList() {
		return updateDetailList;
	}

	public List<OrderDetail> getDelOrdList() {
		return delOrdList;
	}

	public void setAddDetailList(List<OrderDetail> addDetailList) {
		this.addDetailList = addDetailList;
	}

	public void setUpdateDetailList(List<OrderDetail> updateDetailList) {
		this.updateDetailList = updateDetailList;
	}

	public void setDelOrdList(List<OrderDetail> delOrdList) {
		this.delOrdList = delOrdList;
	}

	private List<OrderDetail> delOrdList;//删除订单明细集合
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public Order getOrder() {
		return order;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * 保存订单信息
	 * @return
	 */
	public String insertOrder(){
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
			result = orderService.insertOrder(order, addDetailList);
			System.out.println("保存订单结果==================："+result);
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
	 * 获取订单列表
	 * @return
	 */
	public String getOrderList(){
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
			if(null == order){
				order = new Order();
			}
			order.setPageSize(super.limit);
			order.setPageStart(super.page);
			result = orderService.orderList(order);
			System.out.println("获取订单列表========"+result);
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
	 * 获取订单详情
	 * @return
	 */
	public String getOrderInfo(){
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
			result = orderService.orderInfo(order);
			System.out.println("获取订单详情========"+result);
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
	 * 更订单店详情
	 * @param Shop
	 * @return
	 */
	public String updateOrder(){
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
			Map<String,List<OrderDetail>> ordDetailMap = new HashMap<String,List<OrderDetail>>();
			ordDetailMap.put("addDetailList", addDetailList);
			ordDetailMap.put("updateDetailList", updateDetailList);
			result = orderService.updateOrder(order, ordDetailMap);
			System.out.println("更新订单详情========"+result);
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
	 * 更订单状态
	 * @param Shop
	 * @return
	 */
	public String updateOrderStatus(){
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
			result = orderService.updateOrderStatus(order);
			System.out.println("更订单状态========"+result);
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
	 * 更订单明细
	 * @param Shop
	 * @return
	 */
	public String updateOrderDetail(){
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
			result = orderService.updateOrderDetail(orderDetail);
			System.out.println("更订单明细========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	//*********************预定订单操作start********************
	/**
	 * 查询预定订单
	 * @param order
	 * @return
	 */
	public String reserveOrderList(){
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
			if(null == order){
				order = new Order();
			}
			order.setPageSize(super.limit);
			order.setPageStart(super.page);
			result = orderService.reserveOrderList(order);
			System.out.println("获取订单列表========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	//*********************预定订单操作end********************
}
