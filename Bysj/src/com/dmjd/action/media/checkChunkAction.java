package com.dmjd.action.media;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;

public class checkChunkAction implements Action {

	String serverPath = "D:/Program Files/Tomcat/apache-tomcat-7.0.90/webapps/Dmjd/pict";//保存路径
	String fileMd5 = null;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 校验文件是否已经上传并返回结果给前端
		System.out.println("支持断点续传，发送到后台判断是否已经上传过");

		// 文件唯一表示								
		fileMd5 = request.getParameter("fileMd5");
		// 当前分块下标
		String chunk = request.getParameter("chunk");
		// 当前分块大小
		String chunkSize = request.getParameter("chunkSize");

		// 找到分块文件
		File checkFile = new File(serverPath + "/" + fileMd5 + "/" + chunk);

		// 检查文件是否存在，且大小一致
		response.setContentType("text/html;charset=utf-8");
		if (checkFile.exists() && checkFile.length() == Integer.parseInt((chunkSize))) {
			response.getWriter().write("{\"ifExist\":1}");
		} else {
			response.getWriter().write("{\"ifExist\":0}");
		}
		
		return "manage.jsp";
	}

}
