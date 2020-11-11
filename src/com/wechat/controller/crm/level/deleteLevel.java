package com.wechat.controller.crm.level;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.bean.level;
import com.wechat.model.dao.crm.impl.LevelDaoImpl;



public class deleteLevel extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		LevelDaoImpl LevelDaoImpl=new LevelDaoImpl();
		level level = LevelDaoImpl.get(id);
		LevelDaoImpl.delete(id);
		request.getRequestDispatcher("LevelServlet").forward(request, response);
	}
}