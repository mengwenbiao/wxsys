package com.wechat.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.factory.DaoFactory;
import com.wechat.model.pojo.User;
import com.wechat.utils.JdbcUtil;

public class addUser {
	@Test
	public void testaddUser() {
		UserDao userdao=(UserDao)DaoFactory.getInstance().getDaoByName("userDao");
		User user=new User();
		user.setNickname("笑");
	}
	@Test
	public void getTotal() {
		String superOpenid="1234";
		String sql="select count(superOpenid) from level where superOpenid="+superOpenid+" group by superOpenid";
		Connection conn=null;
		int total=0;
		try {
			conn=JdbcUtil.getConnection();
		
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询列表出现问题！！");
		}
		System.out.println(total+"------");
	}
}
