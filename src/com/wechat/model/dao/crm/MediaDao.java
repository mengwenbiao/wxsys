
package com.wechat.model.dao.crm;

import java.util.List;

import com.wechat.model.pojo.Media;

public interface MediaDao {

	public void addMedia(Media media);//增加用户信息
	public List<Media> queryMediaById(); //通过openid查用户信息
}