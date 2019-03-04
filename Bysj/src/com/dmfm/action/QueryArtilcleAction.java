package com.dmfm.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.pojo.Article;
import com.dmjd.factory.DaoFactory;

public class QueryArtilcleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		ArrayList<com.dmjd.pojo.Article> articles = new ArrayList<>();
		try {
			articles = DaoFactory.getArticleDaoInstance().queryBytitle(title);
			if(articles!=null){
				request.getSession().setAttribute("fm_Menu","查询结果:"+title);
				request.setAttribute("fm_articles", articles);
				System.out.println("查询fm_articles成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fm.jsp";
	}

}
