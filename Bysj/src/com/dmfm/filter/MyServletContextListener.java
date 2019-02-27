package com.dmfm.filter;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Menu;


@WebListener
public class MyServletContextListener implements ServletContextListener {

	private static Logger log = Logger.getLogger("MyServletContextListener");
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
				log.debug("Tomcat正在关闭中...");
				System.out.println("----------------");
				System.out.println("Tomcat正在关闭中...");
				System.out.println("----------------");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//通过servletContextEvent获得ServletContext对象
		try {
			ServletContext context = sce.getServletContext();
			String name = context.getInitParameter("project_name");
			log.debug("初始化参数name的值："+name);
			log.debug("Tomcat正在启动中...");
			ArrayList<Menu> menus = DaoFactory.InitDaoInstance().InitMenus();
			context.setAttribute("menus", menus);
			System.out.println("----------------");
			System.out.println("columns正在初始化中...");
			System.out.println("----------------");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------------");
		System.out.println("Tomcat正在启动中...");
		System.out.println("----------------");
	}

}
