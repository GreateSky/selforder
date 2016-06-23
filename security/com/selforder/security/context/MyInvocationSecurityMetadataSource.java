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
 
      private   void  loadResourceDefine()  {
    	  List<Map> refList = powerDao.allRoleResourceList();
    	  if(null != refList && refList.size()>0){
    		  for(Map map :refList){
    			  String role = map.get("ROLE_CODE").toString();
    			  String url = map.get("RESOURCE_URL").toString();
    			  resourceMap  =   new  HashMap < String, Collection < ConfigAttribute >> ();
		          Collection < ConfigAttribute >  atts  =   new  ArrayList < ConfigAttribute > ();
		          ConfigAttribute ca  = new SecurityConfig(role);
		          atts.add(ca);
		          resourceMap.put(url, atts);
    		  }
    		  //访问所有资源都需登录权限
    		  resourceMap  =   new  HashMap < String, Collection < ConfigAttribute >> ();
	          Collection < ConfigAttribute >  atts  =   new  ArrayList < ConfigAttribute > ();
	          ConfigAttribute ca  = new SecurityConfig( "ROLE_LOGIN" );
	          atts.add(ca);
	          resourceMap.put( "/**" , atts);
    	  }
         
     } 
 
      //  According to a URL, Find out permission configuration of this URL. 
      public  Collection < ConfigAttribute >  getAttributes(Object object)
              throws  IllegalArgumentException  {
          //  guess object is a URL. 
         String url  =  ((FilterInvocation)object).getRequestUrl();
         Iterator < String >  ite  =  resourceMap.keySet().iterator();
          while  (ite.hasNext()){
             String resURL  =  ite.next();
             return  resourceMap.get(resURL);
         } 
          return   null ;
     } 
 
      public   boolean  supports(Class <?>  clazz)  {
          return   true ;
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
