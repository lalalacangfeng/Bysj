package com.dmjd.action.article;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Article;
import com.dmjd.entity.Column;
import com.dmjd.factory.DaoFactory;

public class InitArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("nid")==null){
				request.getSession().removeAttribute("article");
				request.getSession().removeAttribute("nid");
				System.out.println("nid不存在");
				ArrayList<Column> columns = DaoFactory.getArticleDaoInstance().initColumn();
				request.setAttribute("columns", columns);
//				for (Column column : columns) {
//					System.out.println("column.getClassname():"+column.getClassname());
//				}				
			}else {
				System.out.println("nid存在");
				int nid = Integer.valueOf(request.getParameter("nid"));
				request.getSession().setAttribute("nid", nid);
				System.out.println("nid:"+nid);
				Article article = DaoFactory.getArticleDaoInstance().queryBynid(nid);
				ArrayList<Column> columns = DaoFactory.getArticleDaoInstance().initColumn();
				request.setAttribute("columns", columns);
				if (article!=null) {
					request.setAttribute("article", article);
					System.out.println("进入修改");
					request.setAttribute("status", "进入修改");
				} else {
					System.out.println("进入修改失败");
					request.setAttribute("status", "进入修改失败");
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "article/releasearticle.jsp";
	}

}
