package com.dmfm.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.User;

@WebServlet(urlPatterns = { "/fm/user" }, name = "userServlet")
public class FMUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		String action = req.getRequestURI();
		String action = req.getParameter("action");
//		System.out.println(action);
		String path = null;
		Action targetAction = null;
//		action = (String) action.subSequence(14, action.length());
//		String username =  (String) req.getSession().getAttribute("username");
		System.out.println("action:"+action);
//		System.out.println("username:"+username);
//		System.out.println("uname:"+req.getParameter("uname"));
		switch (action) {
		case "editinf":
			targetAction = new EditInfAction();
			path = targetAction.execute(req, resp);
			break;
		case "editpass":
			targetAction = new EditPassAction();
			path = targetAction.execute(req, resp);
			break;
		case "inputmessage":
			targetAction = new InputMessageAction();
			path = targetAction.execute(req, resp);
			break;
		case "delmessage":
			targetAction = new DelmessageAction();
			path = targetAction.execute(req, resp);
			resp.sendRedirect(path);
			break;			
		default:
			System.out.println("页面丢失了");
			break;
		}
		
	}

}
