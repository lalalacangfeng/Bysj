package com.dmfm.dao;

import java.util.ArrayList;

import com.dmfm.pojo.Pict;

public interface PictDao {
	
	//查询所有图片
	public ArrayList<Pict> findPict(int id) throws Exception;
}
