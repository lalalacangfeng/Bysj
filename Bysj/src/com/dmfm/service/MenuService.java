package com.dmfm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmfm.dao.MenuDao;
import com.dmfm.dao.MenuDaoImpl;
import com.dmfm.db.ConSql;
import com.dmfm.pojo.Menu;

public class MenuService implements MenuDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private MenuDao dao = null;
	
	public MenuService() throws ClassNotFoundException,SQLException{
		this.con = db.getCon();
		this.dao = new MenuDaoImpl(this.con);
	}

	@Override
	public ArrayList<String> findMenu(int id) throws Exception {
		ArrayList<String> menus = new ArrayList<>();
		try {
			menus = this.dao.findMenu(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return menus;
	}

	
}
