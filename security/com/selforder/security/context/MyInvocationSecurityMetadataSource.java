package com.selforder.security.context;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.selforder.dao.PowerDao;
import com.selforder.dao.impl.PowerDaoImpl;
 
 /** 
  * 
  * 此类在初始化时，应该取到所有资源及其对应角色的定义
  * 
  * @author user
  * 
  */ 
 public   class  MyInvocationSecurityMetadataSource implements  FilterInvocationSecurityMetadataSource  {
      //private  UrlMatcher urlMatcher  =   new  AntUrlPathMatcher();
      private   static  Map < String, Collection < ConfigAttribute >>  resourceMap  =   null ;
      public PowerDao powerDao;
      private JdbcTemplate jdbcTemplate;
      
      //由spring调用  
      public MyInvocationSecurityMetadataSource(PowerDao powerDao) {  
          this.powerDao = powerDao;  
          loadResourceDefine();  
      }
 
   // 在Web服务器启动时，提取系统中的所有权限
      private   void  loadResourceDefine()  {
    	  List<Map> refList = powerDao.allRoleResourceList();
    	  /*
           * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
           * sparta
           */
    	  resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
    	  if(null != refList && refList.size()>0){
    		  for(Map map :refList){
    			  String role = map.get("ROLE_CODE").toString();
    			  String url = map.get("RESOURCE_URL").toString();
    			  ConfigAttribute ca  = new SecurityConfig(role);
    			  /*
                   * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                   * sparta
                   */
    			  if (resourceMap.containsKey(url)) {
                      Collection<ConfigAttribute> value = resourceMap.get(url);
                      value.add(ca);
                      resourceMap.put(url, value);
                  } else {
                      Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                      atts.add(ca);
                      resourceMap.put(url, atts);
                  }
    		  }
    	  }
    	  //访问所有资源都需登录权限
		  //resourceMap  =   new  HashMap < String, Collection < ConfigAttribute >> ();
          Collection < ConfigAttribute >  atts  =   new  ArrayList < ConfigAttribute > ();
          ConfigAttribute ca  = new SecurityConfig( "ROLE_LOGIN" );
          atts.add(ca);
          resourceMap.put( "/**" , atts);
          resourceMap.put( "/index.jsp" , atts);
         
     } 
 
      // 根据URL，找到相关的权限配置。
      @Override
      public Collection<ConfigAttribute> getAttributes(Object object)
              throws IllegalArgumentException {

          // object 是一个URL，被用户请求的url。
          String url = ((FilterInvocation) object).getRequestUrl();
          System.out.println("url" + url);
          int firstQuestionMarkIndex = url.indexOf("?");

          if (firstQuestionMarkIndex != -1) {
              url = url.substring(0, firstQuestionMarkIndex);
          }

          Iterator<String> ite = resourceMap.keySet().iterator();

          while (ite.hasNext()) {
              String resURL = ite.next();

              if (url.equals(resURL)) {

                  return resourceMap.get(resURL);
              }
          }
          //无匹配url时返回所有请求
          return resourceMap.get("/**");
      }

      @Override
      public boolean supports(Class<?> arg0) {

          return true;
      } 
     
      public  Collection < ConfigAttribute >  getAllConfigAttributes()  {
          return   null ;
     }

	public PowerDao getPowerDao() {
		return powerDao;
	}

	public void setPowerDao(PowerDao powerDao) {
		this.powerDao = powerDao;
	} 
 
 }
