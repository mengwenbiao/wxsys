package com.wechat.model.configuration;

import java.util.Map;

import com.wechat.model.bean.ImageMediaId;
import com.wechat.utils.ImgUtils;

import cn.hutool.core.io.FileUtil;
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
		//下载用户头像到本地
		ImgUtils.downImage(headimgurl,FileUtil.file("../../img/headerimg/header.jpg"));
		System.out.println(nickname);
		//生成带用户openid公众号的二维码
		String ticket=TokenConfig.getQrCodeTicket(openid);
		System.out.println(ticket);
		//拿ticket去换取二维码url
		String qrUrl=TokenConfig.getQrCode(ticket);
		System.out.println(qrUrl);
		//下载带用户参数的二维码到本地
		ImgUtils.downImage(qrUrl,FileUtil.file("../../img/qrcodeimg/qrcode.jpg"));
		System.out.println("1");
		//二维码和头像合并
		String soucreImage="../../img/qrcodeimg/qrcode.jpg";
		//生成的目标图片
		String targetImage="../../img/qrcodeimg/temp.jpg";
		//水印图片
		String a="../../img/headerimg/header.jpg";
		ImgUtils.getPressImage(soucreImage,targetImage,a,0,0);
		//用户二维码
		String user="../../img/qrcodeimg/user.jpg";
		//用户二维码缩放
		ImgUtils.getImageScale(targetImage,user,0.4f);
		//水印二维到海报上
		String soucreImage2="../../img/Templateimg/haibao.jpg";
		String targetImage2="../../img/Templateimg/userhaibao.jpg";
		//水印图片
		String b="../../img/qrcodeimg/user.jpg";
		ImgUtils.getPressImage(soucreImage2,targetImage2,b,170,550);
		//水印昵称
		//最终海报
		String targets="../../img/Templateimg/targethaibao.jpg";
		ImgUtils.getIamgePressText(targetImage2,targets,nickname,-228,-560);
		//上传临时素材库
		//String mediaid=TokenConfig.upload("../../img/Templateimg/targethaibao.jpg","image");
		String url="E:\\eclipse\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\wechatdemo\\img\\Templateimg\\targethaibao.jpg";
		String mediaid=TokenConfig.upload(url,"image");
		//imageID=new ImageMediaId(mediaid);
		return mediaid;
	}

}
