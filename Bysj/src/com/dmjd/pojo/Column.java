package com.dmjd.pojo;

public class Column {
	private int cid;//栏目编号
	private String columnname;//栏目名称
	private int pid;//栏目等级
	private int kid;//种类编号
	private String kindname;//种类名称

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "pid:"+pid+" cid:"+cid+" columnname:"+columnname+" kid:"+kid+" kidname:"+kindname;
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
	public int getKid() {
		return kid;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}
}
