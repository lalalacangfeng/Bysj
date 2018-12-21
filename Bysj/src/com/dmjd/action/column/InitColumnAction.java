package com.dmjd.action.column;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Column;

public class InitColumnAction implements Action {

	/***
	 * 
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			Column column = DaoFactory.getColumnDaoInstance().showColumn(id, pid);
			if (column != null) {
				request.setAttribute("column", column);
				request.setAttribute("status", "查找栏目成功");	
				System.out.println("查找栏目成功");
			}else {
				request.setAttribute("status", "查找栏目失败");
				System.out.println("查找栏目失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "column/editcolumn.jsp";
	}

}
