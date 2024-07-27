package com.sounshop.ShopManagement.controller.dto;



public class StorageDTO {
    private Integer productId;
    private String productName;
    private String category;
    private Integer quantity;

    // Constructors
    public StorageDTO(Integer productId, String productName, String category, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
}
