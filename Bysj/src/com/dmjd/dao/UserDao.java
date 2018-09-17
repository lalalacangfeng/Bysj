package com.dmjd.dao;

import java.util.ArrayList;
import java.util.Vector;

import com.dmjd.entity.User;

public interface UserDao {
	// 添加用户
	public int addUser(User user) throws Exception;

	// 修改用户信息
	public int editInf(int uid, String username, String email) throws Exception;

	// 修改用户密码
	public int editPasswd(int uid, String password) throws Exception;

	// id--删除用户
	public int deleteUser(int uid) throws Exception;
	
	//查询所有用户
	public ArrayList<User> FindAll() throws Exception;

	// 根据用户名查询用户
	public User queryByName(String username) throws Exception;

	// 根据Email查询用户
	public User queryByEmail(String email) throws Exception;

}
