package com.dmfm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dmfm.pojo.Pict;

public class PictDaoImpl implements PictDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	public PictDaoImpl(Connection con){
		this.con = con;
	}
	
	@Override
	public ArrayList<Pict> findPict(int id) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Pict> picts = new ArrayList<>();
		if (id < 2000) {
			String sql = "select * from pict where cid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Pict pict = new Pict();
				pict.setId(rs.getInt(1));
				pict.setName(rs.getString(2));
				pict.setDescript(rs.getString(3));
				pict.setSrc(rs.getString(4));
				picts.add(pict);
			}
			rs.close();
			pstmt.close();
		}
		return picts;
	}

}
