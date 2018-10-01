package com.dmjd.dao;

import com.dmjd.entity.Media;

public interface MediaDao {
	/**
	 * 视频转码
	 * @param ffmpegPath 转码工具存放路径
	 * @param upFilePath 用于指定要转换格式的文件，要截图的视频源文件
	 * @param codcFilePath 格式转换后的文件保存路径
	 * @param mediaPicPath 截图保存路径
	 * @return
	 * @throws Exception
	 */
	public boolean executeCodecs(String ffmpegPath,String upFilePath,String codcFilePath,String mediaPicPath)throws Exception;
	
	/**
	 * 保存文件
	 * @param media 视频
	 * @return
	 * @throws Exception
	 */
	public int saveMedia(Media media)throws Exception;
	
	/**
	 * 查询数据库所有记录
	 * @return
	 * @throws Exception
	 */
	public int getAllMediaCount()throws Exception; 
	
	/**
	 * 根据id查询视频
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Media queryMediaById(int id)throws Exception;  
}
