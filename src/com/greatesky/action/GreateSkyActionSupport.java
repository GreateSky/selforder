package com.greatesky.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.opensymphony.xwork2.ActionSupport;

public class GreateSkyActionSupport extends ActionSupport {
	private Map resultJson;
	public Map getResultJson() {
		return resultJson;
	}

	public void setResultJson(Map resultJson) {
		this.resultJson = resultJson;
	}

	/**
	 * 开始,默认0
	 */
	public int page = 0;
	/**
	 * 每页数量,默认20
	 */
	public int limit = 20;
	/**
	 * 排序字段
	 */
	private String sortField;
	/**
	 * 排序方式
	 */
	private String sortOrder;
	/**
	 * 数据量
	 */
	private long count;
	/**
	 * 结果集
	 */
	private List<?> result;
	/**
	 * 数据id集合
	 */
	protected List<String> idList;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

	public static String CALLBACK_FLAG = "callback";

	/**
	 * @param json
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void setJson(String json, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		boolean scriptTag = false;
		String cb = request.getParameter(CALLBACK_FLAG);
		if (cb != null) {
			scriptTag = true;
			response.setContentType("text/javascript");
		} else {
			response.setContentType("application/x-json");
		}
		Writer out = response.getWriter();
		// 前面补callback
		if (scriptTag) {
			out.write(cb + "(");
		}
		out.write(json);
		// 最后补个括号，为了处理跨域
		if (scriptTag) {
			out.write(");");

		}
		if (cb != null) {
			scriptTag = true;
			response.setContentType("text/javascript");
		} else {
			response.setContentType("application/x-json");
		}
	}

	/**
	 * 向客户端响应数据
	 * 
	 * @param content
	 *            响应的内容
	 */
	public void response(String content) {
		if (null != content && !"".equals(content)) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (null != writer) {
				writer.write(content);
				writer.flush();
				writer.close();
			}
		}
	}

	public void resporseJson(Object jsonObj, HttpServletRequest request,
			HttpServletResponse response) {
		String result = "";
		JsonSerializer<Date> ser = new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src, Type typeOfSrc,
					JsonSerializationContext context) {
				return src == null ? null : new JsonPrimitive(src.getTime());
			}

		};

		JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				// TODO Auto-generated method stub
				return json == null ? null : new Date(json.getAsLong());
			}
		};
		response.setCharacterEncoding("UTF-8");// .setDateFormat("yyyy-MM-dd HH:mm:ss")
		// //.setDateFormat(DateFormat.DAY_OF_WEEK_FIELD)
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, ser)
				.registerTypeAdapter(Date.class, deser).create();
		result = gson.toJson(jsonObj);
		try {
			result = new String(result.getBytes("UTF-8"), "UTF-8");
			response.getWriter().write(result);
			response.getWriter().flush();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, String> getBaseParam() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("sortField", sortField);
		param.put("sortOrder", sortOrder);
		if (page > 0) {
			param.put("pageStart", (page - 1) * limit + "");
		} else {
			param.put("pageStart", 0 + "");
		}

		param.put("pageSize", limit + "");
		return param;
	}
}
