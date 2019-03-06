package com.dmfm.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Message;

public class InputMessageAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(quanxian(request)){
			response.getWriter().write("{\"ifExist\":2}");
			return null;
		}else{
			String title = request.getParameter("title");
			String messcontent = request.getParameter("messcontent");
			String author = request.getParameter("author");
			if(title!=null&&messcontent!=null&&author!=null&&author!=""&&title!=""&&messcontent!=""){
				Message message = new Message();
				message.setTitle(title);
				message.setContent(messcontent);
				message.setAuthor(author);
				try {
					int result = DaoFactory.getMessageDaoInstance().addMessages(message);
					if(result==1){
						System.out.println("success");
						response.getWriter().write("{\"ifExist\":1}");
					}else{
						System.out.println("fail");
						response.getWriter().write("{\"ifExist\":2}");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("error");
				response.getWriter().write("{\"ifExist\":4}");
			}
			return null;
		}
	}
	
	public Boolean quanxian(HttpServletRequest request){
		String role = null;
		boolean flag = false;
		try {
			role = request.getSession().getAttribute("role").toString();
			if("3".equals(role)){
				System.out.println("权限被限制了");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("role:"+role);
		return flag;
	}

}
