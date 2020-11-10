package com.wechat.model.pojo;

public class Ranking {
	
	private int id;
	private String openid;
	private String nickname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	public Ranking() {
		super();
	}
	public Ranking(String openid, String nickname) {
		super();
		this.openid = openid;
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "Ranking [id=" + id + ", openid=" + openid + ", nickname=" + nickname + "]";
	}
	
	
	
	

}
