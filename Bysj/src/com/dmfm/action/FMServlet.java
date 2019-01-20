package com.dmfm.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.action.fm.FindFM;

@WebServlet(name = "FMServlet", urlPatterns = "*.fm")
public class FMServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int servletpath = Integer.valueOf(request.getServletPath().substring(4, request.getServletPath().length()-3));
		System.out.println("servletpath:" + servletpath);
		System.out.println("1111:");
		String path = null;
		Action targetAction = null;
		targetAction = new FindFM();
		path = targetAction.execute(request, response);
		request.getRequestDispatcher(path).forward(request, response);
	}
}
