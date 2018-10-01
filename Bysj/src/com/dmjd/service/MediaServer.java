package com.dmjd.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dmjd.dao.MediaDao;
import com.dmjd.dao.MediaDaoImpl;
import com.dmjd.database.ConSql;
import com.dmjd.entity.Media;

public class MediaServer implements MediaDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private MediaDao dao = null;
	
	public MediaServer() throws ClassNotFoundException, SQLException{
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
	public int saveMedia(Media media) throws Exception {
		int result = 0;
		try {
			result = this.dao.saveMedia(media);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public int getAllMediaCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Media queryMediaById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
