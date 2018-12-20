package com.dmjd.action.media;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.entity.Vedio;
import com.dmjd.entity.Pict;
import com.dmjd.factory.DaoFactory;

public class UpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Vedio> vedios = new ArrayList<>();
		ArrayList<Pict> picts = new ArrayList<>();
		try {
			vedios = DaoFactory.getMediaDaoInstance().getAllMediaCount();
			if(vedios!=null){
				//查询成功
				request.getSession().getServletContext().setAttribute("vedios", vedios);//更新文章列表
				System.out.println("----------------------------\n"
						 + "------更新视频列表------\n"
						 + "----------------------------\n");
				System.out.println("更新数据库成功");
			}else {
				System.out.println("更新数据库失败");
			}				
			picts = DaoFactory.getMediaDaoInstance().getAllPicts();
			if(picts!=null){
				//查询成功
				request.getSession().getServletContext().setAttribute("picts", picts);//更新文章列表
				System.out.println("----------------------------\n"
						 + "------更新图片列表------\n"
						 + "----------------------------\n");
				System.out.println("更新数据库成功");
			}else {
				System.out.println("更新数据库失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "media/vedios.jsp";
	}

}
