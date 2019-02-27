package com.dmjd.action.media;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;

public class DelPictAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			String src = DaoFactory.getMediaDaoInstance().queryPictSrcById(id);//查找服务器图片地址
			System.out.println("src:"+src);
			//获得保存文件的路径
			ServletContext sctx = request.getServletContext();
			String basePath = sctx.getRealPath("");
			System.out.println("basePath:"+basePath);
			boolean flag = false;
			if (src!=null) {
				File delFile = new File(basePath+"/"+src);
				if (delFile.isFile() && delFile.exists()) {
					delFile.delete();
					System.out.println("删除文件："+src);
					flag = true;
				}else {
					flag = false;
				}
			}
			if (flag) {
				if (DaoFactory.getMediaDaoInstance().delPict(id) == 1) {
					//删除成功
					System.out.println("删除成功");
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
