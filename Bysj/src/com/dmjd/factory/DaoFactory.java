package com.dmjd.factory;

import java.sql.SQLException;

import com.dmjd.dao.ArticleDao;
import com.dmjd.dao.ColumnDao;
import com.dmjd.dao.UserDao;
import com.dmjd.service.ArticleServer;
import com.dmjd.service.ColumnServer;
import com.dmjd.service.UserService;

public class DaoFactory {
	//取得User业务操作类
	public static UserDao getUserDaoInstance() throws ClassNotFoundException, SQLException{
		return new UserService();
	}
	
	//取得News业务操作类
	public static ArticleDao getArticleDaoInstance() throws SQLException, ClassNotFoundException{
		return new ArticleServer();
	}
	
	public static ColumnDao getColumnDaoInstance() throws SQLException, ClassNotFoundException{
		return new ColumnServer();
	}
	
}
