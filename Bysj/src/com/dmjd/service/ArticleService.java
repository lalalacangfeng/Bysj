package com.dmjd.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmjd.dao.ArticleDao;
import com.dmjd.dao.ArticleDaoImpl;
import com.dmjd.database.ConSql;
import com.dmjd.entity.Article;

public class ArticleService implements ArticleDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private ArticleDao dao = null;
	
	// 构造方法中实例化数据库连接同时实例化dao对象
	public ArticleService() throws ClassNotFoundException, SQLException{
		this.con = db.getCon();
		this.dao = new ArticleDaoImpl(this.con);
	}
	
	@Override
	public int addArticle(Article article) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = this.dao.addArticle(article);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public Article queryBynid(int nid) throws Exception {
		// TODO Auto-generated method stub
		Article article = new Article();
		try {
			article = this.dao.queryBynid(nid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return article;
	}

	@Override
	public int editArticle(int nid, Article article) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = this.dao.editArticle(nid, article);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public int deleteArticle(int nid) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = this.dao.deleteArticle(nid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public ArrayList<Article> FindAll() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Article> articles = new ArrayList<>();
		try {
			articles = this.dao.FindAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return articles;
	}

	@Override
	public ArrayList<Article> queryBytitle(String title) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Article> articles = new ArrayList<>();
		try {
			articles = this.dao.queryBytitle(title);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return articles;
	}

	@Override
	public int releaseArticle(int nid) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = this.dao.releaseArticle(nid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

	@Override
	public int cancelArticle(int nid) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = this.dao.cancelArticle(nid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return result;
	}

}
