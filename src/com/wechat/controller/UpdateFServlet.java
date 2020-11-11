package com.wechat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.crm.impl.FlagsDaoImpl;
import com.wechat.model.pojo.Flags;


public class UpdateFServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//页面输出形式
		request.setCharacterEncoding("utf-8");
		//new一个空对象
		Flags flag=new Flags();
		//获取页面信息
		String ids=request.getParameter("id");
		String username=request.getParameter("username");
		String sale1=request.getParameter("sale");
		String free1=request.getParameter("free");
		String teamsale1=request.getParameter("teamsale");
		System.out.println(ids+"-"+username+"-"+sale1+"-"+free1+"--"+teamsale1);
		int id=Integer.parseInt(ids);
//		String username=username1;
		int sale=Integer.parseInt(sale1);
		int free=Integer.parseInt(free1);
		int teamsale=Integer.parseInt(teamsale1);
		flag=new Flags(id,username,sale,free,teamsale);
		new FlagsDaoImpl().update(flag);
		//request.setAttribute("ps", ps);
		response.sendRedirect(request.getContextPath() + "/FlagServlet");
		//转发无法跳转到页面，使用重定向
//		request .getRequestDispatcher("MainServlet").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
