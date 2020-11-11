package com.wechat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.crm.impl.FlagsDaoImpl;
import com.wechat.model.pojo.Flags;


public class FlagServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		// 数据库查询内容起始位置
		int start = 0;
		int count = 5;// 修改每个分页显示的数据条数

		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (NumberFormatException e) {
			// 当浏览器没有传参数start时
		}
		// 当点击next的值=0+每一页的数量
		int next = start + count;
		// 点击pre时他的值为当前的start数-每一页的数量
		int pre = start - count;

		int total = new FlagsDaoImpl().getTotal();
		//
		int last;
		if (0 == total % count)
			last = total - count;
		else
			last = total - total % count;
		// 健壮性判断
		pre = pre < 0 ? 0 : pre;
		next = next > last ? last : next;
		// 分页显示总页数和当前页数
		int pages = 0;
		if (total % count == 0) {
			pages = total / count;
		} else {
			pages = total / count + 1;
		}
		// 当前页数
		int page = start / count + 1;

		// 设置参数 key-value
		request.setAttribute("next", next);// 下一页
		request.setAttribute("pre", pre);// 上一页
		request.setAttribute("last", last);// 末页
		request.setAttribute("pages", pages);// 总页数
		request.setAttribute("page", page);// 当前页

		// 模糊查询
		String queryText = request.getParameter("queryText");
		//获得查询框内的内容
		System.out.println("queryText--》" + queryText);
		//判断查询框是否为空
		if (null != queryText) {
			//将获取到的内容放进request请求里面
			request.setAttribute("queryText", queryText);
			//将已存在的删除
//			request.removeAttribute("flags");
			List<Flags> flags=new FlagsDaoImpl().query(queryText);
			request.setAttribute("flags", flags);
		} else {
			// 页面具体数据
//			request.removeAttribute("flags");
			List<Flags> flags = new FlagsDaoImpl().list(start, count);
//	        System.out.println(users);
			request.setAttribute("flags", flags);
		}
		// 转发 服务器资源调用
		request.getRequestDispatcher("view/jsp/user/flag.jsp").forward(request, response);

	}
}
