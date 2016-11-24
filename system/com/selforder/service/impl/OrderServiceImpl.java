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
import com.selforder.util.PushMessage;
import com.selforder.util.Uuid;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	private TableDao tableDao;
	private PushMessage pushMessage;
	public PushMessage getPushMessage() {
		return pushMessage;
	}

	public void setPushMessage(PushMessage pushMessage) {
		this.pushMessage = pushMessage;
	}

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
			//如果is_load_no_print=1 只加载未打印的明细信息（打印加菜单用）
			if(1 == order.getIs_load_no_print()){
				orderDetail.setIsprint("0");
			}
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
			order.setStatus("0");
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
					//推送创建订单消息
					int dining_mode = order.getDining_mode();
					String messageType = "";
					if(dining_mode == 1){
						messageType = "createorder_income";
					}
					if(dining_mode == 2){
						messageType = "createorder_outsell";
					}
					if(dining_mode == 3){
						messageType = "createorder_reserve";
					}
					String pushCreateOrderMessage = pushMessage.sendOrderMessgeToUser("createOrder", order.getId());
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
			//如果是到店订单更新时处理餐桌状态
			if(order.getDining_mode() == 1){
				updateTableStatus(order);
			}
			//更新订单
			int ordTemp = orderDao.updateOrder(order);
			result = JsonResultUtil.getJsonResult(0, "success", "更新订单成功!");
			String pushCreateOrderMessage = pushMessage.sendOrderMessgeToUser("updateOrder", order.getId());
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
			//如果是到店点单订单则同步更新餐桌状态
			if(1 == order.getDining_mode()){
				updateTableStatus(order);
			}
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
	
	/**
	 * 更新订单明细打印状态
	 * @param orderDetail
	 * @return
	 */
	public String updateOrderPrintStatus(Order order){
		String result = "";
		try{
			if(null == order){
				return JsonResultUtil.getJsonResult(-1, "fail", "参数异常！");
			}
			int temp = orderDao.updateOrderPrintStatus(order);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新打印状态成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新打印状态失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 根据订单信息更新对应的餐桌状态
	 * @param orderInfo
	 * @return
	 * @see 逻辑过程：
	 * 判断更新的订单状态若为取消状态则将订单中涉及的餐桌状态设置为空闲
	 * 若为更新订单则判断是否对餐桌做了更新，如果更新了餐桌则将更新前的餐桌设置为空闲将待更新订单中涉及的餐桌设置为已下单状态
	 */
	public int updateTableStatus(Order orderInfo){
		int result = 0;
		try{
			//查询更新前的订单信息
			Order oldOrderInfo =  new Order();
			oldOrderInfo.setId(orderInfo.getId());
			oldOrderInfo =orderDao.orderInfo(oldOrderInfo);
			if(null == oldOrderInfo ) return -1;
			//判断待更新的订单是否将状态设置为取消状态（若是则将涉及的餐桌设置为空闲状态）
			String status = orderInfo.getStatus();
			if("-1".equals(status)){
				Table table = new Table();
				table.setId(oldOrderInfo.getTableid());
				table.setStatus("0");
				return result = tableDao.updateTable(table);
			}else{//只更新订单信息时判断是否更新了餐桌，如果更新了餐桌则修改对应的餐桌状态
				String oldTableid = oldOrderInfo.getTableid();
				String tableid = orderInfo.getTableid();
				if(null != oldTableid && null != tableid && !tableid.equals(oldTableid)){//更新了餐桌信息则将老的餐桌设置为空闲，新的餐桌设置为已下单
					//更新老的餐桌为空闲
					Table oldtable = new Table();
					oldtable.setId(oldTableid);
					oldtable.setStatus("0");
					result = tableDao.updateTable(oldtable);
					//新的餐桌设置为已下单
					Table table = new Table();
					table.setId(tableid);
					table.setStatus("1");
					result = tableDao.updateTable(table);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//*********************预定订单操作start********************
	/**
	 * 查询预定订单
	 * @param order
	 * @return
	 */
	public String reserveOrderList(Order order){
		String result = "";
		Map resultMap = new HashMap();
		String bid = new Context().getLoginUserInfo().getBid();
		String sid = new Context().getLoginUserInfo().getSid();
		order.setWeid(bid);
		order.setStoreid(sid);
		try{
			//查询门店列表
			List<Order> orderList = orderDao.reserveOrderList(order);
			if(orderList != null && orderList.size()>0){
				//查询统计数
				int count = orderDao.reserveOrderListCount(order);
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
	 * 新增预定订单
	 * @param order
	 * @return
	 */
	public String insertReserveOrder(Order order){
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			String bid = new Context().getLoginUserInfo().getBid();
			String sid = new Context().getLoginUserInfo().getSid();
			order.setCrter(crter);
			order.setWeid(bid);
			order.setStoreid(sid);
			order.setDining_mode(3);
			String ordersn = orderDao.getNextOrderSn(bid);//获取订单号
			String id = Uuid.getUuid();//生成订单主键
			order.setOrdersn(ordersn);
			order.setId(id);
			order.setStatus("0");
			double totalprice = 0.00;//总金额
			//组装订单明细
			order.setTotalprice(totalprice);
			int temp = orderDao.insertOrder(order);//插入订单
			if(temp > 0){//保存订单明细
				result = JsonResultUtil.getJsonResult(0, "success", "新增订单成功!");
				String pushCreateOrderMessage = pushMessage.sendOrderMessgeToUser("createOrder", order.getId());
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
	 * 获取订单详情
	 * @param Order
	 * @return
	 */
	public String reserveOrderInfo(Order order){
		String result = "";
		try{
			//获取订单详情
			Order currorder = orderDao.orderInfo(order);
			if(null == currorder){ 
				return JsonResultUtil.getJsonResult(-1,"fail", "查询订单详情失败!");
			}
			result = JsonResultUtil.getJsonResult(0, "succecss", "查询订单详情成功!", currorder);
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1,"fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 更新预定订单
	 * @param order
	 * @return
	 */
	public String updateReserveOrder(Order order){
		String result = "";
		try{
			int temp = orderDao.updateOrder(order);
			if(temp > 0){
				result= JsonResultUtil.getJsonResult(0,"success", "更新订单成功！");
			}else{
				result= JsonResultUtil.getJsonResult(-1,"fail", "更新订单失败！");
			}
			String pushCreateOrderMessage = pushMessage.sendOrderMessgeToUser("updateOrder", order.getId());
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1,"fail", "更新订单异常！");
		}
		return result;
	}
	//*********************预定订单操作end********************
}
