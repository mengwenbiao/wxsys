package com.wechat.model.configuration;

import java.util.Map;
import com.wechat.utils.ImgUtils;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class ImageMediaConfig {
	
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
		String city=jsonObject.getStr("city");
		String headimgurl=jsonObject.getStr("headimgurl");
		String subscribe_scene=jsonObject.getStr("subscribe_scene");
		String qr_scene=jsonObject.getStr("qr_scene");
		String qr_scene_str=jsonObject.getStr("qr_scene_str");
		System.out.println(headimgurl);
		// http://thirdwx.qlogo.cn/mmopen/VaxZ4UVDKX51iahhbnpfjJOIiaJQPBWlkpy1zS8FjugfIQpeViawgdibp4NBw4sD72y5AnrW4wlsLVgic5icWXjWNHV46qPVj7q0jT/132
		String url=ImgUtils.getImg1(headimgurl, openid, nickname);
		//获取media_id
		String mediaid=TokenConfig.upload(url,"image");
		return mediaid;
	}

}
