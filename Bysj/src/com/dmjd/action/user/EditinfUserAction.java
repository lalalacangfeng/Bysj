package com.dmjd.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.User;
import com.dmjd.factory.DaoFactory;

public class EditinfUserAction implements Action {

	/***
	 * 修改用户信息
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int uid=Integer.parseInt(String.valueOf(request.getSession().getAttribute("uid")));//获取用户id
		String username = request.getParameter("username");//获取参数里的用户名
		String email = request.getParameter("email");//获取邮箱
		System.out.println("修改后信息为：username:"+username+" email:"+email);
		try {
			User user = DaoFactory.getUserDaoInstance().queryByName(
					String.valueOf(request.getSession().getAttribute("username")));//根据用户名查询用户
			System.out.println("查询user成功");
			System.out.println("用户信息为：username:"+user.getUsername()+" email:"+user.getEmail());
			if (user.getUsername().equals(username)||
					DaoFactory.getUserDaoInstance().queryByName(username).getUid()==0) {//判断用户名是否已存在
				System.out.println("DaoFactory.getUserDaoInstance().queryByName(username).getUid():"
					+DaoFactory.getUserDaoInstance().queryByName(username).getUid());
				System.out.println("用户名未注册");
				System.out.println("user.getEmail().equals(email):"+user.getEmail().equals(email));
				System.out.println("DaoFactory.getUserDaoInstance().queryByEmail(email).getUid()："+
				DaoFactory.getUserDaoInstance().queryByEmail(email).getUid());
				if(user.getEmail().equals(email)||
						DaoFactory.getUserDaoInstance().queryByEmail(email).getUid()==0){//判断用户邮箱是否已使用
					System.out.println("邮箱未使用");
					if (DaoFactory.getUserDaoInstance().editInf(uid, username, email)==1) {//修改用户信息
						System.out.println("修改成功");
						request.getSession().setAttribute("username", username);
						request.setAttribute("status", "信息修改成功！");
					}else{//用户信息修改失败
						System.out.println("修改失败");
						request.setAttribute("status", "修改操作失败，请重试！");
					}
				}else{//邮箱已经被注册
					System.out.println("邮箱已使用");
					request.setAttribute("status", "电子邮箱账号已被注册,请换一个！");
				}
			}else{//判断用户名已经存在
				System.out.println("用户名已存在");
				request.setAttribute("status", "用户名已存在，请换一个！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user?action=show";//显示修改后用户信息页面
	}

}
