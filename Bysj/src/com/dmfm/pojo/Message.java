package com.dmfm.pojo;

import java.util.Date;

public class Message {
	private int mid;//编号
	private String title;//标题
	private String content;
	private String author;
	private Date releasetime;
	private int pid;
	private int uid;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(Date releasetime) {
		this.releasetime = releasetime;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Message [mid=" + mid + ", title=" + title + ", content="
				+ content + ", author=" + author + ", releasetime="
				+ releasetime + ", pid=" + pid + ", uid=" + uid + "]";
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
