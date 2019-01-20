package com.dmfm.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.action.fm.ShowArticleAction;
import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Reply;
import com.dmfm.pojo.Words;

@WebServlet(name = "FMArticleServlet", urlPatterns = {"/fm/article","/fm/article/*"})
public class FMArticleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");// 获取action类型
		Action targetAction = null;
		String path = null;
		System.out.println("actoin:" + action);
		
		switch (action) {
		case "show":// 显示新闻
			targetAction = new ShowArticleAction();
			path = targetAction.execute(request, response);
			break;

		default:
			System.out.println("页面丢失了");
			// 页面丢失
			break;
		}
		// response.sendRedirect(path);
		request.getRequestDispatcher(path).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("request.getRequestURI():"+request.getRequestURI());
		String serlvetpath = (String) request.getRequestURI().subSequence(17, request.getRequestURI().length()-3);
		System.out.println("serlvetpath:"+serlvetpath);
		switch (serlvetpath) {
		case "saveWords":
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
//					response.sendRedirect("fm/article.jsp");
						request.getRequestDispatcher("fm/article?action=show&nid="+lw_for_article_id).forward(request, response);
					}else {
						request.setAttribute("status", "error");
						System.out.println("留言error");
//					response.sendRedirect("fm/article.jsp");
						request.getRequestDispatcher("fm/article?action=show&nid="+lw_for_article_id).forward(request, response);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("回复失败");
				request.getRequestDispatcher("fm/article?action=show&nid="+lw_for_article_id).forward(request, response);
			}
			break;
		case "saveReply":
			String lr_for_article_id = request.getParameter("lr_for_article_id");
			String lr_name = request.getParameter("lr_name");
			String lr_date = request.getParameter("lr_date");
			String lr_for_name = request.getParameter("lr_for_name");
			String lr_for_words = request.getParameter("lr_for_words");
			String lr_for_reply = request.getParameter("lr_for_reply");
			String lr_content = request.getParameter("lr_content");
			Reply reply = new Reply();
			reply.setLr_content(lr_content);
			reply.setLr_date(lr_date);
			reply.setLr_for_article_id(lr_for_article_id);
			reply.setLr_for_name(lr_for_name);
			reply.setLr_for_reply(lr_for_reply);
			reply.setLr_for_words(lr_for_words);
			reply.setLr_name(lr_name);
			if (lr_for_article_id!=null && lr_name!=null && lr_for_words!=null && lr_name!="") {
				try {
					if (DaoFactory.getArticleDaoInstance().saveReply(reply) == 1){
						request.setAttribute("reply", reply);
						request.setAttribute("status", "success");
						System.out.println("回复success");
						request.getRequestDispatcher("fm/article?action=show&nid="+lr_for_article_id).forward(request, response);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("回复失败");
				request.getRequestDispatcher("fm/article?action=show&nid="+lr_for_article_id).forward(request, response);
			}
			break;
		default:
			break;
		}
		
		
	}
	

}
