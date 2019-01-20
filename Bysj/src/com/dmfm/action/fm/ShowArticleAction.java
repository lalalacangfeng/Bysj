package com.dmfm.action.fm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmfm.action.Action;
import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Article;
import com.dmfm.pojo.Reply;
import com.dmfm.pojo.Words;

public class ShowArticleAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Article article = null;
		ArrayList<Words> words = new ArrayList<>();
		ArrayList<Reply> replies = new ArrayList<>();
		try {
			int nid = Integer.valueOf(request.getParameter("nid"));
			article = DaoFactory.getArticleDaoInstance().queryById(nid);
			if (article != null) {//新闻存在
				System.out.println("查询words and replies");
				words = DaoFactory.getArticleDaoInstance().findByWords();
				replies = DaoFactory.getArticleDaoInstance().findByReply();
//				for (Words word : words) {
//					System.out.println("word.lw_for_article_id:"+word.getLw_for_article_id());
//				}
//				for (Reply reply : replies) {					
//					System.out.println("reply.lr_for_article_id:"+reply.getLr_for_article_id());
//				}
				request.getSession().setAttribute("fm_article", article);
				request.getSession().setAttribute("fm_words", words);
				request.getSession().setAttribute("fm_replies", replies);
				System.out.println("replies:"+replies);
				System.out.println("fm_article.id:"+article.getNid());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "article.jsp";
	}

}
