package com.dmfm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dmfm.pojo.User;

public class UserDaoImpl implements UserDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集

	//默认构造方法中实例化数据库连接
	public UserDaoImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public User queryByName(String username) throws Exception {
		User user = new User();
		String sql = "select * from users where username=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, username);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			user.setUid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setLasttime(rs.getDate(5));
			user.setRole(rs.getInt(6));
		}
		rs.close();
		pstmt.close();
		return user;
	}

	@Override
	public int register(User user) throws Exception {
		int result = 0;
		String sql = "insert into users(username, password, email, lastlogin) values(?,?,?,now())";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getEmail());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int checkName(String username) throws Exception {
		int result = 0;
		String sql = "select * from users where username=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, username);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			result = 1;
		}
		return result;
	}

	@Override
	public int checkEmail(String email) throws Exception {
		int result = 0;
		String sql = "select * from users where email=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, email);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			result = 1;
		}
		return result;
	}

	@Override
	public int resetNumberAndTime(int uid, int number, String misstime)
			throws Exception {
		int result = 0;
		Long now = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(sdf.format(now));//当前时间
		
		String sql = "update users set missnumber=?, misstime=? where uid=?";
		pstmt = this.con.prepareStatement(sql);
		if (number < 5 && time.after(sdf.parse(misstime))) {
			//misstime为允许登录时间，当前时间必须大于他才可以登录
			pstmt.setInt(1, ++number);
			pstmt.setString(2, misstime);
			System.out.println("number:"+number+"misstime:"+misstime);
			System.out.println("uid:"+uid);
			pstmt.setInt(3, uid);
			result = pstmt.executeUpdate();
		}else if (number >= 5 || time.before(sdf.parse(misstime))) {
			pstmt.setInt(1, 0);
			pstmt.setString(2, sdf.format(now));
			System.out.println("number:"+number+"misstime:"+sdf.format(now));
			System.out.println("uid:"+uid);
			pstmt.setInt(3, uid);
			result = pstmt.executeUpdate();
		}
		pstmt.close();
		return result;
	}

	@Override
	public int editinf(User user) throws Exception {
		int result = 0;
		String sql = "update users set username=?,Email=? where uid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getEmail());
		pstmt.setInt(3, user.getUid());
		result = pstmt.executeUpdate();
		return result;
	}

	@Override
	public int editpass(String pass,int uid) throws Exception {
		int result = 0;
		String sql = "update users set password=? where uid=?";
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, pass);
		pstmt.setInt(2, uid);
		result = pstmt.executeUpdate();
		return result;
	}

}
