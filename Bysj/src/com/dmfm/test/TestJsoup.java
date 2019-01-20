package com.dmfm.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dmfm.factory.DaoFactory;

public class TestJsoup {
	public static void main(String[] args) {
		String html;
		try {
			html = DaoFactory.getArticleDaoInstance().detail(14);
			System.out.println("输出内容:"+html);
			
			Document doc = Jsoup.parse(html);
			Elements elements = doc.select("img[src]");//获取到的值为所有的<img src="...">
			ArrayList<String> srcs = new ArrayList<>();
			int i = 0;
			//获得图片地址
			System.out.println("----获得图片地址----");
			for (Element element : elements) {
				String src = element.attr("src");//获取到src的值
				System.out.println("原图片路径:"+src);
				String imgUrl = src.replace("/Dmjd", "D:\\Program Files\\Tomcat\\apache-tomcat-7.0.90\\webapps\\Dmjd");
				srcs.add(i,imgUrl);
				i++;
			}
			if (srcs.isEmpty()) {
				System.out.println("没有图片");
			}else {
				for (String string : srcs) {
					System.out.println("string:"+string);
				}				
			}
			
			String text = Jsoup.parse(html).text();
			
			if (text.length() > 50) {
				text = text.substring(0, 50)+"...";
			}else {
				text = text.substring(0, text.length());
			}
			//存放摘要
			System.out.println("text:"+text);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
