package com.dmfm.dao;

import java.util.ArrayList;

import com.dmfm.pojo.Menu;

public interface InitDao {

	//初始化
	public ArrayList<Menu> InitMenus() throws Exception;
}
