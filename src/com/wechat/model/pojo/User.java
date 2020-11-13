package com.wechat.model.pojo;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2900466913200185672L;
	
	private Integer id;
	private String subscribe;
	private String openid;
	private String nickname;
	private String sex;
	private String city;
	private String country;
	private String province;
	private String language;
	private String headimgurl;
	private String subscribe_time;
	private String remark;
	private String groupid;
	private String tagid_list;
	private String subscribe_scene;
	private String qr_scene;
	private String qr_scene_str;
	

	private String counts;
	private String date;
	
	
	
	public User(String counts, String date) {
		super();
		this.counts = counts;
		this.date = date;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(String tagid_list) {
		this.tagid_list = tagid_list;
	}

	public String getSubscribe_scene() {
		return subscribe_scene;
	}

	public void setSubscribe_scene(String subscribe_scene) {
		this.subscribe_scene = subscribe_scene;
	}

	public String getQr_scene() {
		return qr_scene;
	}

	public void setQr_scene(String qr_scene) {
		this.qr_scene = qr_scene;
	}

	public String getQr_scene_str() {
		return qr_scene_str;
	}

	public void setQr_scene_str(String qr_scene_str) {
		this.qr_scene_str = qr_scene_str;
	}
	

	public User(String openid, String nickname, String headimgurl) {
		super();
		this.openid = openid;
		this.nickname = nickname;
		this.headimgurl = headimgurl;
	}

	public User(String subscribe, String openid, String nickname, String sex, String city, String country,
			String province, String language, String headimgurl, String subscribe_time, String unionid, String remark,
			String groupid, String tagid_list, String subscribe_scene, String qr_scene, String qr_scene_str) {
		super();
		this.subscribe = subscribe;
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.city = city;
		this.country = country;
		this.province = province;
		this.language = language;
		this.headimgurl = headimgurl;
		this.subscribe_time = subscribe_time;
		this.remark = remark;
		this.groupid = groupid;
		this.tagid_list = tagid_list;
		this.subscribe_scene = subscribe_scene;
		this.qr_scene = qr_scene;
		this.qr_scene_str = qr_scene_str;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname
				+ ", sex=" + sex + ", city=" + city + ", country=" + country + ", province=" + province + ", language="
				+ language + ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", unionid="
				+ ", remark=" + remark + ", groupid=" + groupid + ", tagid_list=" + tagid_list
				+ ", subscribe_scene=" + subscribe_scene + ", qr_scene=" + qr_scene + ", qr_scene_str=" + qr_scene_str
				+ "]";
	}
	
	
}
