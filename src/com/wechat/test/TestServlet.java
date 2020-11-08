package com.wechat.test;


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
import com.wechat.utils.MessageUtil;
import com.wechat.utils.StringUtil;



public class TestServlet extends HttpServlet{
	
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
		
 		resp.getWriter().print(echostr);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		InputStream in=req.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		
		String line=null;
		
		while(null!=(line=br.readLine())) {
			System.out.println(line);
		}
		br.close();
		in.close();
		
//		PrintWriter out=resp.getWriter();
//		
//		 try {
//	            Map<String, String> map = MessageUtil.xmlToMap(req);
//	            String toUserName = map.get("ToUserName");
//	            String fromUserName = map.get("FromUserName");
//	            String msgType = map.get("MsgType");
//	            String mediaId = map.get("MediaId");
//	            System.out.println(toUserName+fromUserName);
//	            String message = null;
//	            if ("image".equals(msgType)) {                // 对文本消息进行处理
//	            	Images image = new Images();
//	            	image.setFromUserName(toUserName);         // 发送和回复是反向的
//	            	image.setToUserName(fromUserName);
//	            	image.setMsgType("image");
//	            	image.setCreateTime(new Date().getTime());
//	            	image.setMediaId(mediaId);
//	                //message = MessageUtil.textMessageToXml(text);
//	                message="<xml>\r\n" + 
//	        				"  <ToUserName><![CDATA["+image.getToUserName()+"]]></ToUserName>\r\n" + 
//	        				"  <FromUserName><![CDATA["+image.getFromUserName()+"]]></FromUserName>\r\n" + 
//	        				"  <CreateTime>"+image.getCreateTime()+"</CreateTime>\r\n" + 
//	        				"  <MsgType><![CDATA["+image.getMsgType()+"]]></MsgType>\r\n" + 
//	        				"  <Image>\r\n" + 
//	        				"    <MediaId><![CDATA["+image.getMediaId()+"]]></MediaId>\r\n" + 
//	        				"  </Image>\r\n" +
//	        				"</xml>";
//	                System.out.println(message);            
//	            }
//	            out.print(message);                            // 将回应发送给微信服务器
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }finally{
//	            out.close();
//	        }
	}

}
