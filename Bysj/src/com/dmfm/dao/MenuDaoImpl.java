package com.dmfm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDaoImpl implements MenuDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	//默认构造方法中实例化数据库连接
	public MenuDaoImpl(Connection con){
		this.con = con;
	}
	

	@Override
	public ArrayList<String> findMenu(int id) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> menus = new ArrayList<>();
		String sql = null;
		if (id < 2000) {
			sql = "select kindname from kind where kid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				menus.add(rs.getString(1));
			}
		}else if(id > 2000) {
			sql = "select kindname,columnname from `column` join kind on `column`.kid=kind.kid and cid=? ";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				menus.add(rs.getString(1));
				menus.add(rs.getString(2));
			}
		}
		rs.close();
		pstmt.close();
		return menus;
	}

}
