package com.OnlineMedicineShoppingSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.OnlineMedicineShoppingSystem.model.Products;

public interface ProductRepository extends JpaRepository<Products,Integer> {


    Products findById(int pid);
}
