package com.wechat.model.dao.crm.impl;

import java.util.List;
import java.util.Map;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{
	
	public void queryTest() {
		String sql="select * from user where id=?";
		Object[] params=new Object[] {5};
		User user=queryForBean(sql, params);
//		System.out.println(user);
	}

	@Override
	public User query4Login(String loginacct) {
		String sql="select * from user where nickname=?";
		Object[] params=new Object[] {loginacct};
		User user=queryForBean(sql, params);
		System.out.println("impl"+user);
		return user;
	}

	@Override
	public List<User> pageQueryData(Map<String, Object> map) {
		int start=(int) map.get("start");
		int size=(int) map.get("size");
		String sql="select * from user order by subscribe_time limit ?,?";
		Object[] params=new Object[] {start,size};
		List<User> users=queryForList(sql, params);
//		System.out.println(users);
//		for(int i=0; i<users.size(); i++) {
//			User user = users.get(i); 
//			System.out.println(user.getOpenid());
//		}
		return users;
	}
	
	
	
	@Override
	public int getTotal() {
		int total=0;
		String sql="select count(*) from user";
		total=getTotal(sql);
		return total;
	}

	@Override
	public List<User> vagueQueryData(String name) {
		String sql= "select * from user where nickname like'%" + name +"%"+"'" ;
		List<User> users=queryForList(sql,null);
		return users;
	}

	

	
}
