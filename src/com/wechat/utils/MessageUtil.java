package com.wechat.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wechat.model.bean.TextMessage;

public class MessageUtil {
	
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		request.setCharacterEncoding("utf-8");
		//获得输入流  --微信服务器发到tomcat的服务器
		InputStream  inputStream=request.getInputStream();
		// 读取输入流
		//xml解析器
		SAXReader reader = new SAXReader();
		 // 读取xml文件,转换成Document结点(文档对象)
        Document doc = reader.read(inputStream);
        System.out.println(doc);

		// 得到XML的根元素
        //获取每一个节点
		Element root = doc.getRootElement();
		// 得到根元素的所有子节点
		//去除警告
		@SuppressWarnings("unchecked")
		//读出每一个节点
		List<Element> elementList = root.elements();
		// 判断又没有子元素列表
		if (elementList.size()==0){
			map.put(root.getName(), root.getText());
		}else {
			for (Element e : elementList) {
				//e.getName()-获取节点标签名称
				//e.getText() getStringValue 获取节点内容
				map.put(e.getName(), e.getText());
			}
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		System.out.println("---------xml转换为map-----:"+map);
		return map;
	}
//	public static String textMessageToXml(TextMessage textMessage){
//		XStream xstream = new XStream();
//		xstream.alias("xml", textMessage.getClass());
//		return xstream.toXML(textMessage);
//	}

}
