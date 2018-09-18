package com.dmjd.action.column;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Column;
import com.dmjd.factory.DaoFactory;

public class FindAllColumnAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Column> columns = new ArrayList<>();
		try {
			columns = DaoFactory.getColumnDaoInstance().findAllColumns();
			if (columns!=null) {
				System.out.println("success");
				for (Column column : columns) {
					System.out.println(column.toString());
				}
				request.setAttribute("columns", columns);
				System.out.println("初始化栏目成功");
				request.setAttribute("status", "初始化栏目成功");
			}else {
				System.out.println("初始化栏目失败");
				request.setAttribute("status", "初始化栏目失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "column/columnlist.jsp";
	}

}
