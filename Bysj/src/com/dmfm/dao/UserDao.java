package com.dmfm.dao;

import com.dmfm.pojo.User;



public interface UserDao {

	// 根据用户名查询用户
	public User queryByName(String username) throws Exception;
	
	// 注册用户
	public int register(User user) throws Exception;
	
	//查询用户名是否存在
	public int checkName(String username) throws Exception;
	
	//查询邮箱是否存在
	public int checkEmail(String email) throws Exception;
	
}
