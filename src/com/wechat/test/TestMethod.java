package com.wechat.test;

import java.util.List;

import org.junit.Test;

import com.wechat.model.dao.crm.UserAdminDao;
import com.wechat.model.dao.crm.impl.UserAdminDaoImpl;
import com.wechat.model.pojo.UserAdmin;

public class TestMethod {
	@Test
	public void test1() {
		//UserAdmin a=new UserAdmin(1,"apple","123","123");
		UserAdminDao user=new UserAdminDaoImpl();
		List<UserAdmin> s=user.query();
		for(UserAdmin us:s) {
			System.out.println(us);
		}
	}


}
