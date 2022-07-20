package com.OnlineMedicineShoppingSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
    @Id
    private int user_id;

    private int product_id;

    private int quantity;

    private int price;
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order(int user_id, int product_id, int quantity,int price) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }

    public Order() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
