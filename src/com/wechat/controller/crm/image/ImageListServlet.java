package com.wechat.controller.crm.image;

import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.crm.ImageDao;
import com.wechat.model.dao.crm.impl.FlagsDaoImpl;
import com.wechat.model.dao.crm.impl.ImageDaoImpl;
import com.wechat.model.pojo.Flags;

/**
 * Servlet implementation class ImageListServlet
 */
@WebServlet("/ImageListServlet")
public class ImageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从0开始
		int start = 0; 
		int count = 2; 
		int currpage = 0; 
		try {
			//得到页面的参数
			start = Integer.parseInt(request.getParameter("start")); 
		}catch(Exception e) {
			//e.printStackTrace();
		}
		//下一页 5=5+0 开头加上每页的总条数
		int next = start + count;
		
        int pre = start - count;
		//查询总的条数   
        int total = new ImageDaoImpl().getTotal();
 
        //last代表总共几个整页的总条数
        int last = 0; 
        int totalPage = 0;
        //0 5 10
        if (0 == total % count) {
        	//总数是0   last是-5 
        	//总数是5   last是0   
        	//总数是10   last是5
            last = total - count;
            totalPage = total/count;
             
        } else {
        	//总数3条  last = 0
        	//假如有7条  那么 last就等于5   
        	//总数12  last就等于10  
        	//也就是说最后一页前一页的总数
        	last = total - total % count;
        	totalPage = total/count + 1;
        }
        //这里进行判断 pre<0 则pre为0
        pre = pre < 0 ? 0 : pre;
        
        //没有跳到最后一页  就是取的next本身， 跳到最后一页了就给他重置成last  
        next = next > last ? last : next;
        
        //当前页是多少
        if(start == 0) {
        	currpage = start + 1; 
        }else {
        	currpage = start/count + 1;
        }
        
        //向变量中设置值
        //下一页的参数
        request.setAttribute("next", next);
        //上一页的参数
        request.setAttribute("pre", pre);
        
        request.setAttribute("currpage", currpage); 
        
        //最后一页的参数
        request.setAttribute("last", last);
        request.setAttribute("total", total);
        request.setAttribute("totalPage", totalPage);
        System.out.println(totalPage);
        System.out.println(start);
        System.out.println(count);
        
       //模糊查询
       String querytext = request.getParameter("query"); 
       System.out.println("输出需要查询的内容" + querytext);
       //如果有查询内容
       if(null!=querytext) {
    	   ImageDao service = new ImageDaoImpl(); 
    	   //返回查询结果集合
    	   List<com.wechat.model.bean.Image> images = service.query(querytext);
    	   request.setAttribute("images",images);
       }else {
    	   //这里传入每页开始的条数  
    	   List<com.wechat.model.bean.Image> images = new ImageDaoImpl().list(start, count);
    	   request.setAttribute("images",images);
       }
      //这里跳转到jsp页面
      request.getRequestDispatcher("/view/jsp/image/image.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
