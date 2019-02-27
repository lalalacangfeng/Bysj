package com.dmfm.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Words;

public class SaveWordsAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String lw_name = request.getParameter("lw_name");
		String lw_date = request.getParameter("lw_date");
		String lw_for_article_id = request.getParameter("lw_for_article_id");
		String lw_content = request.getParameter("lw_content");
		Words words = new Words();
		words.setLw_name(lw_name);
		words.setLw_date(lw_date);
		words.setLw_for_article_id(lw_for_article_id);
		words.setLw_content(lw_content);
		if (lw_name!=null && lw_for_article_id!=null && lw_content!=null && lw_name!="") {
			try {
				if (DaoFactory.getArticleDaoInstance().saveWords(words) == 1) {
					request.setAttribute("words", words);
					request.setAttribute("status", "success");
					System.out.println("留言success");
//				response.sendRedirect("fm/article.jsp");
					response.getWriter().write("{\"ifExist\":1}");
//					request.getRequestDispatcher("fm/article?action=show&nid="+lw_for_article_id).forward(request, response);
				}else {
					request.setAttribute("status", "error");
					System.out.println("留言error");
					response.getWriter().write("{\"ifExist\":2}");
//				response.sendRedirect("fm/article?action=show&nid="+lw_for_article_id);
//					request.getRequestDispatcher("fm/article?action=show&nid="+lw_for_article_id).forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("回复失败");
			response.getWriter().write("{\"ifExist\":2}");
//			response.sendRedirect("fm/article?action=show&nid="+lw_for_article_id);
//			request.getRequestDispatcher("fm/article?action=show&nid="+lw_for_article_id).forward(request, response);
		}
		return null;
	}

}
