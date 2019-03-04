package com.dmjd.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dmjd.dao.GonggaoDao;
import com.dmjd.dao.GonggaoDaoImpl;
import com.dmjd.database.ConSql;
import com.dmjd.pojo.Gonggao;

public class GonggaoService implements GonggaoDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private GonggaoDao dao = null;
	
	public GonggaoService() throws SQLException, ClassNotFoundException{
		this.con = db.getCon();
		this.dao = new GonggaoDaoImpl(con);
	}
	
	@Override
	public Gonggao show() throws Exception {
		Gonggao gonggao = new Gonggao();
		try {
			gonggao = dao.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gonggao;
	}

	@Override
	public int update(Gonggao gonggao) throws Exception {
		int result = 0;
		try {
			result = dao.update(gonggao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
