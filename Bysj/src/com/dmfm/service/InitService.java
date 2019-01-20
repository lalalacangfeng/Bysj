package com.dmfm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmfm.dao.InitDao;
import com.dmfm.dao.InitDaoImpl;
import com.dmfm.db.ConSql;
import com.dmfm.pojo.Menu;

public class InitService implements InitDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private InitDao dao = null;
	
	public InitService() throws ClassNotFoundException,SQLException{
		this.con = db.getCon();
		this.dao = new InitDaoImpl(this.con);
	}
	

	@Override
	public ArrayList<Menu> InitMenus() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Menu> menus = new ArrayList<Menu>();
		try {
			menus = this.dao.InitMenus();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return menus;
	}

}
