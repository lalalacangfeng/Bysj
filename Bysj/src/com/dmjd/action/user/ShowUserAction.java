package com.dmjd.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.User;

public class ShowUserAction implements Action {

	/***
	 * 显示用户信息
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = DaoFactory.getUserDaoInstance().queryByName(
					String.valueOf(request.getSession().getAttribute("username")));//通过session获取用户名并查信息
			request.setAttribute("email", user.getEmail());//返回邮箱
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/myuserinf.jsp";//显示用户信息页面
	}

}
