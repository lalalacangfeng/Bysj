package com.dmfm.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.User;

public class EditInfAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("uname"));
		user.setEmail(request.getParameter("email"));
		System.out.println("uname:"+user.getUsername());
		user.setUid((int) request.getSession().getAttribute("uid"));
		int result = 0;
		try {
			result = DaoFactory.getUserDaoInstance().editinf(user);
			if (result == 1) {
				request.getSession().setAttribute("username", user.getUsername());
				request.getSession().setAttribute("email", user.getEmail());
				System.out.println("success");
				response.getWriter().write("{\"ifExist\":1}");
			}else{
				System.out.println("fail");
				response.getWriter().write("{\"ifExist\":2}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
