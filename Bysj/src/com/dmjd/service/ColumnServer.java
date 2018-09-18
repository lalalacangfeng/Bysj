package com.dmjd.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dmjd.dao.ColumnDao;
import com.dmjd.dao.ColumnDaoImpl;
import com.dmjd.database.ConSql;
import com.dmjd.entity.Column;

public class ColumnServer implements ColumnDao {

	ConSql db = new ConSql();
	private Connection con = null;
	private ColumnDao dao = null;
	
	public ColumnServer() throws ClassNotFoundException, SQLException{
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
	public ArrayList<Column> findDmColumns() throws Exception {
		ArrayList<Column> columns = new ArrayList<>();
		try {
			columns = this.dao.findDmColumns();
		} catch (Exception e) {
			throw e;
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
	public int delColumn(int cid,int kid) throws Exception {
		int result = 0;
		try {
			result = this.dao.delColumn(cid,kid);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}


	@Override
	public Column showColumn(int cid, int kid) throws Exception {
		Column column = new Column();
		try {
			column = this.dao.showColumn(cid, kid);
		} catch (Exception e) {
			throw e;
		}
		return column;
	}


	@Override
	public int editColumn(int cid, int kid,Column column) throws Exception {
		int result = 0;
		try {
			result = this.dao.editColumn(cid,kid,column);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}


	@Override
	public ArrayList<Column> initColumn(int cid, int kid) throws Exception {
		ArrayList<Column> columns = new ArrayList<>();
		try {
			columns = this.dao.initColumn(cid,kid);
		} catch (Exception e) {
			throw e;
		}
		return columns;
	}


}
