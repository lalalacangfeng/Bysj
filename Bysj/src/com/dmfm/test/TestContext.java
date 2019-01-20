package com.dmfm.test;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dmfm.factory.DaoFactory;

public class TestContext {
	public static void main(String[] args) {
		String text = "/1009.fm";
		text = text.substring(1, text.length()-3);

		System.out.println("servletpath:" + text);
		
		int servletpath = 2001;
		
		try {
			ArrayList<String> menus = DaoFactory.getColumnDaoInstance().findMenu(servletpath);
			for (String string : menus) {
				System.out.println("menu:"+string);				
			}
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
		
	}
}
