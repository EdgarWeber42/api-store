package com.supinfo.proj.retailr.apistore.data.entity;


import javax.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @Column(name = "ean")
    private String ean;

    @Column(name = "stock")
    private int stock;

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
