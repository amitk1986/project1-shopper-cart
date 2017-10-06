package com.acadgild.store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acadgild.store.service.ShoppingService;

public class LoginController extends HttpServlet{
	ShoppingService shoppingService;
	public LoginController(){
		
		super();
		
		shoppingService = new ShoppingService();
	}
	//called login button is clicked
	protected void doPost(HttpServletRequest request , HttpServletResponse response)throws IOException , ServletException
	{
		
		String inputusername=request.getParameter("inputusername");
		String inputpassword=request.getParameter("inputpassword");
		Long user_id=shoppingService.getUser(inputusername, inputpassword);
		if(user_id!=null && user_id>0){

			request.setAttribute("productList", shoppingService.getProduct());
			Long qty=shoppingService.getQuantity(Integer.parseInt(user_id.toString()),0);
			HttpSession session = request.getSession(false);
			if(session!=null){
			session.setAttribute("userId", user_id);
			session.setAttribute("userName", inputusername);
			session.setAttribute("qty", qty);
			}
			String rememberMe=request.getParameter("rememberMe");
			if("yes".equals(rememberMe))
			{
			    Cookie c = new Cookie("inputusername", inputusername);
			    c.setMaxAge(24*60*60);
			    response.addCookie(c);  
			    Cookie c1 = new Cookie("inputpassword", inputpassword);
			    c1.setMaxAge(24*60*60);
			    response.addCookie(c1); // response is an instance of type HttpServletReponse
			}
			RequestDispatcher view = request.getRequestDispatcher("views/index.jsp");
			view.forward(request, response);
		}else{
			request.setAttribute("productList", shoppingService.getProduct());
			request.setAttribute("errorMsg", "Sorry username or password is incorrect");
			RequestDispatcher rd=request.getRequestDispatcher("views/index.jsp");  
			rd.include(request,response);  
		}
		
	}
	//called when clicked on logout button
	protected void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException , ServletException
	{
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		request.setAttribute("userName", "");
		request.setAttribute("userId", "");
		HttpSession session = request.getSession(false);
		if(session!=null){
		session.setAttribute("qty", "");
		session.setAttribute("userId", "");
		session.setAttribute("userName", "");
		session.invalidate();
		}
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++)
			{ 
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			} 
		}
		request.setAttribute("productList", shoppingService.getProduct());
		RequestDispatcher view = request.getRequestDispatcher("views/index.jsp");
		view.forward(request, response);

		out.close();
		
	}
}
