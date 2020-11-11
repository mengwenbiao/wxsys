package com.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wechat.model.dao.crm.impl.UserAdminDaoImpl;
import com.wechat.model.pojo.UserAdmin;


public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置页码以utf-8的形式打开一个网页
//		response.setContentType("text/html;charset=utf-8");
		// 要求服务器以utf-8输出
//		request.setCharacterEncoding("utf-8");
		// response.setCharacterEncoding("UTF-8");
		// 数据库内容
//		User user=new User();

		// System.out.println(allmsg);
		PrintWriter out = response.getWriter();
		// 获取到页面输入的登录名
		String account = request.getParameter("loginacct");
		String psd = request.getParameter("userpasswd");
		String role1 = request.getParameter("select");
//		String remember = request.getParameter("remember-me");
//		System.out.println(account+"---"+psd+"---"+role1);
		// 判断角色，分别赋值
		String role = null;
		if (role1.equals("member")) {
			role = "会员";
		} else if (role1.equals("user")) {
			role = "管理员";
		}
//		System.out.println(account + "..." + psd + "..." + role);
		UserAdminDaoImpl un = new UserAdminDaoImpl();
		UserAdmin user = new UserAdmin();
		List<UserAdmin> allmsg = un.query();
		for (int i = 0; i < allmsg.size(); i++) {
			user = allmsg.get(i);
			String username = user.getUsername();
			String password = user.getPassword();
			String roles = user.getRole();
			if (account.equals(username) && psd.equals(password) && role.equals(roles)) {
				System.out.println("登录-ok");
				System.out.println(username + "..." + password + "..." + roles );
				// 设置登录状态
				Cookie cookie = new Cookie("loginFlag", "true");

				HttpSession session = request.getSession();
				session.setAttribute("username", username);

				// 返回页面LoginedServlet
				cookie.setPath("/wechatdemo/LoginedServlet");
				response.addCookie(cookie);
				response.sendRedirect(request.getContextPath() + "/view/jsp/main.jsp");
				break;
			} else if (account.trim().isEmpty() || psd.trim().isEmpty()) {
//				request .getRequestDispatcher(request.getContextPath()+"/jsp/login.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath() + "/view/jsp/login.jsp");
				return;
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
