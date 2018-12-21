package com.dmjd.dao;

import java.util.ArrayList;

import com.dmjd.pojo.Column;

public interface ColumnDao {

	//查询所有栏目
	public ArrayList<Column> findAllColumns() throws Exception;
	
	//添加栏目
	public int addColumn(Column column) throws Exception;
	
	//删除栏目
	public int delColumn(int id,int pid) throws Exception;

	//显示修改栏目
	public Column showColumn(int id,int pid) throws Exception;
	
	//修改栏目
	public int editColumn(int id,Column column) throws Exception;
}
