package com.dmjd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dmjd.pojo.Column;

public class ColumnDaoImpl implements ColumnDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集
	
	//构造方法中实例化数据库连接
	public ColumnDaoImpl(Connection con){
		this.con = con;
	}
	
	/***
	 * 查询所有一级栏目
	 */
	@Override
	public ArrayList<Column> findAllColumns() throws Exception {
		ArrayList<Column> columns = new ArrayList<>();//栏目集
		String sql = "select * from kind;";//查询语句---查询一级栏目种类表kind中所有字段
		pstmt = this.con.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while (rs.next()) {//遍历结果集
			Column column = new Column();//新建一个栏目对象
			column.setKid(rs.getInt(1));//保存种类编号
			column.setKindname(rs.getString(2));//保存种类名称
			column.setPid(rs.getInt(3));//保存等级标识
			columns.add(column);//将一级栏目添加到栏目集中
		}
		sql = "select cid,columnname,`column`.pid,`column`.kid,kindname from `column` "
				+ "join kind on `column`.kid=kind.kid;";//查询语句---查询二级栏目及其所属上级栏目
		pstmt = this.con.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while (rs.next()) {//遍历结果集
			Column column = new Column();//新建一个栏目对象
			column.setCid(rs.getInt(1));
			column.setColumnname(rs.getString(2));
			column.setPid(rs.getInt(3));//保存等级标识
			column.setKid(rs.getInt(4));
			column.setKindname(rs.getString(5));
			columns.add(column);//将二级栏目添加到栏目集中
		}
		rs.close();
		pstmt.close();
		return columns;
	}


	/***
	 * 添加一二级栏目
	 */
	@Override
	public int addColumn(Column column) throws Exception {
		int result = 0;
		String sql = null;
		if (column.getCid()==0 && column.getColumnname()==null && column.getPid() == 1) {//添加栏目为一级
			sql = "insert into kind(kindname) values(?)";//向kind表中插入一级栏目名称
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, column.getKindname());
			result = pstmt.executeUpdate();
			System.out.println("添加一级栏目成功");
		} else if (column.getPid() == 2) {//添加栏目为二级
			sql = "insert into `column`(columnname,kid) values(?,?)";//向column表中插入二级栏目名称及所属上级栏目标识
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, column.getColumnname());
			pstmt.setInt(2, column.getKid());
			result = pstmt.executeUpdate();
			System.out.println("添加二级栏目成功");
		} else {
			System.out.println("添加失败");
		}
		pstmt.close();			
		return result;
	}

	/***
	 * 删除栏目
	 */
	@Override
	public int delColumn(int id,int pid) throws Exception {
		int result = 0;
		String sql = null;
		switch (pid) {//判断栏目等级：1--一级栏目；2--二级栏目
		case 1:
			//删除一级栏目
			sql = "delete from kind where kid=?";//通过一级标识删除kind表中的一级栏目---其下所属栏目也会被删除
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			System.out.println("删除一级成功");
			break;
		case 2:
			//删除二级标签
			sql = "delete from `column` where cid=?";//通过二级标识删除column表中的二级栏目
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			System.out.println("删除二级成功");
			break;
		default:
			System.out.println("删除失败");
			break;
		}
		pstmt.close();			
		return result;
	}

	/***
	 * 显示修改栏目
	 */
	@Override
	public Column showColumn(int id, int pid) throws Exception {
		// TODO Auto-generated method stub
		Column column = new Column();
		String sql = null;
		switch (pid) {//判断栏目等级：1--一级栏目；2--二级栏目
		case 1:
			sql = "select * from kind where kid=?";//通过id查询kind
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				column.setKid(rs.getInt(1));
				column.setKindname(rs.getString(2));
				column.setPid(rs.getInt(3));
			}
			break;
		case 2:
			sql = "select * from `column` left join kind on `column`.kid=kind.kid where `column`.cid=?";//通过id查询column
			pstmt = this.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				column.setCid(rs.getInt(1));
				column.setColumnname(rs.getString(2));
				column.setPid(rs.getInt(3));
				column.setKid(rs.getInt(4));
				column.setKindname(rs.getString(6));
			}
			break;
		default:
			System.out.println("出错失败了");
			break;
		}
		rs.close();
		pstmt.close();
		return column;
	}

	/**
	 * 修改--待做
	 */
	@Override
	public int editColumn(int id,Column column) throws Exception {
		int result = 0;
		String sql = null;
		int pid = column.getPid();
		switch (pid) {
		case 1:
			sql = "update kind set kindname=? where kid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, column.getKindname());
			pstmt.setInt(2, column.getKid());
			result = pstmt.executeUpdate();
			break;
		case 2:
			sql = "update `column` set columnname=?,kid=? where cid=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, column.getColumnname());
			pstmt.setInt(2, column.getKid());
			pstmt.setInt(3, column.getCid());
			result = pstmt.executeUpdate();
			break;
		default:
			System.out.println("update出错了");
			break;
		}
		pstmt.close();
		return result;
	}

}
