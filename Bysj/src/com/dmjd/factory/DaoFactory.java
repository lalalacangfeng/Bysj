package com.dmjd.factory;

import java.sql.SQLException;

import com.dmjd.dao.ArticleDao;
import com.dmjd.dao.ColumnDao;
import com.dmjd.dao.MediaDao;
import com.dmjd.dao.UserDao;
import com.dmjd.service.ArticleService;
import com.dmjd.service.ColumnService;
import com.dmjd.service.MediaService;
import com.dmjd.service.UserService;

public class DaoFactory {
	//取得User业务操作类
	public static UserDao getUserDaoInstance() throws ClassNotFoundException, SQLException{
		return new UserService();
	}

	//取得Column业务操作类
	public static ColumnDao getColumnDaoInstance() throws SQLException, ClassNotFoundException{
		return new ColumnService();
	}
	
	//取得Article业务操作类
	public static ArticleDao getArticleDaoInstance() throws SQLException, ClassNotFoundException{
		return new ArticleService();
	}
	
	//取得Media业务操作类
	public static MediaDao getMediaDaoInstance() throws SQLException,ClassNotFoundException{
		return new MediaService();
	}
}
