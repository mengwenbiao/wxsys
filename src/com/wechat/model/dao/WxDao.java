package com.wechat.model.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wechat.model.configuration.TextTemplate;
import com.wechat.model.configuration.TokenConfig;

import cn.hutool.http.HttpUtil;

public class WxDao {
	


	//处理xml信息
	public static Map<String,String> handleMap(HttpServletRequest requst){
		
		try {
			InputStream in=requst.getInputStream();
			//准备一个map
			Map<String,String> xmlMap=new HashMap<String,String>();
			//dom4j
			SAXReader reader=new SAXReader();
			//获取到整个xml内容
			Document doc=reader.read(in);
			//获取到第一个节点，所有节点的父节点
			Element root=doc.getRootElement();
			//拿到所有子节点
			List<Element> element=root.elements();
			for(Element e:element) {
				String tagName=e.getName();
				String value=e.getStringValue();
				xmlMap.put(tagName,value);
			}
			return xmlMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//回送微信端字符串
	public static String getResponseStr(Map<String,String> xmlMap) {
		//查看消息类型
		String msgType=xmlMap.get("MsgType");
		String resultXml="";
		switch(msgType) {
		//如果是文本消息....
		case "text": resultXml=handleTextMessage(xmlMap);break;
		//如果是用户关注等事件
		case "event": resultXml=handleEventMessage(xmlMap);break;
		
		}
		return resultXml;
	}
	
	//处理事件
	private static String handleEventMessage(Map<String, String> xmlMap) {
		//处理事件内容
		//筛选处理事件类型
		//如果是关注用户
		String eventType=xmlMap.get("Event");
		switch(eventType) {
		case "subscribe":
			//获取ticket元素
			String ticket=xmlMap.get("Ticket");
			if(ticket!=null&&ticket.length()>0) {
				//扫描了带参数的二维码，并点击了关注
				return TextTemplate.getEventWithParamsTemplate(xmlMap);
			}else {
				//直接关注，或者是二维码没有带参数
				return TextTemplate.getEventWithOutParamsTemplate(xmlMap);
			}
		case "SCAN":
			//已关注，扫了带参数的二维码
			return TextTemplate.getEventParamsTemplate(xmlMap);
		}
		
		return null;
	}

	//处理文本内容
	private static String handleTextMessage(Map<String, String> xmlMap) {
		//增加一个客服回复功能
		//调用客服接口
		String url=TokenConfig.getCustomerUrl();
		
//		for(int i=0;i<10;i++) {
//			String result=TextTemplate.getCustomerTemplate((i+1)+"号技师为您服务！", xmlMap);
//			HttpUtil.post(url, result);
//		}
		//回复用户头像
		//String result2=TextTemplate.getCustomerImageTemplate(xmlMap);
		//HttpUtil.post(url, result2);
		
		return TextTemplate.getTextTemplate(xmlMap);
	} 
	

}
