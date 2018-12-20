package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Article;
import com.dmjd.factory.DaoFactory;

public class InitArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int nid = Integer.valueOf(request.getParameter("nid"));
			Article article = DaoFactory.getArticleDaoInstance().queryBynid(nid);
			if (article != null) {//文章存在
				request.setAttribute("article", article);
				request.setAttribute("nid", nid);
				System.out.println("进入修改");
				request.setAttribute("status", "进入修改");
			}else {
				System.out.println("进入修改失败，没有该文章，请刷新重试!");
				request.setAttribute("status", "进入修改失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "article/addarticle.jsp";
	}

}
