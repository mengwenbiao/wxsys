package com.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginedServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 要求服务器以utf-8输出
//		response.setCharacterEncoding("utf-8");
		// 设置页码以utf-8的形式打开一个网页
//		response.setContentType("text/html;charset=utf-8");
		// 获取页面输出流
		PrintWriter out = response.getWriter();
		// 获取浏览器的cookie数组
		Cookie[] cookies = request.getCookies();
		// 判断健壮性
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginFlag")) {
					String loginFlag = cookie.getValue();
					if (loginFlag.equals("true")) {
						// 如果已经登录那么直接进入主页面
						response.sendRedirect("/wechatdemo/view/jsp/main.jsp");
						return;
					}
				}
			}
		}
		// 否则进入登录页面
		response.sendRedirect("/wechatdemo/view/jsp/login.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
