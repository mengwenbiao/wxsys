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
		UserDao userDao=(UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		List<User> user = userDao.queryDateCounts();
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		StringBuffer str1 = new StringBuffer();
		StringBuffer str2 = new StringBuffer();
		System.out.println(user);
		for(User users:user) {
//			System.out.println(users.getCounts());
			str1.append(users.getCounts()+",");
			str2.append(users.getDate()+",");
//			list.add(users.getCounts());
//			list1.add(users.getDate());
		}
		str1.delete(str1.length()-1, str1.length());
		str2.delete(str2.length()-1, str2.length());
//		System.out.println(list);
		System.out.println(str1+"---"+str2);
		request.setAttribute("list", str1.toString());
		request.setAttribute("list1", str2.toString());
		request.getRequestDispatcher("/view/echarts/echart.jsp").forward(request, response);
	}
}
