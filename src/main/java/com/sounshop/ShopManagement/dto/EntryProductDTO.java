package com.sounshop.ShopManagement.dto;

import java.time.LocalDate;

public class EntryProductDTO {
    private Integer entryId;
    private String productName;
    private String category;
    private Integer quantity;
    private Integer totalPrice;
    private LocalDate entryDate;

    // Constructors
    public EntryProductDTO(Integer entryId, String productName, String category, Integer quantity, Integer totalPrice, LocalDate entryDate) {
        this.entryId = entryId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.entryDate = entryDate;
    }

    // Getters and Setters
    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}
