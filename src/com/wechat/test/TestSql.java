package com.wechat.test;

import java.util.ArrayList;
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
	
	@Test
	public void test5() {
		UserDao userDao=(UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		List<User> user = userDao.queryDateCounts();
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		StringBuffer str = new StringBuffer();
		System.out.println(user);
		for(User users:user) {
//			System.out.println(users.getCounts());
			str.append(users.getCounts()+",");
//			list.add(users.getCounts());
//			list1.add(users.getDate());
		}
		System.out.println(str.length());
		System.out.println(str);
		str.delete(str.length()-1, str.length());
		System.out.println(str);
//		System.out.println(list);
//		System.out.println(list1);
	}
	
}
