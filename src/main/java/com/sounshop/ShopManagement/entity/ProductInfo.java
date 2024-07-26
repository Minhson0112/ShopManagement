package com.sounshop.ShopManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
@Table(name = "productInfo")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId", nullable = false)
    private Integer productId;

    @Column(name = "productName", nullable = false, unique = true)
    private String productName;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "entryPrice", nullable = false)
    private Integer entryPrice;

    @Column(name = "sellPrice", nullable = false)
    private Integer sellPrice;

    @Column(name = "addDate", nullable = false)
    private LocalDate addDate;

    @Column(name = "isDelete", nullable = false)
    private Boolean isDelete;

    // Constructors
   

    public ProductInfo(String productName, String category, Integer entryPrice, Integer sellPrice, LocalDate addDate, Boolean isDelete) {
        this.productName = productName;
        this.category = category;
        this.entryPrice = entryPrice;
        this.sellPrice = sellPrice;
        this.addDate = addDate;
        this.isDelete = isDelete;
    }
    public ProductInfo() {}

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

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}
