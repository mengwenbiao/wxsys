package com.wechat.model.dao.crm;

import java.util.List;

import com.wechat.model.bean.Image;

public interface ImageDao {
	public List<Image> list();
	public List<Image> list(int start, int count);
	/**
	 * 统计条数
	 */
	public int getTotal();
	public void add(Image image);
	public void delete(int id); 
	public List<Image> query(String query); 
}
