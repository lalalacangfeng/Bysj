package com.dmjd.action.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Article;
import com.dmjd.factory.DaoFactory;

public class EditArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String articletitle = request.getParameter("articletitle");
		String articlewriter = request.getParameter("articlewriter");
		String from = request.getParameter("from");
		String content = request.getParameter("content");
		int layers = Integer.valueOf(request.getParameter("layers"));
		String releasetime = request.getParameter("releasetime");
		int cid =Integer.valueOf(request.getParameter("classname"));
		int nid = (int) request.getSession().getAttribute("nid");
		System.out.println("nid:"+nid+" releasetime:"+releasetime+" articletitle:"+articletitle+" cid:"+cid
				+" articlewriter:"+articlewriter+" from:"+from+" layers:"+layers+" content:"+content);
		Article article = new Article();
		article.setNid(nid);
		article.setArticletitle(articletitle);
		article.setArticlewriter(articlewriter);
		article.setFrom(from);
		article.setLayers(layers);
		article.setReleasetime(releasetime);
		article.setArticlecontent(content);
		article.setCid(cid);
		System.out.println("nid:"+nid);
		try {
			if (DaoFactory.getArticleDaoInstance().editArticle(nid,article) == 1) {
				System.out.println("文章修改成功");
				request.setAttribute("status", "修改成功");
			} else {
				System.out.println("修改失败");
				request.setAttribute("status", "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "article?action=show";
	}

}
