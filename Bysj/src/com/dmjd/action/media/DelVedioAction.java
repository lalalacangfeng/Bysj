package com.dmjd.action.media;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;

public class DelVedioAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			if (DaoFactory.getMediaDaoInstance().delVedio(id) == 1) {
				//删除成功
				System.out.println("删除成功");
				request.setAttribute("status", "删除视频成功");
			}else {
				System.out.println("删除失败");
				request.setAttribute("status", "删除视频失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "media?action=update";
	}

}
