package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Order;
import com.OnlineMedicineShoppingSystem.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository Orderrepo;

    @Override
    public boolean saveOrder(Order o) {
        if(Orderrepo.findById(o.getUser_id(), o.getProduct_id())!=null)
        return false;
        Orderrepo.save(o);
        return true;
    }

    @Override
    public List<Order> fetchOrder() {
        List<Order> Order = Orderrepo.findAll();
        return Order;
    }

    @Override
    public boolean updateOrder(Order o, int cid,int pid) {

        if(Orderrepo.findById(cid, pid)!=null){
            Orderrepo.save(o);
            return true;
        }
        return false;
    }

    @Override
    public Order deleteOrder(int cid, int pid) {
        if(Orderrepo.findById(cid, pid)!=null){
            return Orderrepo.deleteById(cid, pid);
        }
        return null;
    }

    @Override
    public List<Order> fetchOrderByUid(int cid) {
        return Orderrepo.findByUid(cid);
    }
}
