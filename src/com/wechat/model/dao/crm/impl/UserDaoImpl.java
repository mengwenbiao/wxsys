package com.wechat.model.dao.crm.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{
	
	@Override
	public void addUser(User user) {
		String sql = "insert into user (id,subscribe,openid,nickname,sex,city,country,province,language,headimgurl,"
				+ "remark,groupid,tagid_list,subscribe_scene) "
				+ "values(0,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[] {user.getSubscribe(),user.getOpenid(),user.getNickname(),user.getSex(),user.getCity(),user.getCountry(),user.getProvince(),user.getLanguage(),user.getHeadimgurl(),user.getRemark(),user.getGroupid(),user.getTagid_list(),user.getSubscribe_scene()};
		dml(sql, params);
	}

	
	public void queryTest() {
		String sql="select * from user where id=?";
		Object[] params=new Object[] {5};
		User user=queryForBean(sql, params);
//		System.out.println(user);
	}

	@Override
	public User query4Login(String openid) {
		String sql="select * from user where openid=?";
		Object[] params=new Object[] {openid};
		User user=queryForBean(sql, params);
		System.out.println(user);
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

	@Override
	public List<User> queryDateCounts() {
		String sql = "SELECT COUNT(A.date) as counts,A.date FROM\r\n" + 
				"(select DATE_FORMAT(subscribe_time,'%y/%m/%d') as date from user)as A\r\n" + 
				"GROUP BY A.date\r\n" + 
				"ORDER BY A.date ";
		List<User> counts = queryForList(sql,null);
		return counts;
	}
	

	
}
