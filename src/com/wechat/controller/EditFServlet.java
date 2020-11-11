package com.wechat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.crm.impl.FlagsDaoImpl;
import com.wechat.model.pojo.Flags;

public class EditFServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		User user=new User();//new一个person
		response.setContentType("text/html; charset=UTF-8");
		String ids=request.getParameter("id");//根据ID获取值
		Flags flags=new FlagsDaoImpl().get((Integer.parseInt(ids)));//获取到user的所有值根据ID
		request.setAttribute("flags", flags);//保存数据
		System.out.println(flags);
		request .getRequestDispatcher("view/jsp/user/editFplus.jsp").forward(request, response);
	
	}
}
