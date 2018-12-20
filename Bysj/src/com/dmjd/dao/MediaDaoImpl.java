package com.dmjd.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.dmjd.entity.Vedio;
import com.dmjd.entity.Pict;
import com.mysql.fabric.xmlrpc.base.Data;

public class MediaDaoImpl implements MediaDao {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MediaDaoImpl(Connection con) {
		this.con = con;
	}

	/**
	 * 视频转码
	 * @param ffmpegPath    转码工具的存放路径
	 * @param upFilePath    用于指定要转换格式的文件,要截图的视频源文件
	 * @param codcFilePath    格式转换后的的文件保存路径
	 * @param mediaPicPath    截图保存路径
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean executeCodecs(String ffmpegPath, String upFilePath,
			String codcFilePath, String mediaPicPath) throws Exception {
	        // 创建一个List集合来保存转换视频文件为flv格式的命令
		ArrayList<String> convert = new ArrayList<String>();
	        convert.add(ffmpegPath); // 添加转换工具路径
	        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
	        convert.add(upFilePath); // 添加要转换格式的视频文件的路径
	        convert.add("-c:v");     
	        convert.add("libx264");
	        convert.add("-mbd");       
	        convert.add("0");
	        convert.add("-c:a");       
	        convert.add("aac");
	        convert.add("-strict");       
	        convert.add("-2");
	        
	        convert.add("-pix_fmt");
	        convert.add("yuv420p");
	        convert.add("-movflags");
	        convert.add("faststart");
	        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
	        convert.add(codcFilePath);
	 
	        // 创建一个List集合来保存从视频中截取图片的命令
	        ArrayList<String> cutpic = new ArrayList<String>();
	        cutpic.add(ffmpegPath);
	        cutpic.add("-i");
	        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
	        cutpic.add("-y");
	        cutpic.add("-f");
	        cutpic.add("image2");
	        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
	        cutpic.add("17"); // 添加起始时间为第17秒
	        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
	        cutpic.add("0.001"); // 添加持续时间为1毫秒
	        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
	        cutpic.add("800*280"); // 添加截取的图片大小为350*240
	        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
	 
	        boolean mark = true;
	        ProcessBuilder builder = new ProcessBuilder();
	        try {
	        	builder.command(convert);
	        	builder.redirectErrorStream(true);
	        	Process process =builder.start();
	        	
	        	//获取进程的标准输入流
	            final InputStream is1 = process.getInputStream();
	            
	            //获取进程的标准错误流
	            final InputStream is2 = process.getErrorStream();
	            
	            //启动两个线程，一个线程负责读标准输出流一个负责错误流
	            new Thread(){
	            	public void run(){
	            		BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
	            		try {
							String line1 = null;
							while((line1 = br1.readLine()) != null){
								if(line1!= null){}
							}
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
	            		finally{
	            			try {
								is1.close();
							} catch (Exception e2) {
								e2.printStackTrace();
								// TODO: handle exception
							}
	            		}
	            	}
	            }.start();
	            
	            new Thread(){
	            	public void run(){
	            		BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
	            		try {
							String line2 = null;
							while((line2 = br2.readLine()) != null){
								if(line2!= null){}
							}
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
	            		finally{
	            			try {
								is2.close();
							} catch (Exception e2) {
								e2.printStackTrace();
								// TODO: handle exception
							}
	            		}
	            	}
	            }.start();
	            
	            //builder.start();
	            process.waitFor();
	            process.destroy();
	            System.out.println("ffmpeg.exe运行完毕！"); 
	            /*
	             * 如有必要，一直要等到由该 Process 对象表示的进程已经终止。如果已终止该子进程，此方法立即返回。
	             * 但是直接调用这个方法会导致当前线程阻塞，直到退出子进程。
	             * 对此JDK文档上还有如此解释：因为本地的系统对标准输入和输出所提供的缓冲池有效，
	             * 所以错误的对标准输出快速的写入何从标准输入快速的读入都有可能造成子进程的锁，甚至死锁。
	             * 可执行程序的标准输出比较多，而运行窗口的标准缓冲区不够大，所以发生阻塞。
	             * 假设该程序不断在向标准输出流和标准错误流写数据，而JVM不读取的话，当缓冲区满之后将无法继续写入数据，最终造成阻塞在waitfor()这里。
	             * */
	            
	            builder.command(cutpic);
	            builder.redirectErrorStream(true);
	            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
	            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
	            builder.start();
	        } catch (Exception e) {
	            mark = false;
	            System.out.println(e);
	            e.printStackTrace();
	        }
	        return mark;
	}

	/***
	 * 保存上传视频
	 */
	@Override
	public int saveVedio(Vedio vedio) throws Exception {
		int result = 0;
		String sql = "insert into vedio(name,src,picture,author,descript,md5)"
				+ " values(?,?,?,?,?,?)";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, vedio.getName());
		pstmt.setString(2, vedio.getSrc());
		pstmt.setString(3, vedio.getPicture());
		pstmt.setString(4, vedio.getAuthor());
		pstmt.setString(5, vedio.getDescript());
		pstmt.setString(6, vedio.getMd5());
		System.out.println(" vedio.getName()："+vedio.getName()+" vedio.getSrc():"+vedio.getSrc());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	/***
	 * 查询所有视频列表
	 */
	@Override
	public ArrayList<Vedio> getAllMediaCount() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Vedio> vedios = new ArrayList<>();
		String sql = "select * from vedio";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Vedio vedio = new Vedio();
			vedio.setVid(rs.getInt(1));
			vedio.setName(rs.getString(2));
			vedio.setSrc(rs.getString(3));
			vedio.setPicture(rs.getString(4));
			vedio.setAuthor(rs.getString(5));
			vedio.setDescript(rs.getString(6));
			vedios.add(vedio);
		}
		System.out.println("查询成功");
		rs.close();
		pstmt.close();
		return vedios;
	}
	
	/***
	 * 查询所有图片
	 */
	public ArrayList<Pict> getAllPicts() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Pict> picts = new ArrayList<>();
		String sql = "select * from pict";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Pict pict = new Pict();
			pict.setId(rs.getInt(1));
			pict.setName(rs.getString(2));
			pict.setDescript(rs.getString(3));
			pict.setSrc(rs.getString(4));
			picts.add(pict);
		}
		rs.close();
		pstmt.close();
		return picts;
	}

	@Override
	public Vedio queryMediaById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public int savePict(ArrayList<Pict> picts) throws Exception {
//		// TODO Auto-generated method stub
//		int result = 0;
//		for (Pict pict : picts) {
//			String sql = "insert into pict(name,descript,src) value(?,?,?)";
//			pstmt = this.con.prepareStatement(sql);
//			pstmt.setString(1, pict.getName());
//			pstmt.setString(2, pict.getDescript());
//			pstmt.setString(3, pict.getSrc());
//			result = pstmt.executeUpdate();
//			if (result == 1) {
//				continue;
//			}else {
//				break;
//			}
//		}
//		return result;
//	}

	@Override
	public int savePict(Pict pict) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "insert into pict(name,descript,src) value(?,?,?)";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, pict.getName());
		pstmt.setString(2, pict.getDescript());
		pstmt.setString(3, pict.getSrc());
		result = pstmt.executeUpdate();
		return result;
	}

	@Override
	public String findVedioByMd5(String value) throws Exception {
		// TODO Auto-generated method stub
		String md5 = null;
		String sql = "select * from vedio where md5=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, value);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			md5 = rs.getString(7);
		}
		return md5;
	}

	@Override
	public Boolean IfSameVedio(Vedio vedio) throws Exception {
		// TODO Auto-generated method stub
		boolean mask = false;
		String sql = "select * from vedio where name=? or md5=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, vedio.getName());
		pstmt.setString(2, vedio.getMd5());
		rs = pstmt.executeQuery();
		if (rs.next()) {
			mask = true;
		}
		return mask;
	}

	@Override
	public int delVedio(int id) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "delete from vedio where vid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, id);
		result = pstmt.executeUpdate();
		return result;
	}

}
