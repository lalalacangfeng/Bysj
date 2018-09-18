package com.dmjd.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmjd.database.ConSql;
import com.dmjd.entity.Column;
import com.dmjd.factory.DaoFactory;

public class TestFindAllColumn {
	
	public static void main(String[] args) {
		System.out.println("---TestFindAllColumn----");
		ArrayList<Column> columns = new ArrayList<>();
		try {
			columns = DaoFactory.getColumnDaoInstance().findDmColumns();
			System.out.println("success");
			for (Column column : columns) {
				System.out.println(column.toString());
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}

	}

}
