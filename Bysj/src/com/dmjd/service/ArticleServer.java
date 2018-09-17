package com.dmjd.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmjd.dao.ArticleDao;
import com.dmjd.dao.ArticleDaoImpl;
import com.dmjd.database.ConSql;
import com.dmjd.entity.Article;
import com.dmjd.entity.Column;

public class ArticleServer implements ArticleDao {

	ConSql db = new ConSql();
	private Connection con = null;// 定义数据库连接类
	private ArticleDao dao = null;
	
	//构造方法实例化数据库连接同时实例化dao
	public ArticleServer() throws ClassNotFoundException, SQLException{
		this.con = db.getCon();
		this.dao = new ArticleDaoImpl(this.con);
	}

	@Override
	public ArrayList<Article> queryBytitle(String articletitle) throws Exception {
		ArrayList<Article> articleList = new ArrayList<>();
		try {
			articleList = this.dao.queryBytitle(articletitle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return articleList;
	}


	@Override
	public ArrayList<Column> initColumn() throws Exception {
		ArrayList<Column> columns = new ArrayList<>();
		try {
			columns = this.dao.initColumn();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return columns;
	}

	@Override
	public int addArticle(Article article) throws Exception {
		int result = 0;
		try {
			result = this.dao.addArticle(article);
		} catch (Exception e) {
			throw e;
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public int editArticle(int nid,Article article) throws Exception {
		int result = 0;
		try {
			result = this.dao.editArticle(nid,article);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public int deleteArticle(int nid) throws Exception {
		int result = 0;
		try {
			result = this.dao.deleteArticle(nid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public int releaseArticle(int nid) throws Exception {
		int result = 0;
		try {
			result = this.dao.releaseArticle(nid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public ArrayList<Article> FindAll() throws Exception {
		ArrayList<Article> articleList = new ArrayList<>();
		try {
			articleList = this.dao.FindAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return articleList;
	}

	@Override
	public int cancelArticle(int nid) throws Exception {
		int result = 0;
		try {
			result = this.dao.cancelArticle(nid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public Article queryBynid(int nid) throws Exception {
		Article article = new Article();
		try{
			article = this.dao.queryBynid(nid);
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return article;
	}

}
