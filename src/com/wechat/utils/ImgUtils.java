package com.wechat.utils;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import com.wechat.model.configuration.TokenConfig;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

public class ImgUtils {
	
	
	//海报模板一
	public static String getImg1(String headimgurl,String openid,String nickname) {
		
		//下载头像
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
		String url = FileUtil.file(targets).getAbsolutePath();
		return url;
	}
	
	
	
	//下载图片
	public static void downImage(String url,File fileName) {
		
		HttpUtil.downloadFile(url,fileName);
		
	}
	
	//给图片加文字水印
	public static void getIamgePressText(String sourceImage,String targetImage,String nickName,int x,int y) {
		//使用hutool工具
		ImgUtil.pressText(//
			    FileUtil.file(sourceImage), //
			    FileUtil.file(targetImage), //
			    nickName, Color.black, //文字
			    new Font("黑体", Font.BOLD, 30), //字体
			    x, //x坐标修正值。 默认在中间，偏移量相对于中间偏移---230
			    y, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			    1.0f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
			);
	}
	//图片缩放
	public static void getImageScale(String sourceImage,String targetImage,float f) {
		ImgUtil.scale(
			    FileUtil.file(sourceImage), 
			    FileUtil.file(targetImage), 
			    f//0.4f//缩放比例
			);
		
	}
	//图片水印
	public static void getPressImage(String sourceImage,String targetImage,String pressImage,int x,int y) {
		ImgUtil.pressImage(
			   // FileUtil.file("G:\\a\\user.jpg"), 
			    //FileUtil.file("G:\\a\\d.jpg"), 
			   // ImgUtil.read(FileUtil.file("G:\\a\\a.jpg")), //水印图片
			   // 170, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
			   // 550, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			   // 1.0f
				FileUtil.file(sourceImage), 
			    FileUtil.file(targetImage), 
			    ImgUtil.read(FileUtil.file(pressImage)), //水印图片
			    x, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
			    y, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			    1.0f
			);
		
		
		
	}

}
