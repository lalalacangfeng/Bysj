package com.dmfm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dmfm.pojo.Vedio;

public class VedioDaoImpl implements VedioDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	public VedioDaoImpl(Connection con){
		this.con = con;
	}
	
	/* (non-Javadoc)
	 * @see com.dmfm.dao.VedioDao#findVedios(int)
	 */
	@Override
	public ArrayList<Vedio> findVedios(int id) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Vedio> vedios = new ArrayList<>();
		if (id < 2000) {
			String sql = "select * from vedio where cid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vedio vedio = new Vedio();
				vedio.setId(rs.getInt(1));
				vedio.setName(rs.getString(2));
				vedio.setSrc(rs.getString(3));
				vedio.setPicture(rs.getString(4));
				vedio.setTime(rs.getString(8));
				vedios.add(vedio);
			}
			rs.close();pstmt.close();
		}
		return vedios;
	}

	@Override
	public Vedio findVedio(int id) throws Exception {
		// TODO Auto-generated method stub
		Vedio vedio = new Vedio();
		String sql = "select * from vedio where vid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			vedio.setId(rs.getInt(1));
			vedio.setName(rs.getString(2));
			vedio.setSrc(rs.getString(3));
			vedio.setPicture(rs.getString(4));
		}
		return vedio;
	}

}
