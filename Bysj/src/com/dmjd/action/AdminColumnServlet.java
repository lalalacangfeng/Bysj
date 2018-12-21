package com.dmjd.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dmjd.action.column.AddColumnAction;
import com.dmjd.action.column.DelColumnAction;
import com.dmjd.action.column.FindallColumnAction;
import com.dmjd.action.column.InitColumnAction;
import com.dmjd.action.column.EditColumnAction;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Column;

@WebServlet(
		name="columnServlet",
		urlPatterns={"/admin/column"},
		loadOnStartup=1
		)
public class AdminColumnServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		Action targetAction = null;
		String path = null;
		
		System.out.println("column--action:"+action);
		
		switch (action) {
		case "添加":
			targetAction = new AddColumnAction();
			path = targetAction.execute(request, response);
			break;
		case "findall":
			targetAction = new FindallColumnAction();
			path = targetAction.execute(request, response);
			break;
		case "edit":
			targetAction = new InitColumnAction();
			path = targetAction.execute(request, response);
			break;
		case "修改":
			targetAction = new EditColumnAction();
			path = targetAction.execute(request, response);
			break;
		case "del":
			targetAction = new DelColumnAction();
			path = targetAction.execute(request, response);
			break;
		default:
			break;
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			ArrayList<Column> columns = DaoFactory.getColumnDaoInstance().findAllColumns();//显示所有的栏目名	
			getServletContext().setAttribute("columns", columns);//将columns存为全局变量
			System.out.println("----------------------------\n"
							 + "------columns栏目初始化成功------\n"
							 + "----------------------------\n");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------------------------\n"
					 + "------columns栏目初始化失败------\n"
					 + "----------------------------\n");
			e.printStackTrace();
		}
	}


}
