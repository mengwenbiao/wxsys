package com.wechat.model.configuration;

import com.wechat.utils.ImgUtils;

import cn.hutool.core.io.FileUtil;

public class ImageTemplate {
	
	//海报模板一
	public static String getImg1(String headimgurl,String openid,String nickname) {
		
		//下载头像
		ImgUtils.downImage(headimgurl,FileUtil.file("../../img/headerimg/header.jpg"));
		
		System.out.println(nickname);
		//生成带用户openid公众号的二维码
		String openidStr=openid+"haibao";
		String ticket=TokenConfig.getQrCodeTicket(openidStr);
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
		String url = FileUtil.file(targets).getAbsolutePath();
		return url;
	}
	
	//海报模板二
	public static String getImg2(String headimgurl,String openid,String nickname) {
		
		//下载头像
		ImgUtils.downImage(headimgurl,FileUtil.file("../../img/headerimg/header.jpg"));
		System.out.println(nickname);
		//生成带用户openid公众号的二维码
		String openidStr=openid+"two";
		String ticket=TokenConfig.getQrCodeTicket(openidStr);
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
		ImgUtils.getImageScale(targetImage,user,0.5f);
		//水印二维到海报上
		String soucreImage2="../../img/Templateimg/two.jpg";
		String targetImage2="../../img/Templateimg/userhaibao.jpg";
		//水印图片
		String b="../../img/qrcodeimg/user.jpg";
		ImgUtils.getPressImage(soucreImage2,targetImage2,b,-200,420);
		//水印昵称
		//最终海报
		
		String targets="../../img/Templateimg/targethaibao.jpg";
		
		ImgUtils.getIamgePressText(targetImage2,targets,nickname,-228,-560);
		//上传临时素材库
		String url = FileUtil.file(targets).getAbsolutePath();
		return url;
	}
	//海报模板三
	public static String getImg3(String headimgurl,String openid,String nickname) {
		
		//下载头像
		ImgUtils.downImage(headimgurl,FileUtil.file("../../img/headerimg/header.jpg"));
		System.out.println(nickname);
		//生成带用户openid公众号的二维码
		String openidStr=openid+"three";
		String ticket=TokenConfig.getQrCodeTicket(openidStr);
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
		String soucreImage2="../../img/Templateimg/three.jpg";
		String targetImage2="../../img/Templateimg/userhaibao.jpg";
		//水印图片
		String b="../../img/qrcodeimg/user.jpg";
		ImgUtils.getPressImage(soucreImage2,targetImage2,b,250,550);
		//水印昵称
		//最终海报
		
		String targets="../../img/Templateimg/targethaibao.jpg";
		
		ImgUtils.getIamgePressText(targetImage2,targets,nickname,-228,-560);
		//上传临时素材库
		String url = FileUtil.file(targets).getAbsolutePath();
		return url;
	}

}
