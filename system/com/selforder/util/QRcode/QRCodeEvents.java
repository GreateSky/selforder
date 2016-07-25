package com.selforder.util.QRcode;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.automobile.dao.SystemMgrDao;
import com.automobile.listener.SystemConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.Uuid;

/**
 * 二维码生产工具
 * @author xingwanzhao
 *
 * 2016-7-22
 */
public class QRCodeEvents {
	public static void main(String []args){   
		createTableQRcode("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa7e5917aec187a5d&redirect_uri=http://soa.joyoung.com/soa/mp/qyauth2?url=http://soa.joyoung.com/soa/finemill/feedback/feedback.jsp&response_type=code&scope=snsapi_base&state=123#wechat_redirect","dce9a551a7064cbbaebf6cfa1d682af1","87fb60d69aeb1034980f71d9ea096f11");
	}
	
	/**
	 * 生成餐桌二维码图片
	 * @param content	图片内容
	 * @param bid	所属商户
	 * @param sid	所属门店
	 * @return
	 */
	public static String createTableQRcode(String content,String bid,String sid){
		String result = "";
		if("".equals(content) || "".equals(bid) || "".equals(sid) ){
			return JsonResultUtil.getJsonResult(-1, "fail", "生产二维码参数为空!");
		}
		int width = 500;//宽度
		int height = 500;//高度
		String format = "png";//二维码的图片格式
		
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//设定内容所使用编码
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(content,
					BarcodeFormat.QR_CODE, width, height, hints);
		} catch (WriterException e1) {
			e1.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "生产二维码格式转换错误!");
		}
		//获取文件名称及文件保存路径
		String path = SystemConfig.get("fileUploadPath");//获取文件保存路径
		path = "/app/data/selforder/FileUpload/TableQRcode";
		path = path+"/"+bid+"/"+sid+"/";//文件保存路径
		String fileid = Uuid.getUuid();//生成文件名称
		File file = new File(path,fileid+".png");
		if(!file.exists()){
			file.mkdirs();
		}
		//生成二维码并保存至服务器
		try {
			MatrixToImageWriter.writeToFile(bitMatrix, format, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "生产二维码异常!");
		}
		
		//组装文件保存成信息
		long finlesize = file.length();//获取文件大小 单位字节
		finlesize = Math.round(finlesize/1024); //换算成Kb
		HashMap fileInfo = new HashMap();
		fileInfo.put("fileid",fileid );
		fileInfo.put("filename",fileid+".png");
		fileInfo.put("filetype",".png");
		fileInfo.put("filesavename",fileid+".png");
		fileInfo.put("filesize",finlesize );
		fileInfo.put("filepath",path+fileid+".png");
		fileInfo.put("filegroup", "TableQRcode");
		fileInfo.put("currdate",new Date());
		result = JsonResultUtil.getJsonResult(0, "success", "二维码生产成功!",fileInfo);
		System.out.println("生成餐桌二维码:"+result);
		return result;
	}
}

