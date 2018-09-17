package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Article;
import com.dmjd.factory.DaoFactory;

public class ShowArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int nid = Integer.parseInt(request.getParameter("nid"));
			System.out.println("显示新闻");
			Article article = DaoFactory.getArticleDaoInstance().queryBynid(nid);
			if (article!=null) {
				request.setAttribute("article", article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "article/article.jsp";
	}

}
