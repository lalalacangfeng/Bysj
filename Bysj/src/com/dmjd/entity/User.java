package com.dmjd.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户实体类
 * @author cangfeng
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int uid;//编号
	private String username;//用户名
	private String password;//密码
	private Date lasttime;//最后登录时间
	private String Email;//邮箱
	private int role;//权限等级
	
	public  int getRole() {
		return role;
	}

	public  void setRole(int role) {
		this.role = role;
	}

	public  String getEmail() {
		return Email;
	}

	public  void setEmail(String email) {
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
	
	public  int getUid() {
		return uid;
	}
	public  void setUid(int uid) {
		this.uid = uid;
	}
	public  String getUsername() {
		return this.username;
	}
	public  void setUsername(String username) {
		this.username = username;
	}
	public  String getPassword() {
		return password;
	}
	public  void setPassword(String password) {
		this.password = password;
	}

	public  Date getLasttime() {
		return lasttime;
	}

	public  void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

}
