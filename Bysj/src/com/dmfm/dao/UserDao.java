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
	
	public int editinf(User user) throws Exception;
	
	public int editpass(String pass,int uid) throws Exception;
	
	/**
	 * 根据id更新登录次数与时间
	 * @param uid 用户id
	 * @param number 错误次数
	 * @param misstime 允许登录时间
	 * @return 1
	 * @throws Exception
	 */
	public int resetNumberAndTime(int uid, int number, String misstime) throws Exception;
}
