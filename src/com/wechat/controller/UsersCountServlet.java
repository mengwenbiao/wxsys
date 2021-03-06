package com.wechat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.factory.DaoFactory;
import com.wechat.model.pojo.User;

public class UsersCountServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询出要统计的数据
		UserDao userDao=(UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		List<User> user = userDao.queryDateCounts();
		//定义两个动态字符串
		StringBuffer str1 = new StringBuffer();
		StringBuffer str2 = new StringBuffer();
		System.out.println(user);
		for(User users:user) {
//			System.out.println(users.getCounts());
			//将查询到的数据追加到字符串中
			str1.append(users.getCounts()+",");
			str2.append(users.getDate()+",");
		}
		str1.delete(str1.length()-1, str1.length());
		str2.delete(str2.length()-1, str2.length());
//		System.out.println(list);
		System.out.println(str1+"---"+str2);
		//将数据存入request作用域中
		request.setAttribute("list", str1.toString());
		request.setAttribute("list1", str2.toString());
		request.getRequestDispatcher("/view/echarts/echart.jsp").forward(request, response);
	}
}
