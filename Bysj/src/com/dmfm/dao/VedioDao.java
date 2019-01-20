package com.dmfm.dao;

import java.util.ArrayList;

import com.dmfm.pojo.Vedio;

public interface VedioDao {

	//查询所有视频
	public ArrayList<Vedio> findVedios(int id) throws Exception;
	
	//根据id查找视频
	public Vedio findVedio(int id) throws Exception;
}
