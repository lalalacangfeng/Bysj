package com.dmjd.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.article.AddArticleAction;
import com.dmjd.action.article.CancelArticleAction;
import com.dmjd.action.article.DeleteArticleAction;
import com.dmjd.action.article.EditArticleAction;
import com.dmjd.action.article.InitArticleAction;
import com.dmjd.action.article.ReleaseArticleAction;
import com.dmjd.action.article.FindallArticleAction;
import com.dmjd.action.article.ShowArticleAction;


@WebServlet(
		urlPatterns={"/article"},
		name="articleservlet"
)
public class ArticleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ArticleServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("做doget方法");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("做dopost方法");
		String action = request.getParameter("action");
		Action targetAction = null;
		System.out.println("ArticleServlet--action:"+action);
		String path = null;
		if(request.getParameter("nid")!=null){
			int nid = Integer.parseInt(request.getParameter("nid"));
			targetAction = new ShowArticleAction();
			path = targetAction.execute(request, response);
		}
//		String content = request.getParameter("content");
		if(action.equals("init")){
			System.out.println("action:init");
			targetAction = new InitArticleAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("添加")) {
			System.out.println("action:添加");
//			System.out.println("content:"+content);
			targetAction = new AddArticleAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("edit")) {
			System.out.println("action:进入修改新闻");
			System.out.println("action:init");
			targetAction = new InitArticleAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("show")||action.equals("取消")) {
			System.out.println("action:"+action+" 显示文章");
			targetAction = new FindallArticleAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("release")) {
			System.out.println("发布新闻");
			targetAction = new ReleaseArticleAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("cancel")) {
			System.out.println("撤销新闻");
			targetAction = new CancelArticleAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("修改")) {
			System.out.println("action:修改新闻");
			System.out.println("action:init");
			targetAction = new EditArticleAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("delete")) {
			System.out.println("删除新闻");
			targetAction = new DeleteArticleAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("article")) {
			int nid = Integer.parseInt(request.getParameter("nid"));
			targetAction = new ShowArticleAction();
			path = targetAction.execute(request, response);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
