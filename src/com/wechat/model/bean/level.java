package com.wechat.model.bean;

public class level {
	private Integer id;
	private String nickname;
	private String openid;
	private String superNickname;
	private String superOpenid;
	private int ranking;
	public level() {
	}
	public level(Integer id, String nickname, String openid, String superNickname, String superOpenid, int ranking) {
		this.id = id;
		this.nickname = nickname;
		this.openid = openid;
		this.superNickname = superNickname;
		this.superOpenid = superOpenid;
		this.ranking = ranking;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSuperNickname() {
		return superNickname;
	}
	public void setSuperNickname(String superNickname) {
		this.superNickname = superNickname;
	}
	public String getSuperOpenid() {
		return superOpenid;
	}
	public void setSuperOpenid(String superOpenid) {
		this.superOpenid = superOpenid;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	@Override
	public String toString() {
		return "level [id=" + id + ", nickname=" + nickname + ", openid=" + openid + ", superNickname=" + superNickname
				+ ", superOpenid=" + superOpenid + ", ranking=" + ranking + "]";
	}	
			
}
