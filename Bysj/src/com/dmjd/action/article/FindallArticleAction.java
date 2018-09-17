package com.dmjd.action.article;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Article;
import com.dmjd.factory.DaoFactory;

public class FindallArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Article> articleList = DaoFactory.getArticleDaoInstance().FindAll();
			System.out.println("执行查找所有新闻语句");
			request.setAttribute("articleList", articleList);
//			for (Article article : articleList) {
//				System.out.println("nid:"+article.getNid()+" cid:"+article.getCid()+" time:"+article.getReleasetime());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "article/articlelist.jsp";
	}

}
