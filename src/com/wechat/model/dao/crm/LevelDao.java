package com.wechat.model.dao.crm;

import java.util.List;
import java.util.Map;

import com.wechat.model.bean.level;

public interface LevelDao{
	public int getTotal() ;
	public void addLevel(level user);
	public void updateLevel(level user);
	public void deleteLevel(int id);
	public level getLevel(int id);
	public level getLevel(String Openid);
	public int getCountLower(String superOpenid);
	public List<level> list();
	public List<level> listLevel(int start, int count);
	public List<level> likeQueryListLevel(String name,int start, int count);
}
