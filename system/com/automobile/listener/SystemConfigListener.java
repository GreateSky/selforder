/**   
* 项目名：用户库系统
* 文件名： SystemConfigListener   
* 描  述：配置文件监听
*/
package com.automobile.listener;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 配置文件监听类 将资源文件读取到application
 * @创建人 郝水生
 * @创建日期 2014-05-30
 * @修改人
 * @修改日期                                  
 * @修改目的 
 */
public class SystemConfigListener implements ServletContextListener {
    /**
     * 读取资源文件
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        Properties properties = new Properties();
        try
        {
            InputStream propertiesStream = this.getClass().getResourceAsStream("/systemConfig.properties");
            properties.load(propertiesStream);
            Set<String> propertyNames = properties.stringPropertyNames();
            for (String propertyName : propertyNames)
            {
                application.setAttribute(propertyName, properties.getProperty(propertyName));
            }
            SystemConfig.config(properties);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
