package com.dmjd.dao;

import com.dmjd.pojo.Gonggao;

public interface GonggaoDao {

	/**
	 * 
	* @Title: show
	* @Description: 显示公告
	* @param @return
	* @param @throws Exception    设定文件
	* @return Gonggao    返回类型
	* @throws
	 */
	public Gonggao show() throws Exception;
	
	/**
	 * 
	* @Title: update
	* @Description: 修改公告
	* @param @param gonggao
	* @param @return
	* @param @throws Exception    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int update(Gonggao gonggao) throws Exception;
}
