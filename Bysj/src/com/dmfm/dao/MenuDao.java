package com.dmfm.dao;

import java.util.ArrayList;

public interface MenuDao {
	
	//查詢欄目名稱
	public ArrayList<String> findMenu(int id) throws Exception;
}
