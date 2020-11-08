package com.wechat.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;

import org.junit.Test;

import com.wechat.utils.ImgUtils;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

public class TestImage {
	@Test
	public void qrDown() {
		String url="http://thirdwx.qlogo.cn/mmopen/VaxZ4UVDKX51iahhbnpfjJOIiaJQPBWlkpy1zS8FjugfIQpeViawgdibp4NBw4sD72y5AnrW4wlsLVgic5icWXjWNHV46qPVj7q0jT/132";
//		String url="http://thirdwx.qlogo.cn/mmopen/VaxZ4UVDKX51iahhbnpfjJOIiaJQPBWlkpy1zS8FjugfIQpeViawgdibp4NBw4sD72y5AnrW4wlsLVgic5icWXjWNHV46qPVj7q0jT/132";
//		//下载二维码
//		HttpUtil.downloadFile(url,"../../WebContent/img/qrcodeimg/s.jpg");
		ImgUtils.downImage(url,FileUtil.file("../../img/headerimg/sss.jpg"));
		
	}
	
	//"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHI8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyMHdNUll1TlhkVEUxMDAwMHcwN0EAAgT9mqRfAwQAAAAA"
	@Test
	public void getImage4() {
		ImgUtil.pressImage(
			    FileUtil.file("G:\\a\\b\\qrcode.jpg"), 
			    FileUtil.file("G:\\a\\b\\mm.jpg"), 
			    ImgUtil.read(FileUtil.file("G:\\a\\b\\header.jpg")), //水印图片
			    0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
			    0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			    1.0f
			);
	}
	
	@Test
	public void getImage() {
		
		ImgUtil.pressText(//
			    FileUtil.file("/wechatdemo/WebContent/WEB-INF/classes/img/Templateimg/haibao.jpg"), //
			    FileUtil.file("/wechatdemo/WebContent/WEB-INF/classes/img/Templateimg/haibaouser2.jpg"), //
			    "孟文标", Color.red, //文字
			    new Font("黑体", Font.BOLD, 30), //字体
			    -230, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
			    -520, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			    0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
			);
	}
	
	public void getImages() {
		ImgUtil.pressImage(
			    FileUtil.file("G:\\a\\haibao.jpg"), 
			    FileUtil.file("G:\\a\\user.jpg"), 
			    ImgUtil.read(FileUtil.file("d:/picTest/1432613.jpg")), //水印图片
			    0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
			    0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			    0.1f
			);
	}
	@Test
	public void getScale() {
		ImgUtil.scale(
			    FileUtil.file("G:\\a\\qr.jpg"), 
			    FileUtil.file("G:\\a\\a.jpg"), 
			    0.4f//缩放比例
			);
	}
	
	@Test
	public void getImage3() {
		ImgUtil.pressImage(
			    FileUtil.file("G:\\a\\user.jpg"), 
			    FileUtil.file("G:\\a\\d.jpg"), 
			    ImgUtil.read(FileUtil.file("G:\\a\\a.jpg")), //水印图片
			    170, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
			    550, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			    1.0f
			);
	}
	

}
