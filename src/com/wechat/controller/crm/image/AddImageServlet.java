package com.wechat.controller.crm.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 这里在本工程中不起作用，但他也有上传图片到web下的image目录的作用
 * @author Gwin_liu
 *
 */

@WebServlet("/AddImageServlet")
public class AddImageServlet extends HttpServlet {
	
	/**
	 * 下载
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * 上传功能
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义文件名
		String fileName=null;
		//创建一个本地文件的上传的工具工厂
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//用于文件在servlet上的上传
		ServletFileUpload upload=new ServletFileUpload(factory);
		//设置上传文件大小的限制为1m
//		factory.setSizeThreshold(1024*1024);
		List items=null;
		try {
			//上传器处理上传请求
			items=upload.parseRequest(request);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		Iterator it=items.iterator();
		//System.out.println("--这里是图片的内容" + it.next());  //这里是输出图片的内容
		while(it.hasNext()) {
			//Fileitem
			FileItem item=(FileItem)it.next();
			//item中是否是request中的非文件请求
			//request中，getparameter的参数到底是不是文件
			if(!item.isFormField()) {
				
				//根据时间戳来创建文件
				fileName=System.currentTimeMillis()+".jpg";
				//选择上传的文件夹
				String realPath=request.getSession().getServletContext().getRealPath("img/Templateimg");
				//在指定的目录下确定文件生成目标
				//System.out.println("这里是图片存放的路径" + realPath);
				File f=new File(realPath,fileName);
				//System.out.println("图片输出的地方的路径对象" + f);
				//创建文件夹
				f.getParentFile().mkdirs();
				
				//通过inputStream获取浏览器上的文件输入流
				InputStream is=item.getInputStream();
				//复制文件，指定目标进行输出
				FileOutputStream fos=new FileOutputStream(f);
				
//				System.out.println(f);
//				System.out.println("这里是输出流"+fos);
				//写内容
				byte[] buf=new byte[1024*1024];
				int length=0;
				//读到控制台
				while(-1!=(length=is.read(buf))) {
					System.out.println("查看他是否在读写" + length);
					fos.write(buf,0,length);
				}
				fos.close();
				is.close();
			}else { //如果是request中的普通字段 
				String value=item.getString(); 
				value=new String(value.getBytes("ISO-8859-1"),"UTF-8");
				System.out.println("---这是页面主题的值"+ value); 
			}
		}
	}
}
