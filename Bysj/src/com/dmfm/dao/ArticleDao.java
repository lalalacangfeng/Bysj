package com.dmfm.dao;

import java.util.ArrayList;

import com.dmfm.pojo.Article;
import com.dmfm.pojo.Menu;
import com.dmfm.pojo.Reply;
import com.dmfm.pojo.Words;

public interface ArticleDao {

	//查询所有新闻
	public ArrayList<Menu> findAllArticles() throws Exception;
	
	//显示新闻呢
	public Article queryById(int id) throws Exception;
	
	//查询信息
	public String detail(int id) throws Exception;
	
	//根据栏目查询新闻
	public ArrayList<Article> findArticles(int id) throws Exception;
	
	//保存留言
	public int saveWords(Words words) throws Exception;

	//保存回复
	public int saveReply(Reply reply) throws Exception;
	
	//显示所有留言
	public ArrayList<Words> findByWords() throws Exception;
	
	//显示所有回复
	public ArrayList<Reply> findByReply() throws Exception;
}
