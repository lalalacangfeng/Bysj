package com.dmjd.action.column;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Column;

public class FindallColumnAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ArrayList<Column> columns = DaoFactory.getColumnDaoInstance().findAllColumns();//显示所有栏目
			request.getSession().getServletContext().setAttribute("columns", columns);//更新
			System.out.println("----------------------------\n"
					 + "------更新栏目列表------\n"
					 + "----------------------------\n");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "column/columns.jsp";
	}

}
