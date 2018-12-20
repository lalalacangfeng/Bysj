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
		// TODO Auto-generated method stub
		try {
			int nid = Integer.valueOf(request.getParameter("nid"));//获取新闻编号
			if (DaoFactory.getArticleDaoInstance().releaseArticle(nid) == 1) {
				System.out.println("文章发布成功");
				request.setAttribute("status", "文章发布成功！");
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "article?action=findall";
	}

}
