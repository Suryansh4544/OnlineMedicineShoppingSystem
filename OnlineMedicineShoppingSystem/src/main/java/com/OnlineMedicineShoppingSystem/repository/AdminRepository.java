package com.OnlineMedicineShoppingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OnlineMedicineShoppingSystem.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    
    Admin findById(int aid);
}
