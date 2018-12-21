package com.dmjd.action.column;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Column;

public class EditColumnAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Column column = new Column();
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			int optionsRadios = Integer.valueOf(request.getParameter("optionsRadios"));
			System.out.println("optionsRadios:"+optionsRadios);
			switch (optionsRadios) {
			case 1:
				String kindname = request.getParameter("inputname");
				System.out.println("inputname:"+kindname);
				
				if (kindname!=null) {
					column.setKid(id);
					column.setKindname(kindname);
					column.setPid(optionsRadios);
				}
				break;
			case 2:
				String columnname = request.getParameter("inputname");
				System.out.println("inputname:"+columnname);
				int kid = Integer.parseInt(request.getParameter("columnname"));
				System.out.println("-------------");				
				System.out.println("columnname:"+columnname+" 所属栏目ID为："+kid);
				System.out.println("-------------");				
				if (columnname!=null) {
					column.setCid(id);
					column.setColumnname(columnname);
					column.setKid(kid);
					column.setPid(optionsRadios);
				}
				break;
			default:
				System.out.println("-----出错了----");
				break;
			}
			if (DaoFactory.getColumnDaoInstance().editColumn(id, column) == 1) {
				
			}else {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "column?action=findall";
	}

}
