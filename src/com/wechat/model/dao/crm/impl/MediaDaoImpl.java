
package com.wechat.model.dao.crm.impl;

import java.util.List;

import com.wechat.model.dao.crm.MediaDao;

import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.Media;


public class MediaDaoImpl extends BaseDaoImpl<Media> implements MediaDao{

	@Override
	public void addMedia(Media media) {
		String sql="insert into media value (null,?,?,?)";
		Object[] param= {media.getOpenid(),media.getMediaid(),media.getCreatetime()};
		dml(sql,param);
		
	}

	@Override
	public List<Media> queryMediaById() {
		String sql="select * from media";
		return queryForList(sql,null);
	
	}

}