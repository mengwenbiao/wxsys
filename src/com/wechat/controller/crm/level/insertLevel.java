package com.wechat.controller.crm.level;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.bean.level;
import com.wechat.model.dao.crm.impl.LevelDaoImpl;



public class insertLevel extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		int rank=Integer.parseInt(request.getParameter("rank"));
		String superd=request.getParameter("superd");
		//解决乱码
		response.setContentType("text/html;charset=UTF-8");
		
		level level=new level();
		level.setUsername(username);
		level.setRank(rank);
		level.setSuperd(superd);
		LevelDaoImpl levels=new LevelDaoImpl();
		levels.addLevel(level);
		request.getRequestDispatcher("LevelServlet").forward(request, response);
	}
}
