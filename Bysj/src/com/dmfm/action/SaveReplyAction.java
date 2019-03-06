package com.dmfm.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Reply;

public class SaveReplyAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String lr_for_article_id = request.getParameter("lr_for_article_id");
		String lr_name = request.getParameter("lr_name");
		String lr_date = request.getParameter("lr_date");
		String lr_for_name = request.getParameter("lr_for_name");
		String lr_for_words = request.getParameter("lr_for_words");
		String lr_for_reply = request.getParameter("lr_for_reply");
		String lr_content = request.getParameter("lr_content");
		if(quanxian(request)){
			return "article?action=show&nid="+lr_for_article_id;
		}
		
		Reply reply = new Reply();
		reply.setLr_content(lr_content);
		reply.setLr_date(lr_date);
		reply.setLr_for_article_id(lr_for_article_id);
		reply.setLr_for_name(lr_for_name);
		reply.setLr_for_reply(lr_for_reply);
		reply.setLr_for_words(lr_for_words);
		reply.setLr_name(lr_name);
		if (lr_for_article_id!=null && lr_name!=null && lr_for_words!=null && lr_name!="") {
			try {
				if (DaoFactory.getArticleDaoInstance().saveReply(reply) == 1){
					request.setAttribute("reply", reply);
					request.setAttribute("status", "success");
					System.out.println("回复success");
					response.getWriter().write("{\"ifExist\":1}");
//					response.sendRedirect("fm/article?action=show&nid="+lr_for_article_id);
//					request.getRequestDispatcher("fm/article?action=show&nid="+lr_for_article_id).forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("回复失败");
			response.getWriter().write("{\"ifExist\":2}");
//			response.sendRedirect("fm/article?action=show&nid="+lr_for_article_id);
//			request.getRequestDispatcher("fm/article?action=show&nid="+lr_for_article_id).forward(request, response);
		}
		return "article?action=show&nid="+lr_for_article_id;
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
