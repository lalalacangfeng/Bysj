package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;

public class CancelArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int nid = Integer.valueOf(request.getParameter("nid"));
			if (DaoFactory.getArticleDaoInstance().cancelArticle(nid) == 1) {
				System.out.println("文章撤销成功");
				request.setAttribute("status", "文章撤销成功");
			}else {
				System.out.println("文章撤销失败");
				request.setAttribute("status", "文章撤销失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "article?action=findall";
	}

}
