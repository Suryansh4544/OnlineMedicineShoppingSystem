package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Cart;
import com.OnlineMedicineShoppingSystem.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartrepo;

    @Override
    public boolean saveCart(Cart c) {
        if(cartrepo.findById(c.getUser_id(), c.getProduct_id())!=null)
        return false;
        cartrepo.save(c);
        return true;
    }

    @Override
    public List<Cart> fetchCart() {
        
        List<Cart> cart = cartrepo.findAll();
        return cart;
    }

    @Override
    public boolean updateCart(Cart c, int cid,int pid) {

        if(cartrepo.findById(cid, pid)!=null){
            cartrepo.save(c);
            return true;
        }
        return false;
    }

    @Override
    public void deleteCart(int cid, int pid) {
        if(cartrepo.findById(cid, pid)!=null){
             cartrepo.deleteById(cid, pid);
        }
    }

    @Override
    public List<Cart> fetchCartByUid(int cid) {
        return cartrepo.findByUid(cid);
    }
    
}
