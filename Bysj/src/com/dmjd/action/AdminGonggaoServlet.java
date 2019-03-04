package com.dmjd.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.upload.EditGongaoAction;
import com.dmjd.action.upload.ShowGonggaoAction;
import com.dmjd.action.upload.UpdateGonggaoAction;

@WebServlet(
		name = "gonggaoServlet", 
		loadOnStartup=3,
		urlPatterns = { "/admin/upload/gonggao" })
public class AdminGonggaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");//获取action动作类型
		String url = request.getContextPath();
		System.out.println("url:"+url);
		System.out.println("action:"+action);
		Action targetAction = null;
		String path = null;
		switch (action) {
		case "show":
			targetAction = new ShowGonggaoAction();
			path = targetAction.execute(request, response);
			break;
			
		case "edit":
			targetAction = new EditGongaoAction();
			path = targetAction.execute(request, response);
			break;
		case "update":
			targetAction = new UpdateGonggaoAction();
			path = targetAction.execute(request, response);
			break;
		default:
			break;
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	
}
