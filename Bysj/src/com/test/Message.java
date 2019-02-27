package com.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.dmfm.factory.DaoFactory;


public class Message {
	@Test
	public void testmessage() throws ClassNotFoundException, SQLException, Exception{
		ArrayList<com.dmfm.pojo.Message> messages = DaoFactory.getMessageDaoInstance().findMessages(1005);
		System.out.println(messages.toString());
	}
}
