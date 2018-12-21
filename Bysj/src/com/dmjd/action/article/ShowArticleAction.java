package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Article;

public class ShowArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int nid = Integer.valueOf(request.getParameter("nid"));
			Article article = DaoFactory.getArticleDaoInstance().queryBynid(nid);
			if (article != null) {//新闻存在
				request.setAttribute("article", article);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "article/article.jsp";
	}

}
