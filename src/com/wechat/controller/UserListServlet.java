package com.wechat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.factory.DaoFactory;
import com.wechat.model.pojo.User;


public class UserListServlet extends HttpServlet{
	
	private UserDao<User> userDao =  (UserDao<User>) DaoFactory.getInstance().getDaoByName("userDao");
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	response.setContentType("text/html; charset=UTF-8");
    	 
    	 
        int start = 0;//数据库表中查询数据的起始位置
        int count = 4;//查询条数
 
        try {
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传参数时start
        }
        //下一页
        int next = start + count;
        //上一页
        int pre = start - count;
        //数据总条数
        int total = userDao.getTotal();
        //末页--最后一页的起始位置
        int last;
        //13%5
        if (0 == total % count)
            last = total - count;//13-5=8
        else
            last = total - total % count;//13-13%5=10
        
        //健壮性判断
        pre = pre < 0 ? 0 : pre;
        //健壮性判断
        next = next > last ? last : next;
       
        //显示具体页数
        int pages=0;
        if(total%count==0) {
        	pages=total/count;
        }else {
        	pages=total/count+1;
        }
        
        //当前页数
        int page=start/count+1;
        
        
        //页面返回参数
        request.setAttribute("next", next);//setAttribute 设置参数 key-value -map
        request.setAttribute("pre", pre);
        request.setAttribute("last", last);
        request.setAttribute("page", page);
        request.setAttribute("pages", pages);
        
        //页面的具体数据
      //分页查询
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start",start);
		map.put("size",count);
		
		//获取查询框中是否有值
		String queryText = request.getParameter("queryText");
		if(null!=queryText) {
			//将获取到的queryText放到request作用域中
			request.setAttribute("queryText", queryText);
			//将已存在request作用域中的users移除
			request.removeAttribute("users");
			
			List<User> users=userDao.vagueQueryData(queryText);
			request.setAttribute("users", users);
		}else {
			List<User> users=userDao.pageQueryData(map);
//	        List<User> usermannagers = new UserDaoImpl().list(start, count);
	        System.out.println(users);
	        //还可以存对象
	        request.setAttribute("users",users);
		}
		
//        System.out.println("6666");
        //转发  本质是：服务器内部的资源调用  LoginDemo/   servlet找servlet
        request.getRequestDispatcher("/view/jsp/user/user.jsp").forward(request, response);
//      response.sendRedirect(request.getContextPath() + "/jsp/user/user.jsp");
    
    }
	
}
