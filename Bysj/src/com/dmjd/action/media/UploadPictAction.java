package com.dmjd.action.media;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dmjd.action.Action;
import com.dmjd.entity.Vedio;
import com.dmjd.entity.Pict;
import com.dmjd.factory.DaoFactory;

public class UploadPictAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String len = request.getParameter("len");
		
		ArrayList<Pict> picts = new ArrayList<>();
		
		//提供解析时的一些缺省配置
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//创建一个解析器，分析InputStream，该解析器会将分析的结果封装成一个FIleItem对象集合
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {					
			Pict pict = new Pict();
			List<FileItem> items = sfu.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				//区分是否为上传文件还是普通表单域
				if (item.isFormField()) {//isFormFiled()为true，表示不是文件上传表单域
					//普通表单域
					String paramName = item.getFieldName();
					System.out.println("参数名称为："+paramName+"，对应参数值为："+item.getString());
					
					if (paramName.equals("name")) {
						pict.setName(new String(item.getString().getBytes("ISO8859-1"),"UTF-8"));
					}else if (paramName.equals("descript")) {
						pict.setDescript(new String(item.getString().getBytes("ISO8859-1"),"UTF-8"));
					}
				}
				else {
					
					//上传文件
					System.out.println("上传文件:"+item.getName());

					ServletContext sctx = request.getServletContext();

					//获得保存文件的路径
					String basePath = sctx.getRealPath("pict");
					
					//获得文件名
					String fileUrl = item.getName();
					
					//在某些操作系统上，item.getName()方法会返回文件的完整名称，即包括路径
					String fileType = fileUrl.substring(fileUrl.lastIndexOf("."));//截取文件格式

					//自定义方式产生文件名
					String serialName = String.valueOf(System.currentTimeMillis());
					//存放临时文件
					File uploadFile = new File(basePath+"/temp/"+serialName+fileType);
					item.write(uploadFile);

					pict.setSrc(serialName+fileType);
//					picts.add(pict);
					System.out.println("pict属性:"+pict.toString());
					
					System.out.println("sctx:"+sctx+" fileUrl:"+fileUrl+" fileType:"+fileType);

				}
			}
			System.out.println("-------------------------");

			response.setContentType("text/html;charset=utf-8");

			int result = DaoFactory.getMediaDaoInstance().savePict(pict);
			if (result==1) {
				System.out.println("上传图片成功");
				request.setAttribute("status", "上传成功");
				response.getWriter().write("{\"ifExist\":1}");							
			}else {
				request.setAttribute("status", "上传失败");
				response.getWriter().write("{\"ifExist\":2}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "manage.jsp";
	}

}
