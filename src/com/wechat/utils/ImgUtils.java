package com.wechat.utils;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

public class ImgUtils {
	
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
