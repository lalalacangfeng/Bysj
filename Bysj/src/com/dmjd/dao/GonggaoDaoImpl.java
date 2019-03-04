package com.dmjd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dmjd.pojo.Gonggao;

public class GonggaoDaoImpl implements GonggaoDao {
	
	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	public GonggaoDaoImpl(Connection con){
		this.con = con;
	}

	@Override
	public Gonggao show() throws Exception {
		Gonggao gonggao = new Gonggao();
		String sql = "select * from gonggao";
		pstmt = this.con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()){
			gonggao.setTitle(rs.getString(1));
			gonggao.setContent(rs.getString(2));
		}rs.close();pstmt.close();
		return gonggao;
	}

	@Override
	public int update(Gonggao gonggao) throws Exception {
		int result = 0;
		String sql = "update gonggao set title=?,content=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, gonggao.getTitle());
		pstmt.setString(2, gonggao.getContent());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

}
