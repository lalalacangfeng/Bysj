package com.dmjd.dao;

import java.util.ArrayList;

import com.dmjd.entity.Column;

public interface ColumnDao {

	//查询所有栏目
	public ArrayList<Column> findAllColumns() throws Exception;
	
	//查询动漫分类标签
	public ArrayList<Column> findDmColumns() throws Exception;
	
	//添加栏目or动漫标签
	public int addColumn(Column column) throws Exception;
	
	//删除栏目or动漫标签
	public int delColumn(int cid,int kid) throws Exception;

	//显示栏目or动漫标签
	public Column showColumn(int cid,int kid) throws Exception;
	
	//初始化栏目or动漫标签
	public ArrayList<Column> initColumn(int cid,int kid) throws Exception;
	
	//修改栏目or动漫标签
	public int editColumn(int cid,int kid,Column column) throws Exception;
}
