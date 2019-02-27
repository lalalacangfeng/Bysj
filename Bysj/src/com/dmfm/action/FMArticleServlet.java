package com.dmfm.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.action.fm.ShowArticleAction;


@WebServlet(name = "FMArticleServlet", urlPatterns = {"/fm/article","/fm/query","/fm/article/*"})
public class FMArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public String fmurl = null;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");// 获取action类型
		String servletpath = request.getServletPath();
		String quertpath = request.getQueryString();
		fmurl = servletpath+"?"+quertpath;
//		System.out.println("servletpath:"+servletpath);
//		System.out.println("quertpath:"+quertpath);
		Action targetAction = null;
		String path = null;
		System.out.println("fmurl:"+fmurl);
		System.out.println("actoin:" + action);
		
		if (action!=null) {
			switch (action) {
			case "show":// 显示新闻
				targetAction = new ShowArticleAction();
				path = targetAction.execute(request, response);
				break;
			case "query":
				targetAction = new QueryArtilcleAction();
				path = targetAction.execute(request, response);
				break;
			default:
				System.out.println("页面丢失了");
				// 页面丢失
				path="article.jsp";
				break;
			}			
		}
		// response.sendRedirect(path);
		request.getRequestDispatcher(path).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Action targetaction = null;
		String path = null;
		System.out.println("fmurl:"+fmurl);
		System.out.println("request.getRequestURI():"+request.getRequestURI());
		String serlvetpath = (String) request.getRequestURI().subSequence(17, request.getRequestURI().length()-3);
		System.out.println("serlvetpath:"+serlvetpath);
		if (serlvetpath!=null) {
			switch (serlvetpath) {
			case "saveWords":
				targetaction = new SaveWordsAction();
				path = targetaction.execute(request, response);
//				request.getRequestDispatcher(fmurl).forward(request, response);
				break;
			case "saveReply":
				targetaction = new SaveReplyAction();
				break;
			default:
				break;
			}			
		}
//		request.getRequestDispatcher(fmurl).forward(request, response);
		response.sendRedirect(request.getContextPath()+fmurl);
		
	}
	

}
