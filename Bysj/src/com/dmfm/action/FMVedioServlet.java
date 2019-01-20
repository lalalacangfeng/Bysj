package com.dmfm.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.action.fm.ShowVedioAction;


@WebServlet(name = "FMVedioServlet", urlPatterns = "/fm/vedio")
public class FMVedioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");// 获取action类型
		Action targetAction = null;
		String path = null;
		System.out.println("actoin:" + action);

		switch (action) {
		case "show":// 显示视频
			targetAction = new ShowVedioAction();
			path = targetAction.execute(request, response);
			break;

		default:
			System.out.println("页面丢失了");
			// 页面丢失
			break;
		}
		// response.sendRedirect(path);
		request.getRequestDispatcher(path).forward(request, response);
	}
	
}
