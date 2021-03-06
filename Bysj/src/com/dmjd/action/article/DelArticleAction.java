package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;

public class DelArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int nid = Integer.valueOf(request.getParameter("nid"));
			if (DaoFactory.getArticleDaoInstance().deleteArticle(nid) == 1) {
				System.out.println("删除成功");
				request.setAttribute("status", "删除成功");
			}else {
				System.out.println("删除失败");
				request.setAttribute("status", "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return "article?action=findall";
	}

}
