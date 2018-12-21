package com.dmjd.pojo;

public class Pict {
	
	private int id;//编号
	private String name;//名字
	private String src;//路径
	private int lenid;//公有id
	private String descript;//描述
	private String author;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public int getLenid() {
		return lenid;
	}
	public void setLenid(int lenid) {
		this.lenid = lenid;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+id+" name:"+name+" descript:"+ descript+" src:"+src;
	}
	
}
