
package com.wechat.model.pojo;


//上传临时素材库得到的mediaid
public class Media {
	private Integer id;
	private String openid;
	private String mediaid;
	private long createtime;
	
	public Media() {
		super();
	}
	public Media(String openid, String mediaid, long createtime) {
		super();

		this.openid = openid;
		this.mediaid = mediaid;
		this.createtime = createtime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getMediaid() {
		return mediaid;
	}
	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "Media [id=" + id + ", openid=" + openid + ", mediaid=" + mediaid + ", createtime=" + createtime + "]";
	}
	
	
	

}
