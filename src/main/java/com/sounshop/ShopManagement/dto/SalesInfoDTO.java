package com.sounshop.ShopManagement.dto;

import java.time.LocalDate;

public class SalesInfoDTO {
    private Integer tradingId;
    private String productName;
    private String clientName;
    private String address;
    private Integer tradingQuantity;
    private Integer price;
    private LocalDate tradingDate;

    // Constructors
    public SalesInfoDTO(Integer tradingId, String productName, String clientName, String address, Integer tradingQuantity, Integer price, LocalDate tradingDate) {
        this.tradingId = tradingId;
        this.productName = productName;
        this.clientName = clientName;
        this.address = address;
        this.tradingQuantity = tradingQuantity;
        this.price = price;
        this.tradingDate = tradingDate;
    }

    // Getters and Setters
    public Integer getTradingId() {
        return tradingId;
    }

    public void setTradingId(Integer tradingId) {
        this.tradingId = tradingId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }
}
