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

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	//默认构造函数实例化
	public ArticleDaoImpl(Connection con){
		this.con= con;
	}
	

	/***
	 * 添加新闻---标题、作者、来源、内容、级别、时间、所属栏目、发表状态
	 */
	@Override
	public int addArticle(Article article) throws Exception {
		int result = 0;
		String sql = "insert into article(title, writer, `from`,"
				+ " content, layers, releasetime, cid, `status`) "
				+ "values(?,?,?,?,?,?,?,0)";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, article.getTitle());//新闻标题
		pstmt.setString(2, article.getWriter());//新闻作者
		pstmt.setString(3, article.getFrom());//新闻来源
		pstmt.setString(4, article.getContent());//新闻内容
		pstmt.setInt(5, article.getLayers());//新闻级别
		pstmt.setTimestamp(6, Timestamp.valueOf(article.getReleasetime()));//新闻时间----待修改
		pstmt.setInt(7, article.getCid());//新闻所属栏目编号
		System.out.println("getTitle:"+article.getTitle()+" getWriter():"+article.getWriter()+" getFrom():"+
		article.getFrom()+" getContent():"+article.getContent()+" getLayers():"+article.getLayers()+" getReleasetime():"+
				Timestamp.valueOf(article.getReleasetime())+" getCid():"+article.getCid());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}


	/***
	 * 通过编号修改新闻---标题、作者、来源、内容、级别、发表时间和所属栏目
	 */
	@Override
	public int editArticle(int nid,Article article) throws Exception {
		int result = 0;
		String sql = "update article set title=?,writer=?, `from`=?,"
				+ "content=?, layers=?, releasetime=?, cid=? where nid=?";
		pstmt =this.con.prepareStatement(sql);
		pstmt.setString(1, article.getTitle());
		pstmt.setString(2, article.getWriter());
		pstmt.setString(3, article.getFrom());
		pstmt.setString(4, article.getContent());
		pstmt.setInt(5, article.getLayers());
		pstmt.setTimestamp(6, Timestamp.valueOf(article.getReleasetime()));
		pstmt.setInt(7, article.getCid());
		pstmt.setInt(8, nid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}


	/**
	 * 删除新闻
	 */
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


	/**
	 * 通过新闻标题查询新闻
	 */
	@Override
	public ArrayList<Article> queryBytitle(String title)
			throws Exception {
		ArrayList<Article> articles = new ArrayList<>();
		String sql = "select * from article where title like ?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, "%"+title+"%");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Article article = new Article();
			article.setNid(rs.getInt(1));
			article.setTitle(rs.getString(2));
			article.setWriter(rs.getString(3));
			article.setFrom(rs.getString(4));
			article.setHits(rs.getInt(5));
			article.setContent(rs.getString(6));
			article.setGreats(rs.getInt(7));
			article.setLayers(rs.getInt(8));
			article.setReleasetime(rs.getString(9));
			article.setStatus(rs.getInt(10));
			article.setCid(rs.getInt(11));
			articles.add(article);
		}
		rs.close();
		pstmt.close();
		return articles;
	}


	/***
	 * 发布新闻
	 */
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


	/**
	 * 查找所有新闻
	 */
	@Override
	public ArrayList<Article> FindAll() throws Exception {
		ArrayList<Article> articles = new ArrayList<>();
		String sql = "select * from article left join `column` on `column`.cid=article.cid";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Article article = new Article();
			article.setNid(rs.getInt(1));//新闻编号
			article.setTitle(rs.getString(2));//标题
			article.setWriter(rs.getString(3));//作者
			article.setFrom(rs.getString(4));//来源
			article.setHits(rs.getInt(5));//点击量
			article.setContent(rs.getString(6));//内容
			article.setGreats(rs.getInt(7));//点赞了
			article.setLayers(rs.getInt(8));//层次
			article.setReleasetime(rs.getString(9));//编辑时间
			article.setStatus(rs.getInt(10));//发表状态
			article.setCid(rs.getInt(11));//
			article.setColumnname(rs.getString(13));//所属栏目名称
			articles.add(article);
		}
		rs.close();
		pstmt.close();
		return articles;
	}


	/***
	 * 撤销新闻
	 */
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

	/***
	 * 通过新闻id查询新闻
	 */
	@Override
	public Article queryBynid(int nid) throws Exception {
		Article article = new Article();
		String sql = "select * from article where nid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, nid);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			article.setNid(rs.getInt(1));
			article.setTitle(rs.getString(2));
			article.setWriter(rs.getString(3));
			article.setFrom(rs.getString(4));
			article.setHits(rs.getInt(5));
			article.setContent(rs.getString(6));
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
