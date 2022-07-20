package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Products;
import com.OnlineMedicineShoppingSystem.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository prepo;

    @Override
    public boolean saveProduct(Products p) {
        if(prepo.findById(p.getProduct_id())!=null)
            return false;
        prepo.save(p);
        return true;
    }

    @Override
    public List<Products> fetchProducts() {
        List<Products> Products = prepo.findAll();
        return Products;
    }

    @Override
    public boolean updateProduct(Products p, int pid) {
        Products oldProduct = prepo.findById(pid);
        if(oldProduct!=null){
             prepo.save(p);
             return true;
        }
        return false;
    }

    @Override
    public Products deleteProduct(int pid) {
        Products Product = prepo.findById(pid);
        prepo.deleteById(pid);
        return Product;
    }

    @Override
    public Products fetchProductById(int pid) {
        Products Product = prepo.findById(pid);
        if(Product!=null)
            return Product;
        return null;
    }
    
}
