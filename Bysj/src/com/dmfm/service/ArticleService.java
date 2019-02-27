package com.dmfm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmfm.dao.ArticleDao;
import com.dmfm.dao.ArticleDaoImpl;
import com.dmfm.db.ConSql;
import com.dmfm.pojo.Article;
import com.dmfm.pojo.Menu;
import com.dmfm.pojo.Reply;
import com.dmfm.pojo.Words;

public class ArticleService implements ArticleDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private ArticleDao dao = null;

	public ArticleService() throws ClassNotFoundException, SQLException {
		this.con = db.getCon();
		this.dao = new ArticleDaoImpl(this.con);
	}

	@Override
	public ArrayList<Menu> findAllArticles() throws Exception {
		ArrayList<Menu> menus = new ArrayList<Menu>();
		try {
			menus = this.dao.findAllArticles();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.con.close();
		}
		return menus;
	}

	@Override
	public Article queryById(int id) throws Exception {
		Article article = new Article();
		try {
			article = this.dao.queryById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.con.close();
		}
		return article;
	}

	@Override
	public String detail(int id) throws Exception {
		String content = null;
		try {
			content = this.dao.detail(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.con.close();
		}
		return content;
	}

	@Override
	public ArrayList<Article> findArticles(int id) throws Exception {
		ArrayList<Article> articles = new ArrayList<>();
		try {
			articles = this.dao.findArticles(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.con.close();
		}
		return articles;
	}

	@Override
	public int saveWords(Words words) throws Exception {
		int result = 0;
		try {
			result = this.dao.saveWords(words);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.con.close();
		}
		return result;
	}

	@Override
	public int saveReply(Reply reply) throws Exception {
		int result = 0;
		try {
			result = this.dao.saveReply(reply);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.con.close();
		}
		return result;
	}

	@Override
	public ArrayList<Words> findByWords() throws Exception {
		ArrayList<Words> words = new ArrayList<>();
		try {
			words = this.dao.findByWords();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return words;
	}

	@Override
	public ArrayList<Reply> findByReply() throws Exception {
		ArrayList<Reply> replies = new ArrayList<>();
		try {
			replies = this.dao.findByReply();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return replies;
	}

}
