package com.dmjd.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.dmjd.entity.User;
import com.dmjd.factory.DaoFactory;

public class TestUserList {
	
	public static Vector<User> uservector = new Vector<User>();
	
	
	public static void main(String[] args) {
		ArrayList<User> userlist = new ArrayList<>();
		System.out.println("----TestUserList----");
		try {
			userlist = DaoFactory.getUserDaoInstance().FindAll();
			System.out.println(userlist);
			for (User user : userlist) {
				System.out.println("user hasCode:"+user.hashCode());
				System.out.println(userlist.get(1));
				System.out.println(" uid "+user.getUid()+" name "+user.getUsername());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
