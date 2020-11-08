package com.wechat.model.configuration;

import java.io.File;
import java.util.Map;

import com.wechat.bean.User;
import com.wechat.model.bean.ImageMediaId;
import com.wechat.utils.ImgUtils;
import com.wechat.utils.StringUtil;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TextTemplate {
	
	private static User user;
	private static ImageMediaId imageID;
	private static String userOpenid;
	//1 关注回复  2 自动回复  3 其他回复
	public static String getContent(int type) {
		switch(type) {
		case 1:return "关注公众号/左哼哼";
		case 2:return "感谢你的留言";
		case 3:return "为什么要离开我？";
		}
		return null;
	}
	
	//处理文本内容
	public static String getTextTemplate(Map<String,String> xmlMap) {
		
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtil.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+getContent(2)+"]]></Content>\r\n" + 
			
				"</xml>";
		
		return template;
	}

	//处理事件-带参数关注
	public static String getEventWithParamsTemplate(Map<String, String> xmlMap) {
		
		String userOpenId=xmlMap.get("FromUserName");
		//获取扫描二维码的信息
		String toUser=xmlMap.get("EventKey");
		String toUserId=toUser.substring(8);
		userOpenid=toUserId;
		System.out.println("扫了谁的的二维码的openid："+toUserId);
		//获取二维码中带的用户信息
		String  users=TokenConfig.getUserInfo(toUserId);
		JSONObject json1=JSONUtil.parseObj(users);
		System.out.println(userOpenId);
		String  info=TokenConfig.getUserInfo(userOpenId);//json文件格式
		//System.out.println(info);
		//解析微信服务器发送过来的json请求
		JSONObject jsonObject=JSONUtil.parseObj(info);
		String openid=jsonObject.getStr("openid");
		String nickname=jsonObject.getStr("nickname");
		String city=jsonObject.getStr("city");
		String headimgurl=jsonObject.getStr("headimgurl");
		String subscribe_scene=jsonObject.getStr("subscribe_scene");
		String qr_scene=jsonObject.getStr("qr_scene");
		String qr_scene_str=jsonObject.getStr("qr_scene_str");
		user=new User(openid,nickname,city,headimgurl,subscribe_scene);
		System.out.println("你好1");
		//获取客服接口，把助力消息发送给用户
		String url=TokenConfig.getCustomerUrl();
		String result=TextTemplate.getCustomerTemplate(nickname,xmlMap);
		HttpUtil.post(url, result);
		//发送海报给关注公众号的用户
		String media=ImageMediaConfig.getMedia(xmlMap);
		String result2=TextTemplate.getCustomerImageTemplate(media,xmlMap);
		HttpUtil.post(url, result2);
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtil.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+"欢迎您"+user.getNickname()+getContent(1)+"]]></Content>\r\n" + 
				"</xml>";
		return template;
	}
	//不带参数公众号
	public static String getEventWithOutParamsTemplate(Map<String, String> xmlMap) {
		String userOpenId=xmlMap.get("FromUserName");
		//System.out.println(userOpenId);
		String  info=TokenConfig.getUserInfo(userOpenId);//json文件格式
		//System.out.println(info);
		//解析微信服务器发送过来的json请求
		JSONObject jsonObject=JSONUtil.parseObj(info);
		String openid=jsonObject.getStr("openid");
		System.out.println("openid-->:"+openid);
		String nickname=jsonObject.getStr("nickname");
		String city=jsonObject.getStr("city");
		String headimgurl=jsonObject.getStr("headimgurl");
		String subscribe_scene=jsonObject.getStr("subscribe_scene");
		String qr_scene=jsonObject.getStr("qr_scene");
		String qr_scene_str=jsonObject.getStr("qr_scene_str");
		System.out.println(headimgurl);
		System.out.println("你好2");
		String url2=TokenConfig.getCustomerUrl();
		String media=ImageMediaConfig.getMedia(xmlMap);
		String result2=TextTemplate.getCustomerImageTemplate(media,xmlMap);
		HttpUtil.post(url2, result2);
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtil.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+"欢迎"+nickname+getContent(1)+"]]></Content>\r\n" + 
				"</xml>";
		return template;
	}
	//直接取出参数 
	public static String getEventParamsTemplate(Map<String, String> xmlMap) {
		
		
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtil.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+xmlMap.get("Ticket")+"]]></Content>\r\n" + 
				"</xml>";
		return template;
	}
	
	//客服模板选用
	public static String getCustomerTemplate(String name,Map<String, String> xmlMap) {
		String result=""
				+ "{\r\n" + 
				"    \"touser\":\""+userOpenid+"\",\r\n" + 
				"    \"msgtype\":\"text\",\r\n" + 
				"    \"text\":\r\n" + 
				"    {\r\n" + 
				"         \"content\":\""+user.getNickname()+"为您助力"+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}
	
	//客服模板选用
	public static String getCustomerImageTemplate(String media,Map<String, String> xmlMap) {
		////String headimgurl=user.getHeadimgurl();
		//String media_id=TokenConfig.upload(headimgurl,"image");
	//	System.out.println(media_id);
		String result=""
				+ "{\r\n" + 
				"    \"touser\":\""+xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"image\",\r\n" + 
				"    \"image\":\r\n" + 
				"    {\r\n" + 
				"      \"media_id\":\""+media+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}

}
