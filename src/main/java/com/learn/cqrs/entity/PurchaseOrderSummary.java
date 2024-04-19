package com.learn.cqrs.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PurchaseOrderSummary {

    @Id
    private String state;
    private Double totalSale;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Double totalSale) {
        this.totalSale = totalSale;
    }
}
