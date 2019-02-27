package com.dmfm.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;

public class DelmessageAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int mid = Integer.valueOf(request.getParameter("mid"));
		try {
			int result= DaoFactory.getMessageDaoInstance().delMessages(mid);
			if(result==1){
				System.out.println("success");
//				response.getWriter().write("{\"ifExist\":1}");
			}else{
				System.out.println("fail");
//				response.getWriter().write("{\"ifExist\":2}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/Dmjd/1005.fm";
	}

}
