package com.dmjd.action.media;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmjd.action.Action;

public class mergeChunksAction implements Action {

	//文件md5值
	String fileMd5 = null;
	//保存路径
	String serverPath = "D:/Program Files/Tomcat/apache-tomcat-7.0.90/webapps/Dmjd/pict";
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 获得需要合并的目录
			fileMd5 = request.getParameter("fileMd5");

			//获取文件名
			String uploadname = (String) request.getSession().getAttribute("uploadname");
			System.out.println("******获取文件名:"+uploadname);
			
			String fileType = getFileType(uploadname);
			
			// 读取目录所有文件
			File f = new File(serverPath + "/" + fileMd5);
			File[] fileArray = f.listFiles(new FileFilter() {

				// 排除目录，只要文件
				@Override
				public boolean accept(File pathname) {
					if (pathname.isDirectory()) {
						return false;
					}
					return true;
				}
			});

			// 转成集合，便于排序
			List<File> fileList = new ArrayList<File>(Arrays.asList(fileArray));
			// 从小到大排序
			Collections.sort(fileList, new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
						return -1;
					}
					return 1;
				}

			});

			// 新建保存文件
			File outputFile = new File(serverPath + "/" + UUID.randomUUID().toString() + fileType );
			
			System.out.println("*****outputFile:"+outputFile.getName());
			
			// 创建文件
			outputFile.createNewFile();

			// 输出流
			FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
			FileChannel outChannel = fileOutputStream.getChannel();

			// 合并
			FileChannel inChannel;
			for (File file : fileList) {
				inChannel = new FileInputStream(file).getChannel();
				inChannel.transferTo(0, inChannel.size(), outChannel);
				inChannel.close();

				// 删除分片
				file.delete();
			}

			// 关闭流
			fileOutputStream.close();
			outChannel.close();

			// 清除文件加
			File tempFile = new File(serverPath + "/" + fileMd5);
			if (tempFile.isDirectory() && tempFile.exists()) {
				tempFile.delete();
			}

			System.out.println("合并文件成功");
		
		return "manage.jsp";
	}
	
	
	/**
	 * 获取文件的后缀
	 * @param name
	 * @return .jpeg
	 */
	public static String getFileType(String name) {
		if (name != null && !"".equals(name)) {
			int index = name.lastIndexOf(".");
			if (index != -1) {
				return name.substring(index);
			}
		}else{
			return ".jpeg";
		}
		return "";
	}


}
