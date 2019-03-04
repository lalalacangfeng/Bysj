package com.dmjd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import com.dmjd.pojo.User;

public class UserDaoImpl implements UserDao {

	private Connection con = null;//连接语句
	private PreparedStatement pstmt = null;//数据集
	ResultSet rs = null;//查询结果集

	//默认构造方法中实例化数据库连接
	public UserDaoImpl(Connection con) {
		this.con = con;
	}

	/***
	 * 查询所有用户
	 */
	@Override
	public ArrayList<User> FindAll() throws Exception {
		ArrayList<User> users = new ArrayList<User>();//用户列表
		String sql = "select * from users";//查询语句
		pstmt = this.con.prepareStatement(sql);
		rs =this.pstmt.executeQuery();
		while (rs.next()) {//遍历结果集
			User user = new User();
			user.setUid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setEmail(rs.getString(4));
			user.setLasttime(rs.getString(5));//有需要改动
			user.setRole(rs.getInt(6));
//			System.out.println("用户权限:"+user.getRole());
//			System.out.println("最后登录时间："+user.getLasttime());
			users.add(user);//将数据结果添加到用户列表中
		}
		rs.close();
		pstmt.close();
		return users;
	}

	/***
	 * 添加用户
	 */
	@Override
	public int addUser(User user) throws Exception {
		// XXX 没用等待删除修改
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

	/***
	 * 修改用户信息
	 */
	@Override
	public int editInf(int uid, String username, String email) throws Exception {
		System.out.println(uid + "----" + username + "----" + email);
		String sql = "update users set username=?,email=? where uid=?";
		int result = 0;
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, email);
		pstmt.setInt(3, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	/***
	 * 修改用户密码
	 */
	@Override
	public int editPasswd(int uid, String password) throws Exception {
		String sql = "update users set password=? where uid=?";
		int result = 0;
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	/**
	 * 通过id--删除用户
	 */
	@Override
	public int deleteUser(int uid) throws Exception {
		String sql = "delete from users where uid=?";
		int result = 0;
		pstmt = this.con.prepareStatement(sql);
		pstmt.setInt(1, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	/**
	 * 根据用户名查询用户
	 */
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
			user.setLasttime(rs.getString(5));
			user.setRole(rs.getInt(6));
			user.setCreatetime(rs.getString(7));
			user.setMissnumber(rs.getInt(8));
			user.setMisstime(rs.getString(9));
		}
		rs.close();
		pstmt.close();
		return user;
	}
	
	/**
	 * 根据id更新登录次数与时间
	 * @throws Exception
	 */
	public int resetNumberAndTime(int uid, int number, String misstime) throws Exception{
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

	/***
	 * 根据Email查询用户
	 */
	@Override
	public User queryByEmail(String email) throws Exception {
		String sql = "select * from users where email=?";
		User user = new User();
		pstmt = this.con.prepareStatement(sql);
		pstmt.setString(1, email);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			user.setUid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setLasttime(rs.getString(5));
			user.setRole(rs.getInt(6));
		}
		rs.close();
		pstmt.close();
		return user;
	}
	
	public int updateRole(int id,int role) throws Exception{
		int result = 0;
		String sql = "update users set role=? where uid=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, role);
		pstmt.setInt(2, id);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
}
