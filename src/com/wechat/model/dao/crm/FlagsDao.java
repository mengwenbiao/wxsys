package com.wechat.model.dao.crm;

import java.util.List;

import com.wechat.model.pojo.Flags;


//标签
public interface FlagsDao {
	//添加一个标签
	public void add(Flags flags);
	//模糊查询
	public List<Flags> query();
	public List<Flags> query(String str);
	//指定链表查询
	public List<Flags> list(int start,int count);
	//列表查询
	List<Flags> list();
//	List<Flags> list(String username);
	public int getTotal() ;
	public Flags get(int id);
	public void update(Flags flag);
	
}
