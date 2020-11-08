package com.wechat.model.dao.crm.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.User;
import com.wechat.utils.JdbcUtil;
import com.wechat.utils.MyQueryRunner;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	//引入sql工具
	private MyQueryRunner qr=new MyQueryRunner();

	@Override
	public void addUser(User user) {
		String sql="insert into user value (?,?,?)";
		Object[] param= {1,"apple","123"};
		dml(sql,param);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User queryUserById(String openid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> queryUserByTags(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> queryUserByLevel(String level) {
		// TODO Auto-generated method stub
		return null;
	}
}
