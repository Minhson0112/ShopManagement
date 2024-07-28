package com.sounshop.ShopManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "entryProduct")
public class EntryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "productId", nullable = false)
    private Integer productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "entryDate", nullable = false)
    private LocalDate entryDate;

    // Constructors
    public EntryProduct() {}

    public EntryProduct(Integer productId, Integer quantity, Integer price, LocalDate entryDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.entryDate = entryDate;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}
