package com.dmjd.filter;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Menu;

//@WebListener
public class MyServletContextAttributeListener implements
		ServletContextAttributeListener {

	private static Logger log = Logger.getLogger("MyServletContextAttributeListener");
	
	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext context = arg0.getServletContext();
		log.debug("重新初始化");
		System.out.println("chongxingchushihua");
		ArrayList<Menu> menus;
		try {
			menus = DaoFactory.InitDaoInstance().InitMenus();
			context.setAttribute("menus", menus);
			
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
		System.out.println("columns正在初始化中...");
		System.out.println("----------------");
	}

}
