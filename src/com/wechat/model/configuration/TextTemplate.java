package com.wechat.model.configuration;

import java.io.File;
import java.util.Map;

import com.wechat.model.bean.ImageMediaId;
import com.wechat.model.dao.crm.RankingDao;
import com.wechat.model.dao.crm.impl.RankingDaoImpl;
import com.wechat.model.pojo.Ranking;
import com.wechat.utils.ImgUtils;
import com.wechat.utils.StringUtil;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TextTemplate {
	
	private static String usernickname;

	private static String userOpenid;
	//1 关注回复  2 自动回复  3 其他回复
	public static String getContent(int type) {
		switch(type) {
		case 1:return "关注公众号/:rose/:rose/:rose";
		case 2:return "感谢你的留言";
		case 3:return "老客户，欢迎您回家/:rose/:rose/:rose";
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
		//qrscene_o8ft36DgD0lV9CoQQUjNNqh0rfnU
		String toUserId=toUser.substring(8);
		userOpenid=toUserId;
		System.out.println("扫了谁的的二维码的openid："+toUserId);
		//获取二维码中带的用户信息
		String  users=TokenConfig.getUserInfo(toUserId);
		JSONObject json1=JSONUtil.parseObj(users);
		//获得二维码带信息的用户昵称
		String nick=json1.getStr("nickname");
		//
		System.out.println("二维码带参数："+nick);
		//存入数据库
		Ranking rk=new Ranking(toUserId,nick);
		RankingDao rank=new RankingDaoImpl();
		rank.addRanking(rk);
		
		System.out.println(userOpenId);
		String  info=TokenConfig.getUserInfo(userOpenId);//json文件格式
		//System.out.println(info);
		//解析微信服务器发送过来的json请求
		JSONObject jsonObject=JSONUtil.parseObj(info);
		String nickname=jsonObject.getStr("nickname");
//		String openid=jsonObject.getStr("openid");
//		String city=jsonObject.getStr("city");
//		String headimgurl=jsonObject.getStr("headimgurl");
//		String subscribe_scene=jsonObject.getStr("subscribe_scene");
//		String qr_scene=jsonObject.getStr("qr_scene");
//		String qr_scene_str=jsonObject.getStr("qr_scene_str");
		usernickname=nickname;
		//获取客服接口，把助力消息发送给用户
		String url=TokenConfig.getCustomerUrl();
		String result=TextTemplate.getCustomerTemplate(nickname,xmlMap);
		HttpUtil.post(url, result);
		//查看助力榜单
		String rankUrl=TextTemplate.getRanking();
		HttpUtil.post(url, rankUrl);
		//发送海报给关注公众号的用户
		String media=ImageMediaConfig.getMedia(xmlMap);
		String result2=TextTemplate.getCustomerImageTemplate(nickname,xmlMap);
		HttpUtil.post(url, result2);
		String template=getTemplate(media,xmlMap);
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
//		String openid=jsonObject.getStr("openid");
//		System.out.println("openid-->:"+openid);
		String nickname=jsonObject.getStr("nickname");
//		String city=jsonObject.getStr("city");
//		String headimgurl=jsonObject.getStr("headimgurl");
//		String subscribe_scene=jsonObject.getStr("subscribe_scene");
//		String qr_scene=jsonObject.getStr("qr_scene");
//		String qr_scene_str=jsonObject.getStr("qr_scene_str");
//		System.out.println(headimgurl);
		String url2=TokenConfig.getCustomerUrl();
		String media=ImageMediaConfig.getMedia(xmlMap);
		String result2=TextTemplate.getCustomerImageTemplate(nickname,xmlMap);
		HttpUtil.post(url2, result2);
		String template=getTemplate(media,xmlMap);
		return template;
	}
	
	//关注回复海报
	public static String getTemplate(String media,Map<String, String> xmlMap) {
		String template="<xml>\r\n" + 
				"		  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"		  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"		  <CreateTime>"+StringUtil.getWxCreateTime()+"</CreateTime>\r\n" + 
				"		  <MsgType><![CDATA[image]]></MsgType>\r\n" + 
				"		  <Image>\r\n" + 
				"		    <MediaId><![CDATA["+media+"]]></MediaId>\r\n" + 
				"		  </Image>\r\n" + 
				"		</xml>";
		return template;
		
	}

	
	//直接取出参数 
	public static String getEventParamsTemplate(Map<String, String> xmlMap) {
		
		
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtil.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+getContent(3)+"]]></Content>\r\n" + 
				"</xml>";
		return template;
	}
	
	//客服发送为谁助力
	public static String getCustomerTemplate(String name,Map<String, String> xmlMap) {
		String result=""
				+ "{\r\n" + 
				"    \"touser\":\""+userOpenid+"\",\r\n" + 
				"    \"msgtype\":\"text\",\r\n" + 
				"    \"text\":\r\n" + 
				"    {\r\n" + 
				"         \"content\":\""+usernickname+"已为您助力/:rose/:rose/:rose"+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}
	
	//	//客服发送排行榜
	public static String getRanking() {
		String url="http://vipsm.natapp1.cc/wechatdemo/Rank.jsp";
		String result="{\r\n" + 
				"    \"touser\":\""+userOpenid+"\",\r\n" + 
				"    \"msgtype\":\"news\",\r\n" + 
				"    \"news\":{\r\n" + 
				"        \"articles\": [\r\n" + 
				"         {\r\n" + 
				"             \"title\":\"点击链接查看您的助力排行榜\",\r\n" + 
				"             \"description\":\"排行榜\",\r\n" + 
				"             \"url\":\""+url+"\",\r\n" + 
				"             \"picurl\":\"PIC_URL\"\r\n" + 
				"         }\r\n" + 
				"         ]\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}
	
	//客服发送欢迎关注信息
	public static String getCustomerImageTemplate(String nickname,Map<String, String> xmlMap) {
	
		String result="	{\r\n" + 
				"		    \"touser\":\""+xmlMap.get("FromUserName")+"\",\r\n" + 
				"		    \"msgtype\":\"text\",\r\n" + 
				"		    \"text\":\r\n" + 
				"		    {\r\n" + 
				"		         \"content\":\""+"欢迎"+nickname+getContent(1)+"\"\r\n" + 
				"		    }\r\n" + 
				"		}";
		return result;
	}

	//点击菜单回复对应的海报
	public static String getEventClick(Map<String, String> xmlMap) {
		String key=xmlMap.get("EventKey");
		if(key.equals("a001")) {
			String mediaId=ImageMediaConfig.getMedia(xmlMap);
			String result=getTemplate(mediaId,xmlMap);
			return result;
		}else if(key.equals("a002")) {
			String mediaId=ImageMediaConfig.getMedia2(xmlMap);
			String result=getTemplate(mediaId,xmlMap);
			return result;
		}
		else if(key.equals("a003")) {
			String mediaId=ImageMediaConfig.getMedia3(xmlMap);
			String result=getTemplate(mediaId,xmlMap);
			return result;
		}
		return null;
	}

}
