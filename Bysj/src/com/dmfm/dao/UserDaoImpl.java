package com.dmfm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dmfm.pojo.User;

public class UserDaoImpl implements UserDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集

	//默认构造方法中实例化数据库连接
	public UserDaoImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public User queryByName(String username) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		String sql = "select * from users where username=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, username);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			user.setUid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setLasttime(rs.getDate(5));
			user.setRole(rs.getInt(6));
		}
		rs.close();
		pstmt.close();
		return user;
	}

	@Override
	public int register(User user) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "insert into users(username, password, email, lastlogin) values(?,?,?,now())";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getEmail());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int checkName(String username) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "select * from users where username=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, username);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			result = 1;
		}
		return result;
	}

	@Override
	public int checkEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "select * from users where email=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, email);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			result = 1;
		}
		return result;
	}

}
