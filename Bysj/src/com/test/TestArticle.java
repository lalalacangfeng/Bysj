package com.test;

import java.util.ArrayList;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Menu;

public class TestArticle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ArrayList<Menu> menus = DaoFactory.getArticleDaoInstance().findAllArticles();
			for (Menu menu : menus) {
				System.out.println(menu);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
