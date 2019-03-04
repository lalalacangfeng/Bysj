package com.dmjd.action.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;
import com.dmjd.action.Action;

public class DelMessageAction implements Action {

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
		return "message?action=show";
	}

}
