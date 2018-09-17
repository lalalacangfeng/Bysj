package com.dmjd.action.article;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Article;
import com.dmjd.factory.DaoFactory;

public class AddArticleAction implements Action {

	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:"); 
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String releasetime = request.getParameter("releasetime");
		System.out.println("----releasetime:"+releasetime);
		String articletitle = request.getParameter("articletitle");
		int cid =Integer.valueOf(request.getParameter("classname"));
		String articlewriter = request.getParameter("articlewriter");
		String from = request.getParameter("from");
		int layers = Integer.valueOf(request.getParameter("layers"));
		System.out.println("request.getParameter:"+request.getParameter("releasetime"));
		String content = request.getParameter("content");
		try {
//			System.out.println("articletitle:"+articletitle+"cid:"+cid+"content:"+content);
			Article article = new Article();
			article.setArticletitle(articletitle);
			article.setArticlewriter(articlewriter);
			article.setFrom(from);
			article.setLayers(layers);
			article.setReleasetime(releasetime);
			article.setArticlecontent(content);
			article.setCid(cid);
			if(DaoFactory.getArticleDaoInstance().addArticle(article)==1){
				System.out.println("文章添加成功");
				request.getSession().setAttribute("article", article);
				request.setAttribute("status", "文章添加成功！");
			}else {
				System.out.println("文章添加失败");
				request.setAttribute("status", "文章添加失败！");
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return "article?action=show";
	}

}
