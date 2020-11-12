package com.wechat.model.bean;

public class Image {
	private int id; 
	private String theme;
	public Image() {
		super();
	}
	public Image(int id, String theme) {
		super();
		this.id = id;
		this.theme = theme;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", theme=" + theme + "]";
	}
}
