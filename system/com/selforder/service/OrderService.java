package com.selforder.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.selforder.bean.Order;
import com.selforder.bean.OrderDetail;

/**
 * 订单管理业务层
 * @author xingwanzhao
 *
 * 2016-7-18
 */
public interface OrderService {
	/**
	 * 获取订单列表
	 * @param Order
	 * @return
	 */
	public String orderList(Order order) ;
	
	/**
	 * 获取订单详情
	 * @param Order
	 * @return
	 */
	public String orderInfo(Order order);
	
	/**
	 * 新增订单
	 * @param Order
	 * @return
	 */
	public String insertOrder (Order order,List<OrderDetail> orderDetailList);
	
	/**
	 * 更新订单
	 * @param order orderDetailList 
	 * @return
	 */
	public String updateOrder(Order order,Map<String,List<OrderDetail>> orderListMap);
	
	/**
	 * 更新订单明细
	 * @param OrderDetail
	 * @return
	 */
	public String updateOrderDetail(OrderDetail orderDetail);
	
	/**
	 * 获取订单明细列表 
	 * @param OrderDetail
	 * @return
	 */
	public String orderDetailList(OrderDetail orderDetail); 
	
	/**
	 * 更新订单状态
	 * @param order
	 * @return
	 */
	public String updateOrderStatus(Order order);
	
	/**
	 * 更新订单明细打印状态
	 * @param orderDetail
	 * @return
	 */
	public String updateOrderPrintStatus(Order order);
	//*********************预定订单操作start********************
	/**
	 * 查询预定订单
	 * @param order
	 * @return
	 */
	public String reserveOrderList(Order order);
	
	/**
	 * 新增预定订单
	 * @param order
	 * @return
	 */
	public String insertReserveOrder(Order order);
	
	/**
	 * 获取订单详情
	 * @param Order
	 * @return
	 */
	public String reserveOrderInfo(Order order);
	
	/**
	 * 更新预定订单
	 * @param order
	 * @return
	 */
	public String updateReserveOrder(Order order);
	//*********************预定订单操作end********************
	
}
