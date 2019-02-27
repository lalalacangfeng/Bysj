package com.test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dmjd.dao.UserDaoImpl;
import com.dmjd.factory.DaoFactory;
import com.dmjd.pojo.User;

public class Timetest {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, Exception {
		// System.out.println("time:"+System.currentTimeMillis());
		// Long now = System.currentTimeMillis();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(sdf.parse(sdf.format(System.currentTimeMillis())));
		// User user = DaoFactory.getUserDaoInstance().queryByName("super");
		// System.out.println("getLasttime:"+user.getLasttime());
		// System.out.println("getMisstime:"+user.getMisstime());

		int r = 0;
		int uid = 1;
		int number = 3;
		String misstime = "2019-01-23 14:16:57.0";
		r = DaoFactory.getUserDaoInstance().resetNumberAndTime(uid, number,
				misstime);
		System.out.println(r);
	}
}
