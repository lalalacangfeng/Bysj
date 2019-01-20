package com.dmfm.pojo;

import java.util.ArrayList;

public class Menu {
	// 菜单id
    private int id;
    // 菜单名称
    private String name;
    //父菜单
    private int parentId;
    // 菜单等级id
    private int pid;
    // 子菜单
    private ArrayList<Menu> childMenus;
    //文章
    private ArrayList<Article> childArticles;
    //视频
    private ArrayList<Vedio> childVedios;
    //图片
    private ArrayList<Pict> childPicts;
    
    
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
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public ArrayList<Menu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(ArrayList<Menu> childMenus) {
		this.childMenus = childMenus;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+id+" name:"+name+" pid:"+pid+" parentId:"+parentId+"\n"
				+" childMenus:"+childMenus+"\n"
				+" childArticles:"+childArticles+"\n"
				+" childVedios:"+childVedios+"\n"
				+" childPicts:"+childPicts+"\n";
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public ArrayList<Article> getChildArticles() {
		return childArticles;
	}
	public void setChildArticles(ArrayList<Article> childArticles) {
		this.childArticles = childArticles;
	}
	public ArrayList<Vedio> getChildVedios() {
		return childVedios;
	}
	public void setChildVedios(ArrayList<Vedio> childVedios) {
		this.childVedios = childVedios;
	}
	public ArrayList<Pict> getChildPicts() {
		return childPicts;
	}
	public void setChildPicts(ArrayList<Pict> childPicts) {
		this.childPicts = childPicts;
	}
}
