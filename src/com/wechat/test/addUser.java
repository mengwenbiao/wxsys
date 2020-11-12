package com.wechat.test;

import org.junit.Test;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.factory.DaoFactory;
import com.wechat.model.pojo.User;

public class addUser {
	@Test
	public void testaddUser() {
		UserDao userdao=(UserDao)DaoFactory.getInstance().getDaoByName("userDao");
		User user=new User();
		user.setNickname("笑");
	}
}
