package com.dmfm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmfm.dao.MenuDaoImpl;
import com.dmfm.dao.VedioDao;
import com.dmfm.dao.VedioDaoImpl;
import com.dmfm.db.ConSql;
import com.dmfm.pojo.Vedio;

public class VedioService implements VedioDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private VedioDao dao = null;
	
	public VedioService() throws ClassNotFoundException,SQLException{
		this.con = db.getCon();
		this.dao = new VedioDaoImpl(this.con);
	}
	
	@Override
	public ArrayList<Vedio> findVedios(int id) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Vedio> vedios = new ArrayList<>();
		try {
			vedios = this.dao.findVedios(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vedios;
	}

	@Override
	public Vedio findVedio(int id) throws Exception {
		// TODO Auto-generated method stub
		Vedio vedio = new Vedio();
		try {
			vedio = this.dao.findVedio(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vedio;
	}

}
