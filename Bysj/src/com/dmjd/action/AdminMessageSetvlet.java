package com.dmjd.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.upload.DelMessageAction;
import com.dmjd.action.upload.ShowMessageAction;



@WebServlet(
		urlPatterns = { "/admin/message" },
		name = "messageServlet"
)
public class AdminMessageSetvlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AdminMessageSetvlet() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");//获取action动作类型
		String url = request.getContextPath();
		System.out.println("url:"+url);
		Action targetAction = null;
		String path = null;
		System.out.println("action:"+action);
		switch (action) {
		case "show":
			targetAction = new ShowMessageAction();
			path = targetAction.execute(request, response);
			System.out.println("path:"+path);
//			response.sendRedirect(path);
			request.getRequestDispatcher(path).forward(request, response);
			break;
		case "delmessage":
			System.out.println("删除");
			targetAction = new DelMessageAction();
			path = targetAction.execute(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			break;
		default:
			System.out.println("找不到匹配项");
			path = "index.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			break;
		}
		
		


	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		
	}

}
