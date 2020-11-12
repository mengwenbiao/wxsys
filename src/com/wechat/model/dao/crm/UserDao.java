package com.wechat.model.dao.crm;

import java.util.List;
import java.util.Map;

import com.wechat.model.dao.crm.base.BaseDao;
import com.wechat.model.pojo.User;

public interface UserDao<User> extends BaseDao<User>{
	public void addUser(User user);
	
	List<User> pageQueryData(Map<String, Object> map);

	int getTotal();

	List<User> vagueQueryData(String name);

	User query4Login(String loginacct);
	
	
}
