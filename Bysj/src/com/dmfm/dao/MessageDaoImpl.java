package com.dmfm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.dmfm.pojo.Message;

public class MessageDaoImpl implements MessageDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	//默认构造方法中实例化数据库连接
	public MessageDaoImpl(Connection con){
		this.con = con;
	}
	
	@Override
	public ArrayList<Message> findMessages(int id) throws Exception {
		ArrayList<Message> messages = new ArrayList<>();
		String sql = "select * from message join kind where message.pid=? and message.pid=kind.kid order by mid desc";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, id);
		System.out.println("id:"+id);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Message message = new Message();
			message.setMid(rs.getInt(1));
			message.setTitle(rs.getString(2));
			message.setContent(rs.getString(3));
			message.setAuthor(rs.getString(4));
			message.setReleasetime(rs.getTimestamp(5));
			message.setPid(rs.getInt(6));
			message.setUid(rs.getInt(7));			
			messages.add(message);
		}rs.close();pstmt.close();
		return messages;
	}

	@Override
	public int addMessages(Message message) throws Exception {
		int result = 0;
		String sql = "insert into message(title,content,author,releasetime) values("
				+ "?,?,?,now())";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, message.getTitle());
		pstmt.setString(2, message.getContent());
		pstmt.setString(3, message.getAuthor());
		result=pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int delMessages(int mid) throws Exception {
		int result = 0;
		String sql = "delete from message where mid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, mid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

}
