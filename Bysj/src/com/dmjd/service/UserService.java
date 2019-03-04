package com.dmjd.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.dmjd.dao.UserDao;
import com.dmjd.dao.UserDaoImpl;
import com.dmjd.database.ConSql;
import com.dmjd.pojo.User;

public class UserService implements UserDao {

	ConSql db = new ConSql();
	private Connection con = null;// 定义数据库连接类
	private UserDao dao = null;

	// 构造方法中实例化数据库连接同时实例化dao对象
	public UserService() throws ClassNotFoundException, SQLException {
		this.con = db.getCon();
		this.dao = new UserDaoImpl(this.con);
	}

	@Override
	public ArrayList<User> FindAll() throws Exception {
		ArrayList<User> users = new ArrayList<>();
		//Vector<User> uservector = new Vector<>();
		try {
			users = this.dao.FindAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return users;
	}
	
	@Override
	public int addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = this.dao.addUser(user);
		} catch (Exception e) {
			throw e;
		} finally {
			this.con.close();
		}
		return result;
	}

	@Override
	public int editInf(int uid, String username, String email) throws Exception {
		int result = 0;
		try {
			result = this.dao.editInf(uid, username, email);
		} catch (Exception e) {
			throw e;
		} finally {
			this.con.close();
		}
		return result;
	}

	@Override
	public int editPasswd(int uid, String password) throws Exception {
		int result = 0;
		try {
			result = this.dao.editPasswd(uid, password);
		} catch (Exception e) {
			throw e;
		} finally {
			this.con.close();
		}
		return result;
	}

	@Override
	public int deleteUser(int uid) throws Exception {
		int result = 0;
		try {
			result = this.dao.deleteUser(uid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.con.close();
		}
		return result;
	}

	@Override
	public User queryByName(String username) throws Exception {
		User user = new User();
		try {
			user = this.dao.queryByName(username);
		} catch (Exception e) {
			throw e;
		} finally {
			this.con.close();
		}
		return user;
	}

	@Override
	public User queryByEmail(String email) throws Exception {
		User user = new User();
		try {
			user = this.dao.queryByEmail(email);
		} catch (Exception e) {
			throw e;
		} finally {
			this.con.close();
		}
		return user;
	}

	@Override
	public int resetNumberAndTime(int uid, int number, String misstime)
			throws Exception {
		int result = 0;
		try {
			result = this.dao.resetNumberAndTime(uid, number, misstime);
		} catch (Exception e) {
			throw e;
		} finally {
			this.con.close();
		}
		return result;
	}

	@Override
	public int updateRole(int id, int role) throws Exception {
		int result = 0;
		try {
			result = this.dao.updateRole(id, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
