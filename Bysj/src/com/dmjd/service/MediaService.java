package com.dmjd.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmjd.dao.MediaDao;
import com.dmjd.dao.MediaDaoImpl;
import com.dmjd.database.ConSql;
import com.dmjd.entity.Vedio;
import com.dmjd.entity.Pict;

public class MediaService implements MediaDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private MediaDao dao = null;
	
	public MediaService() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		this.con = db.getCon();
		this.dao = new MediaDaoImpl(this.con);
	}
	
	@Override
	public boolean executeCodecs(String ffmpegPath, String upFilePath,
			String codcFilePath, String mediaPicPath) throws Exception {
		boolean mask = false;
		try {
			mask = this.dao.executeCodecs(ffmpegPath, upFilePath, codcFilePath, mediaPicPath);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.con.close();
		}
		return mask;
	}

	@Override
	public int saveVedio(Vedio vedio) throws Exception {
		int result = 0;
		try {
			result = this.dao.saveVedio(vedio);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public ArrayList<Vedio> getAllMediaCount() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Vedio> vedios = new ArrayList<>();
		try {
			vedios = this.dao.getAllMediaCount();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vedios;
	}

	public ArrayList<Pict> getAllPicts() throws Exception{
		ArrayList<Pict> picts = new ArrayList<>();
		try {
			picts = this.dao.getAllPicts();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return picts;
	}
	
	@Override
	public Vedio queryMediaById(int id) throws Exception {
		// TODO Auto-generated method stub

		return null;
	}

//	@Override
//	public int savePict(ArrayList<Pict> picts) throws Exception {
//		// TODO Auto-generated method stub
//		int result = 0;
//		try {
//			result = this.dao.savePict(picts);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}

	@Override
	public int savePict(Pict pict) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = this.dao.savePict(pict);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public String findVedioByMd5(String value) throws Exception {
		// TODO Auto-generated method stub
		String md5 = null;
		try {
			md5 = this.dao.findVedioByMd5(value);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return md5;
	}

	@Override
	public Boolean IfSameVedio(Vedio vedio) throws Exception {
		// TODO Auto-generated method stub
		boolean mask = false;
		try {
			mask = this.dao.IfSameVedio(vedio);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mask;
	}

	@Override
	public int delVedio(int id) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = this.dao.delVedio(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return result;
	}

}
