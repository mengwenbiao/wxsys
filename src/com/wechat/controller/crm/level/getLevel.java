package com.wechat.controller.crm.level;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.bean.level;
import com.wechat.model.dao.crm.impl.LevelDaoImpl;


public class getLevel extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
				
        int id = Integer.parseInt(request.getParameter("id"));
		LevelDaoImpl levels=new LevelDaoImpl();
		level level = levels.getLevel(id);
		level levelx=new level();
		levelx.setId(id);
		System.out.println("当前用户nickname:"+level.getNickname());
		levelx.setNickname(level.getNickname());
		levelx.setOpenid(level.getOpenid());
		levelx.setSuperNickname(level.getSuperNickname());
		levelx.setSuperOpenid(level.getSuperOpenid());
		levelx.setRanking(level.getRanking());
	 	
	 	request.setAttribute("level",levelx);
		request.getRequestDispatcher("view/jsp/level/edit.jsp").forward(request, response);
		
		
	}
}
