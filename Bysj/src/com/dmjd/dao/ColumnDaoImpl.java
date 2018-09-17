package com.dmjd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dmjd.entity.Column;

public class ColumnDaoImpl implements ColumnDao {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ColumnDaoImpl(Connection con){
		this.con = con;
	}
	
	@Override
	public ArrayList<Column> findAllColumns() throws Exception {
		ArrayList<Column> columns = new ArrayList<>();
		String sql = "select * from kind;";
		pstmt = this.con.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while (rs.next()) {
			Column column = new Column();
			column.setKid(rs.getInt(1));
			column.setKindname(rs.getString(2));
			columns.add(column);
		}
		sql = "select * from class join kind on class.kid=kind.kid having class.kid<=2;";
		pstmt = this.con.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while (rs.next()) {
			Column column = new Column();
			column.setCid(rs.getInt(1));
			column.setClassname(rs.getString(2));
			column.setKid(rs.getInt(4));
			column.setKindname(rs.getString(5));
			columns.add(column);
		}
		rs.close();
		pstmt.close();
		return columns;
	}

	@Override
	public ArrayList<Column> findDmColumns() throws Exception {
		ArrayList<Column> columns = new ArrayList<>();
		String sql = "select * from class join kind on class.kid=kind.kid having class.kid=4";
		pstmt = this.con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Column column = new Column();
			column.setCid(rs.getInt(1));
			column.setClassname(rs.getString(2));
			column.setKid(rs.getInt(4));
			column.setKindname(rs.getString(5));
			columns.add(column);
		}
		rs.close();
		pstmt.close();
		return columns;
	}

	@Override
	public int addColumn(Column column) throws Exception {
		int result = 0;
		String sql = null;
		if (column.getCid()==0 && column.getClassname()==null) {
			//添加栏目为一级
			sql = "insert into kind(kid,kindname) values(?,?)";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, column.getKid());
			pstmt.setString(2, column.getKindname());
			result = pstmt.executeUpdate();
			System.out.println("添加一级栏目成功");
		} else if (column.getKid()==4) {
			//添加栏目为动漫标签
			sql = "insert into class(cid,classname,kid) values(?,?,4)";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, column.getCid());
			pstmt.setString(2, column.getClassname());
			result = pstmt.executeUpdate();
			System.out.println("添加动漫标签成功");
		} else if (column.getKid()<=2) {
			//添加栏目为二级
			sql = "insert into class(cid,classname,kid) values(?,?,?)";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, column.getCid());
			pstmt.setString(2, column.getClassname());
			pstmt.setInt(3, column.getKid());
			result = pstmt.executeUpdate();
			System.out.println("添加二级栏目成功");
		} else {
			System.out.println("添加失败");
		}
		pstmt.close();			
		return result;
	}

	@Override
	public int delColumn(int cid,int kid) throws Exception {
		int result = 0;
		String sql = null;
		if(cid==0&kid>0) {
			//还有问题，联表删除
			//删除一级栏目
			sql = "delete from kind where kid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, kid);
			result = pstmt.executeUpdate();
			System.out.println("删除一级成功");
		}else if (cid!=0&&kid<=2) {
			//删除二级标签
			sql = "delete from class where cid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			result = pstmt.executeUpdate();
			System.out.println("删除二级成功");
		} else if (cid!=0&&kid==4) {
			//删除动漫标签
			sql = "delete from class where cid=? and kid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.setInt(2, kid);
			result = pstmt.executeUpdate();
			System.out.println("删除动漫标签成功");
		} else {
			System.out.println("删除失败");
		}
		pstmt.close();			
		return result;
	}

	@Override
	public Column showColumn(int cid, int kid) throws Exception {
		Column column = new Column();
		String sql = null;
		if(cid==0&kid>0) {
			//显示一级
			sql = "select * from kind where kid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, kid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				column.setKid(rs.getInt(1));
				column.setKindname(rs.getString(2));
				System.out.println("显示1");
			}
		}else if (cid!=0&&kid<=2) {
			//显示二级标签
			sql = "select * from class join kind on class.kid=kind.kid where class.cid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				column.setCid(rs.getInt(1));
				column.setClassname(rs.getString(2));
				column.setKid(rs.getInt(3));
				column.setKindname(rs.getString(5));
				System.out.println("显示2");
			}
		}else if(cid!=0&&kid==4){
			//显示动漫标签
			sql = "select * from class join kind on class.kid=kind.kid where class.cid=? and class.kid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.setInt(2, kid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				column.setCid(rs.getInt(1));
				column.setClassname(rs.getString(2));
				column.setKid(rs.getInt(3));
				column.setKindname(rs.getString(5));
				System.out.println("显示3");
			}
		}
		rs.close();
		pstmt.close();
		return column;
	}

	@Override
	public int editColumn(int cid, int kid,Column column) throws Exception {
		int result =0;
		String sql = null;
		if(cid==0&kid>0) {
			//修改一级
			sql = "update kind set kindname=? where kid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, column.getKindname());
			pstmt.setInt(2, kid);
			result = pstmt.executeUpdate();
			System.out.println("修改1成功");
		}else if (cid!=0&&kid<=2) {
			sql = "update kind set classname=?,kid=? where cid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, column.getClassname());
			pstmt.setInt(2, column.getKid());
			pstmt.setInt(3, cid);
			result = pstmt.executeUpdate();
			System.out.println("修改2成功");
		}else if(cid!=0&&kid==4){
			sql = "update kind set classname=?,kid=? where kid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, column.getClassname());
			pstmt.setInt(2, column.getKid());
			pstmt.setInt(3, kid);
			result = pstmt.executeUpdate();
			System.out.println("修改3成功");
		}
		pstmt.close();
		return result;
	}
}
