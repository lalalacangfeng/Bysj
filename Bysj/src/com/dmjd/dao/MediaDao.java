package com.dmjd.dao;

import java.util.ArrayList;

import com.dmjd.pojo.Pict;
import com.dmjd.pojo.Vedio;

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
	 * @param media 
	 * @return
	 * @throws Exception
	 */
	public int saveVedio(Vedio vedio)throws Exception;
	
	
	
	/**
	 * 保存图片
	 * @param picts
	 * @return
	 * @throws Exception
	 */
	public int savePict(Pict pict) throws Exception;
//	public int savePict(ArrayList<Pict> picts) throws Exception;
	
	/**
	 * 查询数据库所有记录
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Vedio> getAllMediaCount()throws Exception; 
	
	
	/***
	 * 通过md5值查询视频是否已经上传过
	 * @param md5
	 * @return
	 * @throws Exception
	 */
	public String findVedioByMd5(String value) throws Exception;
	
	
	/***
	 * 查询标题视频是否重复
	 * @param vedio
	 * @return
	 * @throws Exception
	 */
	public Boolean IfSameVedio(Vedio vedio) throws Exception;
	
	
	/***
	 * 获得所有的图片
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Pict> getAllPicts() throws Exception;
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Vedio queryMediaById(int id)throws Exception;
	
	
	/***
	 * 根据id删除视频
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delVedio(int id) throws Exception;
}
