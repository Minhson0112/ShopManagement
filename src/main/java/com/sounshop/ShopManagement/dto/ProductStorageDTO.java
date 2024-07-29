package com.sounshop.ShopManagement.dto;

public class ProductStorageDTO {
    private Integer productId;
    private Integer entryPrice;
    private Integer sellPrice;
    private Integer quantity;

    public ProductStorageDTO(Integer productId, Integer entryPrice, Integer sellPrice, Integer quantity) {
        this.productId = productId;
        this.entryPrice = entryPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(Integer entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
