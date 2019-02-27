package com.dmjd.action.media;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
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
			boolean flag = false;
			int id = Integer.valueOf(request.getParameter("id"));
			//获得保存文件的路径
			ServletContext sctx = request.getServletContext();
			String basePath = sctx.getRealPath("");
			ArrayList<String> srcs = DaoFactory.getMediaDaoInstance().queryVediaSrcById(id);//查找服务器视频地址
			for (String string : srcs) {
				System.out.println("string:"+string);
				File delfile = new File(basePath+"/"+string);
				// 路径为文件且不为空则进行删除
				if (delfile.exists()) {
					delfile.delete();
					System.out.println("删除文件："+string);
					flag = true;
				}else {
					flag = false;
				}
			}
			if (flag) {
				if (DaoFactory.getMediaDaoInstance().delVedio(id) == 1) {
					//删除成功
					System.out.println("数据库视频删除成功");
					request.setAttribute("status", "删除视频成功");
					
				}else {
					System.out.println("删除失败");
					request.setAttribute("status", "删除视频失败");
				}				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "media?action=update";
	}

}
