package com.acadgild.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.acadgild.store.model.Product;
import com.acadgild.store.util.DBUtil;

public class ShoppingDAO {
	public void registerUser(String name,String address,String password){
		Connection connection = DBUtil.getConnection();
		try{
			
			String sql="INSERT INTO USER(user_name,password,user_address) values ( ?, ?, ?)";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			pst.setString(3, address);
			int res = pst.executeUpdate();
			
			if(res > 0){
				System.out.println("User Added Successfully");
			}
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public Long getUser(String username,String password){
		Connection connection = DBUtil.getConnection();
		Long user_id = null;
		try{

			String sql="SELECT USER_ID FROM USER WHERE USER_NAME = ? and PASSWORD= ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);		
			pst.setString(2, password);		
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				user_id=rs.getLong(1);
					break;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return user_id;
	}
	public List<Product> getProduct(){
		Connection connection = DBUtil.getConnection();
		List<Product> productList=new ArrayList<Product>();
		try{

			String sql="SELECT * FROM PRODUCT";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
	
			while(rs.next()){
				Product product=new Product();
				product.setProductId(rs.getLong(1));
				product.setProductName(rs.getString(2));
				product.setProductPrice(rs.getString(3));
				product.setQty(rs.getInt(4));
				productList.add(product);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return productList;
	}
	public void addCart(int user_id,int product_id,Long qty,Long cart_id){
		Connection connection = DBUtil.getConnection();
		try{
			PreparedStatement pst=null;
			PreparedStatement pstOrder=null;
			if(cart_id!=null && cart_id>0){
				String sql="update CART set qty=? where cart_id=?";
				pst = connection.prepareStatement(sql);
				pst.setLong(1, qty);
				pst.setLong(2, cart_id);
				
				String sqlOrder="update ORDERS set qty=? where cart_id=?";
				pstOrder = connection.prepareStatement(sqlOrder);
				pstOrder.setLong(1, qty);
				pstOrder.setLong(2, cart_id);
				pstOrder.executeUpdate();
			}
			else if(qty>0){
				String sql="update CART set qty=? where user_id=? and product_id=?";
				pst = connection.prepareStatement(sql);
				pst.setLong(1, qty+1);
				pst.setLong(2, user_id);
				pst.setLong(3, product_id);
				
				String sqlOrder="update ORDERS set qty=? where user_id=? and product_id=?";
				pstOrder = connection.prepareStatement(sqlOrder);
				pstOrder.setLong(1, qty+1);
				pstOrder.setLong(2, user_id);
				pstOrder.setLong(3, product_id);
				pstOrder.executeUpdate();

			}else{
				String sql="INSERT INTO CART(user_id,product_id,qty) values ( ?, ?, ?)";
				pst = connection.prepareStatement(sql);
				pst.setLong(1, user_id);
				pst.setLong(2, product_id);
				pst.setLong(3, qty+1);
				
				String sqlOrder="INSERT INTO ORDERS(user_id,product_id,qty) values ( ?, ?, ?)";
				pstOrder = connection.prepareStatement(sqlOrder);
				pstOrder.setLong(1, user_id);
				pstOrder.setLong(2, product_id);
				pstOrder.setLong(3, qty+1);
				pstOrder.executeUpdate();
			}
			int res = pst.executeUpdate();
			
			if(res > 0){
				System.out.println("Cart Added Successfully");
			}
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public Long getQuantity(int user_id,int product_id){
		Connection connection = DBUtil.getConnection();
		Long qty=0l;
		try{
			if(product_id>0){
				String sql="SELECT QTY FROM CART WHERE USER_ID = ? and PRODUCT_ID=?";
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setLong(1, user_id);	
				pst.setLong(2, product_id);	
				ResultSet rs = pst.executeQuery();
				while(rs.next()){
					qty=qty+rs.getLong(1);
				}
			}
			else{
				String sql="SELECT QTY FROM CART WHERE USER_ID = ?";
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setLong(1, user_id);	
				ResultSet rs = pst.executeQuery();
				while(rs.next()){
					qty=qty+rs.getLong(1);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return qty;
	}
	
	public List<Product> getCartDetails(int user_id){
		Connection connection = DBUtil.getConnection();
		List<Product> cartList=new ArrayList<Product>();
		try{

			String sql="SELECT p.product_name,c.qty,c.cart_id,p.product_id,p.product_price FROM `CART` c,PRODUCT p where c.product_id=p.product_id and c.user_id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setLong(1, user_id);		
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				Product p=new Product();
				p.setProductName(rs.getString(1));
				p.setQty(rs.getInt(2));
				p.setCartId(rs.getLong(3));
				p.setProductId(rs.getLong(4));
				p.setProductPrice(rs.getString(5));
				cartList.add(p);

			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cartList;
	}
	public void deleteCart(Long cart_id){
		Connection connection = DBUtil.getConnection();
		try{
			PreparedStatement pst=null;

				String sql="DELETE FROM CART WHERE cart_id = ? ";	
				pst = connection.prepareStatement(sql);
				pst.setLong(1, cart_id);
				int res = pst.executeUpdate();
			if(res > 0){
				System.out.println("Cart Deleted Successfully");
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void saveBillingDetails(int user_id){
        Connection connection = DBUtil.getConnection();
        List<Product> cartList=new ArrayList<Product>();
        try{

            String sql="SELECT c.qty,c.cart_id,p.product_price FROM `CART`
c,PRODUCT p where c.product_id=p.product_id and c.user_id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, user_id);       
            ResultSet rs = pst.executeQuery();
            int totalVal=0;
            while(rs.next()){
                totalVal=totalVal+rs.getInt(1)*Integer.parseInt(rs.getString(3));
                Product p=new Product();
                p.setCartId(rs.getLong(2));
                cartList.add(p);
            }
            for(Product product:cartList){
                String sql1="SELECT * from BILLING where cart_id=? and user_id = ?";
                PreparedStatement pst1 = connection.prepareStatement(sql1);
                pst1.setLong(1, product.getCartId());
                pst1.setLong(2, user_id);
                ResultSet rs1 = pst1.executeQuery();
                boolean updateFlag=false;
                while(rs1.next()){
                    updateFlag=true;
                }
                if(updateFlag){
                    String sql2="update BILLING set total_bill=? where user_id=? and
cart_id=?";
                    PreparedStatement pstBilling1 = connection.prepareStatement(sql2);
                    pstBilling1.setLong(1, totalVal);
                    pstBilling1.setLong(2, user_id);
                    pstBilling1.setLong(3, product.getCartId());
                    pstBilling1.executeUpdate();
                }else{
                    String sqlBilling="INSERT INTO
BILLING(user_id,cart_id,total_bill) values ( ?, ?, ?)";
                    PreparedStatement pstBilling = connection.prepareStatement(sqlBilling);
                    pstBilling.setLong(1, user_id);
                    pstBilling.setLong(2, product.getCartId());
                    pstBilling.setLong(3, totalVal);
                    pstBilling.executeUpdate();
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
	
}
