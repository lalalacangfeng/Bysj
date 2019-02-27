package com.dmjd.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.pojo.Menu;
import com.dmjd.action.article.AddArticleAction;
import com.dmjd.action.article.CancelArticleAction;
import com.dmjd.action.article.DelArticleAction;
import com.dmjd.action.article.EditArticleAction;
import com.dmjd.action.article.FindallArticleAction;
import com.dmjd.action.article.InitArticleAction;
import com.dmjd.action.article.ReleaseArticleAction;
import com.dmjd.action.article.ShowArticleAction;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Article;

@WebServlet(
		urlPatterns={"/admin/article"},
		name="articleServlet",
		loadOnStartup=2
		)
public class AdminArticleServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");//获取action类型
		Action targetAction = null;
		String path = null;
		ArrayList<Menu> menus = null;
		System.out.println("actoin:"+action);
		switch (action) {
		case "添加"://添加新闻
			targetAction = new AddArticleAction();
			path = targetAction.execute(request, response);
			break;
		case "findall"://显示所有新闻
			targetAction = new FindallArticleAction();
			path = targetAction.execute(request, response);
			break;
		case "release"://发表新闻
			targetAction = new ReleaseArticleAction();
			path = targetAction.execute(request, response);
			menus = InitMenus();
			break;
		case "cancel"://撤销新闻
			targetAction = new CancelArticleAction();
			path = targetAction.execute(request, response);
			menus = InitMenus();
						
			break;
		case "edit"://进入修改新闻页面
			targetAction = new InitArticleAction();
			path = targetAction.execute(request, response);
			break;
		case "修改"://修改新闻
			targetAction = new EditArticleAction();
			path = targetAction.execute(request, response);
			break;
		case "show"://显示新闻
			targetAction = new ShowArticleAction();
			path = targetAction.execute(request, response);
			break;
		case "del":
			targetAction = new DelArticleAction();
			path = targetAction.execute(request, response);
			break;
		default:
			System.out.println("页面丢失了");
			//页面丢失
			break;
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init() throws ServletException {
		try {
			ArrayList<Article> articles = DaoFactory.getArticleDaoInstance().FindAll();
			getServletContext().setAttribute("articles", articles);//将文章存为全局变量
		} catch (Exception e) {
			System.out.println("----------------------------\n"
					 + "------articles栏目初始化失败------\n"
					 + "----------------------------\n");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Menu> InitMenus(){
		ArrayList<Menu> menus = new ArrayList<>();
		try {
			menus = com.dmfm.factory.DaoFactory.InitDaoInstance().InitMenus();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getServletContext().setAttribute("menus", menus);
		return menus;		
	}

}
