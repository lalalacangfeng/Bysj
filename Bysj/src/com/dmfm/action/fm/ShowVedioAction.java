package com.dmfm.action.fm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.action.Action;
import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Vedio;

public class ShowVedioAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			Vedio vedio = DaoFactory.getVedioDaoInstance().findVedio(id);
			if (vedio != null) {
				request.getSession().setAttribute("fm_vedio", vedio);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "vedio.jsp";
	}

}
