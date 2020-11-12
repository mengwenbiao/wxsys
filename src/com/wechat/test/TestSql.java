package com.wechat.test;

import java.util.List;

import org.junit.Test;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.factory.DaoFactory;
import com.wechat.model.pojo.User;

public class TestSql {
	
	@Test
	public void test() {
		UserDao userDao1 = (UserDao) DaoFactory.getInstance().getDaoByName("userDao1");
		String sql = "select * from user order by subscribe_time limit ?,?";
		Object[] params = new Object[] {1,5};
		List<User> users = userDao1.queryForList(sql, params);
		System.out.println(users);
	}
	
	
}
