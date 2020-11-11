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
		level level = levels.get(id);
		level levelx=new level();
		levelx.setId(id);
		System.out.println("username:"+level.getUsername());
		levelx.setUsername(level.getUsername());
		
		levelx.setRank(level.getRank());
		levelx.setSuperd(level.getSuperd());
	 	
	 	request.setAttribute("level",levelx);
		request.getRequestDispatcher("view/jsp/level/edit.jsp").forward(request, response);
		
		
	}
}
