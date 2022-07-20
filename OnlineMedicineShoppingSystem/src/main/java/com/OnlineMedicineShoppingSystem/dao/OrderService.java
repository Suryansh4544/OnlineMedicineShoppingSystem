package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Order;

@Service
public interface OrderService {
    public boolean saveOrder(Order o);
    public List<Order> fetchOrder();
    public boolean updateOrder(Order o, int cid , int pid);
    public Order deleteOrder(int cid,int pid);
    public List<Order> fetchOrderByUid(int cid);
}
