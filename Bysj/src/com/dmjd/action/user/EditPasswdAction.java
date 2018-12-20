package com.dmjd.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.User;
import com.dmjd.factory.DaoFactory;
import com.dmjd.security.Base64;

public class EditPasswdAction implements Action {
	
	/***
	 * 修改用户密码
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int uid = Integer.parseInt(String.valueOf(request.getSession().getAttribute("uid")));//获取用户id
		String username = String.valueOf(request.getSession().getAttribute("username"));//获取用户名
		System.out.println("uid:"+uid+" username:"+username);
		String oldPasswd = request.getParameter("oldPasswd");//获取原密码
		oldPasswd = Base64.jdkBase64(oldPasswd);//原密码加密
		String password = request.getParameter("password");//获取新密码
		String confirepasswd = request.getParameter("confirepasswd");//获取确认密码
		String passwd = Base64.jdkBase64(password);//加密新密码
		System.out.println("oldPasswd："+oldPasswd+" password："+password+" confirepasswd："+confirepasswd+" passwd："+passwd);
		try {
			User user = DaoFactory.getUserDaoInstance().queryByName(
					String.valueOf(request.getSession().getAttribute("username")));//通过用户名查询用户
			System.out.println("成功获得user用户");
			System.out.println("user.getPassword():"+user.getPassword());
			if(user.getPassword().equals(oldPasswd)){//判断旧密码是否正确
				System.out.println("原密码正确");
				if(isValidPassword(password,confirepasswd)){
					System.out.println("密码为空或者密码不一致！");
					request.setAttribute("status", "密码为空或者密码不一致！");
				}else {
					System.out.println("密码一致");
					if(DaoFactory.getUserDaoInstance().editPasswd(uid, passwd)==1){//修改用户密码
						System.out.println("密码修改成功");
						request.setAttribute("status", "success");
					}else {
						System.out.println("密码修改失败");
						request.setAttribute("status", "密码修改操作失败");
					}
				}
			}else {
				System.out.println("原密码不正确");
				request.setAttribute("status", "原密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user?action=show";//显示修改后用户信息页面
	}

	/**
	 * 验证密码一致性
	 */
	private boolean isValidPassword(String password, String confirepasswd) {
		return password==null||confirepasswd==null||password.length()<6||
				confirepasswd.length()<6||!password.equals(confirepasswd);//密码与确认密码是否为空，位数小于6，一致
	}

}
