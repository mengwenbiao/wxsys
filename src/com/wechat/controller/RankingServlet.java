package com.wechat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.crm.RankingDao;
import com.wechat.model.dao.crm.impl.RankingDaoImpl;
import com.wechat.model.pojo.Ranking;

public class RankingServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RankingDao rank=new RankingDaoImpl();
		List<Ranking> a=rank.queryRank();
		int i=1;
		for(Ranking b:a) {
			response.getWriter().println(b.getNickname()+"--------------->助力排行榜排名："+i+++"</br>");
		}
	
		
	}

}
