package com.dmjd.action.column;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;

public class DelColumnAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			int kid = Integer.parseInt(request.getParameter("kid"));
			int cid = Integer.parseInt(request.getParameter("cid"));
			try {
				if (DaoFactory.getColumnDaoInstance().delColumn(cid, kid)==1) {
					System.out.println("删除成功");
					request.setAttribute("status", "已删除成功");
				}else {
					System.out.println("删除失败");
					request.setAttribute("status", "已删除失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "column?action=findall";
	}

}