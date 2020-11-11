package com.wechat.controller.crm.level;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.bean.level;
import com.wechat.model.dao.crm.impl.LevelDaoImpl;


public class editLevel extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		int rank = Integer.parseInt(request.getParameter("rank"));
		String superd = request.getParameter("superd");
		// 解决乱码
		String username2 = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		String superd2 = new String(superd.getBytes("ISO-8859-1"), "UTF-8");		
		response.setContentType("text/html;charset=UTF-8");
		level level = new level(id,username2,rank,superd2);
		LevelDaoImpl levels = new LevelDaoImpl();
		levels.update(level);
		request.getRequestDispatcher("LevelServlet").forward(request, response);
	}
}
