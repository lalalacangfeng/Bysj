package com.dmfm.test;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dmfm.factory.DaoFactory;
import com.dmfm.pojo.Words;

public class Testword {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, Exception {
		ArrayList<Words> words = DaoFactory.getArticleDaoInstance()
				.findByWords();
		System.out.println("words:" + words);
	}
}
