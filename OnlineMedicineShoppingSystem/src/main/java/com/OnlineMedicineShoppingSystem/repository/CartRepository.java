package com.OnlineMedicineShoppingSystem.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.OnlineMedicineShoppingSystem.model.Cart;


public interface CartRepository extends JpaRepository<Cart,Integer>{


    @Query(value = "SELECT * FROM cart WHERE user_id=:uid AND product_id=:pid",nativeQuery = true)
    Cart findById(int uid, int pid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE  FROM cart WHERE user_id = :uid AND product_id=:pid",nativeQuery = true)
    void deleteById(int uid, int pid);

    @Query(value = "SELECT * FROM cart WHERE user_id=:uid",nativeQuery = true)
    List<Cart> findByUid(int uid);

}
