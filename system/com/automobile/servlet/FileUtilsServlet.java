package com.automobile.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.automobile.dao.SystemMgrDao;
import com.automobile.listener.SystemConfig;
import com.automobile.util.JsonResultUtil;

/**
 * 
 * @author Administrator
 * 文件上传
 * 具体步骤：
 * 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包
 * 2） 利用 request 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同
 * 3）对 DiskFileItemFactory 对象设置一些 属性
 * 4）高水平的API文件上传处理  ServletFileUpload upload = new ServletFileUpload(factory);
 * 目的是调用 parseRequest（request）方法  获得 FileItem 集合list ，
 *     
 * 5）在 FileItem 对象中 获取信息，   遍历， 判断 表单提交过来的信息 是否是 普通文本信息  另做处理
 * 6）
 *    第一种. 用第三方 提供的  item.write( new File(path,filename) );  直接写到磁盘上
 *    第二种. 手动处理  
 *
 */
public class FileUtilsServlet extends HttpServlet {
        
	private static final long serialVersionUID = -6747808041163232799L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public FileUtilsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//设置编码
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		Writer writeout;
		//初始化dao层bean
		ServletContext application;     
		WebApplicationContext wac;     
		application = getServletContext();     
		wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context     
		SystemMgrDao systemMgrDao = (SystemMgrDao) wac.getBean("systemMgrDao");     
		
		if("upload".equals(method)){
			//获得磁盘文件条目工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//获取文件需要上传到的路径
			String path = SystemConfig.get("fileUploadPath");
			Date curDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			path = path+sdf.format(curDate)+"/";
			String callback=request.getParameter("callback");
			
			//如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
			//设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
			/**
			 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 
			 * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的 
			 * 然后再将其真正写到 对应目录的硬盘上
			 */
			factory.setRepository(new File(path));
			//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
			factory.setSizeThreshold(1024*1024) ;
			
			//高水平的API文件上传处理
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				//可以上传多个文件
				List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
				if(list.size()<=0){//无上传文件直接调用回调函数
					writeout = response.getWriter();
					System.out.println("<script type=\"text/javascript\">\n   parent."+callback+"();\n</script>");
					writeout.write("<script type=\"text/javascript\">\n   parent."+callback+"();\n</script>");
					//writeout.write(fileInfos);
					writeout.flush();
					writeout.close();
				}else{//有上传文件处理文件
					List resultList = new ArrayList();
					List returnList = new ArrayList();
					for(FileItem item : list)
					{
						
						//获取表单的属性名字
						String name = item.getFieldName();
						Map<String, Object> fileInfo = new HashMap<String, Object>();
						Map<String, Object> returnFile = new HashMap<String, Object>();
						//如果获取的 表单信息是普通的 文本 信息
						if(item.isFormField())
						{					
							//获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
							String value = item.getString() ;
							request.setAttribute(name, value);
						}
						//对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
						else
						{
							//获取上传的文件名
							String value = item.getName() ;
							if("".equals(value)){
								continue;
							}
							int start = value.lastIndexOf("\\");
							String filename = value.substring(start+1);
							//获取文件属性
							String tempfile[] = filename.split("\\.");
							String filetype = "."+tempfile[tempfile.length-1];
							//生产新的文件名
							String newfilename = UUID.randomUUID().toString().replace("-", "")+filetype;
							//文件大小
							long filesize = item.getSize();
							//文件路径
							String filepath = path+newfilename;
							filepath = filepath.replaceAll("\\\\", "/");
							//文件ID
							String fileid = UUID.randomUUID().toString().replace("-", "");
							//文件所属组
							String filegroup = request.getParameter("filegroup");
							//文件日期
							Date currdate = new Date();
							//获取访问者IP
							String ipaddress = getIpAddr(request);
							
							//创建目录
							File dir = new File(path);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							//真正写到磁盘上
							//它抛出的异常 用exception 捕捉
							//item.write( new File(path,filename) );//第三方提供的
							//手动写的
							OutputStream out = new FileOutputStream(new File(path,newfilename));
							InputStream in = item.getInputStream() ;
							int length = 0 ;
							byte [] buf = new byte[1024] ;
							System.out.println("获取上传文件的总共的容量："+item.getSize());
							// in.read(buf) 每次读到的数据存放在   buf 数组中
							while( (length = in.read(buf) ) != -1)
							{
								//在   buf 数组中 取出数据 写到 （输出流）磁盘上
								out.write(buf, 0, length);
							}
							in.close();
							out.close();
							
							//组装附件信息
							fileInfo.put("fileid",fileid );
							fileInfo.put("filename",filename);
							fileInfo.put("filetype",filetype);
							fileInfo.put("filesavename",newfilename );
							fileInfo.put("filesize",filesize );
							fileInfo.put("filepath",filepath );
							fileInfo.put("filegroup", filegroup);
							fileInfo.put("currdate",currdate);
							fileInfo.put("ipaddress",ipaddress);
							resultList.add(fileInfo);
							String save = systemMgrDao.insertUpload(fileInfo);
							returnFile.put("fileid", fileid);
							returnFile.put("filesize",filesize );
							returnFile.put("filetype",filetype);
							returnList.add(returnFile);
						}
					}
					String fileInfos = JsonResultUtil.ArrayListToJsonStr(returnList);
					//拼接回调执行函数
					StringBuffer sb = new StringBuffer("  var args= new Array(); \n");
					sb.append("  args="+fileInfos+"; \n");
					writeout = response.getWriter();
					System.out.println("<script type=\"text/javascript\">\n "+sb.toString()+"  parent."+callback+"(args);\n</script>");
					writeout.write("<script type=\"text/javascript\">\n "+sb.toString()+"  parent."+callback+"(args);\n</script>");
					//writeout.write(fileInfos);
					writeout.flush();
					writeout.close();
				}
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			//request.getRequestDispatcher("filedemo.jsp").forward(request, response);
		}else if("download".equals(method)){
			//String downloadPath = "/app/data/automobile/FileUpload/2016/04/21/da574a62349b43f8944aaa220676dfbf.jpg";
			String fileid = request.getParameter("fileid");
			//根据fileid获取文件的存储位置
			Map fileInfo;
			if(!"".equals(fileid)){
				fileInfo = systemMgrDao.getFileInfo(fileid);
				if(fileInfo == null){
					return;
				}else{
					String filepath = fileInfo.get("FILEPATH").toString();
					File f = new File(filepath);
					if(f.exists()){  
			            FileInputStream  fis = new FileInputStream(f);  
			            String filename=URLEncoder.encode(f.getName(),"utf-8"); //解决中文文件名下载后乱码的问题  
			            byte[] b = new byte[fis.available()];  
			            fis.read(b);  
			            response.setCharacterEncoding("utf-8");  
			            response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
			            //获取响应报文输出流对象  
			            ServletOutputStream  out =response.getOutputStream();  
			            //输出  
			            out.write(b);  
			            out.flush();  
			            out.close();  
			        }
				}
			}
		}
	}
	
	/**
	 * 获取访问者的IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}  
	
	public static void main(String[] args) {
		String test = "2016_3d\\_abstract_polygon_\\wallpaper_cs9_fx_design\\-wallpaper-1920x1080.jpg";
		test = test.replaceAll("\\\\", "/");
		System.out.println(test);
	}
}
