package com.selforder.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.mapping.SqlMapperException;

import com.selforder.bean.Order;
import com.selforder.bean.OrderDetail;
import com.selforder.bean.Table;

/**
 * 订单管理持久层
 * @author xingwanzhao
 *
 * 2016-7-18
 */
public interface OrderDao {
	/**
	 * 获取订单列表
	 * @param Order
	 * @return
	 */
	public List<Order> orderList(Order order) throws SQLException;
	
	/**
	 * 获取订单列表统计数
	 * @param Order
	 * @return
	 * @throws SQLException
	 */
	public int orderListCount(Order order)throws SQLException;
	
	/**
	 * 获取订单详情
	 * @param Order
	 * @return
	 */
	public Order orderInfo(Order order)throws SQLException;
	
	/**
	 * 新增订单
	 * @param Order
	 * @return
	 */
	public int insertOrder (Order order)throws SQLException;
	
	/**
	 * 更新订单
	 * @param order
	 * @return
	 */
	public int updateOrder(Order order)throws SQLException;
	
	/**
	 * 批量新增订单明细
	 * @param OrderDetail
	 * @return
	 */
	public int insertOrderDetail4Bach (List<OrderDetail> orderDetailList)throws SQLException;
	
	/**
	 * 新增订单明细
	 * @param OrderDetail
	 * @return
	 */
	public int insertOrderDetail (OrderDetail orderDetail)throws SQLException;
	
	/**
	 * 更新订单明细
	 * @param OrderDetail
	 * @return
	 */
	public int updateOrderDetail(OrderDetail orderDetail)throws SQLException;
	
	/**
	 * 获取订单明细列表 
	 * @param OrderDetail
	 * @return
	 * @throws SQLException
	 */
	public List<OrderDetail> orderDetailList(OrderDetail orderDetail)throws SQLException; 
	
	/**
	 * 获取下一个订单号
	 * @param weid 商户ID
	 * @return
	 * @throws SQLException
	 */
	public String getNextOrderSn(String weid) throws SQLException;
	
	/**
	 * 根据订单ID获取订单总金额
	 * @param oid 订单ID
	 * @return
	 */
	public double getTotalPriceByOrdid(String oid) throws Exception;
	
	/**
	 * 更新订单明细打印状态
	 * @param orderDetail
	 * @return
	 * @throws SQLException
	 */
	public int updateOrderPrintStatus(Order order)throws SQLException;
	
	//*********************预定订单操作start********************
	/**
	 * 查询预定订单列表
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public List<Order> reserveOrderList(Order order) throws SQLException;
	
	/**
	 * 查询预定订单列表统计数
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public int reserveOrderListCount(Order order)throws SQLException;
	//*********************预定订单操作end********************
	
	
}
