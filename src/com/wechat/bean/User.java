package com.wechat.bean;

public class User {
	
	private String openid;
	private String nickname;
	private String city;
	private String headimgurl;//用户头像
	private String subscribe_scene;//返回用户关注的渠道来源，
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribe_scene() {
		return subscribe_scene;
	}
	public void setSubscribe_scene(String subscribe_scene) {
		this.subscribe_scene = subscribe_scene;
	}
	public User(String openid, String nickname, String city, String headimgurl, String subscribe_scene) {
		super();
		this.openid = openid;
		this.nickname = nickname;
		this.city = city;
		this.headimgurl = headimgurl;
		this.subscribe_scene = subscribe_scene;
	}
	public User() {
		super();
	}
	
	
	

}
