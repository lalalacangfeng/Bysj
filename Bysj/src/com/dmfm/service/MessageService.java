package com.dmfm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmfm.dao.MessageDao;
import com.dmfm.dao.MessageDaoImpl;
import com.dmfm.db.ConSql;
import com.dmfm.pojo.Message;

public class MessageService implements MessageDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private MessageDao dao = null;
	
	public MessageService() throws ClassNotFoundException,SQLException{
		this.con = db.getCon();
		this.dao = new MessageDaoImpl(this.con);
	}

	
	@Override
	public ArrayList<Message> findMessages(int id) throws Exception {
		ArrayList<Message> messages = new ArrayList<>();
		try {
			messages = this.dao.findMessages(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return messages;
	}

	@Override
	public int addMessages(Message message) throws Exception {
		int result = 0;
		try {
			result = this.dao.addMessages(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return result;
	}

	@Override
	public int delMessages(int mid) throws Exception {
		int result= 0 ;
		try {
			result = this.dao.delMessages(mid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			con.close();
		}
		return result;
	}

}
