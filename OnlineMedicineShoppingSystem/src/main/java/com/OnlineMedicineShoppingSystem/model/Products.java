package com.OnlineMedicineShoppingSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="products")
public class Products {

    @Id
    @NotNull(message = "product_id can not be empty")
    private int product_id;
    
    @NotNull(message = "prize can not be empty")
    private int prize;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public Products(int product_id, int prize) {
        this.product_id = product_id;
        this.prize = prize;
    }

    public Products() {
    }

    
}
