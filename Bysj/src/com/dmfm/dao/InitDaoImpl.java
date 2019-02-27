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
import com.dmfm.pojo.Pict;
import com.dmfm.pojo.Vedio;

public class InitDaoImpl implements InitDao{

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	//默认构造方法中实例化数据库连接
	public InitDaoImpl(Connection con){
		this.con = con;
	}
	
	@Override
	public ArrayList<Menu> InitMenus() throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<Menu> menus = new ArrayList<Menu>();//将要输出的菜单
		
		ArrayList<Menu> first = findFirst();//一级菜单
		ArrayList<Menu> second = findSecond();//二级菜单
		ArrayList<Article> articles = findArticles();//新闻
		ArrayList<Vedio> vedios = findVedios();//视频
		ArrayList<Pict> picts = findPicts();//图片
		
		//将一级菜单存入将要输出的菜单中
		for (Menu menu : first) {
			menus.add(menu);
		}
		
		//将二级菜单存入一级菜单的子菜单中，新闻视频图片存入旗下
		for (Menu menu : menus){
			menu.setChildMenus(getChildMenus(menu.getId(), second, articles));
		}
		
		//将图片存入一级菜单中
		for (Menu menu : menus) {
			menu.setChildPicts(getChildPicts(menu.getId(), picts));
		}
		
		//将视频存放到一级菜单中
		for (Menu menu : menus) {
			menu.setChildVedios(getChildVedios(menu.getId(), vedios));
		}

		return menus;
	}
	


	/***
	 * 查询一级栏目菜单
	 * @return 一级menus
	 * @throws Exception
	 */
	private ArrayList<Menu> findFirst() throws Exception{
		ArrayList<Menu> menus = new ArrayList<Menu>();
		String sql = "select * from kind ;";//查询语句---查询栏目种类表kind中所有字段
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Menu menu = new Menu();//新建一个栏目对象
			menu.setId(rs.getInt(1));//保存种类编号
			menu.setName(rs.getString(2));//保存种类名称
			menu.setPid(rs.getInt(3));//保存等级标识
			menu.setParentId(rs.getInt(4));
			menus.add(menu);//将一级栏目添加到栏目集中
		}
		rs.close();
		pstmt.close();
		System.out.println("查询1栏目完毕");
		return menus;
	}
	
	/***
	 * 查询二级栏目菜单
	 * @return 二级menus
	 * @throws Exception
	 */
	private ArrayList<Menu> findSecond() throws Exception{
		ArrayList<Menu> menus = new ArrayList<Menu>();
		String sql = "select * from `column` ;";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Menu menu = new Menu();//新建一个栏目对象
			menu.setId(rs.getInt(1));
			menu.setName(rs.getString(2));
			menu.setPid(rs.getInt(3));//保存等级标识
			menu.setParentId(rs.getInt(4));
			menus.add(menu);//将二级栏目添加到栏目集中
		}
		rs.close();
		pstmt.close();
		System.out.println("查询2栏目完毕");
		return menus;
	}
	
	/***
	 * 查询所有新闻呢
	 * @return articles
	 * @throws Exception
	 */
	private ArrayList<Article> findArticles() throws Exception{
		ArrayList<Article> articles = new ArrayList<Article>();
		String sql = "select * from `article` where status=1 order by nid desc";//暂时查询未发布的新闻。
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Article article = new Article();
			article.setNid(rs.getInt(1));
			article.setTitle(rs.getString(2));
			article.setContent(rs.getString(6));
			article.setProfile(setProfile(rs.getString(6)));//获取文章文本并截取首段前40个文字
			article.setSrc(setSrc(rs.getString(6)));//获取文章中首张图片
			article.setReleasetime(rs.getString(9));
			article.setCid(rs.getInt(11));
			articles.add(article);
		}
		rs.close();
		pstmt.close();
		System.out.println("查询新闻目完毕");
		return articles;
	}
	
	/***
	 * 查询所有视频
	 * @return vedios
	 * @throws Exception
	 */
	private ArrayList<Vedio> findVedios() throws Exception{
		ArrayList<Vedio> vedios = new ArrayList<Vedio>();
		String sql = "select * from vedio order by vid desc";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Vedio vedio = new Vedio();
			vedio.setId(rs.getInt(1));
			vedio.setName(rs.getString(2));
			vedio.setSrc(rs.getString(3));
			vedio.setPicture(rs.getString(4));
			vedio.setTime(rs.getString(8));
			vedio.setCid(rs.getInt(10));
			vedios.add(vedio);
		}
		rs.close();
		pstmt.close();
		System.out.println("查询视频目完毕");
		return vedios;
	}
	
	/***
	 * 查询所有图库
	 * @return picts
	 * @throws Exception
	 */
	private ArrayList<Pict> findPicts() throws Exception{
		ArrayList<Pict> picts = new ArrayList<Pict>();
		String sql = "select * from pict order by id desc";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Pict pict = new Pict();
			pict.setId(rs.getInt(1));
			pict.setName(rs.getString(2));
			pict.setDescript(rs.getString(3));
			pict.setSrc(rs.getString(4));
			pict.setTime(rs.getString(6));
			pict.setCid(rs.getInt(7));
			picts.add(pict);
		}
		rs.close();
		pstmt.close();
		System.out.println("查询图库完毕");
		return picts;
	}
	
	/***
	 * 存放二级菜单
	 * @param id
	 * @param menus
	 * @return
	 */
	private ArrayList<Menu> getChildMenus(int id,ArrayList<Menu> menus,
			ArrayList<Article> articles){
		// 子菜单
	    ArrayList<Menu> childList = new ArrayList<Menu>();
	    //存放子菜单
	    for (Menu menu : menus) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (menu.getPid() == 2) {
	            if (menu.getParentId() == id) {
	                childList.add(menu);
	            }
	        }
	    }
	    //存放子菜单的新闻
	    for (Menu menu : childList) {
	    	//遍历子菜单中的新闻，将其添加到子菜单上
			menu.setChildArticles(getChildArticles(menu.getId(), articles));

		}
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	}
	
	/***
	 * 存放二级菜单的新闻
	 * @param id
	 * @param articles
	 * @return
	 */
	private ArrayList<Article> getChildArticles(int id,ArrayList<Article> articles){
		ArrayList<Article> childList = new ArrayList<Article>();
		for (Article article : articles) {
			if(article.getCid() == id ){
				childList.add(article);
			}
		}
		return childList;
	}

	/***
	 * 存放一级菜单下的图片
	 * @param id
	 * @param picts
	 * @return
	 */
	private ArrayList<Pict> getChildPicts(int id,ArrayList<Pict> picts){
		ArrayList<Pict> childList = new ArrayList<Pict>();
		for (Pict pict : picts) {
			if(pict.getCid() == id ){
				childList.add(pict);
			}
		}
		return childList;
	}
	
	/***
	 * 存放一级菜单下的视频
	 * @param id
	 * @param picts
	 * @return
	 */
	private ArrayList<Vedio> getChildVedios(int id,ArrayList<Vedio> vedios){
		ArrayList<Vedio> childList = new ArrayList<Vedio>();
		for (Vedio vedio : vedios) {
			if(vedio.getCid() == id ){
				childList.add(vedio);
			}
		}
		return childList;
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
//			System.out.println("src:"+src);
//			String imgUrl = src.replace("/Dmjd", "D:\\Program Files\\Tomcat\\apache-tomcat-7.0.90\\webapps\\Dmjd");
			srcs.add(i,src);
			i++;
		}
		if (srcs.isEmpty()) {
			System.out.println("没有图片");
			return null;
		}else {
			for (String string : srcs) {
//				System.out.println("string:"+string);
			}				
			return srcs.get(0);
		}
	}
}
