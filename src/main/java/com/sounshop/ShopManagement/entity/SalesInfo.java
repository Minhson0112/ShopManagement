package com.sounshop.ShopManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "salesInfo")
public class SalesInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tradingId", nullable = false)
    private Integer tradingId;

    @Column(name = "productId", nullable = false)
    private Integer productId;

    @Column(name = "clientName", nullable = false)
    private String clientName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "tradingQuantity", nullable = false)
    private Integer tradingQuantity;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "profit", nullable = false)
    private Integer profit;

    @Column(name = "tradingDate", nullable = false)
    private LocalDate tradingDate;

    // Constructors
    public SalesInfo() {}

    public SalesInfo(Integer productId, String clientName, String address, Integer tradingQuantity, Integer price, Integer profit, LocalDate tradingDate) {
        this.productId = productId;
        this.clientName = clientName;
        this.address = address;
        this.tradingQuantity = tradingQuantity;
        this.price = price;
        this.profit = profit;
        this.tradingDate = tradingDate;
    }

    // Getters and Setters
    public Integer getTradingId() {
        return tradingId;
    }

    public void setTradingId(Integer tradingId) {
        this.tradingId = tradingId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTradingQuantity() {
        return tradingQuantity;
    }

    public void setTradingQuantity(Integer tradingQuantity) {
        this.tradingQuantity = tradingQuantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }
}
