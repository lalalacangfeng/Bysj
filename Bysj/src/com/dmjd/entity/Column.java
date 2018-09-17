package com.dmjd.entity;

public class Column {
	private int cid;
	private String classname;
	private int kid;
	private String kindname;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "cid:"+cid+" classname:"+classname+" kid:"+kid+" kidname:"+kindname;
	}
	public String getKindname() {
		return kindname;
	}
	public void setKindname(String kindname) {
		this.kindname = kindname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public int getKid() {
		return kid;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}
}
