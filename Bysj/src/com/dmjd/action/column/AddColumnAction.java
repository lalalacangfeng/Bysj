package com.dmjd.action.column;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Column;
import com.dmjd.factory.DaoFactory;

public class AddColumnAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Column column = new Column();
		try {
			int optionsRadios = Integer.parseInt(request.getParameter("optionsRadios"));
			System.out.println("optionsRadios的值为："+optionsRadios);
			if (optionsRadios==0) {
				String kindname = request.getParameter("inputname");
				System.out.println("inputname:"+kindname);
				if (kindname!=null) {
					column.setKindname(kindname);
				}
			}else if (optionsRadios == 100) {
				String classname = request.getParameter("inputname");
				System.out.println("inputname:"+classname);
				int kid = Integer.parseInt(request.getParameter("classname"));
				System.out.println("所属栏目ID为："+kid);
				if (classname!=null && kid>0) {
					column.setKid(kid);
					column.setClassname(classname);
				}			
			}else if(optionsRadios == 4) {
				String classname = request.getParameter("inputname");
				System.out.println("inputname:"+classname);
				if (classname!=null) {
					column.setClassname(classname);
					column.setKid(4);
				}
			}
			if (DaoFactory.getColumnDaoInstance().addColumn(column)==1) {
				System.out.println("添加成功");
				request.getSession().setAttribute("column", column);
				request.setAttribute("status", "添加成功");
			}else {
				System.out.println("添加失败");
				request.setAttribute("status", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "column?action=findall";
	}

}
