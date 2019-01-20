package com.dmfm.action.fm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dmfm.action.Action;
import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Article;
import com.dmfm.pojo.Pict;
import com.dmfm.pojo.Vedio;

public class FindFM implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Article> articles = new ArrayList<>();
		ArrayList<String> fm_Menu = new ArrayList<>();
		ArrayList<Pict> picts = new ArrayList<>();
		ArrayList<Vedio> vedios = new ArrayList<>();
//		HttpSession session = request.getSession();//获得session
//		session.invalidate();//将销毁session
//		System.out.println("销毁session成功");
		try {
//			System.out.println("新建session成功");
			System.out.println("request.getServletPath():"+request.getServletPath());
			int servletpath = Integer.valueOf(request.getServletPath().substring(1, request.getServletPath().length()-3));
			articles = DaoFactory.getArticleDaoInstance().findArticles(servletpath);
			fm_Menu = DaoFactory.getColumnDaoInstance().findMenu(servletpath);
			picts = DaoFactory.getPictDaoInstance().findPict(servletpath);
			vedios = DaoFactory.getVedioDaoInstance().findVedios(servletpath);
			if (!articles.isEmpty() && picts.isEmpty() && vedios.isEmpty()) {//新闻存在
				request.setAttribute("fm_articles", articles);
				System.out.println("新建fm_articles成功");
			} else if (articles.isEmpty() && !picts.isEmpty() && vedios.isEmpty()) {
				request.setAttribute("fm_picts", picts);
				System.out.println("新建fm_picts成功");
			} else if (articles.isEmpty() && picts.isEmpty() && !vedios.isEmpty()) {
				request.setAttribute("fm_vedios", vedios);
				System.out.println("新建fm_vedios成功");
			}
			request.getSession().setAttribute("fm_Menu", fm_Menu);			
			System.out.println("新建fm_Menu成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "fm/fm.jsp";
	}

}
