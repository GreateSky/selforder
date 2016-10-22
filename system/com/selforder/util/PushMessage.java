package com.selforder.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.selforder.WebSocket.MessageSend.WebSocketMessageInboundPool;
import com.selforder.bean.Comment;
import com.selforder.bean.Order;
import com.selforder.bean.Table;
import com.selforder.dao.CommentDao;
import com.selforder.dao.OrderDao;
import com.selforder.dao.PushMessageDao;
import com.selforder.dao.TableDao;

/**
 * 向客户端推送消息
 * @author xingwanzhao
 *
 * 2016-9-26
 */
public class PushMessage {
	private PushMessageDao pushMessageDao;
	private OrderDao orderDao;
	private CommentDao commentDao;
	private TableDao tableDao;
	private enum ROLE_CODE{ROLE_ORDER_MGR,ROLE_ORDER_OUT_MGR,ROLE_ORDER_RESERVE};
	public TableDao getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public PushMessageDao getPushMessageDao() {
		return pushMessageDao;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setPushMessageDao(PushMessageDao pushMessageDao) {
		this.pushMessageDao = pushMessageDao;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	private Logger logger = Logger.getLogger(PushMessage.class);
	
	/**
	 * 根据EmpID向当前登录用户推送消息
	 * @param empid 用户empid
	 * @param content 要推送的内容
	 * @return
	 */
	public String sendMessageToUser(String empid,String content){
		String result = "";
		try{
			
		}catch(Exception e){
			
		}
		return result;
	}
	
	/**
	 * 根据用户权限获取推送消息
	 * @param weid 商户ID
	 * @param role 权限编码
	 * @param content 发送内容
	 * @return
	 */
	public String sendMessgeToUserByRole(String weid,String role,String content){
		String result = "";
		try{
			//参数判断
			if(null == weid || "".equals(weid.trim())){
				return JsonResultUtil.getJsonResult(-1, "fail", "商户ID不能为空！");
			}
			if(null == role || "".equals(role.trim())){
				return JsonResultUtil.getJsonResult(-1, "fail", "权限编码不能为空！");
			}
			if(null == content || "".equals(content.trim())){
				return JsonResultUtil.getJsonResult(-1, "fail", "推送消息内容不能为空！");
			}
			//组装参数
			HashMap param  = new HashMap();
			param.put("weid", weid);
			param.put("role_code", role);
			//查询消息接受者集合
			List<Map<String,String>> emplist = pushMessageDao.emplist(param);
			if(null != emplist && emplist.size()>0){
				//遍历消息接受者集合为当前在线的用户推送消息
				for(int i=0;i<emplist.size();i++){
					Map emp = emplist.get(i);
					String empid = (String)emp.get("empid");
					String sendRresult = WebSocketMessageInboundPool.sendMessageToUser(empid, content);
				}
			}else{
				return JsonResultUtil.getJsonResult(-1, "fail", "消息接受者集合为空！");
			}
		}catch(Exception e){
			
		}
		return result;
	}
	
	/**
	 * 向用户推送订单消息
	 * @param messageType 消息类型：createOrder，updateOrder，payOrder,comment,service
	 * @param orderid 订单ID
	 * @return
	 */
	public String sendOrderMessgeToUser(String messageType,String orderid){
		/**
		 * 逻辑处理过程：
		 * 1、根据订单ID查询订单信息
		 * 2、根据订单类型判断要查询权限
		 * 3、组装消息体
		 * 4、根据商户ID和权限编码查询需要通知的用户集合
		 * 5、遍历用户集合推送消息
		 */
		String result = "";
		try{
			//参数判断
			if(null == messageType || "".equals(messageType.trim())){
				return JsonResultUtil.getJsonResult(-1, "fail", "消息类型不能为空！");
			}
			if(null == orderid || "".equals(orderid.trim())){
				return JsonResultUtil.getJsonResult(-1, "fail", "订单ID不能为空！");
			}
			//根据订单ID查询订单信息
			Order temp = new Order();
			temp.setId(orderid);
			Order orderInfo = orderDao.orderInfo(temp);
			if(null == orderInfo){
				return JsonResultUtil.getJsonResult(-1, "fail", "未获取到订单信息！");
			}
			String ordersn = orderInfo.getOrdersn();
			int diningMode = orderInfo.getDining_mode();
			String weid = orderInfo.getWeid();//商户ID
			String role_code = "";//要推送的权限
			if(diningMode == 1){
				role_code = ROLE_CODE.ROLE_ORDER_MGR.toString();
			}else if(diningMode == 2){
				role_code = ROLE_CODE.ROLE_ORDER_OUT_MGR.toString();
			}else if(diningMode == 3){
				role_code = ROLE_CODE.ROLE_ORDER_RESERVE.toString();
			}
			//根据消息类型组装推送的消息内容
			String sendContent = "{\"NO\":\""+getRandom(4)+"\",\"messageType\":\""+messageType+"\",\"orderid\":\""+orderid+"\",\"ordersn\":\""+ordersn+"\",\"diningMode\":\""+diningMode+"\"}";
			
			//组装参数
			HashMap param  = new HashMap();
			param.put("weid", weid);
			param.put("role_code", "ROLE_ORDER_MGR");
			//查询消息接受者集合
			List<Map<String,String>> emplist = pushMessageDao.emplist(param);
			if(null != emplist && emplist.size()>0){
				//遍历消息接受者集合为当前在线的用户推送消息
				for(int i=0;i<emplist.size();i++){
					Map emp = emplist.get(i);
					String empid = (String)emp.get("empid");
					String sendRresult = WebSocketMessageInboundPool.sendMessageToUser(empid, sendContent);
				}
			}else{
				return JsonResultUtil.getJsonResult(-1, "fail", "消息接受者集合为空！");
			}
			result = JsonResultUtil.getJsonResult(0, "success", "推送消息成功！");
		}catch(Exception e){
			logger.error("推送消息异常", e);
			return JsonResultUtil.getJsonResult(-1, "fail", "推送消息失败！");
		}
		return result;
	}
	
	/**
	 * 推送评论消息
	 * @param rid 评论ID
	 * @return
	 */
	public String sendCommentMessageToUser(String rid){
		String result = "";
		try{
			Comment comment = new Comment();
			comment.setRid(rid);
			List<Map> comments = (List<Map>)commentDao.getCommentDetailList(comment);
			if(null == comments || comments.size()<=0){
				return JsonResultUtil.getJsonResult(-1, "fail", "未查询到相关评论信息！");
			}
			Map map = comments.get(0);
			String description = map.get("description").toString();
			String username = map.get("username").toString();
			String nickname = map.get("nickname").toString();
			String weid = map.get("weid").toString();
			String storeid = map.get("storeid").toString();
			Date crtdate = (Date)map.get("crtdate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String crtdateStr = sdf.format(crtdate);
			//根据消息类型组装推送的消息内容
			String sendContent = "{\"NO\":\""+getRandom(4)+"\",\"messageType\":\"comment\",\"rid\":\""+rid+"\",\"username\":\""+username+"\",\"nickname\":\""+nickname+"\",\"description\":\""+description+"\",\"crtdate\":\""+crtdateStr+"\"}";
			//组装查询推送人参数
			HashMap param  = new HashMap();
			param.put("weid", weid);
			param.put("storeid", storeid);
			param.put("role_code", "ROLE_COMMENT_MGR");
			//查询消息接受者集合
			List<Map<String,String>> emplist = pushMessageDao.emplist(param);
			if(null != emplist && emplist.size()>0){
				//遍历消息接受者集合为当前在线的用户推送消息
				for(int i=0;i<emplist.size();i++){
					Map emp = emplist.get(i);
					String empid = (String)emp.get("empid");
					String sendRresult = WebSocketMessageInboundPool.sendMessageToUser(empid, sendContent);
				}
			}else{
				return JsonResultUtil.getJsonResult(-1, "fail", "消息接受者集合为空！");
			}
			result = JsonResultUtil.getJsonResult(0, "success", "推送消息成功！");
		}catch(Exception e){
			logger.error("推送评论信息异常！", e);
			return JsonResultUtil.getJsonResult(-1, "fail", "推送消息失败！");
		}
		return result;
	}
	
	/*
	 * 推送服务消息
	 * @return
	 */
	public String sendCallServiceMessageToUser(String tableid){
		String result = "";
		try{
			Table table = new Table();
			table.setId(tableid);
			table = tableDao.tableInfo(table);
			if(null == table){
				return JsonResultUtil.getJsonResult(-1, "fail", "未获取到餐桌信息！");
			}
			String weid = table.getWeid();
			String storeid = table.getStoreid();
			String room_name = table.getRoom_name();
			String tablecode = table.getTitle();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String crtdateStr = sdf.format(new Date());
			//根据消息类型组装推送的消息内容
			String sendContent = "{\"NO\":\""+getRandom(4)+"\",\"messageType\":\"callService\",\"tablecode\":\""+tablecode+"\",\"room_name\":\""+room_name+"\",\"callTime\":\""+crtdateStr+"\"}";
			//组装查询推送人参数
			HashMap param  = new HashMap();
			param.put("weid", weid);
			param.put("storeid", storeid);
			param.put("role_code", "ROLE_CALLSERVICE_MGR");
			//查询消息接受者集合
			List<Map<String,String>> emplist = pushMessageDao.emplist(param);
			if(null != emplist && emplist.size()>0){
				//遍历消息接受者集合为当前在线的用户推送消息
				for(int i=0;i<emplist.size();i++){
					Map emp = emplist.get(i);
					String empid = (String)emp.get("empid");
					String sendRresult = WebSocketMessageInboundPool.sendMessageToUser(empid, sendContent);
				}
			}else{
				return JsonResultUtil.getJsonResult(-1, "fail", "消息接受者集合为空！");
			}
			result = JsonResultUtil.getJsonResult(0, "success", "推送消息成功！");
		}catch(Exception e){
			logger.error("推送评论信息异常！", e);
			return JsonResultUtil.getJsonResult(-1, "fail", "推送消息失败！");
		}
		return result;
	}
	
	/**
	 * 获取count个随机数
	 * @param count 随机数个数
	 * @return
	 */
	public static String getRandom(int count){
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random r = new Random();
		for(int i=0;i<count;i++){
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num)+""), "");
		}
		return sb.toString();
	}

	
	public static void main(String[] args) {
		System.out.println(ROLE_CODE.ROLE_ORDER_MGR);
		System.out.println(getRandom(4));
	}
}
