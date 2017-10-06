package com.acadgild.store.service;

import java.util.List;

import com.acadgild.store.dao.ShoppingDAO;
import com.acadgild.store.model.Product;

public class ShoppingService {
	ShoppingDAO shoppingDAO;
	public ShoppingService(){
		
		super();
		
		shoppingDAO = new ShoppingDAO();
	}
	public void registerUser(String name,String address,String password){
		shoppingDAO.registerUser(name, address, password);
	}
	public Long getUser(String username,String password){
		return shoppingDAO.getUser(username, password);
	}
	public List<Product> getProduct(){
		return shoppingDAO.getProduct();
	}
	public void addCart(int user_id,int product_id,Long qty,Long cart_id){
		shoppingDAO.addCart(user_id,product_id,qty,cart_id);
	}
	public Long getQuantity(int user_id,int product_id){
		return shoppingDAO.getQuantity(user_id,product_id);
	}
	public List<Product> getCartDetails(int user_id){
		return shoppingDAO.getCartDetails(user_id);
	}
	public void deleteCart(Long cart_id){
		shoppingDAO.deleteCart(cart_id);
	}
	public void saveBillingDetails(int user_id){
        shoppingDAO.saveBillingDetails(user_id);
    }

}
