package com.wechat.controller.crm.level;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.bean.level;
import com.wechat.model.dao.crm.impl.LevelDaoImpl;



public class LevelServlet extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int start = 0;// 开始位置
		int count = 5;// 一列显示几条数据

		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (NumberFormatException e) {
			// 当浏览器没有传参数start时
		}
		int page = (start / count) + 1;// 第几页
		// System.out.println("第几页："+page);
		int next = start + count;// 获取后五条
		int pre = start - count;// 获取前5条

		LevelDaoImpl LevelDaoImpl2 = new LevelDaoImpl();
		int total = LevelDaoImpl2.getTotal();// 获取总共多少条数据
//        int sumPage=0;//总页数
//        if(total%count==0) {
//        	sumPage=total/count;//
//        }else{
//        	sumPage=total/count+1;//
//        }

		int last;
		int sum=0;
		if (0 == total % count) {
			last = total - count;
			sum=total/count;
		}
		else {
			last = total - total % count;
			sum=total/count+1;
		}
			

		pre = pre < 0 ? 0 : pre;
		next = next > last ? last : next;
		
		request.setAttribute("next", next);
		request.setAttribute("pre", pre);
		request.setAttribute("last", last);
		request.setAttribute("page", page);
		request.setAttribute("sum", sum);
		// 判断是否进行模糊查询
		String querySelect2 = request.getParameter("querySelect");

		System.out.println("querySelect2:" + querySelect2);
		if (querySelect2 != null && querySelect2 != "") {
			// 不为空进行模糊查询
			request.setAttribute("querySelect2", querySelect2);
			request.removeAttribute("levels");
			List<level> levels = LevelDaoImpl2.likeQueryListLevel(querySelect2, start, count);
			System.out.println(levels);
			request.setAttribute("levels", levels);
		} else {
			request.removeAttribute("levels");
			List<level> levels = LevelDaoImpl2.listLevel(start, count);
			System.out.println(levels);
			request.setAttribute("levels", levels);
		}
		request.getRequestDispatcher("view/jsp/level/level.jsp").forward(request, response);

	}
}
