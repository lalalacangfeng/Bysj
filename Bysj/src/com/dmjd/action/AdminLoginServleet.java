package com.dmjd.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.Base64;

import com.dmjd.entity.User;
import com.dmjd.factory.DaoFactory;

@WebServlet(
		urlPatterns = {"/admin/login"},
		name = "loginServlet"
)
public class AdminLoginServleet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminLoginServleet() {
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
		String username = request.getParameter("username");//获取用户名
		String password = request.getParameter("password");//获取用户密码
		String action = request.getParameter("action");//获取action类型
		password = com.dmjd.security.Base64.jdkBase64(password);//后台加密
		String path = null;//跳转路径
		System.out.println("action:"+action);//输出action类型
		
		HttpSession session = request.getSession();//创建一个session对象存放信息
		
		if(action!=null){//判断获取action是否存在
			try {
				if (action.equals("login")) {//登录操作
					User user= DaoFactory.getUserDaoInstance().queryByName(username);//查询用户名返回用户
					if(user!=null){//用户名存在
						if(password.equals(user.getPassword())){//输入密码与用户密码相等
							if (user.getRole()!=0) {//判断是否为管理员权限
								request.setAttribute("status", "暂无权限！");
								System.out.println("暂无权限！");
								path = "login.jsp";
								request.getRequestDispatcher(path).forward(request, response);//返回登录页面
							}else {
								session.setAttribute("username", username);//将用户信息存到session中
								session.setAttribute("uid", user.getUid());
								session.setAttribute("role", user.getRole());
								session.setAttribute("email", user.getEmail());
								request.setAttribute("status", "登陆成功！");
								path = "manage.jsp";
								response.sendRedirect(path);//跳转到主页
							}
						}else {
							request.setAttribute("status", "用户名或密码错误！");
							System.out.println("用户名或密码错误！");
							path = "login.jsp";
							request.getRequestDispatcher(path).forward(request, response);			
						}	
					}
				}else if (action.equals("logout")) {
					request.getSession().removeAttribute("username");
					request.getSession().removeAttribute("uid");
					path ="login.jsp";
					response.sendRedirect(path);			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			session.invalidate();//销毁session
			path = "login.jsp";
			response.sendRedirect(path);
		}

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
