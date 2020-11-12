package com.wechat.controller.crm.image;


import java.awt.image.BufferedImage;	
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.controller.crm.base.BaseBackServlet;
import com.wechat.model.bean.Image;
import com.wechat.model.dao.crm.ImageDao;
import com.wechat.model.dao.crm.impl.ImageDaoImpl;
import com.wechat.utils.ImageUtil;

@WebServlet("/AddServlet")
public class AddServlet extends BaseBackServlet {
	
	//上传需要post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> params = new HashMap<>();  //集合存储的主题名和图片的名字
		InputStream is = super.parseUpload(request, params);  //调用父类的下载方法
		
		String name= params.get("name");
		Image image = new Image();
		image.setTheme(name);
		System.out.println("---这是名字"+ name);
		ImageDao imagedao = new ImageDaoImpl();
		imagedao.add(image);  //将jsp获得主题信息插入数据库
		
		//得到图片的路径
		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("img/Templateimg"));
		File file = new File(imageFolder,image.getId()+".jpg");  //这里设置图片的名字
		
		try {
			if(null!=is && 0!=is.available()){
			    try(FileOutputStream fos = new FileOutputStream(file)){  //写出流
			        byte b[] = new byte[1024 * 1024];
			        int length = 0;
			        while (-1 != (length = is.read(b))) { //读文件
			            fos.write(b, 0, length);  //从缓冲区写出
			        }
			        fos.flush();
			        //使用工具   更改图片格式
			        BufferedImage img = ImageUtil.change2jpg(file);  
			        ImageIO.write(img, "jpg", file);
			        fos.close();
			    	is.close();
			    }
			    catch(Exception e){
			    	e.printStackTrace();
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/ImageListServlet").forward(request,response);
	}
}