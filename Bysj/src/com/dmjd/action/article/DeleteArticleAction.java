package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;

public class DeleteArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int nid = Integer.parseInt(request.getParameter("nid"));
		try {
			if(DaoFactory.getArticleDaoInstance().deleteArticle(nid)==1){
				System.out.println("删除成功");
				request.setAttribute("status", "已删除新闻成功");
			} else {
				System.out.println("删除失败");
				request.setAttribute("status", "删除新闻失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"article?action=show";
	}

}
