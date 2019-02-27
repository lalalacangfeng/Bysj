package com.dmjd.dao;

import java.util.ArrayList;

import com.dmjd.pojo.Article;
import com.dmjd.pojo.Column;

public interface ArticleDao {

	/**
	 * 添加新闻
	 * @param article 新闻
	 * @return 1
	 * @throws Exception
	 */
	public int addArticle(Article article) throws Exception;
	
	/**
	 * 根据编号查询新闻
	 * @param nid 编号
	 * @return 新闻
	 * @throws Exception
	 */
	public Article queryBynid(int nid) throws Exception;
	
	/**
	 * 修改新闻
	 * @param nid
	 * @param article
	 * @return 1
	 * @throws Exception
	 */
	public int editArticle(int nid,Article article) throws Exception;
	
	/**
	 * 删除新闻
	 * @param nid
	 * @return 1
	 * @throws Exception
	 */
	public int deleteArticle(int nid) throws Exception;
	
	/**
	 * 查找所有新闻
	 * @return 新闻列表
	 * @throws Exception
	 */
	public ArrayList<Article> FindAll() throws Exception;
	
	/**
	 * 查询新闻
	 * @param title
	 * @return 新闻列表
	 * @throws Exception
	 */
	public ArrayList<Article> queryBytitle(String title) throws Exception;
	
	/**
	 * 发布新闻
	 * @param nid
	 * @return 1
	 * @throws Exception
	 */
	public int releaseArticle(int nid) throws Exception;
	
	/**
	 * 撤销新闻
	 * @param nid
	 * @return 1
	 * @throws Exception
	 */
	public int cancelArticle(int nid) throws Exception;
	
}
