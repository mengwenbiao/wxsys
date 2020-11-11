package com.wechat.model.dao.crm;

import java.util.List;
import java.util.Map;

import com.wechat.model.bean.level;

public interface LevelDao{
//	public void addLevel(level level);
//	public void queryLevel(int id);
//	public List<level> pageQueryData(Map<String, Object> map);
//	public List<level> queryUserByTags(String tag);
//	public List<level> queryUserByLevel(String level);
	public int getTotal() ;
	public void add(level user);
	public void update(level user);
	public void delete(int id);
	public level get(int id);
	public List<level> list();
	public List<level> list(int start, int count);
	public List<level> likeQueryList(String name,int start, int count);
}
