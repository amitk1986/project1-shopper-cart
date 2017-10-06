package com.acadgild.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acadgild.store.model.Product;
import com.acadgild.store.service.ShoppingService;

public class CartController extends HttpServlet{
	ShoppingService shoppingService;
	public CartController(){
		
		super();
		
		shoppingService = new ShoppingService();
	}
	//called when viewcart is called
	protected void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException , ServletException
	{
		String viewCart=request.getParameter("viewCart");
		response.setContentType("text/html");  
		RequestDispatcher view=null;
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false);
		int userId = 0;
		if(session!=null)
		 userId=Integer.parseInt(session.getAttribute("userId").toString());
		if("yes".equals(viewCart)){
			String operation=request.getParameter("operation");
			String cartId=request.getParameter("cartId");
			if("delete".equals(operation)){
				shoppingService.deleteCart(Long.parseLong(cartId));
			}
			List<Product>  cartList=shoppingService.getCartDetails(userId);
			request.setAttribute("cartList", cartList);
			int count=0;
			for(Product product:cartList){
				count=count+product.getQty()*Integer.parseInt(product.getProductPrice());
			}
			request.setAttribute("totalValue", count);
			if("yes".equals(request.getParameter("order"))){
				view = request.getRequestDispatcher("views/order.jsp");
			}else{
				view = request.getRequestDispatcher("views/cart.jsp");
			}
		}else{
			Long qty=shoppingService.getQuantity(userId,Integer.parseInt(request.getParameter("productId")));
			if(qty==null){
				qty=0l;
			}
			shoppingService.addCart(Integer.parseInt(session.getAttribute("userId").toString()), Integer.parseInt(request.getParameter("productId")), qty,null);
			Long qtyFinal=shoppingService.getQuantity(userId,0);
			session.setAttribute("qty", qtyFinal);
			request.setAttribute("productList", shoppingService.getProduct());
			view = request.getRequestDispatcher("views/index.jsp");
		}
		view.forward(request, response);
		out.close();
		
	}
	// called when cart is updated
	protected void doPost(HttpServletRequest request , HttpServletResponse response)throws IOException , ServletException
	{
		HttpSession session = request.getSession(false);
		int userId = 0;
		if(session!=null)
		 userId=Integer.parseInt(session.getAttribute("userId").toString());
		int cartsize=shoppingService.getCartDetails(userId).size();
		for(int i=0;i<cartsize;i++){
			int count=i+1;
			shoppingService.addCart(0, 0, Long.parseLong((request.getParameter("cartQty"+count))), Long.parseLong(request.getParameter("cartId"+count)));
		}
/*		response.setContentType("text/html");  
		RequestDispatcher view=null;
		PrintWriter out = response.getWriter(); 
		view = request.getRequestDispatcher("/viewCart");
		view.forward(request, response);
		out.close();*/
		//doGet(request,response);
		RequestDispatcher view=null;
		request.setAttribute("cartList", shoppingService.getCartDetails(userId));
		view = request.getRequestDispatcher("views/cart.jsp");
		view.forward(request, response);
	}
}
