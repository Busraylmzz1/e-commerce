package com.enoca.spring.e_commerce.entity;


import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;
    private int stockQuantity;

    // Getter ve Setter metodları
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}