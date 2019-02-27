package com.dmjd.action.article;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Article;

public class FindallArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Article> articles = DaoFactory.getArticleDaoInstance().FindAll();
			request.getSession().getServletContext().setAttribute("articles", articles);//更新文章列表
			System.out.println("----------------------------\n"
					 + "------更新文章列表------\n"
					 + "----------------------------\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "article/articles.jsp";
	}

}
