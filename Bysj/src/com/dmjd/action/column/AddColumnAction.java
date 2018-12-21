package com.dmjd.action.column;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Column;

public class AddColumnAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Column column = new Column();
		int pid = 0;
		try {
			int optionsRadios = Integer.valueOf(request.getParameter("optionsRadios"));
			switch (optionsRadios) {
			case 1:
				String kindname = request.getParameter("inputname");
				System.out.println("inputname:"+kindname);
				pid = 1;
				if (kindname!=null) {
					column.setKindname(kindname);
					column.setPid(pid);
				}
				break;
			case 2:
				String columnname = request.getParameter("inputname");
				System.out.println("inputname:"+columnname);
				pid = 2;
				int kid = Integer.parseInt(request.getParameter("columnname"));
				System.out.println("-------------");				
				System.out.println("columnname:"+columnname+" 所属栏目ID为："+kid);
				System.out.println("-------------");				
				if (columnname!=null) {
					column.setKid(kid);
					column.setColumnname(columnname);
					column.setPid(pid);
				}
				break;
			default:
				System.out.println("--------出错啦-------");
				break;
			}
			
			if (DaoFactory.getColumnDaoInstance().addColumn(column) == 1) {
				System.out.println("添加成功");
				request.setAttribute("status", "添加成功");
			}else {
				System.out.println("添加失败");
				request.setAttribute("status", "添加失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "column?action=findall";
	}

}
