package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Article;

public class EditArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int nid = Integer.valueOf(request.getParameter("nid"));
		System.out.println("nid:"+nid);
		String title = request.getParameter("title");//获取标题
		String writer = request.getParameter("writer");//获取作者
		String from = request.getParameter("from");//获取来源
		int layers = Integer.valueOf(request.getParameter("layers"));//获取层级
		String content = request.getParameter("content");//获取内容
		String releasetime = request.getParameter("releasetime");//获取发布时间
		int cid =Integer.valueOf(request.getParameter("columnname"));//获取所属栏目
		Article article = new Article();			
		try {
			article.setTitle(title);
			article.setWriter(writer);
			article.setFrom(from);
			article.setContent(content);
			article.setReleasetime(releasetime);
			article.setCid(cid);
			if (DaoFactory.getArticleDaoInstance().editArticle(nid, article) == 1) {
				System.out.println("修改文章成功");
				request.setAttribute("status", "修改文章成功!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "article?action=findall";
	}

}
