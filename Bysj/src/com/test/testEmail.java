package com.test;

import java.sql.SQLException;

import com.dmfm.factory.DaoFactory;

public class testEmail {
public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
	String email1 = "55921121@qq.com";
	String email2 = "546378592@qq.com";
	String uname = "xiao";
	int result1,result2,rs3;
	result1 = DaoFactory.getUserDaoInstance().checkEmail(email1);
	result2 = DaoFactory.getUserDaoInstance().checkEmail(email2);
	rs3 = DaoFactory.getUserDaoInstance().checkName(uname);
	System.out.println("r1:"+result1);
	System.out.println("r2:"+result2);
	System.out.println("r3:"+rs3);
}
}
