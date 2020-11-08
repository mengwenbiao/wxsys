package com.wechat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.bean.Images;
import com.wechat.model.bean.TextMessage;
import com.wechat.model.configuration.TokenConfig;
import com.wechat.model.dao.WxDao;
import com.wechat.utils.MessageUtil;
import com.wechat.utils.StringUtil;

import cn.hutool.http.HttpUtil;



public class ConnectionServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String echostr=req.getParameter("echostr");
		String signature=req.getParameter("signature");
		String timestamp=req.getParameter("timestamp");
		String nonce=req.getParameter("nonce");
		String token="abc";
		//System.out.println(echostr);
		//按照字典方式进行排序  abcd...
		List<String> list=new ArrayList<String>();
 		list.add(token);
 		list.add(timestamp);
 		list.add(nonce);
 		
 		Collections.sort(list);
 		String str=StringUtil.getSha1str(list.get(0)+list.get(1)+list.get(2));
 		
 		if(str.equals(signature)) {
 			resp.getWriter().print(echostr);
 		}else {
 			System.out.println("接入失败");
 		}
 	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//获取微信端的内容
		Map<String,String> xmlMap=WxDao.handleMap(req);
		//针对内容给微信端做回复响应
		String responseStr=WxDao.getResponseStr(xmlMap);
		
		//回送给微信服务器
		resp.getWriter().print(responseStr);
		
	}
	
}
