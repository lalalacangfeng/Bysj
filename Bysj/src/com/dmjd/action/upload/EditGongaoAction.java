package com.dmjd.action.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Gonggao;

public class EditGongaoAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gonggao gonggao = new Gonggao();
		try {
			gonggao = DaoFactory.getGonggaoDaoInstance().show();
			request.getSession().setAttribute("gonggao", gonggao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editgonggao.jsp";
	}

}
