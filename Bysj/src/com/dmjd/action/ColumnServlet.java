package com.dmjd.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.column.AddColumnAction;
import com.dmjd.action.column.FindAllColumnAction;
import com.dmjd.action.column.InitStColumnAction;


@WebServlet(
		urlPatterns = { "/column" },
		name = "columnServlet"
)
public class ColumnServlet extends HttpServlet {

	public ColumnServlet(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Action targetAction = null;
		System.out.println("------ColumnServlet------+action:"+action);
		String path = null;
		if (action.equals("findall")) {
			targetAction = new FindAllColumnAction();
			path = targetAction.execute(request, response);
		}else if (action.equals("init")) {
			targetAction = new InitStColumnAction();
			path = targetAction.execute(request, response);
		}else if (action.equals("add")) {
			targetAction = new AddColumnAction();
			path = targetAction.execute(request, response);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub	
	}

}
