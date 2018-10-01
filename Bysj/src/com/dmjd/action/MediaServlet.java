package com.dmjd.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dmjd.action.media.UploadFileAction;
import com.dmjd.entity.Media;
import com.dmjd.factory.DaoFactory;


@WebServlet(
		urlPatterns = { "/media" },
		name = "mediaServlet"
)
public class MediaServlet extends HttpServlet {

	public MediaServlet(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("测试测试测试测试测试测试测试测试测试测试");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		String uripath = uri.substring(uri.lastIndexOf("/"));
		System.out.println("uripath:"+uripath);
		
		Action targetAction = null;
		String path = null;
		
		if("/media".equals(uripath)){
			System.out.println("----上传测试----"+uripath);
			targetAction = new UploadFileAction();
			path = targetAction.execute(request, response);
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

}
