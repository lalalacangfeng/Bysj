package com.dmjd.entity;

import java.io.Serializable;
import java.util.Set;

public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nid;//新闻编号
	private String title;//标题
	private String writer;//作者
	private String from;// 来源
	private int hits;// 点击数
	private String content;// 内容
	private int greats;// 点赞
	private int layers;// 优先级
	private String releasetime;// 发布时间
	private int cid;//所属栏目编号
	private int status;//发表状态
	
	private String columnname;//所属栏目名称
	private Set<Answer> answers;//回复
	
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGreats() {
		return greats;
	}
	public void setGreats(int greats) {
		this.greats = greats;
	}
	public int getLayers() {
		return layers;
	}
	public void setLayers(int layers) {
		this.layers = layers;
	}
	public String getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Set<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

}
