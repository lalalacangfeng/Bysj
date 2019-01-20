package com.dmfm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmfm.dao.PictDao;
import com.dmfm.dao.PictDaoImpl;
import com.dmfm.db.ConSql;
import com.dmfm.pojo.Pict;

public class PictService implements PictDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private PictDao dao = null;
	
	public PictService() throws ClassNotFoundException,SQLException{
		this.con = db.getCon();
		this.dao = new PictDaoImpl(this.con);
	}
	
	@Override
	public ArrayList<Pict> findPict(int id) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Pict> picts = new ArrayList<>();
		try {
			picts = this.dao.findPict(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return picts;
	}

}
