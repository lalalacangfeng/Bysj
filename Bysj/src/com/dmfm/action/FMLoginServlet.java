package com.dmfm.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.User;


@WebServlet(urlPatterns = { "/fm/login","/fm/logout","/fm/register","/fm/checkEmail","/fm/checkName" }, name = "loginServlet")
public class FMLoginServlet extends HttpServlet {
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");// 获取用户名
		String passwd = request.getParameter("passwd");// 获取用户密码
		String action = request.getServletPath().substring(4, request.getServletPath().length());// 获取action类型
		passwd = com.dmjd.security.Base64.jdkBase64(passwd);//后台加密
		String path = null;
		System.out.println("action:"+action);

		try {
			if (action.equals("login")) {// 如果是登录
				User user = DaoFactory.getUserDaoInstance().queryByName(uname);// 根据用户名查询用户
				long time = System.currentTimeMillis();

				if (passwd.equals(user.getPassword())) {// 输入的密码与数据库中的一致
					request.getSession().setAttribute("username", uname);
					request.getSession().setAttribute("uid", user.getUid());
					request.getSession().setAttribute("email", user.getEmail());
					request.getSession().setAttribute("role", user.getRole());
					System.out.println("role:"+request.getSession().getAttribute("role"));
					System.out.println("success");
					response.getWriter().write("{\"ifExist\":1}");
//					path = "../index.jsp";
				} else {
					request.setAttribute("loginstatus", "用户名或密码错误！");
					System.out.println("用户名或密码错误");
//					path = "../index.jsp";
					response.getWriter().write("{\"ifExist\":2}");
				}
//				request.getRequestDispatcher(path).forward(request, response);
			} else if (action.equals("logout")) {// 用户退出，注销session中的用户
				request.getSession().removeAttribute("username");
				request.getSession().removeAttribute("uid");
				System.out.println("注销成功");
//				path = "../index.jsp";
//				request.getRequestDispatcher(path).forward(request, response);
				response.sendRedirect("../index.jsp");
			} else if (action.equals("register")) {
				String email = request.getParameter("email");// 获取用户邮箱
				User user = new User();
				user.setUsername(uname);
				user.setEmail(email);
				user.setPassword(passwd);
				if (DaoFactory.getUserDaoInstance().register(user)==1) {
					response.getWriter().write("{\"ifExist\":1}");
					System.out.println("注册成功");
				}else {
					response.getWriter().write("{\"ifExist\":2}");
					System.out.println("注册失败");
				}
			} else if (action.equals("checkEmail")) {
				String email = request.getParameter("email");// 获取用户邮箱
				int result = DaoFactory.getUserDaoInstance().checkEmail(email);
				if (result == 0) {
					response.getWriter().write("{\"ifExist\":1}");
				} else if (result == 1) {
					response.getWriter().write("{\"ifExist\":2}");
				} 
			} else if (action.equals("checkName")) {
				int result = DaoFactory.getUserDaoInstance().checkName(uname);
				if (result == 0) {
					response.getWriter().write("{\"ifExist\":1}");
				} else if (result == 1) {
					response.getWriter().write("{\"ifExist\":2}");
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
