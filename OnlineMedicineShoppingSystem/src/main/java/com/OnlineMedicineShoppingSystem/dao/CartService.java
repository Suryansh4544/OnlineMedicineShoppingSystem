package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Cart;

@Service
public interface CartService  {
    public boolean saveCart(Cart c);
    public List<Cart> fetchCart();
    public boolean updateCart(Cart c, int cid , int pid);
    public void deleteCart(int cid,int pid);
    public List<Cart> fetchCartByUid(int cid);
}
