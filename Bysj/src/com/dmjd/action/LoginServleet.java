package com.dmjd.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Base64;

import com.dmjd.entity.User;
import com.dmjd.factory.DaoFactory;

@WebServlet(
		urlPatterns = {"/dologin"},
		name = "loginServlet"
)
public class LoginServleet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServleet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = com.dmjd.security.Base64.jdkBase64(password);
		String action = request.getParameter("action");//获取action类型
		String path = null;
		System.out.println("action:"+action);
		try {
			if (action.equals("dologin")) {
				User user= DaoFactory.getUserDaoInstance().queryByName(username);
				if(password.equals(user.getPassword())){
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("uid", user.getUid());
					request.getSession().setAttribute("role", user.getRole());
					//request.getSession().setAttribute("email", user.getEmail());
					if (user.getRole()!=0) {
						request.setAttribute("status", "暂无权限！");
						path = "login.jsp";
					}else {
						path = "index.jsp";					
					}
				}else {
					request.setAttribute("status", "用户名或密码错误！");
					path = "login.jsp";
				}
			}else if (action.equals("logout")) {
				request.getSession().removeAttribute("username");
				request.getSession().removeAttribute("uid");
				path ="login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
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
