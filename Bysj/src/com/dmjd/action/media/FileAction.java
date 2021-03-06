package com.dmjd.action.media;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.dmjd.action.Action;

public class FileAction implements Action {

	private String serverPath = "D:/Program Files/Tomcat/apache-tomcat-7.0.90/webapps/Dmjd/pict";//保存路径

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("进入后台...");

		// 1.创建DiskFileItemFactory对象，配置缓存用
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		// 2. 创建 ServletFileUpload对象
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

		// 3. 设置文件名称编码
		servletFileUpload.setHeaderEncoding("utf-8");

		// 4. 开始解析文件
		// 文件md5获取的字符串
		String fileMd5 = null;
		// 文件的索引
		String chunk = null;
		try {
			List<FileItem> items = servletFileUpload.parseRequest(request);
			for (FileItem fileItem : items) {

				if (fileItem.isFormField()) { // >> 普通数据
					String fieldName = fileItem.getFieldName();
					if ("info".equals(fieldName)) {
						String info = fileItem.getString("utf-8");
						System.out.println("info:" + info);
					}
					if ("fileMd5".equals(fieldName)) {
						fileMd5 = fileItem.getString("utf-8");
						System.out.println("fileMd5:" + fileMd5);
					}
					if ("chunk".equals(fieldName)) {
						chunk = fileItem.getString("utf-8");
						System.out.println("chunk:" + chunk);
					}
				} else { // >> 文件
					/*// 1. 获取文件名称
					String name = fileItem.getName();
					// 2. 获取文件的实际内容
					InputStream is = fileItem.getInputStream();
					
					// 3. 保存文件
					FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" + name));*/

					// 1. 获取文件名称
					String name = fileItem.getName();
					request.getSession().setAttribute("uploadname", name);//将名称存为参数进行传递
					
					// 2. 获取文件的实际内容
					InputStream is = fileItem.getInputStream();
					
					System.out.println("----------文件名称name:"+name);
					System.out.println("----------实际内容:"+is);
					
					// 如果文件夹没有创建文件夹
					File file = new File(serverPath + "/" + fileMd5);
					if (!file.exists()) {
						file.mkdirs();
					}
					// 保存文件
					File chunkFile = new File(serverPath + "/" + fileMd5 + "/" + chunk);
					System.out.println("---------chunkFile:"+chunkFile);
					
					//将文件名存入session进行传递
					request.getSession().setAttribute("chunkFile", chunkFile);
					
					FileUtils.copyInputStreamToFile(fileItem.getInputStream(), chunkFile);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "manage.jsp" ;
	}

}
