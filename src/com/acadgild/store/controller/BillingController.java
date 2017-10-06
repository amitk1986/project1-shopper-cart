package com.acadgild.store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acadgild.store.service.ShoppingService;

public class BillingController extends HttpServlet{
	ShoppingService shoppingService;
	public BillingController(){
		
		super();
		
		shoppingService = new ShoppingService();
	}
	protected void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException , ServletException
	{
		RequestDispatcher view=null;
		HttpSession session = request.getSession(true);
		int userId = 0;
		if(session!=null)
		 userId=Integer.parseInt(session.getAttribute("userId").toString());
		PrintWriter out = response.getWriter(); 
		shoppingService.saveBillingDetails(userId);
		view = request.getRequestDispatcher("views/billing.jsp");
		view.forward(request, response);
		out.close();
	}

}
