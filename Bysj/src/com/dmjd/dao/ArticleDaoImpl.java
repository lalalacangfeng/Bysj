package com.dmjd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.dmjd.entity.Article;
import com.dmjd.entity.Column;

public class ArticleDaoImpl implements ArticleDao {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArticleDaoImpl(Connection con){
		this.con= con;
	}
	

	@Override
	public ArrayList<Column> initColumn() throws Exception {
		ArrayList<Column> columns = new ArrayList<>();
		String sql = "select * from class where kid<=2";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			Column column = new Column();
			column.setCid(rs.getInt(1));
			column.setClassname(rs.getString(2));
			column.setKid(rs.getInt(3));
			columns.add(column);
		}
		rs.close();
		pstmt.close();
		return columns;
	}

	@Override
	public int addArticle(Article article) throws Exception {
		int result = 0;
		String sql = "insert into article(articletitle, articlewriter, `from`,"
				+ " articlecontent, layers, releasetime, cid, `status`) "
				+ "values(?,?,?,?,?,?,?,0)";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, article.getArticletitle());
		pstmt.setString(2, article.getArticlewriter());
		pstmt.setString(3, article.getFrom());
		pstmt.setString(4, article.getArticlecontent());
		pstmt.setInt(5, article.getLayers());
		pstmt.setTimestamp(6, Timestamp.valueOf(article.getReleasetime()));
		pstmt.setInt(7, article.getCid());
		System.out.println("getArticletitle:"+article.getArticletitle()+" getArticlewriter():"+article.getArticlewriter()+" getFrom():"+
		article.getFrom()+" getArticlecontent():"+article.getArticlecontent()+" getLayers():"+article.getLayers()+" getReleasetime():"+
				Timestamp.valueOf(article.getReleasetime())+" getCid():"+article.getCid());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}


	@Override
	public int editArticle(int nid,Article article) throws Exception {
		int result = 0;
		String sql = "update article set articletitle=?,articlewriter=?, `from`=?,"
				+ "articlecontent=?, layers=?, releasetime=?, cid=? where nid=?";
		pstmt =this.con.prepareStatement(sql);
		pstmt.setString(1, article.getArticletitle());
		pstmt.setString(2, article.getArticlewriter());
		pstmt.setString(3, article.getFrom());
		pstmt.setString(4, article.getArticlecontent());
		pstmt.setInt(5, article.getLayers());
		pstmt.setTimestamp(6, Timestamp.valueOf(article.getReleasetime()));
		pstmt.setInt(7, article.getCid());
		pstmt.setInt(8, nid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}


	@Override
	public int deleteArticle(int nid) throws Exception {
		int result = 0;
		String sql = "delete from article where nid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, nid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}


	@Override
	public ArrayList<Article> queryBytitle(String articletitle)
			throws Exception {
		ArrayList<Article> articleList = new ArrayList<>();
		String sql = "select * from article where articletitle like ?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, "%"+articletitle+"%");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Article article = new Article();
			article.setNid(rs.getInt(1));
			article.setArticletitle(rs.getString(2));
			article.setArticlewriter(rs.getString(3));
			article.setFrom(rs.getString(4));
			article.setHits(rs.getInt(5));
			article.setArticlecontent(rs.getString(6));
			article.setGreats(rs.getInt(7));
			article.setLayers(rs.getInt(8));
			article.setReleasetime(rs.getString(9));
			article.setStatus(rs.getInt(10));
			article.setCid(rs.getInt(11));
			articleList.add(article);
		}
		rs.close();
		pstmt.close();
		return articleList;
	}


	@Override
	public int releaseArticle(int nid) throws Exception {
		int result = 0;
		String sql = "update article set `status`=1 where nid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, nid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}


	@Override
	public ArrayList<Article> FindAll() throws Exception {
		ArrayList<Article> articleList = new ArrayList<>();
		String sql = "select * from article left join class on class.cid=article.cid";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Article article = new Article();
			article.setNid(rs.getInt(1));
			article.setArticletitle(rs.getString(2));
			article.setArticlewriter(rs.getString(3));
			article.setFrom(rs.getString(4));
			article.setHits(rs.getInt(5));
			article.setArticlecontent(rs.getString(6));
			article.setGreats(rs.getInt(7));
			article.setLayers(rs.getInt(8));
			article.setReleasetime(rs.getString(9));
			article.setStatus(rs.getInt(10));
			article.setCid(rs.getInt(11));
			article.setClassname(rs.getString(13));
			articleList.add(article);
		}
		rs.close();
		pstmt.close();
		return articleList;
	}


	@Override
	public int cancelArticle(int nid) throws Exception {
		int result = 0;
		String sql = "update article set `status`=0 where nid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, nid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}


	@Override
	public Article queryBynid(int nid) throws Exception {
		Article article = new Article();
		String sql = "select * from article where nid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, nid);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			article.setNid(rs.getInt(1));
			article.setArticletitle(rs.getString(2));
			article.setArticlewriter(rs.getString(3));
			article.setFrom(rs.getString(4));
			article.setHits(rs.getInt(5));
			article.setArticlecontent(rs.getString(6));
			article.setGreats(rs.getInt(7));
			article.setLayers(rs.getInt(8));
			article.setReleasetime(rs.getString(9));
			article.setStatus(rs.getInt(10));
			article.setCid(rs.getInt(11));
		}
		rs.close();
		pstmt.close();
		return article;
	}

}
