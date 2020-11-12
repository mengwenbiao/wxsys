package com.wechat.controller.crm.image;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.crm.ImageDao;
import com.wechat.model.dao.crm.impl.ImageDaoImpl;

/**
 * Servlet implementation class DeleteImageServlet
 */
@WebServlet("/DeleteImageServlet")
public class DeleteImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取id
	    int id = Integer.parseInt(request.getParameter("id")); 
		ImageDao service = new ImageDaoImpl();
		service.delete(id);
		request.getRequestDispatcher("/ImageListServlet").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
