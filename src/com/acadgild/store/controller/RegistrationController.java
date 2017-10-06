package com.acadgild.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acadgild.store.model.Product;
import com.acadgild.store.service.ShoppingService;

public class RegistrationController extends HttpServlet{
	ShoppingService shoppingService;
	public RegistrationController(){
		
		super();
		
		shoppingService = new ShoppingService();
	}
	//called when submit is clicked
	protected void doPost(HttpServletRequest request , HttpServletResponse response)throws IOException , ServletException
	{
		
		String fName=request.getParameter("first_name");
		String lName=request.getParameter("last_name");
		String address=request.getParameter("address");
		String password=request.getParameter("password");
		shoppingService.registerUser(fName, address, password);
		request.setAttribute("productList", shoppingService.getProduct());
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
		
	}
	//loads when page is loaded first time
	protected void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException , ServletException
	{
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		request.setAttribute("userName", "");
		request.setAttribute("userId", "");
		request.setAttribute("qty", "");
		HttpSession session = request.getSession(false);
		if(session!=null){
		session.setAttribute("qty", "");
		session.setAttribute("userId", "");
		session.setAttribute("userName", "");
		}
		Cookie[] cookies = request.getCookies();
		String userId=null;
		String password=null;
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++)
			{ 
				Cookie c = cookies[i];
				if (c.getName().equals("inputusername"))
				{
					userId= c.getValue();
				}
				if (c.getName().equals("inputpassword"))
				{
					password= c.getValue();
				}
			} 
		}
			Long user_id=shoppingService.getUser(userId, password);
			if(user_id!=null && user_id>0){

				Long qty=shoppingService.getQuantity(Integer.parseInt(user_id.toString()),0);
				if(session!=null){
				session.setAttribute("userId", user_id);
				session.setAttribute("userName", userId);
				session.setAttribute("qty", qty);
				}
			}
		request.setAttribute("productList", shoppingService.getProduct());
		RequestDispatcher view = request.getRequestDispatcher("/views/index.jsp");
		view.forward(request, response);
		out.close();
		
	}
}
