package com.dmjd.action.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Gonggao;

public class UpdateGonggaoAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		Gonggao gonggao = new Gonggao();
		gonggao.setTitle(request.getParameter("title"));
		gonggao.setContent(request.getParameter("content"));
		try {
			result = DaoFactory.getGonggaoDaoInstance().update(gonggao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result:"+result);
		return "/admin/upload/gonggao?action=show";
	}

}
