package com.dmjd.dao;

import java.util.ArrayList;

import com.dmjd.entity.Article;
import com.dmjd.entity.Column;

public interface ArticleDao {
	
	//初始化栏目
	public ArrayList<Column> initColumn() throws Exception;

	//添加新闻
	public int addArticle(Article article) throws Exception;
	
	//根据编号查询新闻
	public Article queryBynid(int nid) throws Exception;
	
	//修改新闻
	public int editArticle(int nid,Article article) throws Exception;
	
	//删除新闻
	public int deleteArticle(int nid) throws Exception;
	
	//查找所有新闻
	public ArrayList<Article> FindAll() throws Exception;
	
	//查询新闻
	public ArrayList<Article> queryBytitle(String articletitle) throws Exception;
	
	//发布新闻
	public int releaseArticle(int nid) throws Exception;
	
	//撤销新闻
	public int cancelArticle(int nid) throws Exception;
	
}
