package com.dmfm.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;

public class EditPassAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String passwd = request.getParameter("passwd");
		if(CheckPass(passwd)){
			String oldPasswd = request.getParameter("oldPasswd");
			passwd = com.dmjd.security.Base64.jdkBase64(passwd);//后台加密
			oldPasswd = com.dmjd.security.Base64.jdkBase64(oldPasswd);//后台加密
			int uid = (int) request.getSession().getAttribute("uid");
			
			try {
				if(DaoFactory.getUserDaoInstance().queryByName((String) request.getSession().getAttribute("username")).getPassword().equals(oldPasswd)){
					int result = DaoFactory.getUserDaoInstance().editpass(passwd,uid);
					if(result == 1){
						System.out.println("success");
						response.getWriter().write("{\"ifExist\":1}");
					}else{
						System.out.println("fail");
						response.getWriter().write("{\"ifExist\":2}");
					}
				}else{
					System.out.println("原密码不正确");
					response.getWriter().write("{\"ifExist\":3}");
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("密码太短");
			response.getWriter().write("{\"ifExist\":4}");
		}
		return null;
	}

	private boolean CheckPass(String passwd) {
		return passwd.length()>6 && passwd.length()<15;
	}

}
