package com.dmjd.action.upload;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Message;
import com.dmjd.action.Action;

public class ShowMessageAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Message> messages = new ArrayList<>();
		try {
			messages= DaoFactory.getMessageDaoInstance().findMessages(1005);//查询留言板
			if(messages!=null){
				System.out.println("success");
				request.setAttribute("messages", messages);
//				response.getWriter().write("{\"ifExist\":1}");
			}else{
				System.out.println("fail");
//				response.getWriter().write("{\"ifExist\":2}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "message.jsp";
	}

}
