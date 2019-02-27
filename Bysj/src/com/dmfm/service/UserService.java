package com.dmfm.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dmfm.dao.UserDao;
import com.dmfm.dao.UserDaoImpl;
import com.dmfm.db.ConSql;
import com.dmfm.pojo.User;

public class UserService implements UserDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private UserDao dao = null;
	
	public UserService() throws ClassNotFoundException,SQLException{
		this.con = db.getCon();
		this.dao = new UserDaoImpl(this.con);
	}
	
	
	@Override
	public User queryByName(String username) throws Exception {
		User user = new User();
		try {
			user = this.dao.queryByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return user;
	}

	@Override
	public int register(User user) throws Exception {
		int result = 0;
		try {
			result = this.dao.register(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return result;
	}


	@Override
	public int checkName(String username) throws Exception {
		int result = 0;
		try {
			result = this.dao.checkName(username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return result;
	}


	@Override
	public int checkEmail(String email) throws Exception {
		int result = 0;
		try {
			result = this.dao.checkEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return result;
	}


	@Override
	public int resetNumberAndTime(int uid, int number, String misstime)
			throws Exception {
		int result = 0;
		try {
			result = this.dao.resetNumberAndTime(uid, number, misstime);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return result;
	}


	@Override
	public int editinf(User user) throws Exception {
		int result = 0;
		try {
			result = this.dao.editinf(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return result;
	}


	@Override
	public int editpass(String pass,int uid) throws Exception {
		int result = 0;
		try {
			result = this.dao.editpass(pass,uid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return result;
	}

}
