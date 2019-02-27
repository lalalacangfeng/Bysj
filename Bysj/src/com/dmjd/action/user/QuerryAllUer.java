package com.dmjd.action.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.User;

public class QuerryAllUer implements Action {

	/***
	 * 查询所有用户信息
	 * 0------超级管理员
	 * 1------管理员
	 * 2------验证用户
	 * 3------等待验证用户
	 * 4------未验证用户
	 * 5------删除用户
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<User> users = DaoFactory.getUserDaoInstance().FindAll();
			System.out.println("查询所有用户");
			request.setAttribute("users", users);
			request.setAttribute("length", users.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return "user/clients.jsp";
	}

}
