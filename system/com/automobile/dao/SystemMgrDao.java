package com.automobile.dao;

import java.util.List;
import java.util.Map;

import com.automobile.bean.BizDict;
import com.automobile.bean.Parts;

public interface SystemMgrDao {
	/**
	 * 获取配件
	 * @param parts
	 * @return
	 */
	public List<Map> getPartsList(Parts parts);
	
	/**
	 * 新增配件
	 * @param parts
	 * @return
	 */
	public String insertParts(Parts parts);
	
	/**
	 * 更新配件
	 * @param parts
	 * @return
	 */
	public String updateParts(Parts parts);
	
	/**
	 * 保存附件信息
	 * @param file
	 * @return 
	 */
	public String insertUpload(Map file);
	
	/**
	 * 获取附件详情
	 * @param fileid  文件ID
	 * @return
	 */
	public Map getFileInfo(String fileid);
}
