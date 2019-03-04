package com.dmjd.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;

public class UpdateUser implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		int role = Integer.valueOf(request.getParameter("role"));
//		System.out.println("id:"+id+"   role:"+role);
		int result = 0;
		try {
			result = DaoFactory.getUserDaoInstance().updateRole(id, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/user?action=list";
	}

}
