package com.dmfm.pojo;

import java.util.ArrayList;

public class Article {
	private int nid;//文章编号
	private String title;//标题
	private String writer;//作者
	private String from;//来源
	private String content;//内容
	private int hits;//点击率
	private int greats;//点赞量
	private int status;//状态
	private int cid;//所属栏目
	private String cname;//所属栏目名称
	private String releasetime;// 发布时间
	private String profile;//概要
	private String src;//首张图片地址
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getGreats() {
		return greats;
	}
	public void setGreats(int greats) {
		this.greats = greats;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "nid:"+nid+" title:"+title+" cid:"+cid
				+"profile:"+profile+" src:"+src
				+"cname:"+cname;
	}
	public String getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
