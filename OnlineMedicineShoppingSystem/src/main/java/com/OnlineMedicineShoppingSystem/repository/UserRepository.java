package com.OnlineMedicineShoppingSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.OnlineMedicineShoppingSystem.model.Users;


public interface UserRepository extends JpaRepository<Users,Integer>{
    
    Users findById(int uid);
}
