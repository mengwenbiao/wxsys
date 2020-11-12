package com.wechat.model.configuration;

import java.util.Map;
import com.wechat.utils.ImgUtils;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class ImageMediaConfig {
	
	//获得海报1的media id
	public static String getMedia(Map<String, String> xmlMap) {
		String userOpenId=xmlMap.get("FromUserName");
		//System.out.println(userOpenId);
		String  info=TokenConfig.getUserInfo(userOpenId);//json文件格式
		//System.out.println(info);
		//解析微信服务器发送过来的json请求
		JSONObject jsonObject=JSONUtil.parseObj(info);
		String openid=jsonObject.getStr("openid");
		System.out.println("openid-->:"+openid);
		String nickname=jsonObject.getStr("nickname");
		String headimgurl=jsonObject.getStr("headimgurl");
//		System.out.println(headimgurl);
		// http://thirdwx.qlogo.cn/mmopen/VaxZ4UVDKX51iahhbnpfjJOIiaJQPBWlkpy1zS8FjugfIQpeViawgdibp4NBw4sD72y5AnrW4wlsLVgic5icWXjWNHV46qPVj7q0jT/132
		String url=ImageTemplate.getImg1(headimgurl, openid, nickname);
		//获取media_id
		String mediaid=TokenConfig.upload(url,"image");
		return mediaid;
	}
	
	
	//获得海报2的media id
	public static String getMedia2(Map<String, String> xmlMap) {
		String userOpenId=xmlMap.get("FromUserName");
		//System.out.println(userOpenId);
		String  info=TokenConfig.getUserInfo(userOpenId);//json文件格式
		//System.out.println(info);
		//解析微信服务器发送过来的json请求
		JSONObject jsonObject=JSONUtil.parseObj(info);
		String openid=jsonObject.getStr("openid");
		System.out.println("openid-->:"+openid);
		String nickname=jsonObject.getStr("nickname");
		String headimgurl=jsonObject.getStr("headimgurl");
		System.out.println(headimgurl);
		// http://thirdwx.qlogo.cn/mmopen/VaxZ4UVDKX51iahhbnpfjJOIiaJQPBWlkpy1zS8FjugfIQpeViawgdibp4NBw4sD72y5AnrW4wlsLVgic5icWXjWNHV46qPVj7q0jT/132
		String url=ImageTemplate.getImg2(headimgurl, openid, nickname);
		//获取media_id
		String mediaid=TokenConfig.upload(url,"image");
		return mediaid;
	}
	
	//获得海报3的media id
	public static String getMedia3(Map<String, String> xmlMap) {
		String userOpenId=xmlMap.get("FromUserName");
		//System.out.println(userOpenId);
		String  info=TokenConfig.getUserInfo(userOpenId);//json文件格式
		//System.out.println(info);
		//解析微信服务器发送过来的json请求
		JSONObject jsonObject=JSONUtil.parseObj(info);
		String openid=jsonObject.getStr("openid");
		System.out.println("openid-->:"+openid);
		String nickname=jsonObject.getStr("nickname");
		String headimgurl=jsonObject.getStr("headimgurl");
		System.out.println(headimgurl);
		// http://thirdwx.qlogo.cn/mmopen/VaxZ4UVDKX51iahhbnpfjJOIiaJQPBWlkpy1zS8FjugfIQpeViawgdibp4NBw4sD72y5AnrW4wlsLVgic5icWXjWNHV46qPVj7q0jT/132
		String url=ImageTemplate.getImg3(headimgurl, openid, nickname);
		//获取media_id
		String mediaid=TokenConfig.upload(url,"image");
		return mediaid;
	}

}
