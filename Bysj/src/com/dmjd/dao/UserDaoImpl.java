package com.dmjd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.dmjd.entity.User;

public class UserDaoImpl implements UserDao {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	Vector<User> uservector = new Vector<User>();

	public UserDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<User> FindAll() throws Exception {
		ArrayList<User> userlist = new ArrayList<User>();
		String sql = "select * from users";
		pstmt = this.con.prepareStatement(sql);
		rs =this.pstmt.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setUid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setEmail(rs.getString(4));
			user.setLasttime(rs.getDate(5));
//			System.out.println("rs2:"+rs.getString(1));
			userlist.add(user);
//			System.out.println("userlist:"+userlist.get(0).getUsername());
		}
		rs.close();
		pstmt.close();
		return userlist;
	}

	@Override
	public int addUser(User user) throws Exception {
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
	public int editInf(int uid, String username, String email) throws Exception {
		System.out.println(uid + "----" + username + "----" + email);
		String sql = "update users set username=?,email=? where uid=?";
		int result = 0;
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, email);
		pstmt.setInt(3, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int editPasswd(int uid, String password) throws Exception {
		String sql = "update users set password=? where uid=?";
		int result = 0;
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int deleteUser(int uid) throws Exception {
		String sql = "delete from users where uid=?";
		int result = 0;
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public User queryByName(String username) throws Exception {
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
	public User queryByEmail(String email) throws Exception {
		String sql = "select * from users where email=?";
		User user = new User();
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, email);
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
}
