package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;

public class ReleaseArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ReleaseArticleAction");
		try {
			int nid = Integer.valueOf(request.getParameter("nid"));
			System.out.println("---nid:"+nid);
			if (DaoFactory.getArticleDaoInstance().releaseArticle(nid)==1) {
				request.getSession().setAttribute("nid", nid);
				System.out.println("文章发布成功");
				request.setAttribute("status", "文章发布成功！");
			} else {
				System.out.println("文章发布失败");
				request.setAttribute("status", "文章发布失败！");
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return "article?action=show";
	}

}
