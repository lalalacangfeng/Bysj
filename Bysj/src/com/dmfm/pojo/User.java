package com.dmfm.pojo;

import java.util.Date;

public class User {
	private static final long serialVersionUID = 1L;
	private int uid;// 编号
	private String username;// 用户名
	private String password;// 密码
	private Date lasttime;// 最后登录时间
	private String Email;// 邮箱
	private int role;// 权限等级
	private String createtime;//创建时间
	private int missnumber;//错误次数
	private String misstime;//允许登录时间

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getMissnumber() {
		return missnumber;
	}

	public void setMissnumber(int missnumber) {
		this.missnumber = missnumber;
	}

	public String getMisstime() {
		return misstime;
	}

	public void setMisstime(String misstime) {
		this.misstime = misstime;
	}
}
