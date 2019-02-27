package com.dmfm.factory;

import java.sql.SQLException;

import com.dmfm.dao.ArticleDao;
import com.dmfm.dao.InitDao;
import com.dmfm.dao.MenuDao;
import com.dmfm.dao.MessageDao;
import com.dmfm.dao.PictDao;
import com.dmfm.dao.UserDao;
import com.dmfm.dao.VedioDao;
import com.dmfm.pojo.Message;
import com.dmfm.service.ArticleService;
import com.dmfm.service.InitService;
import com.dmfm.service.MenuService;
import com.dmfm.service.MessageService;
import com.dmfm.service.PictService;
import com.dmfm.service.UserService;
import com.dmfm.service.VedioService;

public class DaoFactory {
	//取得Column项目类
	public static MenuDao getColumnDaoInstance() throws ClassNotFoundException,SQLException{
		return new MenuService();
	}

	//取得Article项目类
	public static ArticleDao getArticleDaoInstance() throws ClassNotFoundException,SQLException{
		return new ArticleService();
	}
	
	//初始化
	public static InitDao InitDaoInstance() throws ClassNotFoundException,SQLException{
		return new InitService();
	}
	
	//vedio
	public static VedioDao getVedioDaoInstance() throws ClassNotFoundException,SQLException{
		return new VedioService();
	}
	
	public static PictDao getPictDaoInstance() throws ClassNotFoundException,SQLException{
		return new PictService();
	}
	
	//取得User项目类
	public static UserDao getUserDaoInstance()throws ClassNotFoundException,SQLException{
		return new UserService();
	}

	public static MessageDao getMessageDaoInstance() throws ClassNotFoundException,SQLException{
		return new MessageService();
	}
}
