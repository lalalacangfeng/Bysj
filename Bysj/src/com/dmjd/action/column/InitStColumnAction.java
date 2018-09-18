package com.dmjd.action.column;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Column;
import com.dmjd.factory.DaoFactory;

public class InitStColumnAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			int cid = 0;
			int kid = 1;//显示为一级栏目
			try {
				ArrayList<Column> columns = DaoFactory.getColumnDaoInstance().initColumn(cid, kid);
				if (columns!=null) {
					request.getSession().setAttribute("columns", columns);
					request.setAttribute("status", "显示一级栏目成功");
					for (Column column : columns) {
						System.out.println(column.toString());
					}
				}else {
					request.setAttribute("status", "显示一级失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "column/addcolumn.jsp";
	}

}
