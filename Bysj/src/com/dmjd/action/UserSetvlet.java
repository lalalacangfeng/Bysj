package com.dmjd.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.user.EditPasswdAction;
import com.dmjd.action.user.EditUserAction;
import com.dmjd.action.user.EditinfUserAction;
import com.dmjd.action.user.ShowUserAction;

@WebServlet(
		urlPatterns = { "/user" },
		name = "userServlet"
)
public class UserSetvlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserSetvlet() {
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
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Action targetAction = null;
		String path = null;
		if (action.equals("show")) {
			targetAction = new ShowUserAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("edit")) {
			System.out.println("修改用户");
			targetAction = new EditUserAction();
			path = targetAction.execute(request, response);
		} else if (action.equals("editinf")) {
			System.out.println("修改用户信息");
			targetAction = new EditinfUserAction();
			System.out.println("path:"+path);
			path = targetAction.execute(request, response);
		} else if (action.equals("editpasswd")) {
			System.out.println("修改用户密码");
			targetAction = new EditPasswdAction();
			path = targetAction.execute(request, response);
		}
		request.getRequestDispatcher(path).forward(request, response);

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
