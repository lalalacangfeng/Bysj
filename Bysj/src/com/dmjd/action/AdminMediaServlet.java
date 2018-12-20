package com.dmjd.action;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;












import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.media.DelVedioAction;
import com.dmjd.action.media.FileAction;
import com.dmjd.action.media.UpdateAction;
import com.dmjd.action.media.UploadPictAction;
import com.dmjd.action.media.UploadVedioAction;
import com.dmjd.action.media.checkChunkAction;
import com.dmjd.action.media.mergeChunksAction;
import com.dmjd.entity.Column;
import com.dmjd.entity.Vedio;
import com.dmjd.entity.Pict;
import com.dmjd.factory.DaoFactory;

@WebServlet(
		name = "mediaServlet", 
		loadOnStartup=3,
		urlPatterns = { "/admin/media" })
public class AdminMediaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("\n*****上传文件媒体servlet测试*****\n");
		System.out.println("-----进入后台-----");
		String serverPath = "D:/Program Files/Tomcat/apache-tomcat-7.0.90/webapps/Dmjd/pict";//保存路径
		String fileMd5 = null;


		String action = request.getParameter("action");
		Action targetAction = null;
		String path = null;
		
		if (action!=null) {
			System.out.println("获取到action,动作为："+action);

			switch (action) {
			case "vedio":
				System.out.println("******上传视频******");
				targetAction = new UploadVedioAction();
				path = targetAction.execute(request, response);
				//此处不能用内部跳转，会影响js输出跳转
//				request.getRequestDispatcher(path).forward(request, response);
				break;
//			case "checkChunk":
//				// 校验文件是否已经上传并返回结果给前端
//				targetAction = new checkChunkAction();
//				path = targetAction.execute(request, response);
//				break;
//			case "mergeChunks":
//				// 通知合并分块
//				targetAction = new mergeChunksAction();
//				path = targetAction.execute(request, response);
//				break;
			case "pict":
				//上传图片表单
				System.out.println("上传图片");
				targetAction = new UploadPictAction();
				path = targetAction.execute(request, response);
				break;
			case "update":
				System.out.println("更新列表");
				targetAction = new UpdateAction();
				path = targetAction.execute(request, response);
				request.getRequestDispatcher(path).forward(request, response);
				break;
			case "del":
				System.out.println("删除视频");
				targetAction = new DelVedioAction();
				path = targetAction.execute(request, response);
				request.getRequestDispatcher(path).forward(request, response);
				break;
			default:
				break;
			}	
		}


	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			ArrayList<Pict> picts = DaoFactory.getMediaDaoInstance().getAllPicts();	
			ArrayList<Vedio> vedios = DaoFactory.getMediaDaoInstance().getAllMediaCount();	
			getServletContext().setAttribute("picts", picts);//将columns存为全局变量
			getServletContext().setAttribute("vedios", vedios);//将columns存为全局变量
			System.out.println("----------------------------\n"
							 + "------picts栏目初始化成功------\n"
							 + "------vedios栏目初始化成功------\n"
							 + "----------------------------\n");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------------------------\n"
					 + "------picts栏目初始化失败------\n"
					 + "------vedios栏目初始化失败------\n"
					 + "----------------------------\n");
			e.printStackTrace();
		}
	}

}
