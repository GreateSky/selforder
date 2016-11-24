package com.selforder.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Goods;
import com.selforder.bean.Order;
import com.selforder.bean.OrderDetail;
import com.selforder.dao.OrderDao;

public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao {
	/**
	 * 获取订单列表
	 * @param Order
	 * @return
	 */
	public List<Order> orderList(Order order) throws SQLException{
		return getSqlSession().selectList("com.selforder.order.getOrderList",order);
	}
	
	/**
	 * 获取订单列表统计数
	 * @param Order
	 * @return
	 * @throws SQLException
	 */
	public int orderListCount(Order order)throws SQLException{
		return (Integer)getSqlSession().selectOne("com.selforder.order.getOrderListCount",order);
	}
	
	/**
	 * 获取订单详情
	 * @param Order
	 * @return
	 */
	public Order orderInfo(Order order)throws SQLException{
		return (Order)getSqlSession().selectOne("com.selforder.order.getOrderInfo", order);
	}
	
	/**
	 * 新增订单
	 * @param Order
	 * @return
	 */
	public int insertOrder (Order order)throws SQLException{
		return getSqlSession().insert("com.selforder.order.insertOrder",order);
	}
	
	/**
	 * 更新订单
	 * @param order
	 * @return
	 */
	public int updateOrder(Order order)throws SQLException{
		return getSqlSession().update("com.selforder.order.updateOrder",order);
	}
	
	/**
	 * 新增订单明细
	 * @param OrderDetail
	 * @return
	 */
	public int insertOrderDetail4Bach (List<OrderDetail> orderDetailList)throws SQLException{
		//更新食谱被点击次数
		if(null != orderDetailList && orderDetailList.size()>0){
			for(int i=0;i<orderDetailList.size();i++){
				Goods goods = new Goods();
				goods.setId(orderDetailList.get(i).getGoods_id());
				getSqlSession().update("com.selforder.goods.updateGoodsSubcount",goods);
			}
		}
		return getSqlSession().insert("com.selforder.order.insertOrderDetail4Bach",orderDetailList);
	}
	
	/**
	 * 新增订单明细
	 * @param OrderDetail
	 * @return
	 */
	public int insertOrderDetail (OrderDetail orderDetail)throws SQLException{
		//更新食谱被点击次数
		Goods goods = new Goods();
		goods.setId(orderDetail.getGoods_id());
		getSqlSession().update("com.selforder.goods.updateGoodsSubcount",goods);
		return getSqlSession().insert("com.selforder.order.insertOrderDetail",orderDetail);
	}
	
	/**
	 * 更新订单明细
	 * @param OrderDetail
	 * @return
	 */
	public int updateOrderDetail(OrderDetail orderDetail)throws SQLException{
		return getSqlSession().update("com.selforder.order.updateOrderDetail",orderDetail);
	}
	
	/**
	 * 获取订单明细列表 
	 * @param OrderDetail
	 * @return
	 * @throws SQLException
	 */
	public List<OrderDetail> orderDetailList(OrderDetail orderDetail)throws SQLException{
		return getSqlSession().selectList("com.selforder.order.getOrderDetailList",orderDetail);
	}

	/**
	 * 获取下一个订单号
	 * @param weid 商户ID
	 * @return
	 * @throws SQLException
	 */
	public String getNextOrderSn(String weid) throws SQLException {
		return(String)getSqlSession().selectOne("com.selforder.order.getNextOrderSn",weid);
	}
	
	/**
	 * 根据订单ID获取订单总金额
	 * @param oid 订单ID
	 * @return
	 */
	public double getTotalPriceByOrdid(String oid) throws Exception{
		return (Double)getSqlSession().selectOne("com.selforder.order.getTotalPriceByOrdid", oid);
	}
	
	/**
	 * 更新订单明细打印状态
	 * @param orderDetail
	 * @return
	 * @throws SQLException
	 */
	public int updateOrderPrintStatus(Order order)throws SQLException{
		return getSqlSession().update("com.selforder.order.updateOrderPrintStatus", order);
	}
	
	//*********************预定订单操作start********************
	/**
	 * 查询预定订单列表
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public List<Order> reserveOrderList(Order order) throws SQLException{
		return getSqlSession().selectList("com.selforder.order.getReserveOrderList", order);
	}
	
	/**
	 * 查询预定订单列表统计数
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public int reserveOrderListCount(Order order)throws SQLException{
		return (Integer)getSqlSession().selectOne("com.selforder.order.getReserveOrderListCount", order);
	}
	//*********************预定订单操作end********************
}
