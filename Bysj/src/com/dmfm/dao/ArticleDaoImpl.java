package com.dmfm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dmfm.pojo.Article;
import com.dmfm.pojo.Menu;
import com.dmfm.pojo.Reply;
import com.dmfm.pojo.Words;

public class ArticleDaoImpl implements ArticleDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	public ArticleDaoImpl(Connection con){
		this.con = con;
	}
	
	@Override
	public ArrayList<Menu> findAllArticles() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Menu> menus = new ArrayList<Menu>();
		ArrayList<Article> articles = new ArrayList<Article>();
		String sql = "select * from `column`";
		pstmt = this.con.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while (rs.next()) {
			Menu menu = new Menu();
			menu.setId(rs.getInt(1));
			menu.setName(rs.getString(2));
			menu.setPid(rs.getInt(3));
			menus.add(menu);
		}
		sql = "select * from article where status=1";//查询发布的新闻。
		pstmt = this.con.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while (rs.next()) {
			Article article = new Article();
			article.setNid(rs.getInt(1));
			article.setTitle(rs.getString(2));
			article.setWriter(rs.getString(3));
			article.setReleasetime(rs.getString(9));
			article.setCid(rs.getInt(11));
			articles.add(article);
		}
		//存放的最后结果
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		//先找到所有二级菜单
		for (int i = 0; i < menus.size(); i++) {
			//二级菜单pid为1
			if(menus.get(i).getPid() == 2){
				menuList.add(menus.get(i));
			}
		}
		//将文章插入到menu中
		for (Menu menu : menuList) {
			menu.setChildArticles(getChild(menu.getId(), articles));
		}
		System.out.println("---------------");
		for (Menu menu : menuList) {
			System.out.println("menu:"+menu);
		}
		rs.close();
		pstmt.close();
		System.out.println("查询文章完毕");
		return menuList;
	}
	
	private ArrayList<Article> getChild(int id,ArrayList<Article> articles){
		ArrayList<Article> childList = new ArrayList<Article>();
		for (Article article : articles) {
			if(article.getCid() == id ){
				childList.add(article);
			}
		}
		return childList;
	}

	@Override
	public Article queryById(int id) throws Exception {
		// TODO Auto-generated method stub
		Article article = new Article();
		String sql = "select * from article where nid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			article.setNid(rs.getInt(1));
			article.setTitle(rs.getString(2));
			article.setWriter(rs.getString(3));
			article.setFrom(rs.getString(4));
			article.setHits(rs.getInt(5));
			article.setContent(rs.getString(6));
			article.setGreats(rs.getInt(7));
			article.setReleasetime(rs.getString(9));
			article.setStatus(rs.getInt(10));
			article.setCid(rs.getInt(11));
		}
		rs.close();
		pstmt.close();
		return article;
	}
	
	public String detail(int id) throws Exception{
		String content = null;
		String sql = "select content from article where nid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			content = rs.getString(1);
		}
		rs.close();pstmt.close();return content;
	}

	@Override
	public ArrayList<Article> findArticles(int id) throws Exception {
		// TODO Auto-generated method stub
		String sql = null;
		ArrayList<Article> articles = new ArrayList<>();
		//当传值过来id小于2000，则为一级菜单栏目id；大于2000则为二级
		if (id < 2000) {
			sql = "select * from article join `column` on article.cid=`column`.cid and article.cid in (select cid from `column` where kid=? ) and status=1 order by nid desc;";//暂时查询发布的新闻。
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setNid(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setWriter(rs.getString(3));
				article.setFrom(rs.getString(4));
				article.setContent(rs.getString(6));
				article.setProfile(setProfile(rs.getString(6)));//获取文章文本并截取首段前40个文字
				article.setSrc(setSrc(rs.getString(6)));//获取文章中首张图片
				article.setReleasetime(rs.getString(9));
				article.setCid(rs.getInt(11));
				article.setCname(rs.getString(13));//获取文章所属栏目名称
				articles.add(article);
			}
		}else if (id > 2000) {
			sql = "select * from article join `column` on article.cid=`column`.cid and article.cid=? and status=1 order by nid desc;";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setNid(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setWriter(rs.getString(3));
				article.setFrom(rs.getString(4));
				article.setContent(rs.getString(6));
				article.setProfile(setProfile(rs.getString(6)));//获取文章文本并截取首段前40个文字
				article.setSrc(setSrc(rs.getString(6)));//获取文章中首张图片
				article.setReleasetime(rs.getString(9));
				article.setCid(rs.getInt(11));
				article.setCname(rs.getString(13));//获取文章所属栏目名称
				articles.add(article);
			}
		}
		rs.close();
		pstmt.close();
		return articles;
	}
	
	@Override
	public int saveWords(Words words) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into words(lw_name,lw_date,lw_content,lw_for_name,lw_for_article_id) values(?,?,?,?,?)";
		int result = 0;
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, words.getLw_name());
		pstmt.setString(2, words.getLw_date());
		pstmt.setString(3, words.getLw_content());
		pstmt.setString(4, words.getLw_for_name());
		pstmt.setString(5, words.getLw_for_article_id());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int saveReply(Reply reply) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into reply(lr_name,lr_date,lr_content,lr_for_name,lr_for_words,lr_for_reply,lr_for_article_id) values(?,?,?,?,?,?,?) ";
		int result = 0;
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, reply.getLr_name());
		pstmt.setString(2, reply.getLr_date());
		pstmt.setString(3, reply.getLr_content());
		pstmt.setString(4, reply.getLr_for_name());
		pstmt.setString(5, reply.getLr_for_words());
		pstmt.setString(6, reply.getLr_for_reply());
		pstmt.setString(7, reply.getLr_for_article_id());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public ArrayList<Words> findByWords() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from words";
		ArrayList<Words> words = new ArrayList<>();
		pstmt = this.con.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while (rs.next()) {
			Words word = new Words();
			word.setLw_id(rs.getInt(1));
			word.setLw_name(rs.getString(2));
			word.setLw_date(rs.getString(3));
			word.setLw_content(rs.getString(4));
			word.setLw_for_name(rs.getString(5));			
			word.setLw_for_article_id(rs.getString(6));
			System.out.println();
			words.add(word);
		}
		rs.close();
		pstmt.close();
		return words;
	}

	@Override
	public ArrayList<Reply> findByReply() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from reply";
		ArrayList<Reply> replies = new ArrayList<>();
		pstmt = this.con.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while (rs.next()) {
			Reply reply = new Reply();
			reply.setLr_id(rs.getInt(1));
			reply.setLr_name(rs.getString(2));
			reply.setLr_date(rs.getString(3));
			reply.setLr_content(rs.getString(4));
			reply.setLr_for_name(rs.getString(5));
			reply.setLr_for_words(rs.getString(6));
			reply.setLr_for_reply(rs.getString(6));
			reply.setLr_for_article_id(rs.getString(8));			
			replies.add(reply);
		}
		rs.close();
		pstmt.close();
		return replies;
	}
	
	
	
	/***
	 * 截取文章前30个字
	 * @param html
	 * @return
	 */
	public String setProfile(String html) {
		String text = Jsoup.parse(html).text();
		if (text.length() == 0) {
			System.out.println("没有文字");
		}else if (text.length() <= 40) {
			System.out.println("不到40");
			text = text.substring(0, text.length());
		}else{
			System.out.println("截图前40");
			text = text.substring(0, 40)+"...";
		}
		return text;
	}
	
	/***
	 * 截取文章第一章图片并返回，若无图片则返回空
	 * @param html
	 * @return
	 */
	public String setSrc(String html){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("img[src]");//获取到的值为所有的<img src="...">
		ArrayList<String> srcs = new ArrayList<>();
		int i = 0;
		//获得图片地址
		System.out.println("----获得图片地址----");
		for (Element element : elements) {
			String src = element.attr("src");//获取到src的值
			System.out.println("src:"+src);
//			String imgUrl = src.replace("/Dmjd", "D:\\Program Files\\Tomcat\\apache-tomcat-7.0.90\\webapps\\Dmjd");
			srcs.add(i,src);
			i++;
		}
		if (srcs.isEmpty()) {
			System.out.println("没有图片");
			return null;
		}else {
			for (String string : srcs) {
				System.out.println("string:"+string);
			}				
			return srcs.get(0);
		}
	}


}
