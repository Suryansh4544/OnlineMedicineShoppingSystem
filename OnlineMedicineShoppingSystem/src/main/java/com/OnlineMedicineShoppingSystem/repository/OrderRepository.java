package com.OnlineMedicineShoppingSystem.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.OnlineMedicineShoppingSystem.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{
    @Query(value = "SELECT * FROM orders WHERE user_id=:uid AND product_id=:pid",nativeQuery = true)
    Order findById(int uid, int pid);

    @Modifying(flushAutomatically = true)
    @Query(value = "DELETE  FROM orders WHERE user_id = :uid AND product_id=:pid",nativeQuery = true)
    Order deleteById(int uid, int pid);

    @Query(value = "SELECT * FROM orders WHERE user_id=:uid",nativeQuery = true)
    List<Order> findByUid(int uid);
}
