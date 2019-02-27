package com.dmfm.dao;

import java.util.ArrayList;

import com.dmfm.pojo.Message;

public interface MessageDao {
	/**
	 * 
	* @Title: findMessages
	* @Description: TODO 查询留言本所有留言
	* @param @param mid
	* @param @return
	* @param @throws Exception    设定文件
	* @return ArrayList<Message>    返回类型
	* @throws
	 */
	public ArrayList<Message> findMessages(int id) throws Exception;
	
	/**
	 * 
	* @Title: addMessages
	* @Description: TODO 新增留言
	* @param @param message
	* @param @return
	* @param @throws Exception    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int addMessages(Message message) throws Exception;
	
	/**
	 * 
	* @Title: delMessages
	* @Description: TODO 删除留言
	* @param @param mid
	* @param @return
	* @param @throws Exception    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int delMessages(int mid) throws Exception;
}
