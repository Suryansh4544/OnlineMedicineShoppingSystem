package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Products;

@Service
public interface ProductService {

    public boolean saveProduct(Products p);
    public List<Products> fetchProducts();
    public boolean updateProduct(Products p, int pid);
    public Products deleteProduct(int pid);
    public Products fetchProductById(int pid);
    
}
