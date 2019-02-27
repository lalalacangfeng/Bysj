package com.dmjd.action.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.dmjd.action.Action;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.Vedio;

public class UploadVedioAction implements Action {

	/***
	 * 上传视频
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		//提供解析时的一些缺省配置
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//创建一个解析器，分析InputStream，该解析器会将分析的结果封装成一个FIleItem对象集合
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			Vedio vedio = new Vedio();
			List<FileItem> items = sfu.parseRequest(request);
			boolean flag = false;//判断转码是否成功
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				//区分是否为上传文件还是普通表单域
				if (item.isFormField()) {//isFormFiled()为true，表示不是文件上传表单域
					//普通表单域
					String paramName = item.getFieldName();
					System.out.println("参数名称为："+paramName+"，对应参数值为："+item.getString());
					
					if (paramName.equals("name")) {
						vedio.setName(new String(item.getString().getBytes("ISO8859-1"),"UTF-8"));
					}else if (paramName.equals("descript")) {
						vedio.setDescript(new String(item.getString().getBytes("ISO8859-1"),"UTF-8"));
					}
				}
				else {
					//上传文件
					System.out.println("上传文件:"+item.getName());
					ServletContext sctx = request.getServletContext();
					//获得保存文件的路径
					String basePath = sctx.getRealPath("videos");
					
					File videosfile = new File(basePath);
					if (videosfile.exists()) {
						if (videosfile.isDirectory()) {
							System.out.println("vediosfile是文件夹");
						}else {
							 System.out.println("同名的文件存在，不能创建文件夹。");
						}
					}else {
						System.out.println("videosfile文件夹不存在，创建该文件夹。");
						videosfile.mkdir();
					}
					File tempfile = new File(basePath+"/temp/");
					if (tempfile.exists()) {
						if (tempfile.isDirectory()) {
							System.out.println("tempfile是文件夹");
						}else {
							System.out.println("tempfile同名的文件存在，不能创建文件夹。");
						}
					}else {
						System.out.println("文件夹不存在，创建该文件夹。");
						tempfile.mkdir();
					}
					File imagesfile = new File(basePath+"/images/");
					if (imagesfile.exists()) {
						if (imagesfile.isDirectory()) {
							System.out.println("imagesfile是文件夹");
						}else {
							System.out.println("同名的文件存在，不能创建文件夹。");
						}
					}else {
						System.out.println("imagesfile文件夹不存在，创建该文件夹。");
						imagesfile.mkdir();
					}
					
					
					//获得文件名
					String fileUrl = item.getName();
					//在某些操作系统上，item.getName()方法会返回文件的完整名称，即包括路径
					String fileType = fileUrl.substring(fileUrl.lastIndexOf("."));//截取文件格式
					//自定义方式产生文件名
					String serialName = String.valueOf(System.currentTimeMillis());
					//待转码文件
					File uploadFile = new File(basePath+"/temp/"+serialName+fileType);
					//上传到临时文件夹temp中
					item.write(uploadFile);
					
					FileInputStream fis = new FileInputStream(uploadFile);
					String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
					IOUtils.closeQuietly(fis);
					System.out.println("MD5:"+md5);
					vedio.setMd5(md5);
					
					if (DaoFactory.getMediaDaoInstance().IfSameVedio(vedio)) {
						//标题或视频内容重复了
						break;
					}else{
						if (item.getSize()>500*1024*1024) {
							request.setAttribute("status", "上传失败，文件过大，超过500M");
						}
						String codcFilePath = basePath+"/"+serialName+".MP4";//设置转换为flv格式后文件的保存路径
						String mediaPicPath = basePath+"/images"+File.separator+serialName+".jpg";//设置上传视频截图的保存路径
						System.out.println("codcFilePath:"+codcFilePath);
						//获取配置的转换工具（ffmpeg.exe）的存放路径
						String ffmpegPath = request.getServletContext().getRealPath("/tools/ffmpeg.exe");
						
						vedio.setSrc("videos/"+serialName+".MP4");
						vedio.setPicture("videos/images/"+serialName+".jpg");
						
						//转码
						flag = DaoFactory.getMediaDaoInstance().executeCodecs(ffmpegPath, uploadFile.getAbsolutePath(), codcFilePath, mediaPicPath);
					}
					
				}
			}
			System.out.println("-------------------------");
			String action = request.getParameter("action");
			System.out.println("upload action:"+action);
			System.out.println("*************上传结束，flag为："+flag);
			response.setContentType("text/html;charset=utf-8");
			if (flag) {
				//转码成功保存视频
				int result = DaoFactory.getMediaDaoInstance().saveVedio(vedio);
				if (result==1) {
					request.setAttribute("status", "上传成功");
					response.getWriter().write("{\"ifExist\":1}");							
				}else {
					request.setAttribute("status", "上传失败");
					response.getWriter().write("{\"ifExist\":2}");
				}
			}else {
				request.setAttribute("status", "标题或视频内容重复了，转码失败");
				response.getWriter().write("{\"ifExist\":3}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
						
		return "media?action=update";
	}

}
