package com.dmjd.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmjd.dao.ColumnDao;
import com.dmjd.dao.ColumnDaoImpl;
import com.dmjd.database.ConSql;
import com.dmjd.entity.Column;

public class ColumnService implements ColumnDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private ColumnDao dao = null;
	
	// 构造方法中实例化数据库连接同时实例化dao对象
	public ColumnService() throws ClassNotFoundException, SQLException{
		this.con = db.getCon();
		this.dao = new ColumnDaoImpl(this.con);
	}
	
	
	@Override
	public ArrayList<Column> findAllColumns() throws Exception {
		ArrayList<Column> columns = new ArrayList<>();
		try {
			columns = this.dao.findAllColumns();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.con.close();
		}
		return columns;
	}


	@Override
	public int addColumn(Column column) throws Exception {
		int result = 0;
		try {
			result = this.dao.addColumn(column);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int delColumn(int id,int pid) throws Exception {
		int result = 0;
		try {
			result = this.dao.delColumn(id,pid);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}


	@Override
	public Column showColumn(int id, int pid) throws Exception {
		Column column = new Column();
		try {
			column = this.dao.showColumn(id, pid);
		} catch (Exception e) {
			throw e;
		}
		return column;
	}


	@Override
	public int editColumn(int id,Column column) throws Exception {
		int result = 0;
		try {
			result = this.dao.editColumn(id,column);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}


}
