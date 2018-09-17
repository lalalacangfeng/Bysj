package com.dmjd.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.User;
import com.dmjd.factory.DaoFactory;

public class EditUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			User user =DaoFactory.getUserDaoInstance().queryByName(String.valueOf(request.getSession().getAttribute("username")));
			System.out.println("修改前信息为：username:"+user.getUsername()+" email:"+user.getEmail());
			request.setAttribute("email", user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/editinf.jsp";
	}

}
