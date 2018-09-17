package com.dmjd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nid;
	private String articletitle;
	private String articlewriter;
	private String from;// 来源
	private int hits;// 点击数
	
	
	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	private String articlecontent;// 内容
	private int greats;// 点赞
	private int layers;// 优先级
	private String releasetime;// 发布时间
	private int cid;
	private int status;
	private Set<Answer> answers;

	public Set<Answer> getanswers() {
		return answers;
	}

	public void setanswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Article() {
		super();
	}

	public Article(String articletitle, String articlewriter, String from, int hits,
			String articlecontent, int greats, int layers, String releasetime,
			int cid, int status) {
		super();
		this.articletitle = articletitle;
		this.articlewriter = articlewriter;
		this.from = from;
		this.hits = hits;
		this.articlecontent = articlecontent;
		this.greats = greats;
		this.layers = layers;
		this.releasetime = releasetime;
		this.cid = cid;
		this.status = status;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}


	public String getArticletitle() {
		return articletitle;
	}

	public void setArticletitle(String articletitle) {
		this.articletitle = articletitle;
	}

	public String getArticlewriter() {
		return articlewriter;
	}

	public void setArticlewriter(String articlewriter) {
		this.articlewriter = articlewriter;
	}

	public String getArticlecontent() {
		return articlecontent;
	}

	public void setArticlecontent(String articlecontent) {
		this.articlecontent = articlecontent;
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

	

}
