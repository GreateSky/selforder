package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.selforder.bean.Order;
import com.selforder.bean.OrderDetail;
import com.selforder.bean.Table;
import com.selforder.dao.OrderDao;
import com.selforder.dao.TableDao;
import com.selforder.service.OrderService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.Uuid;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	private TableDao tableDao;

	public TableDao getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	/**
	 * 获取订单列表
	 * @param Order
	 * @return
	 */
	public String orderList(Order order){
		String result = "";
		Map resultMap = new HashMap();
		String bid = new Context().getLoginUserInfo().getBid();
		String sid = new Context().getLoginUserInfo().getSid();
		order.setWeid(bid);
		order.setStoreid(sid);
		try{
			//查询门店列表
			List<Order> orderList = orderDao.orderList(order);
			if(orderList != null && orderList.size()>0){
				//查询统计数
				int count = orderDao.orderListCount(order);
				resultMap.put("rows", orderList);
				resultMap.put("total", count);
				result = JsonResultUtil.MapToJsonStr(resultMap);
			}else{
				result = JsonResultUtil.getJsonResult(-1,"fail", "无数据!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1,"fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 获取订单详情
	 * @param Order
	 * @return
	 */
	public String orderInfo(Order order){
		String result = "";
		Map resultMap = new HashMap();
		try{
			//获取订单详情
			Order currorder = orderDao.orderInfo(order);
			if(null == currorder){ 
				return JsonResultUtil.getJsonResult(-1,"fail", "查询订单详情失败!");
			}
			//获取订单明细
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOid(order.getId());
			List<OrderDetail> orderDetailList = orderDao.orderDetailList(orderDetail);
			resultMap.put("order", currorder);
			resultMap.put("orderDetailList", orderDetailList);
			result = JsonResultUtil.getJsonResult(0, "succecss", "查询订单详情成功!", resultMap);
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1,"fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 新增订单
	 * @param Order
	 * @return
	 */
	public String insertOrder (Order order,List<OrderDetail> orderDetailList){
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			String bid = new Context().getLoginUserInfo().getBid();
			String sid = new Context().getLoginUserInfo().getSid();
			order.setCrter(crter);
			order.setWeid(bid);
			order.setStoreid(sid);
			//保存用户信息
			String ordersn = orderDao.getNextOrderSn(bid);//获取订单号
			String id = Uuid.getUuid();//生成订单主键
			order.setOrdersn(ordersn);
			order.setId(id);
			double totalprice = 0.00;//总金额
			//组装订单明细
			if(null != orderDetailList && orderDetailList.size() > 0){
				for(OrderDetail orderDetail:orderDetailList){
					orderDetail.setOid(id);
					//计算总金额
					int num = orderDetail.getNum();
					double price = orderDetail.getPrice();
					totalprice += price*num;
				}
				//保存订单明细
				int saveDeatail = orderDao.insertOrderDetail4Bach(orderDetailList);
				if(saveDeatail >0){
					result = JsonResultUtil.getJsonResult(0, "success", "新增订单明细成功!");
				}else{
					result = JsonResultUtil.getJsonResult(-1, "fail", "新增订单明细失败!");
				}
			}
			order.setTotalprice(totalprice);
			int temp = orderDao.insertOrder(order);//插入订单
			//更新餐桌状态
			String tablieid = order.getTableid();
			if(!"".equals(tablieid)){
				Table table = new Table();
				table.setId(tablieid);
				table.setStatus("1");
				int uptable = tableDao.updateTable(table);
			}
			if(temp > 0){//保存订单明细
				result = JsonResultUtil.getJsonResult(0, "success", "新增订单成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "新增订单失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}

	/**
	 * 更新订单
	 * @param order orderDetailList 
	 * @return
	 */
	public String updateOrder(Order order,Map<String,List<OrderDetail>> orderListMap) {
		/**
		 * 逻辑处理过程
		 * orderListMap中包含：addDetailList(新增订单明细集合)、updateDetailList（修改订单明细集合）
		 * 1、分别遍历三种订单明细集合并更新值数据库
		 * 2、获取新增订单明细的总金额
		 * 3、更新订单表中的金额
		 */
		String result = "";
		try{
			List<OrderDetail> addDetailList = orderListMap.get("addDetailList");//新增订单明细集合
			List<OrderDetail> updateDetailList = orderListMap.get("updateDetailList");//修改订单明细集合
			//保存新增订单集合
			if(null != addDetailList && addDetailList.size()>0){
				int addTemp = orderDao.insertOrderDetail4Bach(addDetailList);
			}
			//保存修改订单明细集合
			if(null != updateDetailList && updateDetailList.size()>0){
				for(OrderDetail upOrdDetail:updateDetailList){
					int updateOrdDetail = orderDao.updateOrderDetail(upOrdDetail);
				}
			}
			//查询最新的订单总金额
			double total = orderDao.getTotalPriceByOrdid(order.getId());
			order.setId(order.getId());
			order.setTotalprice(total);
			//更新订单
			int ordTemp = orderDao.updateOrder(order);
			//更新餐桌状态
			String tablieid = order.getTableid();
			if(!"".equals(tablieid)){
				Table table = new Table();
				table.setId(tablieid);
				table.setStatus("1");
				int uptable = tableDao.updateTable(table);
			}
			result = JsonResultUtil.getJsonResult(0, "success", "更新订单成功!");
			
		}catch(Exception e){
			e.printStackTrace();
			result = JsonResultUtil.getJsonResult(-1, "fail", "更新订单失败!");
		}
		return result;
	}
	
	/**
	 * 更新订单明细
	 */
	@Override
	public String updateOrderDetail(OrderDetail orderDetail) {
		String result = "";
		String opter = new Context().getLoginUserInfo().getCode();
		try{
			orderDetail.setOpter(opter);
			int temp = orderDao.updateOrderDetail(orderDetail);
			if(temp > 0 ){
				//查询最新的订单总金额
				Order order  = new Order();
				order.setId(orderDetail.getOid());
				double total = orderDao.getTotalPriceByOrdid(order.getId());
				order.setTotalprice(total);
				//更新订单
				int ordTemp = orderDao.updateOrder(order);
				result = JsonResultUtil.getJsonResult(0,"success", "操作成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "更新订单明细失败!");
		}
		return result;
	}

	/**
	 * 获取订单明细列表 
	 * @param OrderDetail
	 * @return
	 */
	public String orderDetailList(OrderDetail orderDetail) {
		String result = "";
		try{
			List<OrderDetail> orderDetailList = orderDao.orderDetailList(orderDetail);
			if(null != orderDetailList && orderDetailList.size()> 0){
				result = JsonResultUtil.getJsonResult(0, "success", "查询数据成功!", orderDetailList);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "查询数据为空!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 更新订单状态
	 * @param order
	 * @return
	 */
	public String updateOrderStatus(Order order){
		String result = "";
		String opter  = new Context().getLoginUserInfo().getCode();
		try{
			order.setOpter(opter);
			int update = orderDao.updateOrder(order);
			if(update >0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新订单状态成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新订单状态失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
}
