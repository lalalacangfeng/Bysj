package com.dmjd.action.article;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import com.dmjd.action.Action;
import com.dmjd.entity.Article;
import com.dmjd.factory.DaoFactory;

public class FindallArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ArrayList<Article> articles = DaoFactory.getArticleDaoInstance().FindAll();
			request.getSession().getServletContext().setAttribute("articles", articles);//更新文章列表
			System.out.println("----------------------------\n"
					 + "------更新文章列表------\n"
					 + "----------------------------\n");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "article/articles.jsp";
	}

}
