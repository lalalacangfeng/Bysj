package com.dmfm.test;

import java.util.ArrayList;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Menu;

public class TestInit {
	public static void main(String[] args) {
		try {
			ArrayList<Menu> menus = DaoFactory.InitDaoInstance().InitMenus();
			for (Menu menu : menus) {
				System.out.println("menu:"+menu);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
